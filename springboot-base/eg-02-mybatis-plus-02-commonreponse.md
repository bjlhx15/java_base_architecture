#### 0、基础
    基础配置参看 eg-02-mybatis-plus-01-base.md 基础版本
    
#### 1、正常输出
    统一输出，后端返回给前端一般情况下使用JSON格式, 定义如下
```json
{
 "code": 200,
 "message": "OK",
 "data": {}
}
```

------------------------------------------------------------
#### 2、增加配置参数类
ResultStatus、Result
参看示例：com.github.bjlhx15.mybatis.springboot.base.controller.EgResponseController01
http://localhost:8080/response/result
```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "name": "galaxy",
        "age": "70"
    }
}
```
http://localhost:8080/response/hello是没有响应码包裹的

------------------------------------------------------------
#### 3、统一返回JSON格式-全局处理(@RestControllerAdvice)
    使用@ResponseBody注解会把返回Object序列化成JSON字符串
    大致就是在序列化前把Object赋值给Result<Object>就可以了, 
    参看：org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice
        org.springframework.web.bind.annotation.ResponseBody
开发步骤1、创建一个注解类继承@ResponseBody
```java
//@ResponseResultBody 可以标记在类和方法上这样我们就可以跟自由的进行使用了
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@Documented
@ResponseBody
public @interface ResponseResultBody {
}
```
步骤二、ResponseBodyAdvice继承类
```java
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;
    /**
     * 判断类或者方法是否使用了 @ResponseResultBody
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }
    /**
     * 当类或者方法使用了 @ResponseResultBody 就会调用这个方法
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // 防止重复包裹的问题出现
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
}
```
使用三、
参看示例：com.github.bjlhx15.mybatis.springboot.base.controller.EgResponseController02
http://localhost:8080/response2/result 有包裹
http://localhost:8080/response/hello  没有包裹
均返回以下格式
```json
{
    "code": 200,
    "message": "OK",
    "data": {
        "name": "galaxy",
        "age": "70"
    }
}
```

------------------------------------------------------------
#### 4、统一返回JSON格式-异常处理(@ExceptionHandler))
改造：ResponseResultBodyAdvice类
    参看：org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleException()方法
增加自定义异常
```java
public class ResultException extends Exception {
    private ResultStatus resultStatus;

    public ResultStatus getResultStatus() {
        return resultStatus;
    }
}
```
修改：ResponseResultBodyAdvice类
```java
//@Slf4j
@RestControllerAdvice
public class ResponseResultBodyAdvice implements ResponseBodyAdvice<Object> {
    private  final Logger log = LoggerFactory.getLogger(ResponseResultBodyAdvice.class);
    private static final Class<? extends Annotation> ANNOTATION_TYPE = ResponseResultBody.class;
    /** 判断类或者方法是否使用了 @ResponseResultBody */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return AnnotatedElementUtils.hasAnnotation(returnType.getContainingClass(), ANNOTATION_TYPE) || returnType.hasMethodAnnotation(ANNOTATION_TYPE);
    }
    /** 当类或者方法使用了 @ResponseResultBody 就会调用这个方法 */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof Result) {
            return body;
        }
        return Result.success(body);
    }
    /**
     * 提供对标准Spring MVC异常的处理
     *
     * @param ex the target exception
     * @param request the current request
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Result<?>> exceptionHandler(Exception ex, WebRequest request) {
        log.error("ExceptionHandler: {}", ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
        if (ex instanceof ResultException) {
            return this.handleResultException((ResultException) ex, headers, request);
        }
        // TODO: 2019/10/05 galaxy 这里可以自定义其他的异常拦截
        return this.handleException(ex, headers, request);
    }
    /** 对ResultException类返回返回结果的处理 */
    protected ResponseEntity<Result<?>> handleResultException(ResultException ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure(ex.getResultStatus());
        HttpStatus status = ex.getResultStatus().getHttpStatus();
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }
    /** 异常类的统一处理 */
    protected ResponseEntity<Result<?>> handleException(Exception ex, HttpHeaders headers, WebRequest request) {
        Result<?> body = Result.failure();
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        return this.handleExceptionInternal(ex, body, headers, status, request);
    }
    /**
     * org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler#handleExceptionInternal(java.lang.Exception, java.lang.Object, org.springframework.http.HttpHeaders, org.springframework.http.HttpStatus, org.springframework.web.context.request.WebRequest)
     * <p>
     */
    protected ResponseEntity<Result<?>> handleExceptionInternal(
            Exception ex, Result<?> body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (HttpStatus.INTERNAL_SERVER_ERROR.equals(status)) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST);
        }
        return new ResponseEntity<>(body, headers, status);
    }
}
```
测试：
http://localhost:8080/response2/helloError  注意http响应码 也是500
```json
{"code":500,"message":"Internal Server Error","data":null}
```
http://localhost:8080/response2/helloMyError  自定义异常

