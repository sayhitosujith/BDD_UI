Feature: Verify Services.Engage.AccessGroup

  @APIAutomation_set1 @engage @accessgroup @getaccessgroup
  Scenario: Verify get access groups
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                                   |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroup |
    And I send request for get all access groups
    Then I should see a list containing all access groups


  @APIAutomation_set1 @engage @accessgroup @createwardandaccessgroupandgetaccessgroup
  Scenario: Verify create and add access group to a newly created ward
    Given I am getting the participant token for the "Engage" scope
     #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
#    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Create access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagementKey  |
    Then I should see the access group created successfully status code as 200
    #Get access group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send request for get access group details
    Then I should see the newly created access group in the response


  @APIAutomation_set1 @engage @accessgroup @getaccessgroupbyfilterwithlimitandskip
  Scenario: Verify get access groups by filter with given limit and skip parameter
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupbyfilter |
    And I send a request to get all access group by filter with limit as "3" skip as "0"
    Then I should see a list of 3 access groups

  @APIAutomation_set1 @engage @accessgroup @getaccessgroupbyfilterwardKeyandContinuationtoken
  Scenario: Verify get access groups with given ward key and continuation token
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Create access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagementKey  |
    Then I should see the access group created successfully status code as 200
    #Get access group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupbyfilter |
    And I send a request to get all access group by filter with limit as "10" , skip as "0" and  wardKey
    Then I should see a list of 10 access groups for the given wardKey
    And I send a request to get all access group by filter with continuation token
    Then I should see a list of access groups


  @APIAutomation_set1 @engage @accessgroup @getaccessgroupbyfilterengagementkeysaddedduringcreation
  Scenario: Verify get access groups by engagementKeys that were added during creation of the access group
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Create Access Group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagementKeys |
    Then I should see the access group created successfully status code as 200
    #Get access group by engagementKey filter
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupbyfilter |
    And I send a request to get all access group by filter with engagementkeys
    Then I should see a list of access groups that are added to the given engagements

  @APIAutomation_set1 @engage @accessgroup @addengagementstotheaccessgroupandgetbyfilter
  Scenario: Verify get access groups by engagementKeys that are added to newly created access group
    Given I am getting the participant token for the "Engage" scope
   #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
#    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
   #Create access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagementKeys |
    Then I should see the access group created successfully status code as 200
    #Create Another Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an another engagement as "PL01<rnum4>"
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Adding engagements to the access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.adddeleteengagements |
    And I send request for add engagements to the access group
      | engagementKey |
      | engagementKey |
    Then I should see the response with status code as 204
    #Get Access Group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send a request to get details of access group with given key
    Then I should see the required access group details with status code as 200 and newly added engagements


  @APIAutomation_set1 @engage @accessgroup @addandremoveengagementsfromtheaccessgroup
  Scenario: Verify add and remove engagements from the access group
    Given I am getting the participant token for the "Engage" scope
    #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
#    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #create access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagementKey  |
    Then I should see the access group created successfully status code as 200
    #Create Another Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an another engagement as "PL01<rnum4>"
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Adding new engagement to the access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.adddeleteengagements |
    And I send request for add engagements to the access group
      | engagementKey |
      | engagementKey |
    Then I should see the response with status code as 204
    #Get Access Group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send a request to get details of access group with given key
    Then I should see the required access group details with status code as 200 and newly added engagements
    # Remove engagements
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.adddeleteengagements |
    And I send request to remove engagements from the access group
      | engagementKey |
      | engagementKey |
    Then I should see the response with status code as 204

  @APIAutomation_set1 @engage @accessgroup @addteamstotheaccessgroupandgetbyfilter
  Scenario: Verify add teams to a newly created access group and get access group by filter
    Given I am getting the participant token for the "Engage" scope
  #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
#    #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
   #Create Access Group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagemntKey   |
    Then I should see the access group created successfully status code as 200
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
      | Team<rstr5> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Add team to the access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.accessgroup.addremoveteam |
    And I send request for add team to the access group
      | teamKey |
      | teamKey |
    Then I should see the response with status code as 204
   #Get Access Group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send a request to get details of access group with given key
    Then I should see the required access group details with status code as 200

  @APIAutomation_set1 @engage @accessgroup @addandremoveteamfromtheaccessgroupandgetbyfilter
  Scenario: Verify add teams to a newly created access group and get access group by filter and remove it
    Given I am getting the participant token for the "Engage" scope
      #Create Account
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.account.createAccount |
    And I send request for create account in order to create engagement Key "PL01<rnum4>"
      | accountKey     | name                 |
      | TESTACC<rstr6> | Test Account <rstr6> |
    Then I should see account created successfully with status code 204
    #Create Ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward in order to create engagement
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
   #Create Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.createEngagement |
    And I send request for creating an engagement
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
  #Create Access Group
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.accessgroup.createaccessgroup |
    And I send request for create access group with below details
      | wardKey | name               | description                                            | engagementKeys |
      | wardKey | AccessGroup<rstr5> | This is a test access group created via API automation | engagemntKey   |
    Then I should see the access group created successfully status code as 200
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
      | Team<rstr5> | accountKey | This is a test ward created via API automation | userKey |
    Then I should see the team created with status code as 200
    #Add team to access group
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.accessgroup.addremoveteam |
    And I send request for add team to the access group
      | teamKey |
      | teamKey |
    Then I should see the response with status code as 204
    #Get Access Group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send a request to get details of access group with given key
    Then I should see the required access group details with status code as 200
    #Remove Team from the engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.accessgroup.addremoveteam |
    And I send request for remove team to the access group
      | teamKey |
      | teamKey |
    Then I should see the response with status code as 204
    #Get access group details
    When I am able to get the following API endpoint
      | url                     | endpoint                                         |
      | ServicesEngageEngageApi | services.engage.accessgroup.getaccessgroupdetail |
    And I send a request to get details of access group with given key
    Then I should see the required access group details with status code as 200








