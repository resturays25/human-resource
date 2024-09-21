package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage();
    }

    @AfterClass
    public void finish(){
        DriverSingleton.delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testValidLogin(){
        loginPage.clearCredential();
        loginPage.login("Admin","admin123");
        Assert.assertEquals(loginPage.getTextDashboard(),"Dashboard");
    }

    @Test
    public void testInvalidUsername(){
        loginPage.login("wrong username","admin123");
        Assert.assertEquals(loginPage.getTextInvalidCredentials(),"Invalid credentials");
        Assert.assertEquals(driver.getCurrentUrl(),"https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

    }

    @Test
    public void testUsernameEmpty(){
        loginPage.clearCredential();
        loginPage.login("","admin123");
        Assert.assertEquals(loginPage.getTextRequired(),"Required");
    }

    @Test
    public void testPassEmpty(){
        loginPage.clearCredential();
        loginPage.login("Admin","");
        Assert.assertEquals(loginPage.getTextRequired(),"Required");
    }
}
