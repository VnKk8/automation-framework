Feature: UI
  Description: UI E2E Tests

  @smoke @bug
  Scenario: Validate Login functionality
    Given I navigate to Landing page
    When I execute GET books availability all request with ANONYMOUS user
    And I execute GET books availability by id request with ANONYMOUS user
    And I order a book
    Then I verify that book is ordered