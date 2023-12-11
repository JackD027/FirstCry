package pages.base;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;


public class Base {
	
	String baseURL;
	public static WebDriver driver;
	
	public void LoadProperties() throws IOException {
        FileReader reader = new FileReader(".\\src\\test\\resources\\application.properties");
        Properties props = new Properties();
        props.load(reader);
        baseURL = props.getProperty("baseURL");
    }

    @Before
    public void OpenBrowser() throws IOException {
    	LoadProperties(); 
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(baseURL);
    }

    @After
    public void CloseBrowser() {
        driver.close();
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
