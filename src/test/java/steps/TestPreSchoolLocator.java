package steps;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.HomePage;
import pages.LoggedInPage;
import pages.LoginPage;
import pages.PreSchoolPage;
import pages.base.Base;

public class TestPreSchoolLocator {
	
	HomePage homePage;
	LoginPage loginPage;
	LoggedInPage loggedInPage;
	PreSchoolPage preSchoolPage;
	
	String email;
	
	public void LoadProperties() throws IOException {
        FileReader reader = new FileReader(".\\src\\test\\resources\\application.properties");
        Properties props = new Properties();
        props.load(reader);
        email = props.getProperty("email");
    }

	@Given("I open browser and enter the URL")
	public void i_open_browser_and_enter_the_url() throws IOException {
		homePage = new HomePage(Base.getDriver());
        loginPage = new LoginPage(Base.getDriver());
        loggedInPage = new LoggedInPage(Base.getDriver());
        preSchoolPage = new PreSchoolPage(Base.driver);
        LoadProperties();
	}

	@Then("I click on login link and enter the email then click continue")
	public void i_click_on_login_link_and_enter_the_email_then_click_continue() throws InterruptedException {
		homePage.clickOnLogin();
        loginPage.email.sendKeys(email);
        Thread.sleep(2000);
        loginPage.continueBtn.click();
	}

	@Then("I give the OTP and click submit")
	public void i_give_the_otp_and_click_submit() throws InterruptedException {
		Thread.sleep(30000);
        loginPage.verifyOtpBtn.click();
	}

	@Then("I hover on Stores and PreSchool then Select Find PreSchool")
	public void i_hover_on_stores_and_pre_school_then_select_find_pre_school() throws InterruptedException {
	    loggedInPage.goToPreSchoolPage();
	    Thread.sleep(1000);
	}

	@Then("I click on PreSchool Locator location as {string}")
	public void i_click_on_pre_school_locator(String string) throws InterruptedException {
		preSchoolPage.preSchoolLocatorBtn.click();
		Thread.sleep(2000);
		preSchoolPage.selectLocation(string);
		Thread.sleep(1000);
		preSchoolPage.findSchoolBtn.click();
		Thread.sleep(5000);
	}
	
	@Then("I validate the PreSchool location is {string}")
	public void i_validate_the_preschool(String string) throws InterruptedException {
		Assert.assertEquals(preSchoolPage.location.getText(), string);
		preSchoolPage.goBack();
	}
}
