import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class TestXueqiuTest {
    @Test
    public void postXueQiu(){
        given().log().all()
                .param("username","996845913@qq.com")
                .param("password","123456")
                .param("remember_me","false")
                .param("captcha","")
                .param("geetest_challenge","c86051a7316a65884dcb5267f9b2c80491")
                .param("geetest_validate","b3ada53ebe806aaa6612aa2c88c89841")
                .param("geetest_seccode","b3ada53ebe806aaa6612aa2c88c89841%7Cjordan")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                        "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
                .when()
                .post("https://xueqiu.com/snb/provider/login")
                .then().log().all()
                .statusCode(200);
    }
}
