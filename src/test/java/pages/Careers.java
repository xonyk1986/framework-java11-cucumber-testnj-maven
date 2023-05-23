package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Careers extends Page {

    public Careers() {
        setUrl("https://skryabin-careers.herokuapp.com/");
    }

    @FindBy(xpath="//a[@href='/login']")
    private WebElement loginButton;

    @FindBy(xpath="//a[@href='/recruit']")
    private WebElement recruitButton;

    @FindBy(xpath="//span[@class='logout-box']/a")
    private WebElement loggedInUser;

    public CareersRecruit clickRecruit() {
        recruitButton.click();
        return new CareersRecruit();
    }

    public CareersLogin clickLogin() {
        loginButton.click();
        return new CareersLogin();
    }

    public String getLoggedInUser() {
        return loggedInUser.getText();
    }


}
