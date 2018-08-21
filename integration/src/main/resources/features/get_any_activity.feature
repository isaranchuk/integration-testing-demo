Feature: Get any activity for user

  Scenario: User is bored and he wants to get any activity
    Given I am isaranchuk and I am bored
    When I request any activity
    Then the request was successful and activity was saved
    And the response has the attributes:
      | attribute | value              |
      | activity  | Take a bubble bath |
      | key       | 2581372            |
      | type      | relaxation         |
      | username  | isaranchuk         |
