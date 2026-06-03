package com.example.advanced_spring.proxy.advisor;

import com.example.advanced_spring.proxy.common.advice.TimeAdvice;
import com.example.advanced_spring.proxy.common.service.ServiceImpl;
import com.example.advanced_spring.proxy.common.service.ServiceInterface;
import java.lang.reflect.Method;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;

@Slf4j
public class AdvisorTest {

    @Test
    void advisorTest1() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(Pointcut.TRUE, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

        /**
         * 16:41:50.024 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 실행...
         * 16:41:50.026 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- save 호출...
         * 16:41:50.026 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 종료 resultTime=0
         * 16:41:50.027 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 실행...
         * 16:41:50.027 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- find 호출...
         * 16:41:50.027 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 종료 resultTime=0
         */
    }

    @Test
    void advisorTest2() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(new MyPointcut(), new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

        /**
         * 17:01:55.248 [Test worker] INFO com.example.advanced_spring.proxy.advisor.AdvisorTest -- 포인트컷 호출 method=save, targetClass=class com.example.advanced_spring.proxy.common.service.ServiceImpl
         * 17:01:55.251 [Test worker] INFO com.example.advanced_spring.proxy.advisor.AdvisorTest -- 포인트컷 결과 result=true
         * 17:01:55.252 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 실행...
         * 17:01:55.253 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- save 호출...
         * 17:01:55.253 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 종료 resultTime=0
         *
         * 17:01:55.253 [Test worker] INFO com.example.advanced_spring.proxy.advisor.AdvisorTest -- 포인트컷 호출 method=find, targetClass=class com.example.advanced_spring.proxy.common.service.ServiceImpl
         * 17:01:55.253 [Test worker] INFO com.example.advanced_spring.proxy.advisor.AdvisorTest -- 포인트컷 결과 result=false
         * 17:01:55.253 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- find 호출...
         */
    }

    @Test
    void advisorTest3() {
        ServiceInterface target = new ServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedNames("save");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, new TimeAdvice());
        proxyFactory.addAdvisor(advisor);
        ServiceInterface proxy = (ServiceInterface) proxyFactory.getProxy();

        proxy.save();
        proxy.find();

        /**
         * 17:15:55.486 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 실행...
         * 17:15:55.489 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- save 호출...
         * 17:15:55.489 [Test worker] INFO com.example.advanced_spring.proxy.common.advice.TimeAdvice -- TimeProxy 종료 resultTime=0
         *
         * 17:15:55.490 [Test worker] INFO com.example.advanced_spring.proxy.common.service.ServiceImpl -- find 호출...
         */
    }

    static class MyPointcut implements Pointcut {
        @Override
        public ClassFilter getClassFilter() {
            return ClassFilter.TRUE;
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            return new MyMethodMatcher();
        }
    }

    static class MyMethodMatcher implements MethodMatcher {

        private String matchName = "save";

        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            boolean result = method.getName().equals(matchName);
            log.info("포인트컷 호출 method={}, targetClass={}", method.getName(), targetClass);
            log.info("포인트컷 결과 result={}", result);
            return result;
        }

        @Override
        public boolean isRuntime() {
            return false;
        }

        @Override
        public boolean matches(Method method, Class<?> targetClass, Object... args) {
            return false;
        }
    }
}
