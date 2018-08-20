package com.isaranchuk.integration;

import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.api.java8.En;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public class GetAnyActivityStepdefs implements En {

    private static final String CONTENT_TYPE_HEADER_NAME = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";

    private WireMock wireMock;

    private String queueUrl;

    public GetAnyActivityStepdefs() {
        wireMock = TestContext.wireMock();
        configureSteps();
    }

    private void configureSteps() {
        Before(scenario -> {
//            String getUserSuccessResponseBody = TestUtils.fileToString("rest/get_my_lebara_user_success_response.json");
//            wireMock.register(get(urlMatching("/internal/users/.*"))
//                    .willReturn(aResponse()
//                            .withStatus(200)
//                            .withHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON)
//                            .withBody(getUserSuccessResponseBody)));
//
//            String sendPushSuccessResponseBody = TestUtils.fileToString("rest/send_push_success_response.json");
//            wireMock.register(post(urlMatching("/v1/push/messages"))
//                    .willReturn(aResponse()
//                            .withStatus(200)
//                            .withHeader(CONTENT_TYPE_HEADER_NAME, APPLICATION_JSON)
//                            .withBody(sendPushSuccessResponseBody)));
        });

        Given("I am {word} and I am bored", username -> {
            System.out.printf("Hello " + username);
        });
    }
}
