Feature: Demo Assignment

Background:
Given User is on the Home Page

@Test12
Scenario: TC001 Validate an authenticated user can login successfully
When User enters "userEmail" and "password" to login
Then User clicks on the Login Button
And  User verifies successful login


Scenario Outline:TC002 Validate an authenticated user can enter the Blood Glucose Level
When User enters "userEmail" and "password" to login
Then User clicks on the Login Button
And  User enters the Blood "<Glucose>" Level
Then User verifies entered Blood Glucose level 

Examples:
|Glucose|
|1      |




Scenario Outline: TC003 Validate an authenticated user can delete the Blood Glucose Record
When User enters "userEmail" and "password" to login
Then User clicks on the Login Button
And  User enters the Blood "<Glucose>" Level
Then User verifies entered Blood Glucose level 
And  User deletes the Blood Glucose level record

Examples:
|Glucose|
|1      |
|5      |




Scenario: TC004 Validate an authenticated user can check Blood Glucose Record maximum four times in a day
When User enters "userEmail" and "password" to login
Then User clicks on the Login Button
And  User enters the Blood Glucose Level more than four times in a day
|6|
|7|
|3|
|5|
|9|


#Then User verifies entered Blood Glucose level 
#And  User verifies max enteries allowed in a day message



