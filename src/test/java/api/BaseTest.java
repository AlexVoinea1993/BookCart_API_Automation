package api;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BaseTest {

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://demoqa.com";
    }

    @Test
    public void userTest() {
        given().body("{\n" +
                        "  \"userName\": \"alex1997\",\n" +
                        "  \"password\": \"Test123*\"\n" +
                        "}")
                .when()
                .patch("/Account/v1/User")
                .then()
                .statusCode(404);
    }

    @Test
    public void authorizedTest() {
        given().body("{\n" +
                        "  \"userName\": \"alex1997\",\n" +
                        "  \"password\": \"Test123*\"\n" +
                        "}")
                .when()
                .patch("/Account/v1/Authorized")
                .then()
                .statusCode(404);
    }

    @Test
    public void generateTokenTest() {
        given().body("{\n" +
                        "  \"userName\": \"alex1997\",\n" +
                        "  \"password\": \"Test123*\"\n" +
                        "}")
                .when()
                .patch("/Account/v1/GenerateToken")
                .then()
                .statusCode(404);
    }

    @Test
    public void getUserAccountTest() {
        RestAssured.get("/Account/v1/User/:UserId")
                .then()
                .statusCode(401)
                .log()
                .all();
    }

    @Test
    public void deleteAccountUserTest() {
        RestAssured.delete("/Account/v1/User/:UserId")
                .then()
                .statusCode(401)
                .log()
                .all();
    }

    @Test
    public void getBooksTest() {
        RestAssured.get("/BookStore/v1/Books")
                .then()
                .statusCode(200)
                .log()
                .all();
    }

    @Test
    public void postBooksTest() {
        given().body("""
                        {
                          "userId": "c554146a-b3a0-4f7c-b1fc-7861e371c7ee",
                          "collectionOfIsbns": [
                            {
                              "isbn": "9781491904244"
                            }
                          ]
                        }
                        """)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .log().all();
    }
}
