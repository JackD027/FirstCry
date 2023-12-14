Feature: To test Edit Profile functionality

	Scenario Outline: Test edit profile after login

    Given I open the browser and enter the application URL 	
		Then I click on the login link and enter the email and submit OTP
		Then I go to the My Account then My Profile Section
		And I edit the profile data <Name>,<Day>,<Month>,<Year>,<Gender>,<Weight>,<Height>
		
		Examples:
			|Name     |Day|Month|Year|Gender|Weight|Height|
			|"Shubham"|"11"|"May"|"2021"|"Male"|"56"|"120"|
		 

    
