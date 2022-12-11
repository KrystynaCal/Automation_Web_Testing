package pl.seleniumdemo.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedUserPage {
    @FindBy(xpath = "//*[@id=\"body-section\"]/div[1]/div/div/div[1]/h3")
    private WebElement heading;


    public LoggedUserPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public String getHeadingText() {
        return heading.getText();
    }
}
