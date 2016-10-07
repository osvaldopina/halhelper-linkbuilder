package com.github.osvaldopina.linkbuilder.expression.impl;

import com.github.osvaldopina.linkbuilder.LinkBuilderException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;

public class DefaultWebSecurityExpressionHandlerRecover {

    public DefaultWebSecurityExpressionHandler recover(ApplicationContext applicationContext) {
        try {
            return applicationContext.getBean(DefaultWebSecurityExpressionHandler.class);
        }
        catch(BeansException e) {
            throw new LinkBuilderException(
                    "A expression expression was configured but a instance of " +
                            DefaultWebSecurityExpressionHandler.class +
                            " could not be found because " +e + ". Is expression configured?"
            );
        }

    }
}
