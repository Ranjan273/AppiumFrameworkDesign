package com.RanjanPractice.Page.Android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.AndroidGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class CartPage extends AndroidGestures{
 
	AndroidDriver driver;
	public CartPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);
	}

	@AndroidFindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='Please read our terms of conditions']")
	private WebElement terms;
	
	@AndroidFindBy(id="android:id/button1")
	private WebElement acceptButton;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	@AndroidFindBy(xpath="//android.widget.CheckBox[@text='Send me e-mails on discounts related to selected products in future']")
	private WebElement checkBox;
	
	public List<WebElement> getProductList(){
		
		return productList;
	}
	
	public double getProductSum() {
		
		int count= productList.size();  
		double sum=0;
		for(int i= 0; i< count; i++) {
			
			String price=productList.get(i).getText();
			Double priceofProduct=getFormattedAmount(price);
			sum=sum+priceofProduct;
			
		}
		return sum;
	}
	
	public Double getTotalAmountDisplayed() {
		
		return getFormattedAmount(totalAmount.getText());
	}
	
	public void acceptTermandConditions() {
		
		longPressAction(terms);
		acceptButton.click();
	}
	
	public void submitOrder() {
		
		checkBox.click();
		proceed.click();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
