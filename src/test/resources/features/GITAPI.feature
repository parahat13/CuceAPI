Feature: GIT API Test

Scenario: Verify number of repos
	Given I go to following link "https://github.com/"
	When I click on sign in button
	Then I enter my username "username" and password "password"
	And I click login button
	And I click on my profile
	And I should see my list of repos to be "19"
@Test	
Scenario: Verify repos names UI against API

    When I navigate to the following link "https://github.com/parahat13?tab=repositories"
	And  I make a GET request with following link "http://api.github.com/users/parahat13/repos"
	Then I validate repos' names in UI against the names in API
 





