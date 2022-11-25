package aliexpress;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.testng.Assert;

import static aliexpress.HomePage.productNameHP;
import static aliexpress.HomePage.productPriceHP;
import static com.codeborne.selenide.Selenide.$;

public class ProductPage {

    private SelenideElement cartBtn = $(By.cssSelector("a[href*=\"shopcartDetail\"]"));
    private SelenideElement cartCount = $(By.className("snow-ali-kit_Tag__words__afvess"));
    private SelenideElement addToCartBtn = $(By.cssSelector("span[class*=\"SnowPriceButton\"]"));
    private SelenideElement productName = $(By.cssSelector("h1"));
    private SelenideElement productPrice = $(By.className("snow-price_SnowPrice__mainS__18x8np"));

    public ProductPage clickCartBtn() {
        cartBtn.click();
        return this;
    }

    public ProductPage clickProduct() {
        productName.click();
        return this;
    }

    public ProductPage clickAddToCartBtn() {
        addToCartBtn.click();
        return this;
    }

    public ProductPage checkAddToCartBtnClicked() {
        $(By.cssSelector("span[class*=\"SnowPriceButton\"]")).shouldHave(Condition.text("В корзине"));
        return this;
    }

    public ProductPage checkCartEmpty() {
        $(By.className("snow-ali-kit_Tag__words__afvess")).shouldBe(Condition.hidden);
        return this;
    }

    public void checkProductInformation() {
        Assert.assertEquals(productName.getText(), productNameHP);
        Assert.assertEquals(productPrice.getText(), productPriceHP);
    }
}