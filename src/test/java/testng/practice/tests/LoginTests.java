package testng.practice.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import page.objects.LoginPage;

public class LoginTests {

    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Parameters({"userName","password","expectedWarning"})
    @Test
    public void shouldFailLogin(String userName, String password, String expectedWarning) {
        loginPage.openPage();
        loginPage.login(userName,password);

        String error = loginPage.getExpectedWarningMessage();
        Assert.assertTrue(error.contains(expectedWarning));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}