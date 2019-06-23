package com.vangaurd.netassetvalue;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.Base;
import resources.configurations;

import java.util.List;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;


import static io.restassured.RestAssured.given;

public class netAssetValueValidations extends Base {


    @Test
    public void basicResponseChecks(){

        given().
        when().
                get(configurations.navEndpoint).
        then().
                body("navPriceArray[0].measureType.measureCode", contains("NAV")).
                body("portId[1]", equalTo("8203"));
    }

    @Test
    public void extractNAVDetailsFromResponse() {
        Response response = get(configurations.navEndpoint).
                then().
                        extract().response();

        List<List> prices = response.path("navPriceArray.price");
        List<String> portId = response.path("portId");
        List<List> percentChange = response.path("navPriceArray.percentChange");

        for (int i = 0; i < prices.size(); i++) {
            for(int j=0; j<prices.get(i).size();j++){
                System.out.println("The daily price for Port Id "  + portId.get(i) + " is AUD " + prices.get(i).get(j) + ".The percent change is " + percentChange.get(i).get(j));

            }
        }

    }

}
