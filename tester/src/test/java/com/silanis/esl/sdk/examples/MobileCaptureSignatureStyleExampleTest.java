package com.silanis.esl.sdk.examples;

import com.silanis.esl.sdk.Signature;
import com.silanis.esl.sdk.SignatureStyle;
import org.junit.Test;

import static com.silanis.esl.sdk.examples.MobileCaptureSignatureStyleExample.*;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by schoi on 11/27/14.
 */
public class MobileCaptureSignatureStyleExampleTest {
    @Test
    public void verifyResult() {
        MobileCaptureSignatureStyleExample example = new MobileCaptureSignatureStyleExample();
        example.run();

        for (Signature signature : example.retrievedPackage.getDocument(DOCUMENT_NAME).getSignatures()) {
            if ((int) (signature.getX() + 0.1) == MOBILE_CAPTURE_SIGNATURE_POSITION_X && (int) (signature.getY() + 0.1) == MOBILE_CAPTURE_SIGNATURE_POSITION_Y) {
                assertThat(signature.getStyle(), is(SignatureStyle.MOBILE_CAPTURE));
                assertThat(signature.getPage(), is(MOBILE_CAPTURE_SIGNATURE_PAGE));
                assertTrue(signature.isEnforceCaptureSignature());
            }
        }
    }
}
