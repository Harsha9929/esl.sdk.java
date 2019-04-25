package com.silanis.esl.sdk.internal.converter;

import com.silanis.esl.sdk.SenderType;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

/**
 * Created by lena on 2014-05-30.
 */
public class SenderTypeConverterTest implements ConverterTest {

    private com.silanis.esl.sdk.SenderType sdkSenderType1 = null;
    private com.silanis.esl.sdk.SenderType sdkSenderType2 = null;
    private String apiSenderType1 = null;
    private String apiSenderType2 = null;
    private SenderTypeConverter converter;

    @Override
    @Test
    public void convertNullSDKToAPI() {
        sdkSenderType1 = null;
        converter = new SenderTypeConverter(sdkSenderType1);
        assertThat("Converter didn't return a null api object for a null sdk object", converter.toAPISenderType(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToSDK() {
        apiSenderType1 = null;
        converter = new SenderTypeConverter(apiSenderType1);
        assertThat("Converter didn't return a null sdk object for a null api object", converter.toSDKSenderType(), nullValue());
    }

    @Override
    @Test
    public void convertNullSDKToSDK() {
        sdkSenderType1 = null;
        converter = new SenderTypeConverter(sdkSenderType1);
        assertThat("Converter didn't return a null sdk object for a null sdk object", converter.toSDKSenderType(), nullValue());
    }

    @Override
    @Test
    public void convertNullAPIToAPI() {
        apiSenderType1 = null;
        converter = new SenderTypeConverter(apiSenderType1);
        assertThat("Converter didn't return a null api object for a null api object", converter.toAPISenderType(), nullValue());
    }

    @Override
    @Test
    public void convertSDKToSDK() {
        sdkSenderType1 = com.silanis.esl.sdk.SenderType.MANAGER;
        sdkSenderType2 = new SenderTypeConverter(sdkSenderType1).toSDKSenderType();

        assertThat("Converter returned a null sdk object for a non null sdk object", sdkSenderType2, notNullValue());
        assertThat("Converter didn't return the same non-null sdk object it was given", sdkSenderType2, is(sdkSenderType1));
    }

    @Override
    @Test
    public void convertAPIToAPI() {
        apiSenderType1 = "REGULAR";
        apiSenderType2 = new SenderTypeConverter(apiSenderType1).toAPISenderType();

        assertThat("Converter returned a null api object for a non null api object", apiSenderType2, notNullValue());
        assertThat("Converter didn't return the same non-null api object it was given", apiSenderType2, is(apiSenderType1));
    }

    @Override
    @Test
    public void convertAPIToSDK() {
        apiSenderType1 = "REGULAR";
        sdkSenderType1 = new SenderTypeConverter(apiSenderType1).toSDKSenderType();
        assertThat("Sender type was not set correctly", sdkSenderType1, is(SenderType.REGULAR));

        apiSenderType1 = "MANAGER";
        sdkSenderType1 = new SenderTypeConverter(apiSenderType1).toSDKSenderType();
        assertThat("Sender type was not set correctly", sdkSenderType1, is(SenderType.MANAGER));

        apiSenderType1 = "UNKNOWN";
        sdkSenderType1 = new SenderTypeConverter(apiSenderType1).toSDKSenderType();
        assertThat("Sender type was not set correctly", sdkSenderType1.toString(), is(SenderType.UNRECOGNIZED("UNKNOWN").toString()));
    }

    @Override
    @Test
    public void convertSDKToAPI() {
        sdkSenderType1 = com.silanis.esl.sdk.SenderType.REGULAR;
        apiSenderType1 = new SenderTypeConverter(sdkSenderType1).toAPISenderType();
        assertThat("Sender type was not set correctly", apiSenderType1, is("REGULAR"));

        sdkSenderType1 = com.silanis.esl.sdk.SenderType.MANAGER;
        apiSenderType1 = new SenderTypeConverter(sdkSenderType1).toAPISenderType();
        assertThat("Sender type was not set correctly", apiSenderType1, is("MANAGER"));

        sdkSenderType1 = com.silanis.esl.sdk.SenderType.UNRECOGNIZED("UNKNOWN");
        apiSenderType1 = new SenderTypeConverter(sdkSenderType1).toAPISenderType();
        assertThat("Sender type was not set correctly", apiSenderType1, is("UNKNOWN"));
    }
}
