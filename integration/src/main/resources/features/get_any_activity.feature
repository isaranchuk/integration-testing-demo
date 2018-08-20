Feature: Get any activity for user

  Scenario: User is bored and he wants to get any activity
    Given I am isaranchuk and I am bored
    When I request any activity
    Then the request was successful
    And the response has the attributes:
      | attribute | type   | value              |
      | activity  | string | Take a bubble bath |
      | key       | string | 2581372            |
      | type      | string | relaxation         |
      | username  | string | isaranchuk         |
