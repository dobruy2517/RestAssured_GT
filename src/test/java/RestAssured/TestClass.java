package RestAssured;

import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;
import org.json.*;


import java.io.IOException;
import java.io.OutputStream;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class TestClass {
    private final String BASE_URI = "https://www.mybookingpal.com";

    @Before
    public void precondition() {
        RestAssured.baseURI = BASE_URI;
    }

    @Test
    public void getUserPostTest() throws IOException {
        String expectedLocation = "Paris, France";
        String expecteCheckInDate = "01/09/2017";
        ProductPage expecedProduct = new ProductPage(expectedLocation, expecteCheckInDate);

        OutputStream os = new OutputStream() {
            StringBuilder sb = new StringBuilder();

            @Override
            public String toString() {
                return sb.toString();
            }

            public void write(int b) throws IOException {
                sb.append((char) b);
            }
        };

        Response response = given().
                when().
                get("/xml/services/json/reservation/products/21980/2017-01-20/2017-02-20?pos=a502d2c65c2f7" +
                        "5d3&guests=2&amenity=true&currency=USD&exec_match=false&display_inquire_only=false");

        assertEquals(200, response.getStatusCode());

        JSONArray respJsonArray = new JSONArray(response.asString());
        System.out.println(respJsonArray);
//        JSONObject respJsonObject = respJsonArray.getJSONObject(0);
//        ProductPage actualProduct = new ObjectMapper().readValue(respJsonObject.toString(), ProductPage.class);
//        assertEquals(expecedProduct, actualProduct);
    }
}
