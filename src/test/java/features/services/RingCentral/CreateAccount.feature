Feature: Verify Services.RingCentral

    #....................Verify Create Account...........
  @VerifyCreateAccount
  Scenario:Verify Create Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      |url    |
      |RC13   |
    When I enter Valid details and generate account
    And should see account created successfully
    Then should Logout successfully


      #....................Verify Add user...........
  @VerifyAdduser
  Scenario:Verify Add user
    Given I enter the Valid URL of Application by Launching Chrome Browser
      |url    |
      |RC13   |
    When I enter Valid details and generate account
    Then should see account created successfully
#Add user
    When I should Add User
    And I should see user added successfully
    Then should Logout successfully

    #...............Verify Add Number.............
  @VerifyAddNumber
  Scenario:Verify Add Number
    Given I enter the Valid URL of Application by Launching Chrome Browser
      |url    |
      |RC13   |
    When I enter Valid details and generate account
    Then should see account created successfully
