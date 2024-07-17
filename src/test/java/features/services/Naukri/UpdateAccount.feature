Feature: Verify Services.Naukri

    #....................Verify Update Account...........
  @VerifyUpdateAccount
  Scenario:Verify Update Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      |url    |
      |Naukri   |
    When I enter Valid details and Update account
    Then should Logout Profile successfully



