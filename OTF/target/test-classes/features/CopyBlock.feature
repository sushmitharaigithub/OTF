@CopyBlock
Feature: Copy Block

Background: 
Given user is on the Email Template page 


@copyblock1
Scenario: To validate Copy block component
Then Copy block body copy and CTA should be displayed

@copyblock2
Scenario: To validate Copy block CTA 

When user click on CTA button
Then should navigate to authored URL