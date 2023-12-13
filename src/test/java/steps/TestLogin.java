package steps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import pages.HomePage;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.base.Base;

public class TestLogin{
	
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

	//load the pages with same driver
	@Given("I open the application")
	public void i_open_the_application() throws IOException {
		homePage = new HomePage(Base.getDriver());
        loginPage = new LoginPage(Base.getDriver());
        loggedInPage = new LoggedInPage(Base.getDriver());
        //then copy the email property into local String email
        LoadProperties();
	}

	@Given("Click on Login")
	public void click_on_login() {
        homePage.clickOnLogin();
	}

	@When("I give the email")
	public void i_give_the_email() {
		loginPage.email.sendKeys(email);
	}

	@When("Click continue")
	public void click_continue() throws InterruptedException {
		Thread.sleep(2000);
		loginPage.continueBtn.click();
	}

	@When("Give the OTP and click Submit")
	public void give_the_otp_and_click_submit() throws InterruptedException {
		Thread.sleep(30000);
        loginPage.verifyOtpBtn.click();
	}

	@Then("I go to the My Account then My Profile")
	public void i_go_to_the_my_account_then_my_profile() throws InterruptedException {
		loggedInPage.goToProfile();
	}

	@Then("verify the email")
	public void verify_the_email() {
		System.out.println(loggedInPage.getProfileEmail());
		Assert.assertEquals(email,loggedInPage.getProfileEmail());
	}
}
