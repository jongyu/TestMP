package com.auium.test

import com.auium.framework.AuiumPage
import com.squareup.kotlinpoet.*
import org.junit.jupiter.api.Test
import org.openqa.selenium.WebElement

class KotlinPoetTest {

    @Test
    fun generateKotlinTest() {
        val pageFile = FileSpec.builder("com.auium.page", "HomePage")
            .addType(
                TypeSpec.classBuilder("HomePage").superclass(AuiumPage::class)
                    .addFunction(
                        FunSpec.builder("skip")
                            .returns(WebElement::class)
                            .addStatement("return element(%S)", "By1")
                            .build()
                    )
                    .build()
            ).build()
//        pageFile.writeTo(File("src/test/kotlin"))

        val android = PropertySpec.builder("android", String::class)
            .addModifiers(KModifier.PRIVATE)
            .initializer(""""Oreo v.8.1"""")
            .build()
        val companion = TypeSpec.companionObjectBuilder().addProperty(android)
            .build()


        val handleFile = FileSpec.builder("com.auium.handle", "HomeHandle")
            .addType(companion)
            .addFunction(
                FunSpec.builder("clickSkip").build()
            ).build()
        handleFile.writeTo(System.out)
    }

}