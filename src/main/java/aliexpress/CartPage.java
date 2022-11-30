package aliexpress;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;

import static aliexpress.HomePage.productNameHP;
import static aliexpress.HomePage.productPriceHP;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CartPage {

    private final SelenideElement cartCount = $(By.className("Header_ShopCart__counter__1mrlq"));
    private final SelenideElement cartCount2 = $(By.className("snow-ali-kit_Tag__words__afvess"));
    private final SelenideElement selectAllProducts = $(By.cssSelector("svg[class*=\"Checkbox\"]"));
    private final SelenideElement selectAllProducts2 = $(
            By.cssSelector("span[class*=\"snow-ali-kit_Checkbox-default__checkbox__14zehn snow-ali-kit_Checkbox__checkbox__1b4u61\"]"));
    private final SelenideElement productName = $(By.className("ShoppingcartItemList_ProductCard__productNameLink__1nl31"));
    private final SelenideElement productName2 = $(By.cssSelector("div[class*=\"SnowShoppingcartProductList_ProductDescription__title__1q4gc\"]"));
    private final SelenideElement productPrice = $(By.className("ShoppingcartItemList_ProductPrice__mainCostPrice__t4iks"));
    private final SelenideElement productPrice2 = $(By.cssSelector("[class=SnowShoppingcartProductList_Product__productPriceDesktop__haas9]>div"));
    private final SelenideElement totalPrice = $(By.cssSelector("h4[class*=\"ShoppingcartOrderOverview_OrderTotal__largeTextSize__18zkw\"]"));
    private final SelenideElement totalPrice2 = $(By.tagName("h2"));
    private final SelenideElement deleteOkBtn = $(By.cssSelector("button[class*=\"ShoppingcartItemList_ControlActionGroup__btn__xpegy\"]"));
    private final ElementsCollection deleteOkBtn2 = $$(By.cssSelector("button[class*=\"snow-ali-kit_Button-Primary__button__acii72\"]"));
    private final SelenideElement emptyCartText = $(By.cssSelector("p[class*=\"ShoppingcartStates_CartState__heading__1ww0f\"]"));
    private final SelenideElement emptyCartText2 = $(By.cssSelector("h1[class*=\"SnowShoppingcartStates_EmptyPage__emptyMargin__2kupu\"]"));
    private final ElementsCollection deleteBtn = $$(By.className("ShoppingcartItemList_ControlActionGroup__actionIcon__xpegy"));
    private final SelenideElement deleteBtn2 = $(By.cssSelector("svg[class*=\"SnowShoppingcartHeader_Header__removeIcon__o27uc\"]"));
    private final SelenideElement loginBtn = $(By.cssSelector("button[class*=\"TopHeadV2_ProfileEntryBlock__button__156t9\"]"));
    private final SelenideElement myProfileBtn = $(By.cssSelector("a[href*=\"index.htm?tracelog=ws_topbar\"]"));
    private final SelenideElement maxAmountTooltip = $(By.cssSelector("div[data-type=\"tooltip\"]"));
    private final SelenideElement amountInput = $(By.cssSelector("input[class=\"ShoppingcartItemList_NumActionGroup__numInput__ehpij\"]"));
    private final SelenideElement amountInput2 = $(By.cssSelector("div[class=\"SnowShoppingcartProductList_Product__productNumAction__haas9\"]"));
    private final ElementsCollection addOneMoreBtn = $$(By.cssSelector("button[class*=\"ShoppingcartItemList_NumActionGroup__numBtn__ehpij\"]"));
    private final ElementsCollection addOneMoreBtn2 = $$(By.cssSelector("button[class*=\"SnowShoppingcartProductList_ProductNumAction__button__1yo4e\"]"));

    public CartPage clickSelectAllProducts() {
        if (selectAllProducts.exists()) {
            selectAllProducts.click();
            $(By.cssSelector("div[class*=\"Checkbox__checked\"]")).shouldBe(Condition.visible);
            Assert.assertEquals(totalPrice.getText(), productPrice.getText());
        } else {
            selectAllProducts2.click();
            $(By.cssSelector("svg[class*=\"Checkbox\"]")).shouldBe(Condition.visible);
            $(By.cssSelector("div[class*=\"snow-ali-kit_Typography__sizeTextM__1shggo\"]")).shouldHave(Condition.text("1 товар"));
            Assert.assertEquals(totalPrice2.getText(), productPrice2.getText());
        }
        return this;
    }

    public CartPage clickLoginBtn() {
        myProfileBtn.hover();
        loginBtn.shouldBe(Condition.visible);
        loginBtn.click();
        return this;
    }

    public CartPage checkMaxAmountOfProduct() {
        if (deleteBtn.get(1).exists()) {
            amountInput.sendKeys("301");
            Assert.assertEquals(amountInput.getValue(), "300");
            Assert.assertFalse(addOneMoreBtn.get(1).isEnabled());
            addOneMoreBtn.get(1).click();
            Assert.assertEquals(maxAmountTooltip.getText(), "Количество ограничено");
        } else {
            while (addOneMoreBtn2.get(1).isEnabled()) {
                addOneMoreBtn2.get(1).click();
            }
            Assert.assertEquals(amountInput2.getText(), "50");
        }
        return this;
    }

    public CartPage checkMinAmountOfProduct() {
        if (deleteBtn.get(1).exists()) {
            amountInput.sendKeys(Keys.chord(Keys.COMMAND + "a"));
            amountInput.sendKeys("1");
            amountInput.sendKeys(Keys.chord(Keys.COMMAND + "a"));
            amountInput.sendKeys("0");
            Assert.assertEquals(amountInput.getValue(), "1");
        }
        return this;
    }

    public CartPage clickDeleteBtn() {
        if (deleteBtn.get(1).exists()) {
            deleteBtn.get(1).click();
            deleteOkBtn.click();
            deleteBtn.get(1).should(Condition.not(Condition.visible));
        } else {
            deleteBtn2.click();
            deleteOkBtn2.get(1).click();
            deleteBtn2.should(Condition.not(Condition.visible));
        }
        return this;
    }

    public CartPage checkFullCartCount() {
        if (cartCount.exists()) {
            Assert.assertEquals(cartCount.getText(), "1");
        } else {
            Assert.assertEquals(cartCount2.getText(), "1");
        }
        return this;
    }

    public CartPage checkEmptyCartCount() {
        if (cartCount.exists()) {
            Assert.assertEquals(cartCount.getText(), "0");
        } else {
            Assert.assertEquals(cartCount2.getText(), "0");
        }
        return this;
    }

    public CartPage checkEmptyCartText() {
        if (emptyCartText.exists()) {
            Assert.assertEquals(emptyCartText.getText(), "Ваша Корзина пуста");
        } else {
            Assert.assertEquals(emptyCartText2.getText(), "Ой, пусто!");
        }
        return this;
    }

    public CartPage checkProductInformation() {
        if (productName.exists()) {
            Assert.assertEquals(productName.getText(), productNameHP);
            Assert.assertEquals(productPrice.getText(), productPriceHP);
        } else {
            Assert.assertEquals(productName2.getText(), productNameHP);
            Assert.assertEquals(productPrice2.getText(), productPriceHP);
        }
        return this;
    }

    public CartPage checkTotalPriceEmpty() {
        if (totalPrice.exists()) {
            Assert.assertEquals(totalPrice.getText(), "0,00 руб.");
        } else {
            totalPrice2.should(Condition.exist);
            Assert.assertEquals(totalPrice2.getText(), "0,00 руб.");
        }
        return this;
    }
}