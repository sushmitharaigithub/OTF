package com.steps_definition;

import com.page.methods.CopyBlock_Web_Methods;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class CopyBlock_Web_Steps extends CopyBlock_Web_Methods {
	
	@Then("^Copy block body copy and CTA should be displayed$")
	public void copy_block_body_copy_and_CTA_should_be_displayed() throws Throwable {
		
		verifyCopyBlockBody();
		verifyCopyBlockCTA();
	    
	}

	@When("^user click on CTA button$")
	public void user_click_on_CTA_button() throws Throwable {
		clickCopyBlockCTA();
	    
	}

	@Then("^should navigate to authored URL")
	public void should_navigate_to_authored_URL() throws Throwable {
		
		
		verifyLink();
	    

}
}
