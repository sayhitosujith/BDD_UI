Feature: Verify Update Naukri Application

    #....................Verify Upload Resume And Update Account...........
  @Naukri @VerifyUploadResumeAndUpdateAccount
  Scenario:Verify upload resume and Update Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Naukri |
    When I enter Valid details and Update account
    And I Update my Resume
    Then should Logout Profile successfully

     #....................Verify UpdateResume Headline And Update Account...........
  @Naukri @VerifyUpdateResumeHeadlineAndUpdateAccount
  Scenario:Verify UpdateResume Headline And Update Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Naukri |
    When I enter Valid details and Update account
    And I Update Resume headline
    Then should Logout Profile successfully

         #....................Verify UpdateResume Headline Take screenshot And Update Account...........
  @Naukri @VerifyUpdateResumeHeadlineTakeScreenshotAndUpdateAccount
  Scenario:Verify UpdateResume Headline Take screenshot And Update Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Naukri |
    When I enter Valid details and Update account
    And I Update Resume headline
    Then should Logout Profile successfully

             #....................Verify Employment Experience And Update Account...........
  @Naukri @VerifyEmploymentExperienceAndUpdateAccount
  Scenario:Verify Employment Experience And Update Account
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Naukri |
    When I enter Valid details and Update account
    And I Scroll Page Down and Update Total experience
    Then should Logout Profile successfully

       #....................Verify Applied jobs count...........
  @Naukri @VerifyAppliedjobscount
  Scenario:Verify Applied jobs count
    Given I enter the Valid URL of Application by Launching Chrome Browser
      | url    |
      | Naukri |
    When I enter Valid details and Update account
    And I want to Find the Number of Rows and Columns
    Then should Logout Profile successfully


