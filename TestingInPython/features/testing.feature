Feature: test the api

  Scenario: test the api returns result
    Given getting usd json
    When invoking the api
    Then assert there is result

  Scenario: test call with invalid currency
    Given getting invalid request
    When invoking the api with invalid currency
    Then assert there is and error

  Scenario: test response json is the same as static
    Given getting api response for usd
    When invoking the api with usd
    Then assert there is and error

    # 3 is just random number
  Scenario: test if the response rate is 3
    Given mocked response for usd
    When mocking the response
    Then assert the response value for usd is 3