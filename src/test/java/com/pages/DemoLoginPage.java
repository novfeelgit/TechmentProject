package com.pages;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.base.BaseClass;

public class DemoLoginPage extends BaseClass {
	public static String expTextMessage;

	public DemoLoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//input[@id='userName']")
	private WebElement userName;

	@FindBy(xpath = "//input[@id='password']")
	private WebElement passWord;
	@FindBy(xpath = "//button[@id='login']")
	private WebElement loginBtn;
	
	
	
	

	public void loginIds(String userName1, String passWord1) throws InterruptedException {
		implicitWait(10);
		enterText(userName, userName1);
		implicitWait(10);
		enterText(passWord, passWord1);
		loginBtn.click();
		
	}
	
	
}
