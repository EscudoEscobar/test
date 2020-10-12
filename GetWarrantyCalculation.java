package warranty;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import tokens.dev.DevB2CUserAccessToken;
import io.restassured.http.ContentType;
import static constants.ServerConfiguration.DEV_Server;
import static org.hamcrest.Matchers.hasKey;


public class GetWarrantyCalculation {
    @Test
    @DisplayName("Get warranty calculation /api/warranty/car-calculation/{advertisedCar}")
    public void GetWarrantyCalculation() {
        RestAssured.given()
                .headers(
                        "Authorization",
                        "Bearer " + DevB2CUserAccessToken.userCanGetB2CUserAccessToken(),
                        "Content-Type",
                        ContentType.JSON,
                        "Accept",
                        ContentType.JSON)
                .baseUri(DEV_Server)
                .basePath("/api/warranty/car-calculation/{advertisedCar}")
                .pathParam("advertisedCar", 1467352)
                .queryParam("period_months", 6)
                .queryParam("mileage_limit", 20000)
                .contentType(ContentType.JSON)
                .when().get()
                .then()
                .statusCode(200)
                .body("[0]", hasKey("id"))
                .body("[0]", hasKey("name"))
                .body("[0]", hasKey("price"))
                .body("[0]", hasKey("price_without_vat"))
                .body("[0]", hasKey("original_price"))
                .body("[0]", hasKey("original_price_without_vat"))
                .body("[0]", hasKey("currency"))
                .body("[0].currency", hasKey("id"))
                .body("[0].currency", hasKey("name"))
                .body("[0].currency", hasKey("const_key"));
    }
}
