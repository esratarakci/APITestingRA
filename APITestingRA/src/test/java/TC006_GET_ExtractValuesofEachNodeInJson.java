
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC006_GET_ExtractValuesofEachNodeInJson {


	@Test
	public void GetWeatherDetails() {

		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET,"/Delhi");

		JsonPath jsonpath = response.jsonPath();
		
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		
		Assert.assertEquals(jsonpath.get("Temperature"), "39 Degree celsius");



	}
}
