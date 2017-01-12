package RestAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.json.*;


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TestClass {
    private final String BASE_URI = "https://www.mybookingpal.com";

    @Before
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void productTest() throws IOException, ParseException {

        Response response = given().
                when().
                get("/xml/services/json/reservation/products/21980/2017-01-16/2017-01-23?" +
                        "pos=a502d2c65c2f75d3&guests=2&amenity=true&currency=USD&exec_match=false&display_inquire_only=false");

        assertEquals(200, response.getStatusCode());

        JSONObject respJsonArray = new JSONObject(response.asString());
        System.out.println(respJsonArray);
        JSONArray arr = respJsonArray.getJSONObject("search_response").getJSONObject("search_quotes")
                .getJSONArray("quote");
        System.out.println(arr);
        String expectedProductName = "TT12";
        for (int i = 0; i < arr.length(); i++) {
            String prodName = arr.getJSONObject(i).get("productname").toString();
            if (expectedProductName.equals(prodName)) {
                System.out.println("Product is detected " + prodName);
                String getProductId = arr.getJSONObject(i).get("productid").toString();
                System.out.println("Product id is " + getProductId);
                String checkInDate = arr.getJSONObject(i).get("checkin").toString();
                SimpleDateFormat format1 = new SimpleDateFormat("yyyy-mm-dd");
                Date date = format1.parse(checkInDate);
                DateFormat format2 = new SimpleDateFormat("EEEE");
                String dayOfWeek = format2.format(date);
                Assert.assertEquals("Monday", dayOfWeek);
                if (dayOfWeek.equals("Monday")) {
                    String getQuote = arr.getJSONObject(i).get("quote").toString();
                    String getPrice = arr.getJSONObject(i).get("price").toString();
                    Assert.assertEquals(getQuote, getPrice);
                }
            }
        }
    }
}
