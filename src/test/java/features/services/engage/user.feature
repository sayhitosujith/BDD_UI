Feature: Verify Services.Engage.Users

  @APIAutomation_set1 @engage @engageuser @createandengageuser
  Scenario: Verify Create User
    Given I am getting the participant token for the "Engage" scope
    # Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response

   #  Verify change engage user password for an engage user
  @APIAutomation_set1 @engage @engageuser @changeengageuserpassword
  Scenario: Verify change engage user password for an engage user
    Given I am getting the participant token for the "Engage" scope
    And I am getting the trusted Identifier token for the "Engage" sub scope
    # Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | P@ssword | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #change engage user password
  # inconsistent behaviour need to check and update
#    When I am able to get the following API endpoint
#      | url                     | endpoint                                       |
#      | ServicesEngageEngageApi | services.engage.EngageUsers.changeUserPassword |
#    And I send request for Change user password with below details
#      | id      | oldPassword  | newPassword |
#      | UserKey | P@ssword     | P@ssword!   |
#    Then I should see newly Updated Password in the response

#..Verify add roles to engage user....
  @APIAutomation_set1 @engage @engageuser @addrolestoengageuser
  Scenario: Verify add roles to engage user
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Add Roles to Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.EngageUsers.addEngageUserRoles |
    And I send request for adding a role to the user
      | RoleKeys         |
      | sprintsuperadmin |
    Then I should see the response with status code as 204

#..Verify delete roles of engage user....
  @APIAutomation_set1 @engage @engageuser @deleterolesfromengageuser
  Scenario: Verify delete roles to engage user
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Add Roles to Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.EngageUsers.addEngageUserRoles |
    And I send request for adding a role to the user
      | RoleKeys         |
      | sprintsuperadmin |
    Then I should see the response with status code as 204
    #Remove Roles from Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                |
      | ServicesEngageEngageApi | services.engage.EngageUsers.removeRoles |
    And I send request for remove roles with below details
      | RoleKeys         |
      | sprintsuperadmin |
    Then I should see the response with status code as 204


    #..Verify Is User Ward member....
  @APIAutomation_set1 @engage @engageuser @isuserwardmember
  Scenario: Verify add roles to engage user
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Verify Is User a Ward member
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.isUserWardMember |
    And I send request for Is user ward member with wardKey
    Then I should see "false" as the user is not added to the Ward with status code as 200



  #..Verify search user by account key....
  @APIAutomation_set1 @engage @engageuser @searchuserbyfilter
  Scenario: Verify add roles to engage user
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Search Engage User by accountKey
    When I am able to get the following API endpoint
      | url                     | endpoint                                    |
      | ServicesEngageEngageApi | services.engage.EngageUsers.getUserByFilter |
    And I send request for search user by accountKey filter
    Then I should see user details in the response

   #..Verify filter engage user with team and role....
  @APIAutomation_set1 @engage @engageuser @searchengageuserwithteamandrole
  Scenario: Verify filter engage user with team and role
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey | name         |
      | <rstr6>KEY | NewQA<rstr6> |
    Then I should see account created successfully with status code 204
    #Create Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Add Roles to Engage User
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.EngageUsers.addEngageUserRoles |
    And I send request for adding a role to the user
      | RoleKeys         |
      | sprintsuperadmin |
    Then I should see the response with status code as 204
    #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Search User with Team and role
    When I am able to get the following API endpoint
      | url                     | endpoint                                    |
      | ServicesEngageEngageApi | services.engage.EngageUsers.getUserByFilter |
    And I send request for search user with teamName, roleKey and accountkey
    Then I should see the newly created user in the response with status code as 200

  # remove users functionality can be do through DB
     #..Verify delete engage user
#  @APIAutomation_set1 @engage @engageuser @deleteengageuser
#  Scenario: Verify delete engage user
#    Given I am getting the participant token for the "Engage" scope
#    #Create Account
#    When I am able to get the following API endpoint
#      | url                     | endpoint                              |
#      | ServicesEngageEngageApi | services.engage.account.createAccount |
#    And I send request for create account with below details
#      | accountKey | name         |
#      | <rstr6>KEY | NewQA<rstr6> |
#    Then I should see account created successfully with status code 204
#    #Create Engage User
#    When I am able to get the following API endpoint
#      | url                     | endpoint                                     |
#      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
#    And I send request for create user with below details
#      | accountKey | userName     | password    | firstName    | lastname     | email            |
#      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
#    Then I should see newly created User key in the response
#    #Delete Engage User
#    When I am able to get the following API endpoint
#      | url                     | endpoint                                     |
#      | ServicesEngageEngageApi | services.engage.EngageUsers.deleteEngageUser |
#    And I send request for delete user
#    Then I should see the response with status code as 204