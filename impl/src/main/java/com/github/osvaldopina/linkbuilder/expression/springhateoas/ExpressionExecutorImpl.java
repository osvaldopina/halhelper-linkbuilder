package com.github.osvaldopina.linkbuilder.expression.springhateoas;

import com.github.osvaldopina.linkbuilder.LinkBuilderException;
import com.github.osvaldopina.linkbuilder.expression.ExpressionExecutor;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.EvaluationException;
import org.springframework.expression.Expression;
import org.springframework.security.access.expression.ExpressionUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

public class ExpressionExecutorImpl implements ExpressionExecutor, ApplicationContextAware {

    private EvaluationContextCreator evaluationContextCreator = EvaluationContextCreator.INSTANCE;

    private SecurityExpressionParser securityExpressionParser = SecurityExpressionParser.INSTANCE;

    private ApplicationContext applicationContext;

    @Override
    public boolean isTrue(String expression, Object resource, Object[] params) {

        EvaluationContext ctx = evaluationContextCreator.create(applicationContext);

        if (resource != null) {
            ctx.setVariable("resource", resource);
        }

        if (params != null) {
            ctx.setVariable("params", params);
        }

        Expression parsedExpression = securityExpressionParser.parse(applicationContext, expression);

        return ExpressionUtils.evaluateAsBoolean(parsedExpression, ctx);
    }

    @Override
    public Object getValue(String expression, Object resource, Object[] params) {

        EvaluationContext ctx = evaluationContextCreator.create(applicationContext);

        if (resource != null) {
            ctx.setVariable("resource", resource);
        }

        if (params != null) {
            ctx.setVariable("params", params);
        }

        Expression parsedExpression = securityExpressionParser.parse(applicationContext, expression);

        try {
            return parsedExpression.getValue(ctx);
        }
        catch (EvaluationException e) {
            throw new LinkBuilderException("Failed to evaluate expression '"
                    + parsedExpression.getExpressionString() + "'", e);
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
