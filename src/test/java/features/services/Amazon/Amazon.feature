Feature: Verify Amazon Application

    #....................Verify Upload Resume And Update Account...........
  @Amazon @SearchIphone
  Scenario:Verify Search Iphone12 and sort the price from lowest order
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Amazon |
    When I enter Valid details and search Iphone12
    And I sort the price in ascending order
    Then should Logout from Amazon successfully

