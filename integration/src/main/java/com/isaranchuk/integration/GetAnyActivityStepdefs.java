package com.isaranchuk.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;

import java.util.concurrent.atomic.AtomicReference;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlMatching;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetAnyActivityStepdefs implements En {

    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private WireMock wireMock;


    public GetAnyActivityStepdefs() {
        wireMock = TestContext.wireMock();
        configureSteps();
    }

    private void configureSteps() {
        Before(scenario -> {
            String activityResponseBody = TestUtils.fileToString("rest/get_any_activity.json");
            wireMock.register(get(urlMatching("/api/activities"))
                    .willReturn(aResponse()
                            .withStatus(200)
                            .withHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON)
                            .withBody(activityResponseBody)));
        });

        AtomicReference<String> username = new AtomicReference<>("");
        Given("I'm `{string}` and I'm bored", username::set);


        AtomicReference<Response> response = new AtomicReference<>(null);
        When("I request any activity", () -> {
            response.set(
                    given()
                            .pathParam("username", username.get())
                            .when().get("http:activity-service:8080/v1/users/{username}/activities"));

        });

        Then("the request was successful and activity was saved", () -> {
            response.get()
                    .then()
                    .assertThat()
                    .statusCode(200);
        });

        And("the response has the attributes:", (DataTable dt) -> {
            response.get()
                    .then()
                    .assertThat()
                    .body("$.key", equalTo(dt.column(0).get(0)))
                    .body("$.key", equalTo(dt.column(0).get(0)))
                    .body("$.key", equalTo(dt.column(0).get(0)))
                    .body("$.key", equalTo(dt.column(0).get(0)));
        });
    }
}
