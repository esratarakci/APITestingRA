package datadriventesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_AddNewEmployess {

	@Test(dataProvider = "empdataprovider")
	void postNewEmployees(String ename, String eage, String esal) {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("name", ename);
		requestParams.put("salary", esal);
		requestParams.put("age", eage);

		httpRequest.header("Content-Type", "application/json");

		httpRequest.body(requestParams.toJSONString());

		Response response = httpRequest.request(Method.POST, "/create");

		String responseBody = response.getBody().asString();
		System.out.println("response body:" + responseBody);

		Assert.assertEquals(responseBody.contains(ename), true);
		Assert.assertEquals(responseBody.contains(eage), true);
		Assert.assertEquals(responseBody.contains(esal), true);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@DataProvider(name = "empdataprovider")
	String[][] getEmpData() throws IOException {

		// read data from excel
		
		String path = System.getProperty("user.dir") + "/src/test/java/datadriventesting/empdata.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);

		String empData[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				empData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
			}
		}

		// String empData[][] = { {"abc123", "1236", "40"}, {"xyz123", "4000", "33"} , {
		// "pqr123", "5000", "26"}};

		return (empData);
	}
}
