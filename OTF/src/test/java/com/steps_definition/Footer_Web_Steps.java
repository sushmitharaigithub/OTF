package com.steps_definition;

import com.page.methods.Footer_Web_Methods;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Footer_Web_Steps extends Footer_Web_Methods {
	
	@Then("^Footer logo, Legal Copy and Footer Links should be displayed$")
	public void footer_logo_Legal_Copy_and_Footer_Links_should_be_displayed() throws Throwable {
		verifyFooterLogo();
		verifyFooterLegalCopy();
		verifyFooterLinks();
	    
	}

	@Then("^Footer Social Icons should be displayed$")
	public void footer_Social_Icons_should_be_displayed() throws Throwable {
		
		verifySocialIcons();
	    
	}

	@When("^User click on Social Icons$")
	public void user_click_on_Social_Icons() throws Throwable {
		clickSocialIcons() ;
	}

	@Then("^should navigate to corresponding URL$")
	public void should_navigate_to_corresponding_URL() throws Throwable {
	    System.out.println("hellos");
	}

}
