package com.hello.hello1;

import com.hello.hello1.annotations.MySuperCoolSecurity;
import com.hello.hello1.service.SecurityService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
                return new MySuperCoolSecurityService(bean);
            }
        }
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    private static class MySuperCoolSecurityService implements SecurityService {

        private final Object securityServiceBeanDelegate;

        private MySuperCoolSecurityService(Object securityServiceBeanDelegate) {
            this.securityServiceBeanDelegate = securityServiceBeanDelegate;
        }

        @Override
        public boolean auth(String login, String password) {
            try {
                Method delegateAuth = securityServiceBeanDelegate.getClass().getMethod("auth", String.class, String.class);
                if (SecurityService.LOGINS.contains(login)) {
                    delegateAuth.invoke(securityServiceBeanDelegate, "*", "**");
                    return false;
                }
                delegateAuth.invoke(securityServiceBeanDelegate, login, password);
                return true;
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
