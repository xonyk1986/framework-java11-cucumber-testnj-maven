package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static support.TestContext.getDriver;

public class Usps extends Page {

    public Usps() {
        setUrl("https://www.usps.com/");
    }

    @FindBy(xpath = "//a[@id='stampButton']")
    private WebElement stampsButton;

    @FindBy(xpath = "//a[@id='orderButton']")
    private WebElement boxesButton;

    @FindBy(xpath = "//a[@id='quickMenuButtonShip']")
    private WebElement labelsButton;

    @FindBy(xpath = "//input[@id='trackButton']")
    private WebElement trackButton;

    public void clickLabels() {
        labelsButton.click();
    }

    public void clickTracking() {
        trackButton.click();
    }

    public void clickStamps() {
        stampsButton.click();
    }

    public void clickBoxes() {
        boxesButton.click();
    }

    public void clickMenuItem(String menu) {
        getDriver().findElement(By.xpath("//a[@class='menuitem'][text()='" + menu + "']")).click();
    }
}
