import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC002_POST_Request {

	@Test
	void RegistrationSuccessful() {
		
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/customer";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		
		requestParams.put("FirstName", "JohnXYZ");
		requestParams.put("LastName", "XYZJojn");
		requestParams.put("UserName", "JohnXYZ");
		requestParams.put("Password", "JohnXYZxyx");
		requestParams.put("Email", "JohnXYZ@gmail.com");
		
		
		httpRequest.header("Content-Type","application/json");
		
		httpRequest.body(requestParams.toJSONString()); // attach data to the request
		
		Response response = httpRequest.request(Method.POST, "/register");
		
		String responseBody = response.getBody().asString();
		System.out.println("Response body is:" +responseBody);
		
		int statusCode = response.getStatusCode();
		System.out.println("Status code is:" + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		String statusLine = response.getStatusLine();
		System.out.println("Status line is:" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		String successCode = response.jsonPath().get("SuccessCode");
		Assert.assertEquals(successCode, "OPERATION_SUCCESS");
		
	}
}
