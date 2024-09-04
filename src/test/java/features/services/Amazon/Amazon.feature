Feature: Verify Naukri Application

    #....................Verify Upload Resume And Update Account...........
  @Amazon @SearchIphone
   Scenario:Verify Search Iphone12 and find highest price
    Given I enter the Valid URL of Application by Launching Chrome Browser
      |url    |
      |Amazon   |
    When I enter Valid details and Login to Amazon
    And I search Iphone12
    Then should Logout from Amazon successfully

