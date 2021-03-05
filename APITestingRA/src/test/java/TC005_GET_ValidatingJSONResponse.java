import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_GET_ValidatingJSONResponse {
	
	@Test
	public void GetWeatherDetails() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET,"/Delhi");

		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);
		
		Assert.assertEquals(responseBody.contains("Delhi"), true);

	}
}
