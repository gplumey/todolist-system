Feature: Delete todolist

  Scenario: delete todolist succeed
    Given already created todolists
      | todolist to delete |
    When delete "todolist to delete"
    And "Todolist Deleted" event occurs
