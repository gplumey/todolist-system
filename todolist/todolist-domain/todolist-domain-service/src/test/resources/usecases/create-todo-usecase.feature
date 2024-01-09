Feature: Create todo

  Scenario: Say hello
    Given Only default todolist exists
    When create "my first todo list"
    Then todolist get created
