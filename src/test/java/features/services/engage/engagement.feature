Feature: Verify Services.Engage.Engagements

  @APIAutomation_set1 @engage @engagements @createAndGetEngagementWithPhases @RocketMortgage @RMRegisterPhase @RMAssignedCountDownPhase @RMGameDayPhase
  Scenario: Verify create and get Engagement phases
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
    And I send request for creating an engagement with phase
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            | phaseName            | phaseStartsOn        | phaseEndsOn          |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment | testEngagementPhase1 | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                        |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementPhases |
    And I send request for get engagement with engagement key
    Then I should see the engagement phases details in response

  @APIAutomation_set1 @engage @engagements @createAndGetPhaseByPhaseKey @RocketMortgage @RMRegisterPhase @RMAssignedCountDownPhase @RMGameDayPhase
  Scenario: Verify create and get Engagement phase by phase key
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
    And I send request for creating an engagement with phase
      | accountKey | wardKey | engagementKey | name                                       | starts-on            | ends-on              | baseUrl           | features            | phaseName            | phaseStartsOn        | phaseEndsOn          |
      | accountKey | wardKey | PL01<rnum4>   | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment | testEngagementPhase1 | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                        |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementPhases |
    And I send request for get engagement phases with engagement key
    Then I should see the engagement phases details in response
    #Get phase details by phase key
    When I am able to get the following API endpoint
      | url                     | endpoint                                            |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementByPhaseKey |
    And I send request for get engagement phase by phase key
    Then I should see the engagement phases details in response for the given phase Key


  @APIAutomation_set1 @engage @engagements @getAllEngagements
  Scenario: Verify get all engagements
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.engagements.getAllEngagements |
    And I send request for get all engagements
    Then I should see a list of all engagements

  @APIAutomation_set1 @engage @engagements @getTimeZones
  Scenario: Verify get timezones
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.engagements.getTimeZones |
    And I send request for get all timezones
    Then I should see a list of all timezones in the response with status code as 200

  @APIAutomation_set1 @engage @engagements @createAndGetEngagement @RocketMortgage @RMRegisterPhase @RMAssignedCountDownPhase @RMGameDayPhase
  Scenario: Verify create and get Engagement
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagement |
    And I send request for get engagement with engagement key
    Then I should see the engagement details in response with code 200

  @APIAutomation_set1 @engage @engagements @createAndGetenrichedEngagement
  Scenario: Verify get enriched properties of an engagement
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.getEnrichedEngagements |
    And I send request for get engagement with engagement key
    Then I should see the enriched engagement details in response with code 200

  @APIAutomation_set1 @engage @engagements @getSalesforceMetaData
  Scenario: Verify get salesforce metadata
    Given I am getting the participant token for the "Engage" scope
   # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                          |
      | ServicesEngageEngageApi | services.engage.engagements.getSalesforceMetaData |
    And I send request for get salesforce metadata for engagementKey as "PL000023"
    Then I should see the response for salesforce metadata with code 200

  @APIAutomation_set1 @engage @engagements @createAndGetEngagementSummary
  Scenario: Verify get engagement engagement summary
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementSummaries |
    And I send request for get engagement summaries with partial engagement key
    Then I should see the engagement details containing partial engagement key in response with code 200


  @APIAutomation_set1 @engage @engagements @createUpdateAndGetEngagement
  Scenario: Verify create and update engagement
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Update Engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.engagements.updateEngagement |
    And I send request for updating the engagement
      | name                                       | starts-on                 | ends-on              | baseUrl               | features            |
      | Test Engagement Updated via API Automation | 2019-01-05T12:13:00+00:00 | 2033-01-05T12:13:00Z | https://mybaseurl.com | Admin","Fulfillment |
    Then I should see the response with status code as 204
      # Get Engagement with Engagement Key
    When I am able to get the following API endpoint
      | url                     | endpoint                                  |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagement |
    And I send request for get engagement with engagement key
    Then I should see the engagement details in response with code 200


  @APIAutomation_set1 @engage @engagements @addWardAttributetoEngagement
  Scenario: Verify add engagement attribute
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                          | wardKey | description                           |
      | Sample Ward Attribute <rnum4> | wardKey | Sample Ward Attribute One Description |
    Then I should see the response to Create Attribute to ward details
    #Create ward attribute Value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttributeValue |
    And I send a request to Create Ward Attribute Value
      | value                                   | description      | code     |
      | Test Sample Ward Attribute Value<rnum4> | Test Description | Testcode |
    Then I should see the attribute value key is created with status code as 200
    # Add engagement attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.addEngagementAttribute |
    And I send a request to add ward attribute value to the engagement
      | wardAttributeKeyValue |
      | wardAttributeKeyValue |
    Then I should see the response with status code as 204

  @APIAutomation_set1 @engage @engagements @getanddeleteEngagementWardAttribute
  Scenario: Verify delete engagement attribute
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                          | wardKey | description                           |
      | Sample Ward Attribute <rnum4> | wardKey | Sample Ward Attribute One Description |
    Then I should see the response to Create Attribute to ward details
    #Create ward attribute Value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttributeValue |
    And I send a request to Create Ward Attribute Value
      | value                                   | description      | code     |
      | Test Sample Ward Attribute Value<rnum4> | Test Description | Testcode |
    Then I should see the attribute value key is created with status code as 200
    # Add engagement attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.addEngagementAttribute |
    And I send a request to add ward attribute value to the engagement
      | wardAttributeKeyValue |
      | wardAttributeKeyValue |
    Then I should see the response with status code as 204
    # Get engagement attributes and values
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementAttribute |
    And I send a request to get engagement attribute and value
    Then I should see the response with status code as 200
    # Delete engagement attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                              |
      | ServicesEngageEngageApi | services.engage.engagements.deleteEngagementAttribute |
    And I send a request to delete engagement attribute
      | engagementAttributeValueKey |
      | engagementAttributeValueKey |
    Then I should see the response with status code as 204

  @APIAutomation_set1 @engage @engagements @GetEngagementsByFilterWithWardKey
  Scenario: Verify get engagements by filter with wardkey
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement by Filter with wardkey
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementsByfilter |
    And I send request for get engagement with "wardKey" filter
    Then I should see the engagement key in response with code 200

  @APIAutomation_set1 @engage @engagements @GetEngagementsByFilterWithContinuationKey
  Scenario: Verify get engagements by filter with continuation key
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
      | accountKey | wardKey | PL01<rnum4>>  | Test Engagement Created via API Automation | 2019-01-05T12:13:00Z | 2033-01-05T12:13:00Z | https://myurl.com | Admin","Fulfillment |
    Then I should see the new engagement must be created successfully with status code as 204
    # Get Engagement by Filter with Name
    When I am able to get the following API endpoint
      | url                     | endpoint                                           |
      | ServicesEngageEngageApi | services.engage.engagements.getEngagementsByfilter |
    And I send request for get engagement with "name" filter with value as "Test Engagement Created via API Automation"
    Then I should see the continuation token in response with code 200
    And I send request for get engagement with "continuationToken" filter
    Then I should see the response with status code as 200

