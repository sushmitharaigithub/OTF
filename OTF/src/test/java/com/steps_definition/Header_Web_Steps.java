package com.steps_definition;

import com.core.BaseMethods;
import com.page.methods.Header_Web_Methods;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Header_Web_Steps extends Header_Web_Methods {
	
	@Given("^user is on the Email Template page$")
	public void user_is_on_the_Email_Template_page() throws Throwable {
		BaseMethods.browserURL();
	   
	}

	@Then("^Header Component should be displayed$")
	public void header_Component_should_be_displayed() throws Throwable {
		verifyHeader();
		
	}
	   


	@When("^user click on Header Component$")
	public void user_click_on_Header_Component() throws Throwable {
		clickLogo();
	    
	}

	@Then("^user should be navigated to Orange Theory Homepage$")
	public void user_should_be_navigated_to_Orange_Theory_Homepage() throws Throwable {
	    
		verifyHeaderLink();
	}

}
