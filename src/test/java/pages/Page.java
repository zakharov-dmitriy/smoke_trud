package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Page {
    protected WebDriver driver;
    protected WebDriverWait wait;
    public Header header;

    @FindBy(css = ".content__title")
    public WebElement title;

    @FindBy(xpath = "//*[@class = 'choose-region__tooltip']//button[.='Да']")
    public WebElement region;

    @FindBy(xpath = "//*[@class = 'cookies']//button")
    public WebElement acceptCookie;

    public Page(WebDriver driver, WebDriverWait wait, String subUrl) {
        this.driver = driver;
        this.wait = wait;
        header = new Header(driver);
        PageFactory.initElements(driver, this);
        this.subUrl = subUrl;
    }

    public Page(WebDriver driver, WebDriverWait wait) {
        this(driver, wait, "");
    }

    public Page(WebDriver driver) {
        this.driver = driver;
        header = new Header(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.navigate().to(getPageUrl());
    }

    private String url = "https://trudvsem.ru";
//    private String url = "https://web-hotfix-test14.k8s.trudvsem.ru";
    private String subUrl = "";

    protected String getPageUrl() {
        return url + subUrl;
    }

    public String getTitle() {
        return title.getText();
    }

    public void acceptCookiesAndRegion() {
        region.click();
        acceptCookie.click();
    }
}
