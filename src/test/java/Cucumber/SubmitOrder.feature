@tag
Feature: Purchase the order from Ecommerce Website
  I want to use this template for my feature file

	Background:
	Given I landed on Ecommerce Page
	

  @Regression
  Scenario Outline: Positive test of submitting the order
    Given Logged in with username <username> and password <password>
    When User add product <productname> to Cart
    And Checkout <productname> and submit the order
    Then "THANKYOU FOR THE ORDER." message is displayed on ConfirmatinPage

    Examples: 
      | username				  | password		|productname |
      | anshika@gmail.com | Iamking@000	|ZARA COAT 3 |
    

      