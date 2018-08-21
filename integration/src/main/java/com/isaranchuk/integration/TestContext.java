package com.isaranchuk.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;

import java.util.Properties;

public class TestContext {

    private static final String WIREMOCK_HOST_ENV = "WIREMOCK_HOST";
    private static final String WIREMOCK_PORT_ENV = "WIREMOCK_PORT";
    private static final Properties PROPERTIES = TestUtils.loadProperties("dev.properties");

    public static WireMock wireMock() {
        return new WireMock(getProperty(WIREMOCK_HOST_ENV), Integer.parseInt(getProperty(WIREMOCK_PORT_ENV)));
    }

    private static String getProperty(String key) {
        String envProperty = System.getenv(key);
        if (StringUtils.isBlank(envProperty)) {
            return PROPERTIES.getProperty(key);
        }
        return envProperty;
    }

}
