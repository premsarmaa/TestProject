package test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class UpdatePlace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// validate if Add Place API is working as expected
				
				//given- all input details
				//when - submit the API -resource,hhtp method
				//then - validate response
				
				//keep on using body, headers ,etc in given which will serve as input
				
				RestAssured.baseURI="https://rahulshettyacademy.com";
				String response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.AddPlace())
				.when().post("maps/api/place/add/json")
				.then().assertThat().statusCode(200).body("scope", equalTo("APP"))
				.header("Server", "Apache/2.4.52 (Ubuntu)").extract().asString();
				
				//System.out.println(response); // will print the response without logging
				JsonPath js=new JsonPath(response); // for parsing JSON
				String placeId=js.getString("place_id"); // we will use this placeId to update later
				//System.out.println("place id is "+placeId);
				
				
				//UpdatePlace
				
				given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
				.body(payload.UpdatePlace(placeId))
				.when().put("maps/api/place/update/json")
				.then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
}

}
