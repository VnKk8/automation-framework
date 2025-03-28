Feature: API
  Description: API Tests

  @smoke
  #  GET /api/v1/books/create
  Scenario: Validate POST book create
    Given I create POST book request
    When I execute POST book create with AUTHORIZED user
    Then I verify "POST book create" response code is 200
    When I execute GET books availability all request with AUTHORIZED user
    When I execute GET books availability by id request with AUTHORIZED user
    Then I verify that the correct book is found

  @smoke
  #  GET /api/v1/books/create
  Scenario: Validate POST book create returns Forbidden
    Given I create POST book request
    When I execute POST book create with ANONYMOUS user
    Then I verify "POST book create" response code is 403