package aliexpress;

import baseObjects.SelenideBaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class NothingFoundTest extends SelenideBaseTest {

    @Test(priority = 6, description = "Проверка на ввод некорректных данных")
    public void checkNothingFound_Test() {
        get(HomePage.class)
                .enterSearch("findnothingplease")
                .checkNothingFoundText();
    }
    @AfterTest
    public void postcondition() {
        Selenide.closeWebDriver();
    }
}