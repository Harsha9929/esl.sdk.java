package com.silanis.esl.sdk.examples;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Created by whou on 08/12/14.
 */
public class ProxyConfigurationExampleTest {

    @Test
    public void verifyResult() {
        ProxyConfigurationExample example = new ProxyConfigurationExample(Props.get());
        example.run();

        assertThat(example.retrievedPackage1, notNullValue());
        assertThat(example.retrievedPackage2, notNullValue());
    }
}
