Feature: Create todo

  Scenario: Create todo in default todolist
    Given Only default todolist exists
    When create todo "my first todo list" in default todolist
    Then todo get created
    And "Todo Created" event occurs
