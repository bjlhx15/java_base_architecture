package ${package}.handler;

import ${package}.enums.ErrorCodeEnum;
import ${package}.exception.ResultException;
import ${package}.response.BaseResponse;
import org.slf4j.Logger;
import com.alibaba.fastjson.JSON;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.util.WebUtils;

import java.lang.annotation.Annotation;

/**
 * 统一返回结果异常处理类
 */
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {
    private final Logger log = LoggerFactory.getLogger(ResponseResultBodyAdvice.class);
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseBody.class;

    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // swagger2 过滤
        if (returnType.getMethod().getDeclaringClass().getName().contains("springfox.documentation.swagger")) {
            //因为使用了 RestControllerAdvice 与swagger2 而swagger2也会响应 responsebody 故会被此拦截
            // 如果想使用swagger2生效 需要不被拦截处理 或者自定义一个 ResponseBody 注解来处理
            return false;
        }
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }

    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 避免已经返回基础类型 多次转换
        if (body instanceof BaseResponse) {
            return body;
        }
        //处理String类型作为响应值
        if (!selectedContentType.equals(MediaType.APPLICATION_JSON)) {
            if (body.getClass().getTypeName().equals("java.lang.String")) {
                return JSON.toJSONString(BaseResponse.success(body));
            }
        }
//
        return BaseResponse.success(body);
    }

    /**
     * 提供对标准Spring MVC异常的处理
     *
     * @param ex      the target exception
     * @param request the current request
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<BaseResponse<?>> exceptionHandler(Exception ex, WebRequest request) {
        log.error("ExceptionHandler: {}", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof HttpMediaTypeNotSupportedException) {
            // 针对 返回String 类型 需要客户端设置Content-type 为application/json
            return this.handleResultException(new ResultException(ErrorCodeEnum.HttpMediaTypeNotSupportedException),
                    headers, request);
        } else if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        //TODO: 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }

    /**
     * 对ResultException类返回返回结果的处理
     */
    protected ResponseEntity<BaseResponse<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        BaseResponse<?> body = BaseResponse.fail((ex.getErrorCodeEnum()));
        HttpStatus status = null;
        if (ex.getErrorCodeEnum().getErrorCode().intValue() >= 500000) {
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        } else if (ex.getErrorCodeEnum().getErrorCode().intValue() >= 400000) {
            status = HttpStatus.BAD_REQUEST;
        }
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * 异常类的统一处理
     */
    protected ResponseEntity<BaseResponse<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        BaseResponse<?> body = BaseResponse.fail();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
     * handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders,
     * org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     * <p>
     * A single place to customize the response body of all exception types.
     * <p>The default implementation sets the {@link WebUtils#ERROR_EXCEPTION_ATTRIBUTE}
     * request attribute and creates a {@link ResponseEntity} from the given
     * body, headers, and status.
     */
    protected ResponseEntity<BaseResponse<?>> handleExceptionInternal(
            Exception ex, BaseResponse<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
            body.setInnerErrorMsg(ex != null ? ex.getMessage() : "");
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
