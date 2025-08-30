package test;
import org.testng.Assert;

import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//gp tp https://jsoneditoronline.org/ and paste the json body to understand better
		//below is the json response
		
		/* {

"dashboard": {

"purchaseAmount": 910,

"website": "rahulshettyacademy.com"

},

"courses": [

{

"title": "Selenium Python",

"price": 50,

"copies": 6

},

{

"title": "Cypress",

"price": 40,

"copies": 4

},

{

"title": "RPA",

"price": 45,

"copies": 10

}

]

}


1. Print No of courses returned by API

2.Print Purchase Amount

3. Print Title of the first course

4. Print All course titles and their respective Prices

5. Print no of copies sold by RPA Course

6. Verify if Sum of all Course prices matches with Purchase Amount
*/
		JsonPath js=new JsonPath(payload.CoursePrice());
	
	//print no of courses returned by API . 
		//no of courses=size of array
	//1
		int CoursesCount= js.getInt("courses.size()");
		
		System.out.println(CoursesCount);
		
	//2	
		int purchaseAmount =js.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		
	//3
		String firstTitle=js.get("courses[0].title");
		System.out.println(firstTitle);
		
	//4
		for(int i=0;i<CoursesCount;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			System.out.println(js.get("courses["+i+"].price").toString()); //sysout expects string so we can print this way
			System.out.println(courseTitles);
		}
		
		//5
		
		for(int i=0;i<CoursesCount;i++)
		{
			String courseTitles=js.get("courses["+i+"].title");
			if(courseTitles.equalsIgnoreCase("RPA"))
			{
				int copies=js.get("courses["+i+"].copies");
				//System.out.println(copies);
				System.out.println(copies);
				break;
			}
		}
		
		//6
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
