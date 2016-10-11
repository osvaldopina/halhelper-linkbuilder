package com.github.osvaldopina.linkbuilder.configuration;

import com.github.osvaldopina.linkbuilder.methodtemplate.LinkGenerator;
import com.github.osvaldopina.linkbuilder.methodtemplate.TemplateGenerator;
import com.github.osvaldopina.linkbuilder.methodtemplate.impl.TemplateGeneratorImpl;
import com.github.osvaldopina.linkbuilder.methodtemplate.uridiscover.BaseUriDiscover;
import com.github.osvaldopina.linkbuilder.methodtemplate.uridiscover.impl.BaseUriDiscoverImpl;
import com.github.osvaldopina.linkbuilder.methodtemplate.uridiscover.impl.LinkGeneratorImpl;
import com.github.osvaldopina.linkbuilder.methodtemplate.uridiscover.requestparts.RequestPartsFactoryList;
import com.github.osvaldopina.linkbuilder.methodtemplate.uridiscover.requestparts.impl.RequestPartsFactoryListImpl;
import com.github.osvaldopina.linkbuilder.expression.ExpressionExecutor;
import com.github.osvaldopina.linkbuilder.expression.impl.ExpressionExecutorImpl;

public class DefaultLinkBuilderConfigurer implements LinkBuilderConfigurer {

    @Override
    public TemplateGenerator templateGenerator() {
        return new TemplateGeneratorImpl();
    }

    @Override
    public RequestPartsFactoryList requestPartsFactoryList() {
        return new RequestPartsFactoryListImpl();
    }

    @Override
    public ExpressionExecutor spelExecutor() {
        return new ExpressionExecutorImpl();
    }

    @Override
    public BaseUriDiscover baseUriDiscover() {
        return new BaseUriDiscoverImpl();
    }

    @Override
    public LinkGenerator linkGenerator() {
        return new LinkGeneratorImpl();
    }
}