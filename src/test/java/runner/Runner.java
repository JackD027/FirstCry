package runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features= {"src\\test\\resources\\features"},                          
                 glue= {"steps","pages.base"},//steps is a package name
                 plugin = {"html:target/HTMLReport.html",
                		   "json:target/JSONReport.json",
                		 "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:", //to generate extent report and give path where it reside
           			     "timeline:test-output-thread/"})
public class Runner{

}