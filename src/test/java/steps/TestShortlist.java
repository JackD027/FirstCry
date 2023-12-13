package steps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.base.Base;

public class TestShortlist {
	
	HomePage homePage;
	LoginPage loginPage;
	LoggedInPage loggedInPage;
	
	String email;
	
	public void LoadProperties() throws IOException {
        FileReader reader = new FileReader(".\\src\\test\\resources\\application.properties");
        Properties props = new Properties();
        props.load(reader);
        email = props.getProperty("email");
    }
	
	@Given("I open the browser and enter URL of Firstcry")
	public void i_open_the_browser_and_enter_url_of_firstcry() throws IOException {
		homePage = new HomePage(Base.getDriver());
        loginPage = new LoginPage(Base.getDriver());
        loggedInPage = new LoggedInPage(Base.getDriver());
        LoadProperties();
	}

	@Then("I click on login link and enter the email")
	public void i_click_on_login_link_and_enter_the_email() throws InterruptedException {
		homePage.clickOnLogin();
        loginPage.email.sendKeys(email);
        Thread.sleep(2000);
        loginPage.continueBtn.click();
        Thread.sleep(30000);
        loginPage.verifyOtpBtn.click();
	}

	@Then("I click on Footwear and click on Casualshoes product")
	public void i_click_on_footwear_and_click_on_casualshoes_product() throws InterruptedException {
		loggedInPage.goToFootwear();
	    Thread.sleep(2000);
	    loggedInPage.casualShoes.click();
	}

	@Then("I click on Products on the page")
	public void i_click_on_products_on_the_page() throws InterruptedException {
		loggedInPage.product.click();
		loggedInPage.goToProductPage();
	}

	@Then("I click on ShortList Icon")
	public void i_click_on_short_list_icon() throws InterruptedException {
		//loggedInPage.selectshoesize();
	    LoggedInPage.ShortListBTN.click();
	    Thread.sleep(2000);
	}

	@Then("I Go to ShortList Page")
	public void i_go_to_short_list_page() throws InterruptedException {
	    LoggedInPage.ShortListIcon.click();
	    Thread.sleep(5000);
	    loggedInPage.goBack();
	}
}
