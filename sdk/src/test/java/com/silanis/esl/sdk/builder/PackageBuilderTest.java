package com.silanis.esl.sdk.builder;

import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.DocumentType;
import com.silanis.esl.sdk.VersionUtil;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Test;

import java.io.ByteArrayInputStream;

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName;
import static com.silanis.esl.sdk.builder.DocumentPackageAttributesBuilder.newDocumentPackageAttributes;
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed;
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor;
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PackageBuilderTest {

    @Test
    public void buildWithSpecifiedValues() {
        DocumentPackage documentPackage = newPackageNamed("testing package")
                .withSigner( newSignerWithEmail( "john.doe@acme.com" )
                        .withFirstName( "John" )
                        .withLastName( "Doe" )
                        .build() )
                .withDocument( newDocumentWithName( "first doc" )
                        .fromStream( new ByteArrayInputStream( new byte[ 0 ] ), DocumentType.PDF )
                        .withSignature( signatureFor( "john.doe@acme.com" ) )
                        .build() )
                .withAttributes( newDocumentPackageAttributes()
                        .withAttribute( "age", "29" )
                        .withAttribute( "name", "first name" )
                        .build() )
                .withTimezoneId( "Canada/Mountain" )
                .build();

        assertThat( documentPackage.getName(), is( equalTo( "testing package" ) ) );
        assertThat( documentPackage.getSigner( "john.doe@acme.com" ), is( notNullValue() ) );
        assertThat( documentPackage.getDocument( "first doc" ), is( notNullValue() ) );
        assertThat( documentPackage.getTimezoneId(), is( equalTo("Canada/Mountain" ) ) );
    }

    @Test
    public void buildWithDefaultValues(){
        DocumentPackage documentPackage = newPackageNamed("testing package")
                .withSigner(newSignerWithEmail("test@test.com")
                        .withFirstName("test")
                        .withLastName("test")
                        .build())
                .withDocument(newDocumentWithName("document")
                        .fromStream(new ByteArrayInputStream(new byte[0]), DocumentType.PDF)
                        .build())
                .build();

        assertThat( documentPackage.getAutocomplete(), is( true ) );
    }
}