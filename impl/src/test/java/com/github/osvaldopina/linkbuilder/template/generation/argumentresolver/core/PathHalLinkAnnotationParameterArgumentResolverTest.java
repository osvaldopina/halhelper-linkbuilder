package com.github.osvaldopina.linkbuilder.template.generation.argumentresolver.core;

import com.damnhandy.uri.template.UriTemplate;
import com.damnhandy.uri.template.UriTemplateBuilder;
import com.github.osvaldopina.linkbuilder.LinkBuilderException;

import com.github.osvaldopina.linkbuilder.utils.IntrospectionUtils;
import com.github.osvaldopina.linkbuilder.utils.UriTemplateAugmenter;
import org.easymock.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PathHalLinkAnnotationParameterArgumentResolverTest extends EasyMockSupport {

    @Rule
    public EasyMockRule mocks = new EasyMockRule(this);

    @Mock
    IntrospectionUtils introspectionUtils;

//    @TestSubject
//    private PathVariableAnnotationArgumentResolver pathVariableAnnotationArgumentResolver =
//            new PathVariableAnnotationArgumentResolver(introspectionUtils);

    private UriTemplateBuilder uriTemplateBuilder;

    @Mock
    private UriTemplate uriTemplate;

    private Method method;

    @Mock
    private UriTemplateAugmenter.Factory uriTemplateAugmentFactory;

    @Mock
    private UriTemplateAugmenter uriTemplateAugmenter;

//    @Mock
//    private VariableSubstitutionController variableSubstitutionController;


    @Before
    public void setUp() throws Exception {

        uriTemplateBuilder = UriTemplate.createBuilder();

        method = QueryVariableAnnotationArgumentResolverTest.class.getMethod("equals", Object.class);
    }


    @Test
    public void resolveForAnnotatedMethod() throws Exception {

 //       EasyMock.expect(introspectionUtils.isPathVariableParameter(method, 0)).andReturn(true);

        replayAll();

   //     assertTrue(pathVariableAnnotationArgumentResolver.resolveFor(method, 0));

        verifyAll();

    }

    @Test
    public void resolveForNonAnnotatedMethod() throws Exception {

 //       EasyMock.expect(introspectionUtils.isPathVariableParameter(method, 0)).andReturn(false);

        replayAll();

    //    assertFalse(pathVariableAnnotationArgumentResolver.resolveFor(method, 0));

        verifyAll();

    }

    @Test
    public void augmentTemplate() throws Exception {
        String varName = "var1";

        replayAll();

   //     pathVariableAnnotationArgumentResolver.augmentTemplate(uriTemplateAugmenter, method, 0);

        verifyAll();
    }

    @Test(expected = LinkBuilderException.class)
    @Ignore
    public void setTemplateVariablesParamNameNotInTemplatedParamNames() throws Exception {
        String varName = "var1";
        String value = "value-for-var1";

   //     EasyMock.expect(introspectionUtils.getPathVariableName(method, 0)).andReturn(varName);
   //     EasyMock.expect(variableSubstitutionController.substitution(method, 0, varName, value)).andReturn(true);
   //     EasyMock.expect(uriTemplate.getVariables()).andReturn(new String[] {"var2"});
   //     EasyMock.expect(uriTemplate.getTemplate()).andReturn("template");

        replayAll();

  //      pathVariableAnnotationArgumentResolver.setTemplateVariables(uriTemplate, method, 0, value, variableSubstitutionController);

        verifyAll();

    }

    @Test
    public void setTemplateVariablesParamNameInTemplatedParamNames() throws Exception {
        String varName = "var1";
        String value = "value-for-var1";

 //       EasyMock.expect(introspectionUtils.getPathVariableName(method, 0)).andReturn(varName);
//        EasyMock.expect(variableSubstitutionController.substitution(method, 0, varName, value)).andReturn(true);
 //       EasyMock.expect(uriTemplate.getVariables()).andReturn(new String[] {"var1"});
 //       EasyMock.expect(uriTemplate.set(varName, value)).andReturn(uriTemplate);


        replayAll();

  //      pathVariableAnnotationArgumentResolver.setTemplateVariables(uriTemplate, method, 0, value, variableSubstitutionController);

        verifyAll();

    }

    @Test
    public void setTemplateVariablesSubstitutionControllerSubstitutionNotAllowed() throws Exception {
        String varName = "var1";
        String value = "value-for-var1";

  //      EasyMock.expect(introspectionUtils.getPathVariableName(method, 0)).andReturn(varName);
  //      EasyMock.expect(variableSubstitutionController.substitution(method, 0, varName, value)).andReturn(false);

        replayAll();

  //      pathVariableAnnotationArgumentResolver.setTemplateVariables(uriTemplate, method, 0, value, variableSubstitutionController);

        verifyAll();

    }

}