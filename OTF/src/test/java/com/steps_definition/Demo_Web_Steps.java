package com.steps_definition;

import java.util.List;

import com.core.BaseMethods;
import com.page.methods.Demo_Web_Methods;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class Demo_Web_Steps extends Demo_Web_Methods {


	@Given("^User is on the Home Page$")
	public void user_is_on_the_Home_Page() throws Throwable {
		
		BaseMethods.browserURL();
	    
	}

	@When("^User enters \"([^\"]*)\" and \"([^\"]*)\" to login$")
	public void user_enters_and_to_login(String arg1, String arg2) throws Throwable {
		
		enterUserCrendentials(arg1, arg2);
	   
	}

	@Then("^User clicks on the Login Button$")
	public void user_clicks_on_the_Login_Button() throws Throwable {
		
		clickLoginButton();
	}

	@Then("^User verifies successful login$")
	public void user_verifies_successful_login() throws Throwable {
		
		verifySuccessfulLogin();
	   
	}
	
	
	@Then("^User enters the Blood \"([^\"]*)\" Level$")
	public void user_enters_the_Blood_Level(String arg1) throws Throwable {
	    
		enterBloodGlucoseLevel(arg1);
	}


	@Then("^User verifies entered Blood Glucose level$")
	public void user_verifies_entered_Blood_Glucose_level() throws Throwable {
	    
		verifyGlucoseLevelEntry();
	}
	
	
	@Then("^User deletes the Blood Glucose level record$")
	public void user_deletes_the_Blood_Glucose_level_record() throws Throwable {
		
		deleteGlucoseLevel();
	}
	
	@Then("^User verifies max enteries allowed in a day message$")
	public void user_verifies_max_enteries_allowed_in_a_day_message() throws Throwable {
	    
		verifyMaxEntriesMessageAllowed();
	}
	
	@Then("^User enters the Blood Glucose Level more than four times in a day$")
	public void user_enters_the_Blood_Glucose_Level_more_than_four_times_in_a_day(DataTable dt) throws Throwable {
	   
		List<String> list = dt.asList(String.class);
				
		for (int i = 0; i < list.size(); i++) {
			enterBloodGlucoseLevel(String.valueOf(list.get(i)));
			waitForAjaxLoad();
		}
	}


}



