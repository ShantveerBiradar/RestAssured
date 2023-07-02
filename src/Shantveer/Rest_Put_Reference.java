package Shantveer;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import java.time.LocalDateTime;
import org.testng.Assert;
import io.restassured.path.json.JsonPath;
public class Rest_Put_Reference {

	public static void main(String[] args) {
		// Declare the base URL
		RestAssured.baseURI = "https://reqres.in/";

		//Declare the request body string variable
		String RequestBody = "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"zion resident\"\r\n"
				+ "}";
		//Declare the expected results
		JsonPath JspRequest = new JsonPath(RequestBody);
		String Req_name = JspRequest.getString("name");
		String Req_job = JspRequest.getString("job");
		LocalDateTime currentdate = LocalDateTime.now();
		String expecteddate=currentdate.toString().substring(0, 11);
		//Declare given, when then method (Response Body fetching)
		//String ResponseBody = given().header("Content-Type","application/json").body(RequestBody).log().all().
				//when().post("api/users").then().log().all().extract().response().asString();
		String ResponseBody = given().header("Content-Type","application/json").body(RequestBody).
				when().put("api/users/2").then().extract().response().asString();
		System.out.println(ResponseBody);
		//Create an object of JSON path to parse the response body
		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_updatedAt = JspResponse.getString("updatedAt");
		Res_updatedAt = Res_updatedAt.substring(0,11);
		//Validate the ResponseBody parameters
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_updatedAt, expecteddate);
	}

}
