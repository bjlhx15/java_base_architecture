package ${package}.aspect;

import ${package}.response.BaseResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 统一结果处理切面, 注意返回时 null 时候 处理
 */
@Aspect
@Component
public class ResponseAspect {
    @Around("execution(* ${package}.controller..*(..))")
    public Object controllerProcess(ProceedingJoinPoint pjd) throws Throwable {
        Object result = pjd.proceed();
        // 是null特殊处理
        if (result == null) {
            try {
                MethodSignature signature = (MethodSignature) pjd.getSignature();
                Class returnType = signature.getReturnType();
                if("java.lang.Object".equals(returnType.getName())){
                    // object 使用此方式
                    return BaseResponse.success(result);
                }else if("java.lang.String".equals(returnType.getName())){
                    // 字符串 初始化一个新的返回
                    return returnType.newInstance();
                }
                //其他默认 返回
                return result;
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
}