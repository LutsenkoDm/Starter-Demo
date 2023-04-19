package com.hello.hello1;

import com.hello.hello1.service.SecurityService;
import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.core.type.MethodMetadata;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SecurityServiceBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    @SneakyThrows
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        AnnotatedBeanDefinition mySuperCoolServiceBeanDefinition = (AnnotatedBeanDefinition) beanFactory.getBeanDefinition("mySuperCoolSecurityService");
        MethodMetadata mySuperCoolMethodMetadata = mySuperCoolServiceBeanDefinition.getMetadata().getDeclaredMethods().stream().findFirst().get();
        Field mySource = mySuperCoolMethodMetadata.getClass().getDeclaredField("source");
        mySource.setAccessible(true);
        Set<String> securityServiceDeclaredMethodsNames = Arrays.stream(SecurityService.class.getDeclaredMethods())
                .map(method -> method.getName())
                .collect(Collectors.toSet());

        String[] securityServiceBeanNames = beanFactory.getBeanNamesForType(SecurityService.class);
        for (String beanName : securityServiceBeanNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanName);
            if (beanDefinition instanceof AnnotatedBeanDefinition annotatedBeanDefinition) {
                annotatedBeanDefinition.getMetadata().getDeclaredMethods().stream()
                        .filter(methodMetadata -> securityServiceDeclaredMethodsNames.contains(methodMetadata.getMethodName()))
                        .forEach(methodMetadata -> {
                            try {
                                Field declaringClassName = methodMetadata.getClass().getDeclaredField("declaringClassName");
                                declaringClassName.setAccessible(true);
                                declaringClassName.set(methodMetadata, "com.hello.hello1.service.MySuperCoolSecurityService");

                                Field source = methodMetadata.getClass().getDeclaredField("source");
                                source.setAccessible(true);
                                source.set(methodMetadata, mySource.get(mySuperCoolMethodMetadata));
                            } catch (NoSuchFieldException | IllegalAccessException e) {
                                throw new RuntimeException(e);
                            }
                        });
                System.out.println("    " + beanName);
                //((DefaultListableBeanFactory) beanFactory).removeBeanDefinition(beanName);
                ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition(beanName, annotatedBeanDefinition);
            }
        }
    }
}
