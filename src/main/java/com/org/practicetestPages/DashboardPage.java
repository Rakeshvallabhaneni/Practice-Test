package com.org.practicetestPages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.org.practicetestBase.*;

public class DashboardPage extends Base {

	@FindBy(id = "//td[contains(text(),'User: Test Automation')]")
	WebElement userNameLabel;

	// Initializing the Page Objects:
	public DashboardPage() {
		PageFactory.initElements(driver, this);
	}
	
	public String verifyHomePageTitle(){
		return driver.getTitle();
	}
	
	public boolean verifyCorrectUserName(){
		return userNameLabel.isDisplayed();
	}
		
}
