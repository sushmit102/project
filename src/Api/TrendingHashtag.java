package Api;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
//import files.Payload;
import java.io.FileInputStream;
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

public class TrendingHashtag {
	
	Properties  prop;
	public static Logger l=Logger.getLogger("TrendingHashtag");
	
	@Test
	
	public   void  hashtags() throws IOException
    {
        
        prop=new Properties();
        PropertyConfigurator.configure("C:\\\\Users\\\\Online Test\\\\git\\\\Test\\\\APIAUTOMATION\\\\Log4j.properties;");
        FileInputStream fis=new FileInputStream("C:\\Users\\Online Test\\git\\Test\\APIAUTOMATION\\src\\files\\data.properties");
        prop.load(fis);
        RestAssured.baseURI="https://api.twitter.com/1.1/trends";
        Response res = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
                
                when().get("/available.json").then().extract().response();
        String response=res.asString();
        System.out.println(response);
        

 

    JsonPath js=new JsonPath(response);
    l.info(response);
    int count=js.get("size()");
    for(int i=0;i<count;i++)
    {
        String country=js.get("["+i+"].country").toString();
        if(country.equalsIgnoreCase("United Kingdom"))
        {String id=js.get("["+i+"].parentid").toString();
        Response res1 = given().auth().oauth(prop.getProperty("ConsumerKey"),prop.getProperty("ConsumerSecret"), prop.getProperty("Token"), prop.getProperty("TokenSecret")).
                param("id",id)
                .when().get("/place.json").then().assertThat().statusCode(200).contentType(ContentType.JSON).extract().response();
        String response1=res1.asString();
        JsonPath js1=new JsonPath(response1);
        l.info(response1);
        int count1=js1.get("["+0+"].trends.size()");
        for(int j=0;j<count1;j++)
        {
            String hash=js1.getString("["+0+"].trends["+j+"].name");
            String hash1=js1.getString("["+0+"].trends["+j+"]").toString();
            if(hash.charAt(0)=='#' && j<=5)
            {System.out.println(hash1);
            l.info(hash1);
            }
            }
            break;
        }
        
    }
    }
}