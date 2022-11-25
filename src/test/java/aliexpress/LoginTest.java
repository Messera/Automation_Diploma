package aliexpress;

import baseObjects.SelenideBaseTest;
import org.testng.annotations.Test;

public class LoginTest extends SelenideBaseTest {

    @Test(priority = 6)
    public void checkLoginFormOpens_Test() {
        get(HomePage.class)
                .clickLoginBtn()
                .checkLoginFormOpened();
    }

    @Test(priority = 7)
    public void checkLoginInformationEntering_Test() {
        get(HomePage.class)
                .enterLoginInfo("test1@test.com", "testUser1")
                .clickLoginSubmitBtn();
    }
}