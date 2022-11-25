package aliexpress;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class ApiTest {

    @BeforeTest
    public void precondition() {
        baseURI = "https://aliexpress.ru";
    }

    @Test
    public void post_checkFreeShippingFilter_test() {
        String body = "{\"catId\":\"202000005\",\"g\":\"n\",\"storeIds\":[],\"brandValueIds\":\"\",\"pvid\":\"\",\"isBigSale\":\"n\",\"isFreeShip\":\"y\"," +
                "\"isFavorite\":\"n\",\"page\":1,\"searchInfo\":\"searchId:0\"}";
        Response response = given().header("Content-Type", "application/json").body(body).post("/aer-webapi/v1/search?_bx-v=2.2.3");
        response.then().assertThat().statusCode(200);
        for (int i = 0; i<20; i++){
            Assert.assertEquals(response.then().extract().response().jsonPath().getString("data.productsFeed.products["+i+"].freeDelivery"), "true");
        }
    }

    @Test
    public void get_checkCartCount_test() {
        Response response = given().header("Content-Type", "application/json").get("/aer-jsonapi/v1/web/cart/count");
        response.then().assertThat().statusCode(200);
            Assert.assertEquals(response.then().extract().response().jsonPath().getString("data.data.count"), "0");
    }
}
