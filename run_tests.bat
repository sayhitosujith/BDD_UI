@echo off
echo Running Selenium Tests with Maven in 'sujith' branch...

:: Navigate to the project folder
cd /d "%~dp0"

:: Ensure we are in the correct branch
git checkout sujith

:: Pull the latest changes from the remote repository (optional)
git pull origin sujith

:: Clean and compile the project (optional)
mvn clean compile

:: Run the tests
mvn test

:: Navigate to the specific directory and run tests
cd /d "D:\WinWire\Price Logic\Automation\BDD_UI"
mvn test -Dtest=RunCukesTest
