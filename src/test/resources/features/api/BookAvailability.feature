Feature: API
  Description: API Tests

  @smoke
  #  GET /api/v1/books/availability/all
  Scenario: Validate GET availability of all books with ADMIN user
    When I execute GET books availability all request with ANONYMOUS user
    Then I verify "GET availability of all books" response code is 200

  @smoke
  #  GET /api/v1/books/availability/all
  Scenario: Validate GET availability of all books with ANONYMOUS user
    When I execute GET books availability all request with AUTHORIZED user
    Then I verify "GET availability of all books" response code is 200

  @smoke
  #  GET /api/v1/books/availability/{bookId}
  Scenario: Validate GET availability of book by ID with ANONYMOUS user
    When I execute GET books availability all request with ANONYMOUS user
    Then I verify "GET availability of book by id" response code is 200
    When I execute GET books availability by id request with ANONYMOUS user
    Then I verify that the correct book is found

  @smoke
  #  GET /api/v1/books/availability/{bookId}
  Scenario: Validate GET availability of book by ID with AUTHORIZED user
    When I execute GET books availability all request with AUTHORIZED user
    Then I verify "GET availability of book by id" response code is 200
    When I execute GET books availability by id request with AUTHORIZED user
    Then I verify that the correct book is found

  @smoke
  #  GET /api/v1/books/availability/save
  Scenario: Validate POST availability save of book by ID with AUTHORIZED user
    When I execute GET books availability all request with AUTHORIZED user
    Then I verify "GET availability of book by id" response code is 200
    When I execute POST availability save with AUTHORIZED user
    Then I verify that the available quantity is updated

  @smoke @bug
  #  GET /api/v1/books/availability/save
  Scenario: Validate POST availability save returns Forbidden
    When I execute GET books availability all request with ANONYMOUS user
    Then I verify "GET availability of book by id" response code is 200
    When I execute POST availability save with ANONYMOUS user
    Then I verify "GET availability of book by id" response code is 403
