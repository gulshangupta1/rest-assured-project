import io.restassured.http.ContentType;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestOne {
    @Test
    public void shouldGetAllUsers() {
        // Arrange
        // Act
        // Assert

        given()
                .when()
                    .get("https://gorest.co.in/public/v2/users")
                .then()
                    .statusCode(200)
                    .body("", Matchers.hasSize(20))
                    .body("", Matchers.hasItem(Matchers.hasEntry("gender", "male")))
                    .log().body();

        Assert.assertEquals(1, 1);
    }

    @Test
    public void shouldCreateUser() {
        // Arrange
        // Act
        // Assert

        given()
                .accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer 457a143dbd57bef631313eb0e02b0777a8d268c3ae52c43ea34aa4581d7e0ed5")
                .body("{\n" +
                        "    \"name\": \"Tenali R\",\n" +
                        "    \"gender\": \"male\",\n" +
                        "    \"email\": \"tenalir19@gmail.com\",\n" +
                        "    \"status\": \"active\"\n" +
                        "}")
                .when()
                    .post("https://gorest.co.in/public/v2/users")
                .then()
                    .log().body()
                    .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("email", Matchers.equalTo("tenalir19@gmail.com"))
                .body("name", Matchers.equalTo("Tenali R"));;
    }
}
