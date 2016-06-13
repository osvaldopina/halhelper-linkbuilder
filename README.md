# linkbuilder

[![Build Status](https://travis-ci.org/osvaldopina/linkbuilder.svg?branch=master)](https://travis-ci.org/osvaldopina/linkbuilder)

[![Join the chat at https://gitter.im/osvaldopina/linkbuilder](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/osvaldopina/linkbuilder?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Up Next

Annotations only...

## How to use it.

### Include the dependency

Add the following dependency to your project:

```xml
<dependency>
    <groupId>com.github.osvaldopina</groupId>
    <artifactId>linkbuilder</artifactId>
    <version>0.1.6</version>
</dependency>

```

Or the following dependency for the version under development:

```xml
<dependency>
    <groupId>com.github.osvaldopina</groupId>
    <artifactId>linkbuilder</artifactId>
    <version>0.1.7-SNAPSHOT</version>
</dependency>

```
### New in 0.1.3

## Spel expressions for link conditional rendering

 ```java
         ResourceSupport payload = new ResourceSupport();

         LinksBuilder  linksBuilder = linksBuilderFactory.create();

         linksBuilder.link()
                 .withRel("link-for-authenticated-users")
                 .when("isAuthenticated()" )
                 .fromControllerCall(ARestController.class)
                 .someControllerMethod();

         payload.add(linksBuilder.buildAll());

         return payload;
 ```

## Resource acessible via Spel expressions

 ```java
         ResourceSupport payload = new ResourceSupport();

         LinksBuilder  linksBuilder = linksBuilderFactory.create(payload);

         linksBuilder.link()
                 .withRel("link-for-authenticated-users")
                 .when("payload.someProperty && isAuthenticated()" )
                 .fromControllerCall(ARestController.class)
                 .someControllerMethod();

         payload.add(linksBuilder.buildAll());

         return payload;
 ```


### Annotate controller methods 

```@EnableSelfFromCurrentCall``` If you want to use the annotated method to generate links, link templates and use the current call to generate a self link:

```java
@RestController
public class RootRestController {

    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

    @RequestMapping("/")
    @EnableSelfFromCurrentCall
    public ResourceSupport root(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

```

Or ```@GenerateUriTemplateFor``` If you just want to use the annotated method to generate links and link templates:

```java
@RestController
public class RootRestController {

    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

    @RequestMapping("/")
    @GenerateUriTemplateFor
    public ResourceSupport root(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

```

### Inject ```LinksBuilderFactory```

```java 

    @Autowired
    private LinksBuilderFactory linksBuilderFactory;

```

### Create the ```LinksBuilder``` than the ```LinkBuilder```, configure them, and then build the links.

```java
        LinksBuilder  linksBuilder = linksBuilderFactory.create();

        linksBuilder
              .link()
              .withSelfRel()
              .fromCurrentCall();

        linksBuilder
              .link()
              .withRel("no-query-parameter")
              .fromControllerCall(RootRestController.class)
              .methodWithoutQueryParameter("value", null);

        linksBuilder
              .link()
              .withRel("user-defined-type")
              .fromControllerCall(RootRestController.class)
              .queryParameterForUserDefinedType(new UserDefinedType("v1", "v2"));

        payload.add(linksBuilder.buildAll());
 ```
 
 