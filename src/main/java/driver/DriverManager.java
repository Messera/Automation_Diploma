package driver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public abstract class DriverManager {

    public static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    {
        if(webDriver.get() == null) {
            createDriver();
        }
    }

    public abstract void createDriver();

    public static WebDriver getDriver() {
        webDriver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        webDriver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
        webDriver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(5));
        return webDriver.get();
    }

    public static void closeWebDriver() {
        if (webDriver.get() != null) {
            webDriver.get().close();
            webDriver.get().quit();
            webDriver.remove();
        }
    }

}
