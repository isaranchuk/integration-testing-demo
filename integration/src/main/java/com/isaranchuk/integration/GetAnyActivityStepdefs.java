package com.isaranchuk.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GetAnyActivityStepdefs implements En {

    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private WireMock wireMock;

    private Jedis jedis;

    private String activitiesServiceBaseUrl;

    public GetAnyActivityStepdefs() {
        wireMock = TestContext.wireMock();
        jedis = TestContext.jedis();
        activitiesServiceBaseUrl = TestContext.activitiesServiceBaseUrl();

        configureSteps();
    }

    private void configureSteps() {
        Before(scenario -> {
            jedis.flushAll();

            String activityResponseBody = TestUtils.fileToString("rest/get_any_activity.json");
            wireMock.register(get(urlMatching("/api/activity"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON)
                            .withBody(activityResponseBody)));
        });

        AtomicReference<String> username = new AtomicReference<>("");
        Given("I am {word} and I am bored", username::set);

        AtomicReference<Response> response = new AtomicReference<>(null);
        When("I request any activity", () -> {
            Response restResponse = given()
                    .pathParam("username", username.get())
                    .when()
                    .baseUri(activitiesServiceBaseUrl)
                    .get("/v1/users/{username}/activities/any")
                    .prettyPeek();
            response.set(restResponse);
        });

        Then("the request was successful and activity was saved", () -> {
            response.get()
                    .then()
                    .assertThat()
                    .statusCode(200);

            String activity = jedis.get(username.get());
            assertThat(activity).isNullOrEmpty();
        });

        And("the response has the attributes:", (DataTable dt) -> {
            ValidatableResponse validatableResponse = response.get().then().assertThat();
            for (Map<String, String> row : dt.asMaps()) {
                validatableResponse.body(row.get("attribute"), equalTo(row.get("value")));
            }
        });
    }
}
