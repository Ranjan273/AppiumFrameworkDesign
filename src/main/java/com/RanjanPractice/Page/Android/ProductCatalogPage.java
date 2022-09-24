package com.RanjanPractice.Page.Android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import Utilities.AndroidGestures;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductCatalogPage extends AndroidGestures {

	
	AndroidDriver driver;
	public ProductCatalogPage(AndroidDriver driver) {
		
		super(driver);
		this.driver=driver;
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);	
	}
	
	@AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement Cart;
	
	
	public void addToCartByIndex(int index) {
		
		addToCart.get(index).click();
	}
	
	public CartPage cartButton() throws InterruptedException {
		Cart.click();
		Thread.sleep(3000);
		return new CartPage(driver);
	}
	
	
	
	
	
	

}
