package pom;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class LogInPage 
	 //extends POMBase
	    {public WebDriver driver;
	       // public void LogIn(WebDriver driver) : base(driver);
	       
	      //  }

	        @FindBy(id = "signin_login_input")
			public
	        WebElement txtsignin_login_input;
	        @FindBy(id = "signin_pass_input")
			public
	        WebElement txtsignin_pass_input;
	        @FindBy(className = "signin_button")
			public
	        WebElement btnsignin_button;
	        @FindBy(className = "btn signin_button")
	        WebElement linklogin;
	        @FindBy(id = "menu_additional")
			public
	        WebElement txtkonto;
	        

			
		}
 class SeleniumBase{
    
       public WebDriver driver;

        public void SeleniumBase( WebDriver driver){}
        
          

        public WebDriver GetDriver()
        {
            if (driver == null)
                driver = new ChromeDriver();
            

            return driver;
        }
    }
    
     class POMBase {
    	public WebDriver driver;
      
			// TODO Auto-generated constructor stub
		

	// public POMBase(WebDriver driver) :  base(driver)
       // { }

       // public void GoToURL(String url)
       // {
       //     _driver.navigate().to(url);
         //   try {
		//		Thread.sleep(5000);
		//	} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			//	e.printStackTrace();
			
        //    PageFactory.initElements(_driver, this);
       // }

      // public void CenterElement1(WebElement element)
        //{
        //JavascriptExecutor javascript = (JavascriptExecutor) driver;
  	 // javascript.executeScript("arguments[0].scrollIntoView(true);",element);
            
        

       

		public void Maximize1()
        {
             driver.manage().window().maximize();
             PageFactory.initElements(driver, this);
        }

        public void Close1()
        {
            driver.close();
        }

  

	public void CenterElement1(WebElement element) {
		// TODO Auto-generated method stub
		JavascriptExecutor javascript = (JavascriptExecutor) driver;
	  	  javascript.executeScript("arguments[0].scrollIntoView(true);",element);
	  	 PageFactory.initElements(driver, this);
	}

	public void GoToUrl1(String string) {
		// TODO Auto-generated method stub
		  driver.navigate().to(string);
          try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
          PageFactory.initElements(driver, this);
	}

	//public void CenterElement(WebElement txtsignin_login_input2) {
		// TODO Auto-generated method stub
		
	

	//public void CenterElement(WebElement txtsignin_login_input2) {
		// TODO Auto-generated method stub
		
	}

	    


