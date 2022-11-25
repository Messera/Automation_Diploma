package baseObjects;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import driver.DriverManagerFactory;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import lombok.extern.log4j.Log4j;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import testNgUtils.ExtentReportListener;
import testNgUtils.Listener;

import static driver.DriverManager.closeWebDriver;
import static driver.DriverManagerFactory.*;
import static propertyHelper.PropertyReader.getProperties;


@Listeners({Listener.class, ExtentReportListener.class})
@Log4j
public abstract class BaseTest {

    protected Properties properties;

    @BeforeTest
    public void setUp() {
        log.debug("I started new web driver!");
        properties = getProperties();
        DriverManagerFactory.getManager(DriverManagerType.valueOf(properties.containsKey("browser") ? properties.getProperty("browser").toUpperCase() : "CHROME"));
    }

    protected <T> T get(Class<T> page){
        T instance = null;
        try {
            instance = page.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    @AfterTest
    public void stop() {
        log.debug("I closed web driver!");
        closeWebDriver();
    }

}
