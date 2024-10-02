Feature: Verify Services.Engage.Account

  @APIAutomation_set1 @engage @account @creataccount
  Scenario: Verify create account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204

  @APIAutomation_set1 @engage @account @createandgetaccount
  Scenario: Verify get account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    When I am able to get the following API endpoint
      | url                     | endpoint                           |
      | ServicesEngageEngageApi | services.engage.account.getAccount |
    And I send a request to get created account
    Then I should see newly created account in the response

  @APIAutomation_set1 @engage @account @createandupdateaccount
  Scenario: Verify update account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.updateAccount |
    And I send a request to update created account with following details
      | name                 | isActive | isLocalLoginEnabled | passwordExpiration |
      | Test Account <rstr6> | false    | true                | Never              |
    Then I should see account should be updated


  @APIAutomation_set1 @engage @account @searchaccountWithFilter
  Scenario: Verify search account with filter
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.searchAccount |
    And I send request for search account with below query parameters
      | search |
      | mil    |
    Then I should see account created successfully with status code 200

  @APIAutomation_set1 @engage @account @getAllAccounts
  Scenario: Verify get all accounts
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                               |
      | ServicesEngageEngageApi | services.engage.account.getAllAccounts |
    And I send request to get all accounts
    Then I should see list of accounts in the response

  @APIAutomation_set1 @engage @account @searchWithNoFilter
  Scenario: Verify search account with no filter
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.searchAccount |
    And I send request for search account
    Then I should see list of search accounts in the response

  @APIAutomation_set1 @engage @account @getaccountwithcontinuationtoken
  Scenario: Verify search account with continuation token
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.searchAccount |
    And I send request for search account
    And I get continuation token from search account
    And I send request for search account with continuation token
    Then I should see list of search accounts in the response

  @APIAutomation_set1 @engage @account @createauthenticationprovider
  Scenario: Verify create new Authentication Provider for an Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    When I am able to get the following API endpoint
      | url                     | endpoint                                              |
      | ServicesEngageEngageApi | services.engage.account.accountAuthenticationProvider |
    And I send a request create new Authentication Provider for an Account with following details
      | authenticationScheme  | isActive |
      | authentication<rstr5> | true     |
    Then I should see Authentication Provider in the response


  @APIAutomation_set1 @engage @account @createauthenticationproviders
  Scenario: Verify get Authentication Providers for an Account in Engage Service
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    When I am able to get the following API endpoint
      | url                     | endpoint                                              |
      | ServicesEngageEngageApi | services.engage.account.accountAuthenticationProvider |
    And I send a request create new Authentication Provider for an Account with following details
      | authenticationScheme | isActive |
      | <rstr5>              | true     |
    Then I should see Authentication Provider in the response
    When I am able to get the following API endpoint
      | url                     | endpoint                                                  |
      | ServicesEngageEngageApi | services.engage.account.getAccountAuthenticationProviders |
    And I send a request to get Account Authentication Providers
    Then I should see Authentication Providers in the response


