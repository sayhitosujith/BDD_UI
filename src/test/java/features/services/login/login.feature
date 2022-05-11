Feature: Verify Services.Login

  #....................Login to Application...........
  @DemoApp_Login
  Scenario: Verify Login to DemoApp
    Given I enter the Valid URL of Demo Application by Launching Chrome Browser
      |url        |
      |demowebshop|
    When I enter Valid Credentials and Login Successfully
#      |Email                   |Password    |
#      |sayhitosujith@gmail.com |Qw@12345678 |
    Then i should Login Successfully

#....................Login and Logout Application...........
  @DemoApp_Logout
  Scenario: Verify Login to DemoApp and Logout
    Given I enter the Valid URL of Demo Application by Launching Chrome Browser
      |url        |
      |demowebshop|
    When I enter Valid Credentials and Login Successfully
#      |url         |Email                              |Password    |
#      |Naukri_UI   |sayhitosujith<rstr6>@gmail.com     |Qw@12345678 |
    And i should Verify page title
    Then should Logout successfully

#....................Add smart phone product to cart...........
  @Addsmartphoneproducttocart
  Scenario: Add smart phone product to cart
    Given I enter the Valid URL of Demo Application by Launching Chrome Browser
      |url        |
      |demowebshop|
    When I enter Valid Credentials and Login Successfully
#      |url         |Email                              |Password    |
#      |Naukri_UI   |sayhitosujith<rstr6>@gmail.com     |Qw@12345678 |
    And I Add smart phone product to cart
    Then should Logout successfully

 #....................Verify Add Product to cart...........
  @VerifyAddProducttocart
  Scenario:Verify Add Product to cart
    Given I enter the Valid URL of Demo Application by Launching Chrome Browser
      |url        |
      |demowebshop|
    When I enter Valid Credentials and Login Successfully
#      |url         |Email                              |Password    |
#      |Naukri_UI   |sayhitosujith<rstr6>@gmail.com     |Qw@12345678 |
    Then i should Login Successfully

    When I Add smart phone product to cart
    And I verify cart details
    Then should Logout successfully

    #....................Verify Update cart...........
  @VerifyDeleteProductfromcart
  Scenario:Verify Update cart
    Given I enter the Valid URL of Demo Application by Launching Chrome Browser
      |url        |
      |demowebshop|
    When I enter Valid Credentials and Login Successfully
#      |url         |Email                              |Password    |
#      |Naukri_UI   |sayhitosujith<rstr6>@gmail.com     |Qw@12345678 |
    Then i should Login Successfully

    When I Add smart phone product to cart
    And I Delete the cart details
    Then should Logout successfully


