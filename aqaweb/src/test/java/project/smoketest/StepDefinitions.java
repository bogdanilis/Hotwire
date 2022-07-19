package project.smoketest;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StepDefinitions extends CommonDefinitions {

    // GIVEN Steps

    @Given("^I open the browser$")
    public void initialize() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Given("^I close the browser$")
    public void tearDown() {
        driver.close();
        driver.quit();
    }

    @Given("^I Navigate To \"(.*)\" Page$")
    public void i_Navigate_To_Page(String value) {
        driver.navigate().to(value);
    }

    // WHEN Steps

    @When("^I Click On \"(.*)\" Button$")
    public void i_Click_On_Button(String nameOfButtonToClickOn) {
        wait10AndClick(nameOfButtonToClickOn);
    }

    @When("^I Insert \"(.*)\" Keywords Into \"(.*)\"$")
    public void i_Insert_Keywords(String stringToGoToTextBox, String statedTextBox) {
        wait10AndType(environment.resolve(statedTextBox), stringToGoToTextBox);
    }

    // THEN Steps


    @Then("^I Verify That \"(.*)\" Element Is Loaded$")
    public void i_Verify_That_Element_Is_Loaded(String element) {
        Assert.assertTrue(findElementByXPath(environment.resolve(element)).isDisplayed());
    }


    @After
    public void afterScenario(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            Path path = Paths.get(SCREENSHOTS_PATH, scenario.getName() + ".png");
            FileUtils.writeByteArrayToFile(path.toFile(), screenshot);
        }
    }
}

