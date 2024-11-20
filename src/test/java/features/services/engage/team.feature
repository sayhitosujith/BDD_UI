Feature: Verify Services.Engage.Teams

  @APIAutomation_set1 @engage @team @createteam
  Scenario: Verify Create Teams
   #create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
     #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200

  @APIAutomation_set1 @engage @team @getAllTeams
  Scenario: Verify get all Teams
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                          |
      | ServicesEngageEngageApi | services.engage.teams.getAllTeams |
    And I send request for get all teams
    Then I should see the response with a list of all Teams


  @APIAutomation_set1 @engage @team @getAllTeambyAccountKey
  Scenario: Verify get all Teams by AccountKey
    #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Get All Teams by Account Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.teams.getAllTeamsbyFilter |
    And I send request for get team with accountkey
    Then I should see teamkey in response

  @APIAutomation_set1 @engage @team @getteamsusingteamKeys
  Scenario: Verify get all Teams by TeamKey
  #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Get all teams by accountKey
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.teams.getAllTeamsbyFilter |
    And I send request for get all teams with teamkey
    Then I should see teamkey in response

  @APIAutomation_set1 @engage @team @getteamdetails
  Scenario: Verify get team details
    #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
     #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Get Team details
    When I am able to get the following API endpoint
      | url                     | endpoint                             |
      | ServicesEngageEngageApi | services.engage.teams.getTeamDetails |
    And I send request for get team with teamkey
    Then I should see teamkey in team details response

  @APIAutomation_set1 @engage @team @updateTeam
  Scenario: Verify Update Team
   #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
     #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test team created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Update Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.updateTeam |
    And I send request for Update team with teamkey
      | teamName | description                                           |
      | teamName | This is a test Team Updated via API automation<rstr6> |
    Then I should see the response with status code as 204


  @APIAutomation_set1 @engage @team @DeleteTeam
  Scenario: Verify delete team
   #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Delete Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.deleteTeam |
    And I send request for Delete team
    Then I should see team Deleted Successfully


  @APIAutomation_set1 @engage @team @addusertoTeam
  Scenario: Verify Add users to Team
   #Create Account
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account with below details
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
   #Create Team
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.teams.createTeam |
    And I send request for create Team with below details ward
      | teamName    | accountKey | description                                    | userIds |
      | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Create  another User
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
    And I send request for create user with below details
      | accountKey | userName     | password    | firstName    | lastname     | email            |
      | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
    Then I should see newly created User key in the response
    #Add Users to the team
    When I am able to get the following API endpoint
      | url                     | endpoint                             |
      | ServicesEngageEngageApi | services.engage.teams.addUsersToTeam |
    And I send request Add Users to team
      | EngageUserKeys |
      | UserKey        |
    Then I should see Users added to team Successfully
    #Get Team details
    When I am able to get the following API endpoint
      | url                     | endpoint                             |
      | ServicesEngageEngageApi | services.engage.teams.getTeamDetails |
    And I send request for get team with teamkey
    Then I should see newly added user in the team details response with status code as 200

# remove users functionality can be do through DB
#  @APIAutomation_set1 @engage @team @removeusersfromTeam
#  Scenario: Verify Remove users from Team#Create Account
#    Given I am getting the participant token for the "Engage" scope
#    When I am able to get the following API endpoint
#      | url                     | endpoint                      |
#      | ServicesEngageEngageApi | services.engage.account.createAccount |
#    And I send request for create account with below details
#      | accountKey     | name                 |
#      | TESTACC<rstr6> | Test Account <rstr6> |
#    Then I should see account created successfully with status code 204
#     #Create User
#      When I am able to get the following API endpoint
#        | url                     | endpoint                                     |
#        | ServicesEngageEngageApi | services.engage.EngageUsers.createEngageUser |
#      And I send request for create user with below details
#        | accountKey | userName     | password    | firstName    | lastname     | email            |
#        | accountKey | NewQA<rstr6> | PASS<rstr6> | FNAME<rstr6> | LNAME<rstr6> | QATest@gmail.com |
#      Then I should see newly created User key in the response
#   #Create Team
#      When I am able to get the following API endpoint
#        | url                     | endpoint                  |
#        | ServicesEngageEngageApi | services.engage.teams.createTeam |
#      And I send request for create Team with below details ward
#        | teamName    | accountKey | description                                    | userIds |
#        | Team<rstr6> | accountKey | This is a test ward created via API automation | userKey |
#      Then I should see the team created with status code as 200
#      #Add Users to the team
#      When I am able to get the following API endpoint
#        | url                     | endpoint                 |
#        | ServicesEngageEngageApi | services.engage.teams.addUsersToTeam|
#      And I send request Add Users to team
#        | EngageUserKeys  |
#        | UserKey |
#      Then I should see Users added to team Successfully
#    #Remove user from Team
#    When I am able to get the following API endpoint
#      | url                     | endpoint                 |
#      | ServicesEngageEngageApi | services.engage.teams.removeUsersFromTeam|
#    And I send request Remove Users from team
#      | userKey  |
#      | UserKey |
#    Then I should see Users Removed from team Successfully