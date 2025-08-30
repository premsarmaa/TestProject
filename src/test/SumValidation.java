package test;
import org.testng.Assert;
import org.testng.annotations.Test;

import files.payload;
import io.restassured.path.json.JsonPath;

public class SumValidation {

	//instead of using public static void main , we will use testNG to run this
	@Test
	public void sumOfCourses() {
		
		JsonPath js=new JsonPath(payload.CoursePrice());
		int CoursesCount= js.getInt("courses.size()");
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		int sum=0;
		for(int i=0;i<CoursesCount;i++)
		{
			
			int price=js.get("courses["+i+"].price");
			int copies=js.get("courses["+i+"].copies");
			sum+=+price*copies;
		}
		System.out.println(sum);
		Assert.assertEquals(sum, purchaseAmount);
	}
}
