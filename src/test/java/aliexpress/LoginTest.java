package aliexpress;

import baseObjects.SelenideBaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class LoginTest extends SelenideBaseTest {

    @Test(priority = 8)
    public void checkLoginFormOpens_Test() {
        get(HomePage.class)
                .clickLoginBtn()
                .checkLoginFormOpened();
    }

    @Test(priority = 9)
    public void checkLoginInformationEntering_Test() {
        get(HomePage.class)
                .enterLoginInfo("test1@test.com", "testUser1")
                .clickLoginSubmitBtn();
    }
    @AfterTest
    public void postcondition() {
        Selenide.closeWebDriver();
    }
}