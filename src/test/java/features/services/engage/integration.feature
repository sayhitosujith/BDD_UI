Feature: Verify Services.Engage.Integrations

  @APIAutomation_set1 @engage @integrations @getintegrations
  Scenario: Verify get integrations
    Given I am getting the participant token for the "Engage" scope
    # Get Integrations
    When I am able to get the following API endpoint
      | url                     | endpoint                                     |
      | ServicesEngageEngageApi | services.engage.integrations.getIntegrations |
    And I send request for get all integrations
    Then I should see a list of integrations in response with status code as 200

  @APIAutomation_set1 @engage @integrations @createintegration
  Scenario: Verify create integration
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200


  @APIAutomation_set1 @engage @integrations @createintegrationandgetbyWard
  Scenario: Verify create integration and get by Ward
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Get Integrations for a ward
    When I am able to get the following API endpoint
      | url                     | endpoint                                            |
      | ServicesEngageEngageApi | services.engage.integrations.getIntegrationsForWard |
    And I send request for get integration for ward
    Then I should see the integration details in the response with status code as 200

  @APIAutomation_set1 @engage @integrations @createandupdateintegration
  Scenario: Verify create and update integration
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Update Integration for a ward
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.updateIntegration |
    And I send request for update integration for ward
      | integrationName              | endpointUri             | username        | password    | workflow     | customCodeUrl                                          |
      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | https://ci-engage-itg-customcode.azurewebsites.net/api |
    Then I should see the integration updated successfully response with status code as 204

  @APIAutomation_set1 @engage @integrations @createanddeleteintegration
  Scenario: Verify create and delete integration
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Delete Integration for a ward
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.deleteIntegration |
    And I send request for delete integration for ward
    Then I should see the integration deleted successfully response with status code as 204

  @APIAutomation_set1 @engage @integrations @createintegrationforengagement
  Scenario: Verify create integration for engagement
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
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Create Integration for engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegrationForEngagement |
    And I send request for creating an integration for engagement
      | integrationKey               | endpointUri             | username        | password    | workflow     | subscribedEvents   |
      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
    Then I should see the integration created successfully with status code as 200

  @APIAutomation_set1 @engage @integrations @createandgetintegrationforengagement
  Scenario: Verify create and get integration for engagement
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
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Create Integration for engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegrationForEngagement |
    And I send request for creating an integration for engagement
      | integrationKey               | endpointUri             | username        | password    | workflow     | subscribedEvents   |
      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
    Then I should see the integration created successfully with status code as 200
    #Get Integrations for a engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                 |
      | ServicesEngageEngageApi | services.engage.integrations.getIntegrationForEngagement |
    And I send request for get integration for engagement
    Then I should see the integration details in the response with status code as 200


  @APIAutomation_set1 @engage @integrations @createandgetengagementintegrationbytype
  Scenario: Verify create and get engagement integration by type
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
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Create Integration for engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegrationForEngagement |
    And I send request for creating an integration for engagement
      | integrationKey               | endpointUri             | username        | password    | workflow     | subscribedEvents   |
      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
    Then I should see the integration created successfully with status code as 200
    #Get engagement integrations by type
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.getEngagementIntegrationByType |
    And I send request for get engagement integration by type as "CustomCode"
    Then I should see the integration details in the response with status code as 200

  @APIAutomation_set1 @engage @integrations @createandupdateintegrationforengagement
  Scenario: Verify create and update integration for engagement
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
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
    And I send request for creating an integration for newly created ward
      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
    Then I should see the integration created successfully with status code as 200
    #Create Integration for engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegrationForEngagement |
    And I send request for creating an integration for engagement
      | integrationKey               | endpointUri             | username        | password    | workflow     | subscribedEvents   |
      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
    Then I should see the integration created successfully with status code as 200
    #Update Integration for engagement
    When I am able to get the following API endpoint
      | url                     | endpoint                                                    |
      | ServicesEngageEngageApi | services.engage.integrations.updateIntegrationForEngagement |
    And I send request for update integration for engagement
      | endpointUri             | username        | password    | workflow     | subscribedEvents   |
      | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
    Then I should see the integration updated successfully with status code as 204


  @APIAutomation_set1 @engage @integrations @createanddeleteintegrationforengagement
  Scenario: Verify create and delete integration for engagement
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
    #Create Integration
    When I am able to get the following API endpoint
      | url                     | endpoint                                       |
      | ServicesEngageEngageApi | services.engage.integrations.createIntegration |
#    And I send request for creating an integration for newly created ward
#      | integrationKey | integrationName                                     | customCodeUrl                                          | subscribedEvents        |
#      | CUSTOMCODE     | Test Integration created via API automation <rnum4> | https://ci-engage-itg-customcode.azurewebsites.net/api | OnParticipantRegistered |
#    Then I should see the integration created successfully with status code as 200
    #Create Integration for engagement
#    When I am able to get the following API endpoint
#      | url                     | endpoint                                                    |
#      | ServicesEngageEngageApi | services.engage.integrations.createIntegrationForEngagement |
#    And I send request for creating an integration for engagement
#      | integrationKey               | endpointUri             | username        | password    | workflow     | subscribedEvents   |
#      | Test Integrations API<rnum4> | https://example.org/age | testUser<rnum4> | pass<rnum4> | testWorkFlow | create.perticipant |
#    Then I should see the integration updated successfully with status code as 200
#    #Delete Integration for engagement
#    When I am able to get the following API endpoint
#      | url                     | endpoint                                                    |
#      | ServicesEngageEngageApi | services.engage.integrations.deleteIntegrationForEngagement |
#    And I send request for delete integration for engagement
#    Then I should see the integration deleted successfully with status code as 204
