package com.github.osvaldopina.linkbuilder.direct.impl;

import com.github.osvaldopina.linkbuilder.methodtemplate.linkcreator.AnnotatedLinkCreator;
import com.github.osvaldopina.linkbuilder.methodtemplate.urigenerator.AnnotatedMethodUriGenerators;
import com.github.osvaldopina.linkbuilder.utils.IntrospectionUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Method;

public class DirectLinkTargetBeanPostProcessor implements BeanPostProcessor, ApplicationContextAware {

    @Autowired
    private IntrospectionUtils introspectionUtils;

    @Autowired
    private AnnotatedLinkCreator<?> annotatedLinkCreator;

    @Autowired
    private AnnotatedMethodUriGenerators annotatedMethodUriGenerators;

    private ApplicationContext applicationContext;


    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (introspectionUtils.isRestController(bean)) {
            for (Method method : introspectionUtils.getLinksAnnotatedMethods(bean)) {
                ProxyFactory factory = new ProxyFactory();
                factory.addAdvice(
                        new LinksToResourceMethodInterceptor(annotatedLinkCreator,
                                annotatedMethodUriGenerators, introspectionUtils)
                );
                factory.setTarget(bean);
                return factory.getProxy();
            }
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}