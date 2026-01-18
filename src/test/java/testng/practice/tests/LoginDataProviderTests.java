package testng.practice.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import page.objects.LoginPage;

public class LoginDataProviderTests {
    private WebDriver driver;
    LoginPage loginPage;

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @DataProvider(name = "incorrectLoginData")
    public Object[][] incorrectLoginData() {
        return new Object[][]{
                {"tomsmith", "smt", "Your password is invalid!"},
                {"", "", "Your username is invalid!"},
                {"smt", "SuperSecretPassword!", "Your username is invalid!"}
        };
    }

    @Test(dataProvider = "incorrectLoginData")
    public void shouldFailLogin(String userName, String password, String expectedWarning) {
        loginPage.openPage();
        loginPage.login(userName, password);

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