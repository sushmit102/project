package Api;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import files.Payload;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Displayusername 
{
	Properties  prop;
	public static Logger l=Logger.getLogger("Displayusername");
	@Test
	public void userNameDisplay() throws IOException
	{
		prop =new Properties();
		PropertyConfigurator.configure("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\Log4j.properties;");
		FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\src\\files\\data.properties");
		prop.load(fis);
		RestAssured.baseURI="https://api.twitter.com/1.1/search/";
		Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
		param("q","Qualitest").
		when().get("/tweets.json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).
		extract().response();
		
		String response = res.asString();
		//System.out.println("Response = "+response);
		
		JsonPath js = new JsonPath(response);
		int count = js.get("statuses.size()");
		System.out.println(count);
		l.info(count);
		for(int i=0;i<count;i++)
		{
			String userName = js.get("statuses["+i+"].user.screen_name");
			System.out.println("Name of the user - "+userName);
			
		}
		
		
	}
}
