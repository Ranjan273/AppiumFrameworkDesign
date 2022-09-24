package com.RanjanPractice.Page.Android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;


import Utilities.AndroidGestures;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class FormPage extends AndroidGestures{


	AndroidDriver driver;
	public FormPage(AndroidDriver driver) {

		super(driver); 
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}


	@AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameEditBox;

	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Female']")
	private WebElement femaleRadioButton;

	@AndroidFindBy(xpath="//android.widget.RadioButton[@text='Male']")
	private WebElement maleRadioButton;

	@AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
	private WebElement countryDropDown;

	@AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement submitButton;

	public void setActivity() {

		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}


	public void setName(String name) {

		nameEditBox.sendKeys(name);
		driver.hideKeyboard();
	}

	public void selectGender(String gender) {

		if(gender.contains("female")) {
			femaleRadioButton.click();
		}

		else {
			maleRadioButton.click();
		}
	}


	public void setCountrySelection(String countryName) {

		countryDropDown.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+countryName+"']")).click();
	}

	public ProductCatalogPage clickSubmitButton() throws InterruptedException {
		submitButton.click();
		Thread.sleep(3000);
		return new ProductCatalogPage(driver);
	}












}
