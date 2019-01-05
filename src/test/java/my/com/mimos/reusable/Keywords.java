package my.com.mimos.reusable;

import atu.testng.reports.ATUReports;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class Keywords {
    static WebDriver driver;
    static WebDriverWait wait;
    public static void initialize(){
    //Git test    
    	{
            System.setProperty("atu.reporter.config", "atu.properties");
        }
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("Project.properties"));
        }
        catch(Exception e)
        {
            Reporter.log("Project Properties File Not Found");
        }
        switch(p.getProperty("browser","gc"))
        {
            case "gc": case"chrome": case "google chrome":
            	String downloadFilepath = "E:\\";
            	HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            	chromePrefs.put("profile.default_content_settings.popups", 0);
            	chromePrefs.put("download.default_directory", downloadFilepath);
            	ChromeOptions gcopt = new ChromeOptions();
            	gcopt.setExperimentalOption("prefs", chromePrefs);
                gcopt.addArguments("--disable-notifications");
                gcopt.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                driver = new ChromeDriver(gcopt);
                break;
            case "ff": case"firefox":
                FirefoxOptions ffopt = new FirefoxOptions();
                ffopt.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
                driver = new FirefoxDriver(ffopt);
                break;
            case "ie": case"internetexplorer": case"explorer": case "internet explorer":
                System.setProperty("webdriver.ie.driver","drivers/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                ChromeOptions gcdopt = new ChromeOptions();
                gcdopt.setAcceptInsecureCerts(true);
                System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                driver = new ChromeDriver(gcdopt);
                break;
        }
        ATUReports.setWebDriver(driver);
        wait= new WebDriverWait(driver,30);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        ATUReports.add("Browser Initialized",p.getProperty("browser","chrome"), LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
    }

    public static void navigate(String url)
    {
        ATUReports.add("URL before Navigate",driver.getCurrentUrl(),LogAs.INFO,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        driver.get(url);
        ATUReports.add("Navigate to URL",url,LogAs.INFO,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        ATUReports.add("URL after Navigation",driver.getCurrentUrl(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
    }

    private static WebElement findElement(By loc)
    {
        WebElement e=null;
        try
        {
            wait.until(ExpectedConditions.presenceOfElementLocated(loc));
            e = driver.findElement(loc);
            ATUReports.add("Element located",loc.toString(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(NoSuchElementException e1)
        {
            try
            {
                e = frame(loc);
                ATUReports.add("Element located on frame",loc.toString(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
            }
            catch(Exception e2)
            {
                ATUReports.add("Element not found",loc.toString(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
            }

        }
        return e;
    }

    private static WebElement frame(By loc)
    {
        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        List<WebElement> frames = driver.findElements(By.tagName("frame"));
        if(frames.size()==0)
        {
            frames = driver.findElements(By.tagName("iframe"));
        }
        for(WebElement frame:frames)
        {
            driver.switchTo().frame(frame);
            if(driver.findElements(loc).size()>0) {
                driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
                return driver.findElement(loc);
            }
        }
        return null;
    }

    public static void click(By loc)
    {
        ATUReports.add("URL before click",driver.getCurrentUrl(),LogAs.INFO,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        try
        {
            wait.until(ExpectedConditions.visibilityOf(findElement(loc)));
            wait.until(ExpectedConditions.elementToBeClickable(findElement(loc)));
            findElement(loc).click();
            ATUReports.add("Click performed successfully",loc.toString(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(StaleElementReferenceException e)
        {
        	try
        	{
        		click(loc);
        	}
        	catch(Exception e1)
        	{
            ATUReports.add("Element missing on current page but was found earlier",loc.toString(),LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        	}
        }
        catch(WebDriverException e)
        {
            Actions act = new Actions(driver);
            act.click(findElement(loc)).build().perform();
            ATUReports.add("Mouse Click performed as the element is not clickable",loc.toString(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(Exception e)
        {
            ATUReports.add("Click on element failed",loc.toString(),LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        ATUReports.add("URL after Click",driver.getCurrentUrl(),LogAs.INFO,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
    }

    public static void type(By loc, String val){
        try{
            findElement(loc).clear();
            findElement(loc).sendKeys(val);
            ATUReports.add("Type text into "+loc.toString(),val,LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(Exception e)
        {
            ATUReports.add("Type text into "+loc.toString()+" Failed", val, LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    public static void assertTitle(String title){
        try {
            Assert.assertEquals(driver.getTitle(), title);
            ATUReports.add("Title assertion",title, driver.getTitle(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(AssertionError e)
        {
            ATUReports.add("Title assertion",title,driver.getTitle(),LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    public static void assertText(By loc, String text){
        try{
            Assert.assertEquals(findElement(loc).getText(),text);
            ATUReports.add("Text assertion",text,findElement(loc).getText(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(AssertionError e)
        {
            ATUReports.add("Title assertion",text,findElement(loc).getText(),LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }

    public static void switch_window(int count){
        try {
            driver.switchTo().window(driver.getWindowHandles().toArray()[count - 1].toString());
            ATUReports.add("Switch Window",driver.getTitle(),LogAs.PASSED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
        catch(Exception e)
        {
            ATUReports.add("Switch Window failed",e.getMessage(), LogAs.FAILED,new CaptureScreen(CaptureScreen.ScreenshotOf.BROWSER_PAGE));
        }
    }
    
    public static void quit() {
    	driver.quit();
    }

}
