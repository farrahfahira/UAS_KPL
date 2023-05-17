package appiumtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;

public class SimpleNoteTestCase {

	static AppiumDriver driver;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			buatCatatan();
			jedaAksi();
			hapusCatatan();
		}catch(Exception exp) {
			System.out.println(exp.getCause());
			System.out.println(exp.getMessage());
			exp.printStackTrace();
		}
		

	}
	
	public static void setUp() throws MalformedURLException {
		DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Galaxy A22");
        capabilities.setCapability("udid", "RR8RA01AXNH");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "13");
        
        
        capabilities.setCapability("appPackage", "com.eco.notes.notepad.notebook.simplenote");
        capabilities.setCapability("appActivity", "com.eco.notes.notepad.notebook.simplenote.screens.start.StartActivity");
        
        
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<MobileElement>(url, capabilities);
        
        System.out.println("Application opened..");
	}
	
	public static void jedaAksi() {
		//jeda 2 detik
        try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public static void buatCatatan() throws MalformedURLException {
		
		setUp();

        //terdapat landing page yang memerlukan loading, jadi harus ditunggu hingga aplikasi menampilkan page berikutnya
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("com.eco.notes.notepad.notebook.simplenote:id/action_bar_root")));

        //click button add notes utk menambahkan catatan (kondisi: blm ada catatan sama sekali)
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/linearLayoutCompat3").click();
        
        jedaAksi();
        
        //mengetikkan catatan
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etContent").sendKeys("Hello World");
        
        //mengklik bagian untuk edit judul
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etTitle").click();
        //mengetikkan judul
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etTitle").sendKeys("Catatanku");
        
        //click save
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/appCompatImageView7").click();
        
        
        
	}
	
	
	
	public static void buatCatatanOther() throws MalformedURLException {
		//klik button plus yg ada di pojok kanan bawah
		driver.findElement(By.id("com.eco.notes.notepad.notebook.simplenote:id/fabMenu")).findElement(By.className("android.widget.ImageButton")).click();
        
        //mencul 2 pilihan, add note atau list, klik yg note
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/fabNote").click();
        
        jedaAksi();
        
        //langkah yg sama seperti sebelumnya
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etContent").sendKeys("Today will be a good day");
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etTitle").click();
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/etTitle").sendKeys("Happy");
        driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/appCompatImageView7").click();
	}
	
	
	public static void hapusCatatan() throws MalformedURLException {
		
		// Temukan elemen dengan kelas dan indeks yang sesuai
		WebElement element = (WebElement) driver.findElement(By.id("com.eco.notes.notepad.notebook.simplenote:id/rvNotes")).findElements(By.className("android.view.ViewGroup")).get(0);

		LongPressOptions longPressOptions = new LongPressOptions()
		        .withElement(ElementOption.element(element))
		        .withDuration(Duration.ofSeconds(2));
		
		//longpress agar muncul button hapus
		new TouchAction(driver).longPress(longPressOptions).release().perform();
		
		//klik button hapus
		driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/appCompatImageView3").click();
		
		jedaAksi();
		
		//klik OK saat konfirmasi delete
		driver.findElementById("com.eco.notes.notepad.notebook.simplenote:id/appCompatButton").click();
		
	}
	

}
