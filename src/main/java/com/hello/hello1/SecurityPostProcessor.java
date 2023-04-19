package com.hello.hello1;

import com.hello.hello1.annotations.MySuperCoolSecurity;
import com.hello.hello1.service.SecurityService;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Optional;

@Component
public class SecurityPostProcessor implements BeanPostProcessor {
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof SecurityService securityService) {
            Optional<Method> mySuperCoolSecurityMethod = Arrays.stream(securityService.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(MySuperCoolSecurity.class))
                    .findAny();
            if (mySuperCoolSecurityMethod.isPresent()) {
                System.out.println("    ProcessedBefore " + beanName);
                return Proxy.newProxyInstance(securityService.getClass().getClassLoader(),
                        bean.getClass().getInterfaces(),
                        new SecurityServiceInvocationHandler(bean));
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public static class SecurityServiceInvocationHandler implements InvocationHandler {
        private final Object securityServiceBeanDelegate;

        public SecurityServiceInvocationHandler(Object securityServiceBeanDelegate) {
            this.securityServiceBeanDelegate = securityServiceBeanDelegate;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (method.getName().equals("auth")) {
                try {
                    Method delegateAuth = securityServiceBeanDelegate.getClass().getMethod("auth", String.class, String.class);
                    if (SecurityService.LOGINS.contains((String) args[0])) {
                        delegateAuth.invoke(securityServiceBeanDelegate, "*", "**");
                        return false;
                    }
                    delegateAuth.invoke(securityServiceBeanDelegate, (String) args[0], (String) args[1]);
                    return true;
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                method.invoke(securityServiceBeanDelegate, args);
            }
            return 42;
        }
    }
}
