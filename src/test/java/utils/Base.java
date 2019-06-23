package utils;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.Properties;

public class Base {

    public Properties properties = new Properties();

    @BeforeTest
    public void setup() throws IOException {


//        RestAssured.proxy("localhost", 8888);

//        Multiple request specifications can be added with specific request data
        RequestSpecification requestSpecification = new RequestSpecBuilder().
                setBaseUri("https://www.vanguardinvestments.com.au").
                setBasePath("/retail/").
                addHeader("Content-Type", "application/x-www-form-urlencoded").
                build();

//        If there are multiple request Specifications, the request spec listed here will be used in the test
        RestAssured.requestSpecification = requestSpecification;

//        Multiple response specifications can be added with specific response verifications
        ResponseSpecification responseSpecification = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType("application/x-javascript;charset=utf-8").
                build();
//        If there are multiple responseSpecifications, the spec listed here will be used in the test
        RestAssured.responseSpecification = responseSpecification;

        RestAssured.registerParser("application/x-javascript", Parser.JSON);
    }
}
