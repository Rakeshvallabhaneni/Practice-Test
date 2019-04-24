package com.org.practicetestPages;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.org.practicetestBase.Base;

public class LoginPage extends Base {
	
	private Pattern pattern;
	private Matcher matcher;
	private static final String USERNAME_PATTERN = "^[a-zA-Z0-9]{8}$";
	private static final String PASSWORD_PATTERN = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";

	// Page Factory - Objects:
	@FindBy(id = "usernameFor_login")
	WebElement username;

	@FindBy(id = "passwordFor_login")
	WebElement password;

	@FindBy(id = "button_login")
	WebElement loginBtn;

	@FindBy(id = "Sign Up")
	WebElement signUpBtn;

	@FindBy(id = "errormsg_Username")
	WebElement emptyUsernameField;
	
	@FindBy(id="Not_registered?")
	WebElement notRegisteredLabel;
	

	// Initializing the Page Objects:
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	// Actions:
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
 
	// Actions:
	public String validateErrorMessage() {
		String errorMsg = emptyUsernameField.getText();
		return errorMsg;
	}
	
	// Actions:
	public boolean validatenotRegisteredLabel(){
		return notRegisteredLabel.isDisplayed();
	}
	
	// Actions:
       public boolean validateTextWithPattern(String sPattern, String sText)
       {
    	  pattern = Pattern.compile(sPattern);
		  matcher = pattern.matcher(sText);
		  return matcher.matches();    	    
	  }

    // Actions:
	public DashboardPage login(String uname, String pwd)
	{
		Assert.assertFalse(loginBtn.isEnabled(), "Verify login button is disabled.");
		
		username.sendKeys(uname);
		Assert.assertTrue(validateTextWithPattern(USERNAME_PATTERN, uname), "Validate username text");
		
		Assert.assertFalse(loginBtn.isEnabled(), "Verify login button is disabled.");
		
		password.sendKeys(pwd);
		Boolean bPwdMatches = validateTextWithPattern(PASSWORD_PATTERN, pwd);
		
		if(bPwdMatches)		
			Assert.assertTrue(loginBtn.isEnabled(), "Verify login button is enabled");
		else
			Assert.assertFalse(loginBtn.isEnabled(), "Verify login button is disabled");
			
		
		if(loginBtn.isEnabled())
			loginBtn.click();
		
	   return new DashboardPage();
	}

}
