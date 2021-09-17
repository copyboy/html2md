package cn.zhangqingdong.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author qingdong.zhang
 */
@Aspect
@Component
public class WebLogAspect {

    private static final Logger loggger = LogManager.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.quick..controller.ApiController.convert(..))")
    public void logPointCut() {
    }

    @Before("logPointCut()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        loggger.info("请求地址 : " + request.getRequestURL().toString());
        loggger.info("HTTP METHOD : " + request.getMethod());
        loggger.info("IP : " + request.getRemoteAddr());
        loggger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "."
                + joinPoint.getSignature().getName());
        loggger.info("参数 : " + net.sf.json.JSONArray.fromObject(joinPoint.getArgs()));

    }

    // returning的值和doAfterReturning的参数名一致
    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void doAfterReturning(Object ret) throws Throwable {
        loggger.info("返回值 : " + ret);
    }

    @Around("logPointCut()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object ob = pjp.proceed();
        loggger.info("耗时 : " + (System.currentTimeMillis() - startTime));
        return ob;
    }
}