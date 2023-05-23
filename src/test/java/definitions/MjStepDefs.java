package definitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getDriver;

public class MjStepDefs {
    @Given("I open MJ and verify")
    public void iOpenMJandVerify() {

        getDriver().get("https://mjplatform.com/login");
       // assertThat(getDriver().findElement(By.xpath("//div[@class='text-danger']")).getText()).containsIgnoringCase("Chrome and Firefox");
    }

    @Then("I try to login")
    public void iTryToLogin() throws InterruptedException {
        getDriver().findElement(By.xpath("//input[@id='name']")).sendKeys("user");
        getDriver().findElement(By.xpath("//input[@id='password']")).sendKeys("psssw");
        getDriver().findElement(By.xpath("//button[@type='submit']")).click();
        assertThat(getDriver().findElement(By.xpath("div[@class='toastr animated rrt-error']")).isDisplayed());


    }
}
