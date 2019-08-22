import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TesthomeDemo {
    @Test
    public  void extract(){
        HashMap<String,Object> topic=given().log().all()
                .when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then().log().all().statusCode(200)
                .extract().path("topics.find{it.title.contains(\"测试\")}");
        System.out.println(topic);

        String login=given().log().all().when()
                .get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then().log().all().statusCode(200)
                .extract().path("topics.find{it.title.contains(\"测试\")}.user.login");
        System.out.println(login);





    }
    @Test
    public void extract2(){
        ValidatableResponse validatableResponse=given().log().all()
                .when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                .then().log().all().statusCode(200);
        HashMap<String,Object> topic=validatableResponse.extract().path("topics.find{it.title.contains(\"测试\")}");
        String login=validatableResponse.extract().path("topics.find{it.title.contains(\"测试\")}.user.login");
        System.out.println(topic);
        System.out.println(login);

    }
    //最经常使用的方式
    @Test
    public void extract3(){
        Response response=given().log().all()
                 .when().get("https://testerhome.com/api/v3/topics.json").prettyPeek()
                 .then().log().all().statusCode(200)
                 .extract().response();

        HashMap<String,Object> topic=response.path("topics.find{it.title.contains(\"测试\")}");
        String login=response.path("topics.find{it.title.contains(\"测试\")}.user.login");
        System.out.println(topic);
        System.out.println(login);

    }
}



