@Footer
Feature: Footer Validation

Background: 
Given user is on the Email Template page 

@Footer1
Scenario: Validate footer is displayed
Then Footer logo, Legal Copy and Footer Links should be displayed


@Footer2
Scenario: Validate Footer Social Icons
Then Footer Social Icons should be displayed
When User click on Social Icons
Then should navigate to corresponding URL


@Footer3
Scenario: Validate Footer Social Icons
Then Footer Social Icons should be displayed
When User click on Social Icons
Then should navigate to corresponding URL
