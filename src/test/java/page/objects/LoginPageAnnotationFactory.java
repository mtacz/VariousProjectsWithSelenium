package page.objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPageAnnotationFactory {

    WebDriver driver;

    private By userName = By.id("username");
    private By fieldPassword = By.id("password");
    private By loginButton = By.cssSelector("button.radius");
    private By expectedWarning = By.id("flash");

    public LoginPageAnnotationFactory(WebDriver driver) {
        this.driver = driver;
    }

    public void openPage() {
        driver.navigate().to("https://theinternet.przyklady.javastart.pl/login");
    }

    public void login(String username, String password) {
        driver.findElement(userName).sendKeys(username);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    public String getExpectedWarningMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(expectedWarning)).getText();
    }

}