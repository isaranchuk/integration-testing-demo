package com.isaranchuk.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.Jedis;

import java.util.Properties;

public class TestContext {

    private static final String WIREMOCK_HOST_ENV = "WIREMOCK_HOST";
    private static final String WIREMOCK_PORT_ENV = "WIREMOCK_PORT";

    private static final String REDIS_HOST_ENV = "REDIS_HOST";
    private static final String REDIS_PORT_ENV = "REDIS_PORT";

    private static final String ACTIVITIES_SERVICE_BASE_URL_ENV = "ACTIVITIES_SERVICE_BASE_URL";

    private static final Properties PROPERTIES = TestUtils.loadProperties("dev.properties");

    public static WireMock wireMock() {
        return new WireMock(getProperty(WIREMOCK_HOST_ENV), Integer.parseInt(getProperty(WIREMOCK_PORT_ENV)));
    }

    public static Jedis jedis() {
        return new Jedis(getProperty(REDIS_HOST_ENV), Integer.parseInt(getProperty(REDIS_PORT_ENV)));
    }

    public static String activitiesServiceBaseUrl() {
        return getProperty(ACTIVITIES_SERVICE_BASE_URL_ENV);
    }

    private static String getProperty(String key) {
        String envProperty = System.getenv(key);
        if (StringUtils.isBlank(envProperty)) {
            return PROPERTIES.getProperty(key);
        }
        return envProperty;
    }

}
