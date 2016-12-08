package com.github.osvaldopina.linkbuilder.annotation.reader.impl;

import com.github.osvaldopina.linkbuilder.annotation.Link;
import com.github.osvaldopina.linkbuilder.annotation.Links;
import com.github.osvaldopina.linkbuilder.annotation.Param;
import com.github.osvaldopina.linkbuilder.annotation.reader.AnnotationReader;
import com.github.osvaldopina.linkbuilder.annotation.reader.core.DestinationExtractor;
import com.github.osvaldopina.linkbuilder.annotation.reader.core.LinkRelExtractor;
import com.github.osvaldopina.linkbuilder.annotation.reader.properties.LinkAnnotationParameter;
import com.github.osvaldopina.linkbuilder.annotation.reader.properties.LinkAnnotationProperties;
import com.github.osvaldopina.linkbuilder.utils.ReflectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class LinkAnnotationReader implements AnnotationReader {

    private LinkRelExtractor linkRelExtractor = LinkRelExtractor.INSTANCE;

    private DestinationExtractor destinationExtractor = DestinationExtractor.INSTANCE;

    private ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;


    @Override
    public boolean canRead(Method method) {

        return getLinksAnnotation(method) != null;

    }

    @Override
    public List<LinkAnnotationProperties> read(Method method) {
        Annotation  linksAnnotation = getLinksAnnotation(method);

        List<LinkAnnotationProperties> linkAnnotationProperties = new ArrayList<LinkAnnotationProperties>();
        for(Annotation link :reflectionUtils.callMethod(Annotation[].class, linksAnnotation, "value")) {
            linkAnnotationProperties.add(readLinkAnnotion(link));
        }
        return linkAnnotationProperties;
    }

    private Annotation getLinksAnnotation(Method method) {
        for(Annotation annotation: method.getDeclaredAnnotations()) {
            if (Links.class == annotation.annotationType() ||
                    annotation.annotationType().getAnnotation(Links.class) != null)  {
                return annotation;
            }
        }
        return null;

    }

    private LinkAnnotationProperties readLinkAnnotion(Annotation linkAnnotation) {
        String destination = destinationExtractor.extract(linkAnnotation);
        String rel = linkRelExtractor.extract(linkAnnotation);
        boolean templated = reflectionUtils.callMethod(boolean.class,linkAnnotation, "templated");
        List<LinkAnnotationParameter> linkParameters = new ArrayList<LinkAnnotationParameter>();
        for(Param linkParam: reflectionUtils.callMethod(Param[].class,linkAnnotation, "params")) {
            linkParameters.add(new LinkAnnotationParameter(linkParam.name(), linkParam.value(),linkParam.when()));
        }
        return new LinkAnnotationProperties(destination, rel, templated, linkParameters);

    }

}
