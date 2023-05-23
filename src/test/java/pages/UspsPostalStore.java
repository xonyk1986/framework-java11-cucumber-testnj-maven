package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

public class UspsPostalStore extends Page {

    @FindBy(xpath = "//select[@id='Ns']")
    private WebElement sortBy;

    @FindBy(xpath="(//div[@class='results-product-info'])[1]")
    private WebElement firstFoundItem;

    @FindBy(xpath="//div[@class='results-product-info']")
    private List<WebElement> foundItems;

    @FindBy(xpath="//div[@class='result-checkbox-filter-by-holder']")
    private WebElement leftFilterBar;

    @FindBy(xpath="//input[@id='store-search']")
    private WebElement searchIntput;

    @FindBy(xpath="//input[@id='store-search-btn']")
    private WebElement searchButton;

    public void searchFor(String search) {
        searchIntput.sendKeys(search);
        searchButton.click();
    }

    public void selectSortBy(String text) {
        new Select(sortBy).selectByVisibleText(text);
    }
    public String getFirstFoundItem() {
        return firstFoundItem.getText();
    }

    public String getLeftFilters() {
        return leftFilterBar.getText();
    }

    public boolean isCheapestItem(String name) throws ParseException {
        for (WebElement item : foundItems) {
            if (item.getText().contains(name)) {
                By priceSelector = By.xpath("//div[@class='results-product-preview-price']");
                String itemPrice = item.findElement(priceSelector).getText();
                String firstFoundPrice = firstFoundItem.findElement(priceSelector).getText();

                // deal with currency
//                NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("en", "US"));
//                double itemPriceNumber = formatter.parse(itemPrice).doubleValue();
//                double firstPriceNumber = formatter.parse(firstFoundPrice).doubleValue();
//
                return itemPrice.equals(firstFoundPrice);
            }
        }
        return false;
    }
}
