package com.czh.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切面,需要把切面注册成spring的一个组件
 */
@Aspect
@Component
public class TestAspect {

    @Pointcut("execution(* com.czh.dao.UserDao.getUser(..))")
    public void dao(){}

    @Pointcut("execution(* com.czh.dao.BookDao.getBookById(int)) && args(id)")
    public void getBook(int id){}

    //切点
    @Before("dao()")
    public void cBefore() {
        System.out.println("test before aspect1");
    }

    @After("dao()")
    public void cAfter() {
        System.out.println("test after aspect1");
    }

    @Around("dao()")
    public Object cAround(ProceedingJoinPoint point) {
        try {
            System.out.println("test around1 aspect");
            Object o = point.proceed();
            System.out.println("test around2 aspect");
            return o;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("test around3 aspect");
        }
        return null;
    }

    @Before("getBook(id)")
    public void bBefore(int id) {
        System.out.println("book before id = " + id);
    }
}
