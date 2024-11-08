package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Header {
    private final WebDriver driver;

    @FindBy(xpath = "//*[contains(@class, 'mega-menu__link')][.='Поиск работы']")
    public WebElement searchJobLink;

    @FindBy(xpath = "//*[contains(@class, 'mega-menu__link')][.='Поиск работников']")
    public WebElement searchEmployees;

    @FindBy(css = "a.tabs__item")
    public WebElement tabEmployer;

    @FindBy(xpath = "//*[contains(@class, 'breadcrumbs__item-link_active')]")
    public WebElement breadcrumbs;

    @FindBy(xpath = "//a[contains(@class, 'mega-menu__user-button')]")
    public WebElement enterButton;


    //---===Мега-меню
    @FindBy(xpath = "//*[@class = 'mega-menu__toggle']")
    public WebElement megaMenu;

    @FindBy(xpath = "//*[.= 'Все услуги']")
    public WebElement allServices;
    //---=============

    public Header(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getBreadcrumbsText() {
        return breadcrumbs.getText();
    }
}
