
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.xml.fastinfoset.sax.Properties;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class third {
/*	//Properties prop =new Properties();
	//@BeforeTest
	//public void getData() throws FileNotFoundException {
		
	//	 System.getProperty("HOST");
			
	//	FileInputStream fis=new FileInputStream("C:\\New folder\\APIAUTOMATION\\src\\files\\data.properties");
	//prop. (fis);
	}*/
	@Test(dataProvider="NameData")
	
	public void APITEST1(String fname,String lname){
		RestAssured.baseURI="https://reqres.in/";
		Response res=given().
		given().
		param("page","2").
		  
		body(Payload.getPayload(fname, lname)).
		when().
		  get("api/users").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String responseString=res.asString();
		System.out.println(responseString);
		JsonPath js=new JsonPath(responseString);
		String finame=js.getString("data[0].first_name");
		System.out.println(fname);
		
		
		//step3
		Response res1=given().
		given().
		body("{\r\n" + 
				"    \"name\": \""+fname+",\r\n" + 
				"    \"job\": \"zion resident\"\r\n" + 
				"}").
		when().
		put("api/users/2").
		then().
		assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		String responseString1=res1.asString();
		System.out.println(responseString1);
		
	}
@DataProvider(name="NameData")
public Object[][] getvalues() {
	return new Object[][] {{"shj","dsd"},{"ds","sd"},{"ndk","sd"}};
	
}

	
	

}
