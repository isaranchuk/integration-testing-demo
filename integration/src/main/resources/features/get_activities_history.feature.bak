Feature: Get activities history for user

  Scenario: User is interested in his activities history
    Given I'm `isaranchuk` and I want to get my activities history
    When I request any activity
    And I request activities history
    Then the request was successful
    And the response has the attributes:
      | attribute | type   | value              |
      | activity  | string | Take a bubble bath |
      | key       | string | 2581372            |
      | type      | string | relaxation         |
      | username  | string | isaranchuk         |
