Feature: Verify Services.Engage.Ward


  @APIAutomation_set1 @engage @ward @getAllwards
  Scenario: Verify get all wards
    Given I am getting the participant token for the "Engage" scope
    #Get all wards
    When I am able to get the following API endpoint
      | url                     | endpoint                         |
      | ServicesEngageEngageApi | services.engage.ward.getAllWards |
    And I send request for get all wards
    Then I should see the response with a list of all wards


  @APIAutomation_set1 @engage @ward @createward
  Scenario: Verify create ward
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204


  @APIAutomation_set1 @engage @ward @createandgetwardbysearch
  Scenario: Verify create ward and get Wards By Search
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
     #Search newly created ward
    Then I should see the ward created successfully status code as 204
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.ward.getWardsBySearch |
    And I send a request to get ward by search with newly created ward
    Then I should see newly created ward details in the response


  @APIAutomation_set1 @engage @ward @createandupdateward
  Scenario: Verify create ward and update ward
    Given I am getting the participant token for the "Engage" scope
    #Create ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Update ward
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.updateWard |
    And I send a request to update ward
      | wardKey | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isActive | isCustomerCareEnabled |
      | wardKey | Test Ward Service 001 | This is a test ward updated via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true     | true                  |
    Then I should see the response with updated ward details
     #Verify the updated details
    Then I should see the ward created successfully status code as 204
    When I am able to get the following API endpoint
      | url                     | endpoint                              |
      | ServicesEngageEngageApi | services.engage.ward.getWardsBySearch |
    And I send a request to get ward by search with newly created ward
    Then I should see updated details in response with status code as 200

  @APIAutomation_set1 @engage @ward @createwardandattributewithvalue
  Scenario: Verify create ward and Create Attribute to ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Create ward attribute value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttributeValue |
    And I send a request to Create Ward Attribute Value
      | value                              | description      | code     |
      | Test Sample Ward Attribute <rnum4> | Test Description | Testcode |
    Then I should see the attribute value key is created with status code as 200


  #....Create and get ward attribute.....
  @APIAutomation_set1 @engage @ward @createandgetwardattribute
  Scenario: Verify Create and get ward attribute to ward
    #Create ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Get attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                          |
      | ServicesEngageEngageApi | services.engage.ward.getAttribute |
    And I send a request for get ward Attribute
    Then I should see the response with status code as 200

    #....Create and get ward attribute.....
  @APIAutomation_set1 @engage @ward @createandgetwardattributes
  Scenario: Verify Create and get ward attributes to ward
    #Create ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #create another ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Get attributes
    When I am able to get the following API endpoint
      | url                     | endpoint                           |
      | ServicesEngageEngageApi | services.engage.ward.getAttributes |
    And I send a request for get ward Attributes
    Then I should see the response with status code as 200

#.....Verify delete ward attribute....
  @APIAutomation_set1 @engage @ward @createanddeletewardattribute
  Scenario: Verify delete ward attribute
    #Create ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Delete ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.deletewardAttribute |
    And I send a request for delete ward Attribute
    Then I should see the ward attribute deleted successfully status code as 200

    #.....Verify delete ward attribute Value....
  @APIAutomation_set1 @engage @ward @createanddeletewardattributeValue
  Scenario: Verify delete ward attribute Value
    #Create ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Create ward attribute value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttributeValue |
    And I send a request to Create Ward Attribute Value
      | value                              | description      | code     |
      | Test Sample Ward Attribute <rnum4> | Test Description | Testcode |
    Then I should see the attribute value key is created with status code as 200
    #Delete attribute value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.deletewardAttributeValue |
    And I send a request for delete ward Attribute Value
    Then I should see the ward attribute value deleted successfully status code as 204

  #......Updated Ward Attribute Name.............................................
  @APIAutomation_set1 @engage @ward @createandUpdatewardattribute
  Scenario: Verify Updated Ward Attribute Name
    #Create ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                                | description                                   |
      | Sample Ward Attribute <rnum4> three | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Update ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.updatewardAttribute |
    And I send a request to Update Ward Attribute
      | name                                  | description                                   |
      | Sample Ward Attribute <rnum4> updated | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Update Attribute to ward details

  @APIAutomation_set1 @engage @ward @createandupdateattributevalue
  Scenario: Verify create ward and Create Attribute to ward
    Given I am getting the participant token for the "Engage" scope
    When I am able to get the following API endpoint
      | url                     | endpoint                        |
      | ServicesEngageEngageApi | services.engage.ward.createWard |
    And I send request for create ward with below details ward
      | wardKey    | name                  | description                                    | licenseType | licenseTier | isCdpWardSpecific | isPiiShareable | isPiiMatchingAllowed | isAnonymousMatchingAllowed | isAnalyticsEnabled | isAdminEnabled | isCcpaEnabled | isDemoGalleryEnabled | isCustomerCareEnabled |
      | WKS<rstr5> | Test Ward Service 001 | This is a test ward created via API automation | Standard    | _500k       | false             | false          | true                 | false                      | false              | true           | false         | true                 | true                  |
    Then I should see the ward created successfully status code as 204
    #Create ward attribute
    When I am able to get the following API endpoint
      | url                     | endpoint                                 |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttribute |
    And I send a request to Create Ward Attribute
      | name                              | wardKey | description                                   |
      | Sample Ward Attribute <rnum4> One | wardKey | Sample Ward Attribute <rnum4> One Description |
    Then I should see the response to Create Attribute to ward details
    #Create ward attribute value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.createWardAttributeValue |
    And I send a request to Create Ward Attribute Value
      | value                              | description      | code     |
      | Test Sample Ward Attribute <rnum4> | Test Description | Testcode |
    Then I should see the attribute value key is created with status code as 200
    #Update ward attribute value
    When I am able to get the following API endpoint
      | url                     | endpoint                                      |
      | ServicesEngageEngageApi | services.engage.ward.updatewardAttributevalue |
    And I send a request to Update Ward Attribute Value
      | value                        | description      | code     |
      | Test Attribute value updated | Test Description | Testcode |
    Then I should see the response with status code as 204
