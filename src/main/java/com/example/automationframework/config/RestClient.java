package com.example.automationframework.config;

import com.example.automationframework.annotations.LazyConfiguration;
import com.example.automationframework.context.CucumberContext;
import com.example.automationframework.enums.CommonKey;
import com.example.automationframework.wrappers.MapperConfigurator;
import io.restassured.RestAssured;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import static io.restassured.RestAssured.given;

@LazyConfiguration
public class RestClient {

    @Autowired
    private CucumberContext cucumberContext;

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RequestSpecification setupClient() {
        RequestSpecification requestSpecification = given()
                .config(RestAssured.config = RestAssuredConfig.config().objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (cls, charset) -> MapperConfigurator.getJavaObjectMapper())))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);
        cucumberContext.saveOrUpdate(CommonKey.REQUEST_SPECIFICATION, requestSpecification);
        return requestSpecification;
    }

    public RequestSpecification createClientWithBaseUrl(String baseUrl) {
        return setupClient().baseUri(baseUrl);
    }
}
