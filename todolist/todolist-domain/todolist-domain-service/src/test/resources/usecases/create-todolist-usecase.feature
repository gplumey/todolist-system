Feature: Create todolist

  Scenario: Create first todolist succeed
    Given Only default todolist exists
    When create "my first todo list"
    Then todolist get created
    And "Todolist Created" event occurs

  Scenario: Not allow blank todolist name
    Given Only default todolist exists
    When create ""
    Then todolist creation failed
    And thrown "Constraint Violation" with message "name: must not be blank"
    And "Todolist Created" event does not occurs


  Scenario: Not allow todolist name duplication
    Given already created todolists
      | existing todolist |
      | another todolist  |
    When create "existing todolist"
    Then todolist creation failed
    And thrown "Todolist Already Exists" with message "Todolist with name 'existing todolist' already exists"
    And "Todolist Created" event does not occurs
