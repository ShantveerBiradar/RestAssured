package Shantveer;

import io.restassured.RestAssured; 
import static io.restassured.RestAssured.given;
import org.testng.Assert;
import io.restassured.path.xml.*;

public class SoapAPI_Reference {

public static void main(String[] args) {
		RestAssured.baseURI="https://www.dataaccess.com/";
		
		//Declare the request body
		String RequestBody="<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n"
				+ "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n"
				+ "  <soap:Body>\r\n"
				+ "    <NumberToWords xmlns=\"http://www.dataaccess.com/webservicesserver/\">\r\n"
				+ "      <ubiNum>100</ubiNum>\r\n"
				+ "    </NumberToWords>\r\n"
				+ "  </soap:Body>\r\n"
				+ "</soap:Envelope>\r\n" 
				+ "";
		System.out.println(RequestBody);
		//Extract the response body
		String ResponseBody=given().header("Content-Type","text/xml; charset=utf-8").body(RequestBody).when().
				post("webservicesserver/NumberConversion.wso").then().extract().response().asString();
		System.out.println(ResponseBody);
		//Parse the response body
		XmlPath XmlResponse=new XmlPath(ResponseBody);
		String ResponseParameter=XmlResponse.getString("NumberToWordsResult");
		System.out.println(ResponseParameter);
		Assert.assertEquals(ResponseParameter,"one hundred ");
	}

	}