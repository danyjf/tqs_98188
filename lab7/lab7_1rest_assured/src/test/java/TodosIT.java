import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TodosIT {
    private String API_URL = "https://jsonplaceholder.typicode.com/todos";

    @Test
    public void whenGetAllTodos_thenCode200() {
        given().when().get(API_URL).then().statusCode(200);
    }

    @Test
    public void whenGetTodo4_thenReturnObjectWithCorrectTitle() {
        given().when().get(API_URL + "/4").then().body("title", equalTo("et porro tempora"));
    }

    @Test
    public void whenGetAllTodos_thenId198AndId199AreInResults() {
        given().when().get(API_URL).then().body("id", hasItems(198, 199));
    }
}
