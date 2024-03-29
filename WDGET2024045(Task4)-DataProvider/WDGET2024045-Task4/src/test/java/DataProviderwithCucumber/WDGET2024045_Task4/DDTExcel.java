package DataProviderwithCucumber.WDGET2024045_Task4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DDTExcel {
	WebDriver driver;
	  
	 @Test(dataProvider="testdata")
	 public void DemoProject(String username, String password) throws InterruptedException
	 {
	System.setProperty("webdriver.chrome.driver","C:\\Users\\admin\\Downloads\\extractchromedriver-win64\\chromedriver-win64\\chromedriver.exe");
	driver = new ChromeDriver();
			
	 driver.get("http://www.saucedemo.com");
	  
	 driver.findElement(By.name("user-Name")).sendKeys(username);
	 driver.findElement(By.name("password")).sendKeys(password);
	 driver.findElement(By.name("login-button")).click();
	  
	 Thread.sleep(5000);
	 String exceptedurl="https://www.saucedemo.com/inventory.html";
     String actualurl=driver.getCurrentUrl();
     System.out.println("Actual URL"+actualurl);
     Assert.assertEquals(exceptedurl, actualurl);
	 }
	  
	 
	 @AfterMethod
	 void ProgramTermination()
	 {
	 driver.quit();
	 }
	 
	@DataProvider(name="testdata")
	 public Object[][] TestDataFeed()
	 {
	  
	 ReadExcelFile config = new ReadExcelFile("C:\\Users\\admin\\Documents\\Logindata.xlsx");
	  
	 int rows = config.getRowCount(0);
	  
	 Object[][] credentials = new Object[rows][2];
	 
	for(int i=0;i<rows;i++)
	 {
	 credentials[i][0] = config.getData(0, i, 0);
	 credentials[i][1] = config.getData(0, i, 1);
	 }
	  
	 return credentials;
	 }
	}


