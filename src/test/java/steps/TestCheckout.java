package steps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.bouncycastle.mime.MimeWriter;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.base.Base;

public class TestCheckout {

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
	
	@Given("I open the browser and enter the app URL")
	public void i_open_the_browser_and_enter_the_app_url() throws IOException {
		homePage = new HomePage(Base.getDriver());
        loginPage = new LoginPage(Base.getDriver());
        loggedInPage = new LoggedInPage(Base.getDriver());
        LoadProperties();
	}

	@Then("I click on the login link and enter email and submit OTP")
	public void i_click_on_the_login_link_and_enter_email_and_submit_otp() throws InterruptedException {
		homePage.clickOnLogin();
        loginPage.email.sendKeys(email);
        Thread.sleep(2000);
        loginPage.continueBtn.click();
        Thread.sleep(30000);
        loginPage.verifyOtpBtn.click();
	}

	@Then("I open Cart and Proceed to Checkout")
	public void i_open_cart_and_proceed_to_checkout() {
		loggedInPage.goToCart.click();
		loggedInPage.placeOrderBtn.click();
	}

	@Then("I select COD and then submit to check for the status order placed")
	public void i_select_cod_and_then_submit_to_check_for_the_status_order_placed() throws InterruptedException {
		loggedInPage.selectCOD.click();
		System.out.println(loggedInPage.orderValue.getText());
		Thread.sleep(5000);
	}
}
