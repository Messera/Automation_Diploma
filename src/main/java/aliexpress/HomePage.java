package aliexpress;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;

public class HomePage {

    private final SelenideElement searchInput = $(By.id("searchInput"));
    private final SelenideElement cartBtn = $(By.cssSelector("a[href*=\"shopcartDetail\"]"));
    private final SelenideElement loginBtn = $(By.className("SnowHeaderProfileItem_SnowHeaderProfileItem__link__1vsjg"));
    private final SelenideElement loginForm = $(By.cssSelector("div[role=\"dialog\"]"));
    private final SelenideElement email = $(By.id("email"));
    private final SelenideElement password = $(By.id("password"));
    private final SelenideElement loginSubmitBtn = $(By.cssSelector("button[type=\"submit\"]"));
    private final SelenideElement productName = $(By.className("product-snippet_ProductSnippet__name__1ettdy"));
    private final SelenideElement productPrice = $(By.className("snow-price_SnowPrice__mainM__18x8np"));
    private final SelenideElement nothingFoundText = $(By.tagName("h1"));
    public static String productNameHP;
    public static String productPriceHP;

    public HomePage enterSearch(String text) {
        this.searchInput.sendKeys(text);
        this.searchInput.sendKeys(Keys.ENTER);
        return this;
    }

    public HomePage clickCartBtn() {
        cartBtn.click();
        return this;
    }

    public HomePage clickProduct() {
        productName.click();
        return this;
    }

    public HomePage clickLoginBtn() {
        loginBtn.click();
        return this;
    }

    public HomePage clickLoginSubmitBtn() {
        loginSubmitBtn.click();
        return this;
    }

    public HomePage checkNothingFoundText() {
        Assert.assertEquals(nothingFoundText.getText(), "Ничего не нашли");
        return this;
    }

    public HomePage checkLoginFormOpened() {
        loginForm.shouldBe(Condition.visible);
        loginSubmitBtn.shouldBe(Condition.disabled);
        return this;
    }

    public HomePage enterLoginInfo(String email, String password) {
        this.email.sendKeys(email);
        loginSubmitBtn.shouldBe(Condition.disabled);
        this.password.sendKeys(password);
        loginSubmitBtn.shouldBe(Condition.enabled);
        return this;
    }

    public HomePage getProductName() {
        productNameHP = productName.getText();
        return this;
    }

    public HomePage getProductPrice() {
        productPriceHP = productPrice.getText();
        return this;
    }
}