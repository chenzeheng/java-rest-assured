import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class TestBaiduTest {
    @Test
    public void baiduDemo(){
        useRelaxedHTTPSValidation();
        given()
                .log().all()
                .param("wd","霍格沃兹 测试学院")
                .param("ie","utf-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36" +
                        " (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
                .get("https://www.baidu.com/s")
                .then()
                .log().all()
                .statusCode(200);
    }
}
