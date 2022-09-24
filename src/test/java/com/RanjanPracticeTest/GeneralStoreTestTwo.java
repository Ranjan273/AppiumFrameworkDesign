package com.RanjanPracticeTest;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.RanjanPractice.Page.Android.ProductCatalogPage;

import TestUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;

public class GeneralStoreTestTwo extends BaseTest{
	
	@BeforeMethod
	public void preSetUp() {
		
		//For window:-   adb shell dumpsys window | find "mCurrentFocus"
		//for Mac:-  adb shell dumpsys window | grep -E 'mCurrentFocus' 
		Activity activity=new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	

	@Test
	public void validateToastMessage() throws InterruptedException {
		
		formpage.setCountrySelection("Argentina");
		formpage.selectGender("female");
		driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(3000);
		
		String toastMessage=driver.findElement(By.xpath("(//android.widget.Toast)[1]")).getAttribute("name");
		System.out.println(toastMessage);
		Assert.assertEquals(toastMessage, "Please enter your name");
		
		
	}
	
	@Test
	public void noToastMessageShow() throws InterruptedException {
		
		formpage.setName("Ranjan");
		formpage.selectGender("female");
		formpage.setCountrySelection("Argentina");
		ProductCatalogPage productcatalogpage=formpage.clickSubmitButton();
		
		Thread.sleep(3000);
		
		Assert.assertTrue(driver.findElements(By.xpath("(//android.widget.Toast)[1]")).size()<1);
		
		
		
	}
}
