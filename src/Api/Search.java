package Api;

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
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class  Search {
	
	Properties  prop;
	public static Logger l=Logger.getLogger("Search");
	
@Test

public void searchTweet() throws IOException {
	prop =new Properties();
	PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\Log4j.properties;");
	FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\src\\files\\data.properties");
	prop.load(fis);
	
	RestAssured.baseURI="https://api.twitter.com/1.1/search/";
	Response res= given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
			queryParam("q","Qualitest")
			.when().get("/tweets.json").then().
			extract().response();
	
	String response=res.asString();
	System.out.println(response);
	l.info(response);
	/*JsonPath js=new JsonPath(response);
	String id=js.get("id").toString();
	System.out.println(id);
	String text=js.get("text").toString();
	System.out.println(text);*/
}
	

}





