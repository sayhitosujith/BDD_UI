Feature: Verify Services.Engage.Roles

  @APIAutomation_set1 @engage @roles @createrole
  Scenario: Verify create role
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.roles.createRole |
    And I send request for create a role with below details
      |accountKey|name           |description      |permissions                 |
      |SPRINT    |rolename<rstr6>|role description |_engage._engage_engagement.r|
    Then I should see account created successfully with status code 200


  @APIAutomation_set1 @engage @roles @getrole
  Scenario: Verify get role
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.roles.createRole |
    And I send request for create a role with below details
      |accountKey|name           |description      |permissions                 |
      |SPRINT    |rolename<rstr6>|role description |_engage._engage_engagement.r|
    Then I should see account created successfully with status code 200
    When I am able to get the following API endpoint
      | url                     | endpoint                      |
      | ServicesEngageEngageApi | services.engage.roles.getRole |
    And I send a request to get created role
    Then I should see account created successfully with status code 200

  @APIAutomation_set1 @engage @roles @getrolefilter
  Scenario: Verify  get role filter
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.roles.createRole |
    And I send request for create a role with below details
      |accountKey|name           |description      |permissions                 |
      |SPRINT    |rolename<rstr6>|role description |_engage._engage_engagement.r|
    Then I should see account created successfully with status code 200
    When I am able to get the following API endpoint
      | url                     | endpoint                           |
      | ServicesEngageEngageApi | services.engage.roles.getRoleFilter |
    And I send a request to get created role with filter
    Then I should see account created successfully with status code 200

  @APIAutomation_set1 @engage @roles @updaterole
  Scenario: Verify update role
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.roles.createRole |
    And I send request for create a role with below details
      |accountKey|name           |description      |permissions                 |
      |SPRINT    |rolename<rstr6>|role description |_engage._engage_engagement.r|
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.roles.updateRole |
    When I send request to update created role with following data
      |name           |description      |
      |rolename<rstr6>|role description |
    Then I should see account created successfully with status code 204
