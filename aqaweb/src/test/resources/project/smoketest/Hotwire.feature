Feature: Hotwire - Happy Flow

  Scenario: Startup
    Given I open the browser

  Scenario: Happy flow - Hotwire website
    Given I Navigate To "https://www.hotwire.com" Page
    And I Click On "//*[contains(text(),'Bundles')]" Button
    And I Click On "//button[@data-bdd='farefinder-package-bundleoption-car']" Button
    And I Insert "SFO" Keywords Into "(//input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis'])[1]"
    And I Click On "//a[@class='active']" Button
    And I Insert "LAX" Keywords Into "(//input[@class='form-control hw-input hw-input-icon type__400__regular text-ellipsis'])[2]"
    And I Click On "//a[@class='active']" Button
    And I Insert "Evening" Keywords Into "//select[@data-bdd='farefinder-package-pickuptime-input']"
    When I Click On "//*[contains(text(),'Find a deal')]" Button
    # Then does not have permission

  Scenario: Teardown
    Given I close the browser
	