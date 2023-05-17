package appiumtests;
import io.appium.java_client.MobileBy;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

import java.net.MalformedURLException;
import java.net.URL;

public class LoginInstagram {

	static AppiumDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			openInstagramApp();
		}catch(Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		

	}
	
	public static void openInstagramApp() throws MalformedURLException {
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Galaxy A22");
        capabilities.setCapability("udid", "RR8RA01AXNH");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "13");
        capabilities.setCapability("appPackage", "com.instagram.android");
        capabilities.setCapability("appActivity", "com.instagram.mainactivity.MainActivity");
//        capabilities.setCapability("appPackage", "com.google.android.music");
//        capabilities.setCapability("appActivity", "com.google.android.music.ui.HomeActivity");
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, capabilities);
        
        System.out.println("Application opened..");
        
        driver.findElement(MobileBy.id("username")).sendKeys("username Anda");
        driver.findElement(MobileBy.id("password")).sendKeys("password Anda");
        driver.findElement(MobileBy.id("loginButton")).click();
	}

}
