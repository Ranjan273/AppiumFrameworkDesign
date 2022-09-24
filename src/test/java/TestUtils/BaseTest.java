package TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.RanjanPractice.Page.Android.FormPage;
import com.google.common.collect.ImmutableMap;

import Utilities.AppiumUtils;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest extends AppiumUtils{
	
	public AppiumDriverLocalService service;
	public static AndroidDriver driver;
	public FormPage formpage;

	@BeforeClass(alwaysRun = true)
	public void configureAppium() throws IOException {

		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\globalResources\\data.properties");
		String ipAddress=System.getProperty("ipAddress")!=null ? System.getProperty("ipAddress") : prop.getProperty("ipAddress");
		prop.load(fis);
		
		//String ipAddress=prop.getProperty("ipAddress");
		String port=prop.getProperty("port");

		service = startAppiumServer(ipAddress,Integer.parseInt(port));

		UiAutomator2Options options=new UiAutomator2Options();
		options.setDeviceName(prop.getProperty("AndroidDeviceName"));
		options.setChromedriverExecutable("D:\\NewEclipseProjects\\AppiumFrameworkDesign\\src\\test\\java\\resources\\chromedriver.exe");
		//options.setApp("D:\\NewEclipseProjects\\AppiumPracticewithRahulShetty\\src\\test\\java\\resources\\ApiDemos-debug.apk");
		options.setApp("D:\\NewEclipseProjects\\AppiumFrameworkDesign\\src\\test\\java\\resources\\General-Store.apk");


		driver=new AndroidDriver(service.getUrl(),options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		formpage=new FormPage(driver);
	}
	
	
	
	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
		service.stop();
	}

}
