package aliexpress;

import baseObjects.SelenideBaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import testNgUtils.Retry;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class HiddenButtonTest extends SelenideBaseTest {

    @Test(retryAnalyzer = Retry.class, priority = 7, description = "Проверка открытия логин формы через выпадающий список, " +
            "появляющийся при наведении на него мышкой")
    public void checkList_Test() {
        closeWebDriver();
        open("https://aliexpress.ru/");
        get(HomePage.class)
                .clickCartBtn();
        get(CartPage.class)
                .clickLoginBtn();
    }

    @AfterTest
    public void postcondition() {
        Selenide.closeWebDriver();
    }
}