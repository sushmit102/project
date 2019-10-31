import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import files.Payload;
//import files.resources;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class Twitter {
	
	String ConsumerKey = "2Xtd4ILUtinfACHehAvCNcbjW";
	String ConsumerSecret = "pIHDmJy9fgKlLVuAgG4AuP5rRkaOy6TtWqxLqjVeJ2MywdcGJp";
	String Token = "839104214-h1Kaf8iJW2FAyXzNCZNicI1a4Xb8BMw7ZkFKGzmJ";
	String TokenSecret = "PTvXawkmrK3EZWeLSSmqtD7hisogRC3nqG48FYzNcLfQy";
	
@Test

public void getTweet() {
	
	RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
	Response res= given().auth().oauth(ConsumerKey ,ConsumerSecret,Token,TokenSecret).
			queryParam("count","2")
			.when().get("/home_timeline.json").then().
			extract().response();
	
	String response=res.asString();
	System.out.println(response);
	JsonPath js=new JsonPath(response);
	String id=js.get("id").toString();
	System.out.println(id);
	String text=js.get("text").toString();
	System.out.println(text);
}
	

}
