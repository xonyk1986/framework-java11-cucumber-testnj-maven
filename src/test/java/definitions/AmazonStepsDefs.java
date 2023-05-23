package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Amazon;

import static support.TestContext.getDriver;

public class AmazonStepsDefs {
    @Given("Amazon Page")
    public void amazonPage() {
        getDriver().get("https://www.amazon.com");

    }

    @When("Click Login")
    public void clickLogin() {
        Amazon amazon = new Amazon();
        amazon.loginToAmazon("anton.shorygin@gmail.com");

    }


    @Then("Sign in")
    public void signIn() {

    }
}
