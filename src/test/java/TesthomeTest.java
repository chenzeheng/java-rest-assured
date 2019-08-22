import org.junit.Test;
import sun.reflect.generics.tree.VoidDescriptor;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class TesthomeTest {
    @Test
    public void topics(){
        useRelaxedHTTPSValidation();
        get("https://testerhome.com/api/v3/topics.json")
                .then()
                .body("topics[0].title",containsString("持续交付"));
    }
    @Test
    public void getDemo(){
        given().
                log().all()
                .param("wd","mp3")
                .param("ie","utf-8")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64)" +
                        " AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36")
                .get("https://www.baidu.com/s")
                .then().log().all()
                .statusCode(200)
                .body("html.head.title",equalTo("mp3_百度搜索"))
                .body("**.find{it.@class=='nums_text'}",equalTo("百度为您找到相关结果约70,500,000个"));
                //.body(hasXPath("//*[@class='nums_text' and contains(text(), '百度为您找到相关结果约70,500,000个')]"));
    }
    @Test
    public void  postDeom(){
        given().body("username=18521538561&" +
                "password=naOTokIafWTWc5OukLOj9nYykpDkmI%2FIjR9iogXfa3jf8lAtr1DMDFEqhMIwxgOlxYwD34svWdBQsCI1u2pHmLdyDlP0w" +
                "%2FdAQEPWA5iJS4PRU2nYUsKZW9%2B0r1dt21wXvDNcnHEVODT6NorIOR3UvESND6qZzXWnUDnyzKOO9uM%3D")
                .when().post("https://passport.baidu.com/v2/api/?login")
                .then().statusCode(200);
    }
    @Test
    public  void jsonPathDemo(){
        given().when().get("https://testerhome.com/api/v3/topics.json")
                .then()
                    .statusCode(200)
                    .body("topics.title[0]",equalTo("想请问一下目前 android 和 ios 的 app 性能数据是怎么收集的，android 的内存是 dumpsys 还是 top 方式，ios 的怎么通过命令方式获取性能数据呢？"))
                    .body("topics.size()",equalTo(20))
                    .body("topics.findAll {it.title.contains('性能数据')}.title[0]",equalTo("想请问一下目前 android 和 ios 的 app 性能数据是怎么收集的，android 的内存是 dumpsys 还是 top 方式，ios 的怎么通过命令方式获取性能数据呢？"));
    }
}
