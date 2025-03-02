@echo off
echo Running Selenium Tests with Maven...

:: Navigate to the project folder
cd /d "%~dp0"

:: Clean and compile the project (optional)
mvn clean compile

:: Run the tests
mvn test


@echo off
cd /d "D:\WinWire\Price Logic\Automation\BDD_UI"
mvn test -Dtest=RunCukesTest
