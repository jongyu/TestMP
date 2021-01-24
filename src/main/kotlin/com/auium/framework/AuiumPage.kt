package com.auium.framework

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.chrome.ChromeDriver

open class AuiumPage {

    fun element(name: String): WebElement {
        return ChromeDriver().findElement(By.className(name))
    }

}