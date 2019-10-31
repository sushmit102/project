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

	public class Posttweet {
		
		Properties  prop;
		public static Logger l=Logger.getLogger("Posttweet");
	@Test

	public void postTweet() throws IOException {
		
		prop =new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\Log4j.properties;");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\src\\files\\data.properties");
		prop.load(fis);
		
		RestAssured.baseURI="https://api.twitter.com/1.1/statuses/";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
				queryParam("status","API testing")
				.when().post("/update.json").then().
				extract().response();
		
		String response=res.asString();
		System.out.println(response);
		
		JsonPath js=new JsonPath(response);
		l.info(response);
		
		String id=js.get("id").toString();
		System.out.println(id);
		l.info(id);
		
		String text=js.get("text").toString();
		System.out.println(text);
		l.info(text);
	}
		

	}



