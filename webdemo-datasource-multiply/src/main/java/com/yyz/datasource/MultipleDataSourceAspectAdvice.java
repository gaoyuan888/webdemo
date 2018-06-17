package com.yyz.datasource;

import com.yyz.service.ItemService;
import com.yyz.service.ItemService1;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class MultipleDataSourceAspectAdvice {

    @Around("execution(* com.yyz1.service.*.*(..))")
    public Object doAround(ProceedingJoinPoint jp) throws Throwable {
        if (jp.getTarget() instanceof ItemService) {
            MultipleDataSource.setDataSourceKey("dataSource1");
        } else if (jp.getTarget() instanceof ItemService1) {
            MultipleDataSource.setDataSourceKey("dataSource2");
        }
        return jp.proceed();
    }
}