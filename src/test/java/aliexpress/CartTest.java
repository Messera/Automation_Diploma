package aliexpress;

import baseObjects.SelenideBaseTest;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.switchTo;

public class CartTest extends SelenideBaseTest {

    @Test(priority = 1)
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

    @Test(priority = 2)
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

    @Test(priority = 3)
    public void checkSelectInCart_Test() {
        get(CartPage.class)
                .clickSelectAllProducts();
    }

    @Test(priority = 4, description = "Тест, воспроизводящий баг")
    public void checkDeleteFromCart_Test() {
        get(CartPage.class)
                .clickDeleteBtn()
                .checkEmptyCartText()
                .checkEmptyCartCount();
    }
}