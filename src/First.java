import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Test
public class First {



	public void APITEST() {

		
		RestAssured.baseURI="https://reqres.in/"; 
		Response res=(Response) given().
				
		  param("page","2").log().all().
		  
		  when().
		  get("api/users").
		  then().assertThat().log().body().
		  statusCode(200).and().contentType(ContentType.JSON).and().
		  //body("data[0].first_name",equalTo("Michael")).and().
		  //body("data[1].avatar",equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/araa3185/128.jpg")).and().
		  //body("page",equalTo(2)).and().header("Server","cloudflare" ).
		  extract().response();
		  
		String responseString=res.asString();
		System.out.println(responseString);
		JsonPath js=new JsonPath(responseString);
		String fname=js.getString("data[0].first_name");
		System.out.println(fname);
		int count=js.getInt("data.size()");
		System.out.println(count);
		 for(int i=0;i<count;i++) {
			 String First_name= js.get("data["+i+"].first_name");
			 System.out.println(First_name);
			 
		 }
	}

	

	

}
