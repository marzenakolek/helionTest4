package Helion4;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

import java.awt.Image;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.logging.Level;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.internal.FindsByLinkText;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.openqa.selenium.remote.DesiredCapabilities;

import pom.LogInPage;

import java.util.regex.Pattern;

import org.openqa.selenium.support.ui.ExpectedConditions;


public class HelionTest4 {
	private static final TimeUnit SECONDS = null;
	WebDriver driver ;
	protected LogInPage _li;

	@Parameters("browser")
	@BeforeMethod
	public void beforMethod(String browser) {
		
		if(browser.equalsIgnoreCase("Firefox")) {
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
		 driver = new FirefoxDriver(capabilities);
			 // driver = new FirefoxDriver();
		 
		  // If browser is IE, then do this	  
		 
		  }else if (browser.equalsIgnoreCase("Chrome")) { 
		 
			  // Here I am setting up the path for my IEDriver
		 
			  System.setProperty(
				"webdriver.chrome.driver",
				"C:\\Users\\Marzenka\\workspaceselenium\\HelionTestProject\\exe\\chromedriver.exe");
		 driver = new ChromeDriver();
		 
		  }  
		       
		driver.get("http:\\helion.pl");
	
		
		 driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		 driver.manage().window().maximize();
	}

	@Test
	public void findAllLinks() {

		//driver.get("http://helion.pl/");
		java.util.List<WebElement> links = driver.findElements(By.tagName("a"));

		System.out.println(links.size());

		for (int i = 1; i < links.size(); i = i + 1)

		{

			System.out.println(links.get(i).getText());

		}
	
	}
	@Test
	public void findBrokenLinks(){
		
		String Homepage = "http://helion.pl/";
		 String url = "";
	        HttpURLConnection huc = null;
	        int respCode = 200;
	        
			driver.get(Homepage);
			
	       
			java.util.List<WebElement> links = driver.findElements(By.tagName("a"));
	        Iterator<WebElement> it = links.iterator();
	        
	        while(it.hasNext()){
	            
	            url = it.next().getAttribute("href");
	            
	            System.out.println(url);
	        
	            if(url == null || url.isEmpty()){
	System.out.println("URL is either not configured for anchor tag or it is empty");
	                continue;
	            }
	            
	            if(!url.startsWith(Homepage)){
	                System.out.println("URL belongs to another domain, skipping it.");
	                continue;
	            }
	            
	            try {
	                huc = (HttpURLConnection)(new URL(url).openConnection());
	                
	                huc.setRequestMethod("HEAD");
	                
	                huc.connect();
	                
	                respCode = huc.getResponseCode();
	                
	                if(respCode >= 400){
	                    System.out.println(url+" is a broken link");
	                }
	                else{
	                    System.out.println(url+" is a valid link");
	                }
	                    
	            } catch (MalformedURLException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            } catch (IOException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();
	            }
	        }
	    }
	
	

	@Test
	public void scroll_PageStstus() throws IOException, InterruptedException {
		
		//driver.get("http://helion.pl/");
       
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		Boolean horzscrollStatus = (Boolean) javascript
				.executeScript("return document.documentElement.scrollWidth>document.documentElement.clientWidth;");
		
		if(horzscrollStatus = true){
			
		System.out.print("Scroll on page is present");
		}
	Assert.assertSame(true, horzscrollStatus);
		
	}

	@Test
	public void scrollPage() {
		
		//driver.get("http://helion.pl/");
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript(
				"window.scrollTo(0, document.body.scrollHeight)", "");
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		Long value = (Long) executor.executeScript("return window.pageYOffset;");
		
		System.out.print(value);
	}

	@Parameters({ "user", "password" })
	@Test
	public void _logInPage(String user, String password) {
		

		_li = PageFactory.initElements(driver, LogInPage.class);

		driver.get("https://gitarownia.iai-shop.com/signin.php");
		// _li.GoToUrl("https://gitarownia.iai-shop.com/signin.php");
		// _li.CenterElement(_li.txtsignin_login_input);

		_li.txtsignin_login_input.sendKeys(user);
		_li.txtsignin_pass_input.sendKeys(password);
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
		javascript.executeScript(
				"window.scrollTo(0, document.body.scrollHeight)", "");
		_li.btnsignin_button.click();
		Assert.assertEquals("Witamy, Twoje konto | Wyloguj siê",
				_li.txtkonto.getText());
		
	}

	@Test
	public void extrctCookie() {
		
		//driver.get("http://helion.pl/");
		// Get and extract all cookies of google domain and print cookie
		// parameters.
		Set<Cookie> totalCookies = driver.manage().getCookies();
		System.out.println("Total Number Of cookies : " + totalCookies.size());

		for (Cookie currentCookie : totalCookies) {
			System.out.println(String.format("%s -> %s -> %s -> %s",
					"Domain Name : " + currentCookie.getDomain(),
					"Cookie Name : " + currentCookie.getName(),
					"Cookie Value : " + currentCookie.getValue(),
					"Cookie Expiry : " + currentCookie.getExpiry()));
		}
		
	}

	@Test
	public void addCookie() {
		
		//driver.get("http://helion.pl/");
		// Set cookie value and then add It for current domain.
		Cookie name = new Cookie("myCookie", "WSfed-ffsd-234DFGe-YUTYU");
		driver.manage().addCookie(name);

		// Get all cookies and print them.
		Set<Cookie> totalCookies = driver.manage().getCookies();
		System.out.println("Total Number Of cookies : " + totalCookies.size());

		for (Cookie currentCookie : totalCookies) {
			System.out.println(String.format("%s -> %s -> %s", "Domain Name : "
					+ currentCookie.getDomain(), "Cookie Name : "
					+ currentCookie.getName(), "Cookie Value : "
					+ currentCookie.getValue()));
		}
		
	}

	@Test
	public void deleteCookie() {
		
		//driver.get("http://helion.pl/");
		// Add 2 test cookies for your domain.
		Cookie cookie1 = new Cookie("testCookie1", "WSfed-ffsd-234DFGe-YUTYU");
		Cookie cookie2 = new Cookie("testCookie2", "xxCDFSS-234DDs-423SS2-34EE");
		driver.manage().addCookie(cookie1);
		driver.manage().addCookie(cookie2);

		// Get and print all cookies for google domain after adding cookies
		// manually.
		Set<Cookie> totalCookies1 = driver.manage().getCookies();
		System.out.println("Total Number Of cookies : " + totalCookies1.size());

		for (Cookie currentCookie : totalCookies1) {
			System.out.println(String.format("%s -> %s -> %s", "Domain Name : "
					+ currentCookie.getDomain(), "Cookie Name : "
					+ currentCookie.getName(), "Cookie Value : "
					+ currentCookie.getValue()));
		}

		// Deleting specific testCookie2 cookie.
		System.out.println();
		driver.manage().deleteCookieNamed("testCookie2");

		// Get and print all cookies for google domain after deleting cookie
		// manually.
		Set<Cookie> totalCookies2 = driver.manage().getCookies();
		System.out
				.println("Total Number Of cookies after deleting one cookie : "
						+ totalCookies2.size());

		for (Cookie currentCookie : totalCookies2) {
			System.out.println(String.format("%s -> %s -> %s", "Domain Name : "
					+ currentCookie.getDomain(), "Cookie Name : "
					+ currentCookie.getName(), "Cookie Value : "
					+ currentCookie.getValue()));
		}

	}
	@Test
	public void iconSocialActive() throws InterruptedException{
		
		//driver.get("http://helion.pl/");
		
		//driver.manage().window().maximize();
		
		              
		                 
		           String handle = driver.getWindowHandle(); // get the current window handle
		           System.out.println(handle);  
		           driver.findElement(By.linkText("YouTube")).click(); // click some link that opens a new window

		              for (String winHandle : driver.getWindowHandles()) {
		                  driver.switchTo().window(winHandle);
		                  System.out.print(winHandle);// switch focus of WebDriver to the next found window handle (that's your newly opened window)
		              }
                       Thread.sleep(1000);
		              //code to do something on new window
                      
		              driver.close(); // close newly opened window when done with it
		              driver.switchTo().window(handle); // switch back to the original window
		             // }
		             
	}
	@Test
	 public void printPageErrors() throws Exception {
		//driver.get("http://helion.pl/");
		GetJSErrosLog();
		
		//WebDriver _driver = new ChromeDriver();
		//_driver.get("http://helion.pl/");
	
		
		
			  
			  DesiredCapabilities cap = DesiredCapabilities.chrome();

			  // Set logging preference In Google Chrome browser capability to log
			  // browser errors.
			  LoggingPreferences pref = new LoggingPreferences();
			  pref.enable(LogType.BROWSER, Level.ALL);
			  cap.setCapability(CapabilityType.LOGGING_PREFS, pref);
			
			 }


			 // Function to capture JSError log.
			 public void GetJSErrosLog() {
				
			  // Capture all JSerrors and print In console.
			  LogEntries jserrors = driver.manage().logs().get(LogType.BROWSER);
			  for (LogEntry error : jserrors) {
			   System.out.println(error.getMessage());
			  }
			 }
	
	@AfterMethod
	public void afterMethod() {
      
		driver.close();
	}

}
