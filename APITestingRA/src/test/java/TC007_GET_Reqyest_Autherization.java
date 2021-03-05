import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC007_GET_Reqyest_Autherization {

	@Test
	public void Autherization() {
		// Specify base URI
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		//Basic Authentication
		PreemptiveBasicAuthScheme authscheme = new PreemptiveBasicAuthScheme();
		authscheme.setUserName("ToolsQA");
		authscheme.setPassword("TestPassword");
		
		RestAssured.authentication=authscheme;
		
		
		// Request Object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/");
		
		int statusCode = response.getStatusCode();
		System.out.println("status code:" + "statusCode");
		Assert.assertEquals(statusCode, 200);
		
		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);


	}
}
