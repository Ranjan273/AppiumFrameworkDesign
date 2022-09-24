package com.RanjanPracticeTest;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.RanjanPractice.Page.Android.CartPage;
import com.RanjanPractice.Page.Android.FormPage;
import com.RanjanPractice.Page.Android.ProductCatalogPage;

import TestUtils.BaseTest;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class GeneralStoreTestOne extends BaseTest{
	
	@Test(dataProvider="getData",groups= {"smoke"})
	public void handleHybridApp(HashMap<String,String> input) throws InterruptedException {
		
		
		formpage.setName(input.get("name"));
		formpage.selectGender(input.get("gender"));
		formpage.setCountrySelection(input.get("country"));
		ProductCatalogPage productcatalogpage=formpage.clickSubmitButton();
		
		productcatalogpage.addToCartByIndex(0);
		productcatalogpage.addToCartByIndex(0);
		CartPage cartpage=productcatalogpage.cartButton();

		Thread.sleep(5000);
		/*
		 * WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id(
		 * "com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
		 */
		double totalSum=cartpage.getProductSum(); 
		double displayformattedSum=cartpage.getTotalAmountDisplayed();
		Assert.assertEquals(totalSum, displayformattedSum);
		
		cartpage.acceptTermandConditions();
		cartpage.submitOrder();
		
		Thread.sleep(20000);
		
		Set<String> Contexts=driver.getContextHandles();
		for(String contextName : Contexts) {
			System.out.println(contextName);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		
		driver.findElement(By.name("q")).sendKeys("JyotiRanjan");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
		
		driver.context("NATIVE_APP");
	}
	
	@BeforeMethod(alwaysRun = true)
	public void preSetUp() {
		
		formpage.setActivity();
	}	
	
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data=getJsonData("D:\\NewEclipseProjects\\AppiumFrameworkDesign\\src\\test\\java\\dataSource\\eCommerce.json");
		
		return new Object[][] {{data.get(0)},{data.get(1)}};
		//return new Object[][] {{"Ranjan","female","Argentina"},{"Nilu","male","India"}};
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
