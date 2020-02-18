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
    @Around("execution(* com.aaa.test.controller..*(..))")
    public Object controllerProcess(ProceedingJoinPoint pjd) throws Throwable {
        Object result = pjd.proceed();
        // 是null特殊处理

        MethodSignature signature = (MethodSignature) pjd.getSignature();
        Class returnType = signature.getReturnType();
        if (result == null) {
            try {
                if ("java.lang.Object".equals(returnType.getName())) {
                    // object 使用此方式
                    return BaseResponse.success(result);
                } else if ("java.lang.String".equals(returnType.getName())) {
                    // 字符串 初始化一个新的返回
                    return returnType.newInstance();
                }
                //其他默认 返回
                return result;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if ("java.lang.Object".equals(returnType.getName())) {
            switch (result.getClass().getTypeName()) {
                case "java.lang.String":
                    return BaseResponse.success(result);
            }
        }
        return result;
    }
}