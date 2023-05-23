package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Amazon extends Page{
    @FindBy(xpath="//a[@data-nav-ref=\"nav_ya_signin\"]")
    private WebElement loginPage;

    @FindBy(xpath="//input[@name='email']")
    private WebElement loginName;

    @FindBy(xpath="//span[@id=\"continue\"]")
    private WebElement continueButton;



    public void loginToAmazon(String email) {
        loginPage.click();
        loginName.sendKeys(email);
        continueButton.click();
    }

}
