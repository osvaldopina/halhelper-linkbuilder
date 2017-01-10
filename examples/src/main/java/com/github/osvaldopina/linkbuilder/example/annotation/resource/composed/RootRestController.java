package com.github.osvaldopina.linkbuilder.example.annotation.resource.composed;

import com.github.osvaldopina.linkbuilder.LinksBuilderFactory;
import com.github.osvaldopina.linkbuilder.example.annotation.resource.composed.link.LINK_DESTINATION;
import com.github.osvaldopina.linkbuilder.example.annotation.resource.composed.link.MyGenerateUriTemplateFor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootRestController {


    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

    @RequestMapping("/")
    @MyGenerateUriTemplateFor(destination = LINK_DESTINATION.ROOT)
    public Resource root() {
        Resource resource = new Resource();

        resource.setQueryValue("anyQueryValue");
        resource.setPathValue("anyPathValue");

        return resource;
    }

    @RequestMapping("/direct-link/{path}")
    @MyGenerateUriTemplateFor(destination = LINK_DESTINATION.DIRECT_LINK)
    public void directLink(@RequestParam(value = "query", required = false) String query,
                           @PathVariable("path") String path) {

    }

    @RequestMapping("/direct-link/templated")
    @MyGenerateUriTemplateFor(destination = LINK_DESTINATION.DIRECT_LINK_TEMPLATED)
    public void directLinkTemplated(
            @RequestParam(value = "non_templated", required = false) String nonTemplated,
            @RequestParam(value = "templated", required = false) String templated) {
    }

}
