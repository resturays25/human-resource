package com.juaracoding;

import com.juaracoding.drivers.DriverSingleton;
import com.juaracoding.pages.LoginPage;
import com.juaracoding.pages.RecruitmentPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.juaracoding.drivers.DriverSingleton.delay;

public class RecruitmentTest {
    private WebDriver driver;
    private LoginPage loginPage;
    private RecruitmentPage recruitmentPage;

    @BeforeClass
    public void setUp(){
        DriverSingleton.getInstance("chrome");
        driver = DriverSingleton.getDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        loginPage = new LoginPage();
        recruitmentPage = new RecruitmentPage();
    }

    @AfterClass
    public void finish(){
        delay(3);
        DriverSingleton.closeObjectInstance();
    }

    @Test
    public void testAddCandidate(){
        loginPage.login("Admin","admin123");
        recruitmentPage.setMenuRecruitment();
        recruitmentPage.setAdd();
        recruitmentPage.setFirstName("Juara");
        recruitmentPage.setMiddleName("Coding");
        recruitmentPage.setLastName("Juaranya");
        DriverSingleton.delay(3);
        recruitmentPage.setEmail("juara@gmail.co.id");
        recruitmentPage.setResume("C:\\Users\\User\\Documents\\img20240628_08294377.pdf");
        recruitmentPage.setSave();
        Assert.assertEquals(recruitmentPage.getTxtCandidateProfile(),"Candidate Profile");

    }

}
