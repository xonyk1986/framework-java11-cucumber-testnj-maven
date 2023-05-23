package definitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.RestAssured;
import org.junit.Before;
import pages.Careers;
import pages.CareersRecruit;
import support.RestWrapper;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static support.TestContext.getData;

public class CareersStepDefs {
    @And("I login as {string}")
    public void iLoginAs(String role) {
        new Careers()
                .clickLogin()
                .login(getData(role));

    }
    @Before

    @Then("I verify {string} login")
    public void iVerifyLogin(String role) {
        String actualName = new Careers().getLoggedInUser();
        assertThat(actualName).isEqualTo(getData(role).get("name"));
    }

    @When("I create {string} position")
    public void iCreateRequisition(String positionType) {
        new Careers()
                .clickRecruit()
                .clickNewPositionButton()
                .createPosition(getData(positionType));
    }

    @And("I verify {string} position created")
    public void iVerifyPositionCreated(String positionType) throws InterruptedException {
        String actualTitle = new CareersRecruit().getLastCreatedPositionTitle();
        String expectedTitle = getData(positionType).get("title");

        assertThat(actualTitle).isEqualTo(expectedTitle);
    }

    @Given("I login to REST API as {string}")
    public void iLoginToRESTAPIAs(String role) {
        new RestWrapper().login(getData(role));
    }

    @When("I create via REST API {string} position")
    public void iCreateViaRESTAPIPosition(String type) {
        new RestWrapper().createPosition(getData(type));
    }

    @Then("I verify via REST API position is created")
    public void iVerifyViaRESTAPIPositionIsCreated() throws InterruptedException {
        List<Map<String, Object>> positions = new RestWrapper().getPositions();
        Map<String, Object> lastPosition = RestWrapper.getLastPosition();

        boolean isFound = false;
        for (Map<String, Object> position : positions) {
            if (position.get("id").equals(lastPosition.get("id"))) {
                isFound = true;
                for (String key : lastPosition.keySet()) {
                    Object expected = lastPosition.get(key);
                    Object actual = position.get(key);
                    assertThat(actual).isEqualTo(expected);
                }
            }
        }
        assertThat(isFound).isTrue();
    }

    @When("I update via REST API {string} position")
    public void iUpdateViaRESTAPIPosition(String type) {
//        Map<String, String> newPositionFields = Map.of("title", "SDET", "address", "4970 El Camino Real, #110");
        Map<String, String> newPositionFields = getData(type + "_updated");
        Object id = RestWrapper.getLastPosition().get("id");

        new RestWrapper().updatePosition(newPositionFields, id);

    }

    @Then("I verify via REST API position is updated")
    public void iVerifyViaRESTAPIPositionIsUpdated() {
        Map<String, Object> expectedPosition = RestWrapper.getLastPosition();
        Map<String, Object> actualPosition = new RestWrapper().getPositionById(expectedPosition.get("id"));

        for(String key : expectedPosition.keySet()) {
            assertThat(actualPosition.get(key)).isEqualTo(expectedPosition.get(key));
        }
    }

    @And("I delete via REST API created position")
    public void iDeleteViaRESTAPICreatedPosition() {
        Map<String, Object> expectedPosition = RestWrapper.getLastPosition();
        new RestWrapper().deletePositionById(expectedPosition.get("id"));
    }
}
