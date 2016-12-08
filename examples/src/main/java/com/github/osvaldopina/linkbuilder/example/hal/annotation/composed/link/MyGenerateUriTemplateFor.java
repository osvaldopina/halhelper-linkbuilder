package com.github.osvaldopina.linkbuilder.example.hal.annotation.composed.link;

import com.github.osvaldopina.linkbuilder.annotation.GenerateUriTemplateFor;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@GenerateUriTemplateFor
@Retention(RetentionPolicy.RUNTIME)
public @interface MyGenerateUriTemplateFor {

    LINK_DESTINATION destination();
}
