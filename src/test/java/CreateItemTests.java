import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;

public class CreateItemTests {
    @Test
    public void test_createItem() {
        String json = "{"+
                "'name':'QWerty',"+
                "'section':'Платья',"+
                "'description':'Модное платье из новое коллекции!'"+
                "}";

        json = json.replace("'","\"");
        RestAssured.given()
                .baseUri("http://shop.bugred.ru/api/")
                .basePath("items/create/")
                .contentType(ContentType.JSON)
                .body(json)

                .when()

                .post()
                .then()
                    .assertThat().statusCode(200)
                    .and()
                    .assertThat().body("result.name",equalTo("QWerty"))

                .extract()
                .response()
                .prettyPrint();
    }
    @Test
    public void test_createError() {
        String json = "{"+
                "'name':'',"+
                "'section':'Платья',"+
                "'description':'Модное платье из новое коллекции!'"+
                "}";

        json = json.replace("'","\"");
        RestAssured.given()
                .baseUri("http://shop.bugred.ru/api/")
                .basePath("items/create/")
                .contentType(ContentType.JSON)
                .body(json)

                .when()

                .post()
                .then()
                .assertThat().statusCode(200)
                .and()
                .assertThat().body("status",equalTo("error"))
                .and()
                .assertThat().body("message",equalTo("Название товара не заполнено!"))

                .extract()
                .response()
                .prettyPrint();



    }
}
