package aliexpress;

import baseObjects.SelenideBaseTest;
import com.codeborne.selenide.Selenide;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class CartTest extends SelenideBaseTest {


    @Test(priority = 1, description = "Проверка того, что товар ищется по запросу и информация о нем " +
            "на странице поиска совпадает с информацией на странице товара")
    public void checkProductOpens_Test() {
        get(HomePage.class)
                .enterSearch("plush toy")
                .getProductName()
                .getProductPrice()
                .clickProduct();
        switchTo().window(1);
        get(ProductPage.class)
                .checkProductInformation();
    }

    @Test(priority = 2, description = "Проверка того, что товар добавляется в корзину и информация о нем " +
            "в корзине соответствует информации на странице поиска")
    public void checkAddingToCart_Test() {
        get(ProductPage.class)
                .checkCartEmpty()
                .clickAddToCartBtn()
                .checkAddToCartBtnClicked()
                .clickCartBtn();
        get(CartPage.class)
                .checkTotalPriceEmpty()
                .checkProductInformation()
                .checkFullCartCount();
    }

    @Test(priority = 3, description = "Проверка того, что при выборе товара в корзине " +
            "его стоимость корректно отображается в графе 'К оплате'")
    public void checkSelectInCart_Test() {
        get(CartPage.class)
                .clickSelectAllProducts();
    }

    @Test(priority = 4, description = "Проверка поля количество товара на граничные значения и значения, превышающие допустимые")
    public void checkMaxAmount_Test() {
        get(CartPage.class)
                .clickAddOneMoreBtn();
    }

    @Test(priority = 5, description = "Тест, воспроизводящий баг корзины")
    public void checkDeleteFromCart_Test() {
        get(CartPage.class)
                .clickDeleteBtn()
                .checkEmptyCartText()
                .checkEmptyCartCount();
    }
    @AfterTest
    public void postcondition() {
        Selenide.closeWebDriver();
    }
}