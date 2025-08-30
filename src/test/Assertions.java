package test;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import files.payload;

public class Assertions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
// validate if Add Place API is working as expected
		
		//given- all input details
		//when - submit the API -resource,hhtp method
		//then - validate response
		
		//keep on using body, headers ,etc in given which will serve as input
		
		RestAssured.baseURI="https://rahulshettyacademy.com";
		given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace())
		.when().post("maps/api/place/add/json?key =qaclick123")
		.then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.52 (Ubuntu)");
		//keep on using body, headers ,etc in then which will serve as assertions
	}

}
