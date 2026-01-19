package testng.practice.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import page.objects.LoginPageAnnotationFactory;

public class LoginAnnotationFactoryTests {

    private WebDriver driver;
    LoginPageAnnotationFactory loginPageAnnotationFactory;

    private final String userName;
    private final String password;
    private final String expectedWarning;

    public LoginAnnotationFactoryTests(String userName, String password, String expectedWarning) {
        this.userName = userName;
        this.password = password;
        this.expectedWarning = expectedWarning;
    }

    @Factory
    public static Object[] createTests() {
        return new Object[]{
                new LoginAnnotationFactoryTests(
                        "tomsmith",
                        "wrongPass",
                        "Your password is invalid!"
                ),
                new LoginAnnotationFactoryTests(
                        "wrongUser",
                        "SuperSecretPassword!",
                        "Your username is invalid!"
                ),
                new LoginAnnotationFactoryTests(
                        "",
                        "",
                        "Your username is invalid!"
                )
        };
    }

    @BeforeMethod
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPageAnnotationFactory = new LoginPageAnnotationFactory(driver);
    }

    @Test
    public void shouldFailLogin() {
        loginPageAnnotationFactory.openPage();
        loginPageAnnotationFactory.login(userName, password);

        String error = loginPageAnnotationFactory.getExpectedWarningMessage();
        Assert.assertTrue(error.contains(expectedWarning));
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}