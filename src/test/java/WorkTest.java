import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;

public class WorkTest {
    public static String token=null;
    @BeforeClass
    public static void beforeClass(){
        RestAssured.useRelaxedHTTPSValidation();
    }
    @Before
    public void getToken() {
        //企业ID：wwd78e4e604f9002cb
        //Secret:kAL65OFp-oW7aeF9VYALW_L2LA8cqddykNMpsWf6SAw
        //AgentId:1000002
        token = given()
                .param("corpid", "wwd78e4e604f9002cb")
                .param("corpsecret", "kAL65OFp-oW7aeF9VYALW_L2LA8cqddykNMpsWf6SAw")
                .when().get("https://qyapi.weixin.qq.com/cgi-bin/gettoken").prettyPeek()
                .then()
                .statusCode(200).extract().path("access_token");
        System.out.println(token);
    }
    @Test
    public void sendMsg(){
        HashMap<String,String>content=new HashMap<String, String>();
        content.put("content","hello,你好");
        HashMap<String,Object> msg=new HashMap<String, Object>();
        msg.put("touser","ChenZeHeng");
        msg.put("msgtype","text");
        msg.put("agentid","1000002");
        msg.put("text",content);

        given().queryParam("access_token",token).contentType(ContentType.JSON).body(msg)
        .when().post("https://qyapi.weixin.qq.com/cgi-bin/message/send").prettyPeek()
        .then().statusCode(200);

    }

}
