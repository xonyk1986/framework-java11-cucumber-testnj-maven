package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.Careers;
import pages.QuoteForm;
import pages.QuoteResult;
import pages.Usps;

import static org.assertj.core.api.Assertions.assertThat;

public class QuoteObjectStepDefs {

    @Given("I go to {string} page oop")
    public void iGoToPageOop(String page) {
        switch (page) {
            case "quote":
                new QuoteForm().open();
                break;
            case "careers":
                new Careers().open();
                break;
            case "usps":
                new Usps().open();
                break;
            default:
                throw new RuntimeException("Unknown page: " + page);
        }
    }

    @When("I fill out required fields oop")
    public void iFillOutRequiredFieldsOop() {
        QuoteForm form = new QuoteForm();
        form.fillUsername("skryabin");
        form.fillEmail("slava@skryabin.com");
        form.fillPassword("welcome");
        form.fillConfirmPassword("welcome");
        form.fillName("Slava", "Vlad", "Skryabin");
        form.clickPrivacy();
    }

    @And("I submit the form oop")
    public void iSubmitTheFormOop() throws InterruptedException {
        new QuoteForm().clickSubmit();
        Thread.sleep(3000);
    }

    @Then("I verify required fields oop")
    public void iVerifyRequiredFieldsOop() {
        QuoteResult resultPage = new QuoteResult();
        String actualResultText = resultPage.getSectionResult();
        String actualPrivacyPolicy = resultPage.getPrivacyPolicy();

        assertThat(actualPrivacyPolicy).isEqualTo("true");
        assertThat(actualResultText).contains("skryabin");
        assertThat(actualResultText).contains("slava@skryabin.com");
        assertThat(actualResultText).doesNotContain("welcome");
        assertThat(actualResultText).contains("Slava Vlad Skryabin");

    }
}
