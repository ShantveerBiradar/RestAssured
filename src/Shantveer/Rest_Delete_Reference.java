package Shantveer;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class Rest_Delete_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		//Declare the request body string variable
		
		//Declare the expected results
		
		String ResponseBody = given().header("Content-Type","application/json").body("RequestBody").
				when().delete("api/users/2").then().extract().response().asString();
		System.out.println(ResponseBody);
	}

}
