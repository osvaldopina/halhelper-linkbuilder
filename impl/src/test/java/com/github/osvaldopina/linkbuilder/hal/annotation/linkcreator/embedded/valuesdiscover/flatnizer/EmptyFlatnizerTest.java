package com.github.osvaldopina.linkbuilder.hal.annotation.linkcreator.embedded.valuesdiscover.flatnizer;

import com.github.osvaldopina.linkbuilder.LinkBuilderException;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

public class EmptyFlatnizerTest {


    EmptyFlatnizer emptyFlatnizer = new EmptyFlatnizer();

    @Test(expected = LinkBuilderException.class)
    public void canFlat() throws Exception {
        emptyFlatnizer.canFlat(null);
    }

    @Test
    public void flatAndAddToSet() throws Exception {

        Set<Object> values = new HashSet<Object>();

        emptyFlatnizer.flatAndAddToSet("value", values);

        assertThat(values, containsInAnyOrder((Object) "value"));
    }
}