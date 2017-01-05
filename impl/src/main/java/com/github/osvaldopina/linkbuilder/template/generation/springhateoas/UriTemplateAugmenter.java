package com.github.osvaldopina.linkbuilder.template.generation.springhateoas;

import com.damnhandy.uri.template.Expression;
import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import com.damnhandy.uri.template.UriTemplateComponent;
import com.damnhandy.uri.template.impl.Modifier;
import com.damnhandy.uri.template.impl.Operator;
import com.damnhandy.uri.template.impl.VarSpec;
import org.springframework.util.Assert;

import java.util.Collections;

public class UriTemplateAugmenter {

    private Expression queryExpression;
    private UriTemplateBuilder uriTemplateBuilder;

    protected UriTemplateAugmenter(UriTemplateBuilder uriTemplateBuilder) {
        this.uriTemplateBuilder = uriTemplateBuilder;
    }

    public void template(String literal) {
        uriTemplateBuilder.template(literal);
    }

    public void addToQuery(String name) {
        if (queryExpression == null) {
            queryExpression = getFirstQueryExpressionOrCreateIfNotExists(uriTemplateBuilder);
        }
        queryExpression.getVarSpecs().add(new VarSpec(name, Modifier.NONE));
    }

    public UriTemplate getUriTemplate() {
        return uriTemplateBuilder.build();
    }

    private Expression getFirstQueryExpressionOrCreateIfNotExists(UriTemplateBuilder uriTemplateBuilder) {
        Assert.notNull(uriTemplateBuilder);

        for(UriTemplateComponent uriTemplateComponent:uriTemplateBuilder.getComponents()) {
            if (uriTemplateComponent instanceof Expression &&
                    ((Expression) uriTemplateComponent).getOperator() == Operator.QUERY) {
                return (Expression) uriTemplateComponent;
            }
        }
        Expression queryExpression = new Expression(Operator.QUERY, Collections.<VarSpec>emptyList());
        uriTemplateBuilder.query(new VarSpec[] {});
        return getFirstQueryExpressionOrCreateIfNotExists(uriTemplateBuilder);
    }

}
