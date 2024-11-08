package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage extends Page {

    private final JavascriptExecutor jsExecutor;
//    private final String url = "https://trudvsem.ru";

    @FindBy(xpath = "//*[@class = 'choose-region__tooltip']//button[.='Да']")
    public WebElement region;

    @FindBy(xpath = "//*[@class = 'cookies']//button")
    public WebElement acceptCookie;

    @FindBy(xpath = "//*[@class='  col-md-12   ']//*[@class ='main__search']//*[@class='content__title']")
    public WebElement titleEmployees;

    @FindBy(css = ".home__search-control")
    private WebElement searchInput;

    @FindBy(css = ".home__search-button")
    private WebElement searchButton;

    @FindBy(css = ".search-content__button")
    public WebElement searchBtnOnPage;

    @FindBy(css = "a.tabs__item")
    private WebElement tabEmployer;

    @FindBy(xpath = "//*[@class='main__search']//*[@class='content__title']")
    public WebElement searchVacancy;

    @FindBy(xpath = "//*[contains(@class,'search-layout')]//*[@class='content__title']")
    public WebElement searchCV;

    @FindBy(xpath = "//*[@class= 'main__search-content main__search-content_active']")
    public WebElement searchContent;

    @FindBy(xpath = "//*[@infoblock-name='Работа для всех']//*[contains(@class, 'card_flexible')]")
    public List<WebElement> cardBtn;

    @FindBy(xpath = "(//h3[contains(@class,'search-results-full-card__title')]//a)[1]")
    public WebElement titleVacancyFullCard;

    @FindBy(xpath = "(//*[contains(@class,'search-results-full-card__title')])[1]")
    public WebElement titleCvFullCard;

    @FindBy(xpath = "//*[contains(@class,'search-results-full-card__extra-info')]/a")
    public WebElement companyFullCard;

    @FindBy(xpath = "//*[contains(@class,'search-results-full-card__title-section')]/a")
    public WebElement companyNameFullCard;

    @FindBy(xpath = "//*[@data-target='#about-company']")
    public WebElement aboutCompanyTab;

    @FindBy(xpath = "//*[contains(@class, 'breadcrumbs__item-link_active')]/span")
    public WebElement breadcrumbs;

    @FindBy(xpath = "//*[@name='search']")
    public WebElement searchField;

    @FindBy(tagName = "h1")
    public WebElement titleCard;

    @FindBy(xpath = "//*[.='Краткосрочная занятость']")
    public WebElement shortJobTab;

    public String messageInCaseOfFailedTest = "Неверная страница";

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait, "");
        PageFactory.initElements(driver, this);
        jsExecutor = (JavascriptExecutor) driver;
    }

    public String getTitle() {
        return title.getText();
    }

    public void clickOnTab() {
        tabEmployer.click();
    }

    public void fillFieldSearch(String text) {
        searchInput.sendKeys(text);
    }

    public void searchVacancy(String text) {
        searchInput.sendKeys(text);
        searchButton.click();
    }

    public boolean titleContainsText(String text) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElement(titleEmployees, "Вакансии на должность"));
        return wait.until(driver -> titleEmployees.getText().contains(text));
    }

    public String checkTitle(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(searchContent));
        return element.getText();
    }

    public void clickOnCard() {
//        for (WebElement webElement : cardBtn) {
//            jsExecutor.executeScript("arguments[0].click(true)", cardBtn.get(0));
//        }
        jsExecutor.executeScript("arguments[0].click(true)", cardBtn.get(0));
    }

    public void clickOnTitleVacancyFullCard() {
        try {
            Thread.sleep(1000); // Задержка на 1 секунды
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickElement(titleVacancyFullCard);
    }

    public String getCompanyName() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return companyFullCard.getText();
    }

    private void clickElement(WebElement element) {
        try {
            element.click();
        } catch (ElementClickInterceptedException e) {
            jsExecutor.executeScript("arguments[0].click(true)", element);
        }
    }

    public void clickOnCompanyNameFullCard() {
        clickElement(aboutCompanyTab);
        clickElement(companyNameFullCard);
    }

    public void clickOnTitleCvFullCard() {
        try {
            Thread.sleep(1000); // Задержка на 1 секунду
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        clickElement(titleCvFullCard);
    }

    public boolean titleCvCard(String text) {
        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); //ожидание открытия новой вкладки
        return wait.until(driver -> titleCard.getText().contains(text));
    }

    public boolean titleOnPage(String text) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        wait.until(ExpectedConditions.textToBePresentInElement(titleEmployees, "Вакансии на должность"));
//        wait.until(ExpectedConditions.numberOfWindowsToBe(2)); //ожидание открытия новой вкладки
        return wait.until(driver -> titleCard.getText().contains(text));
    }
}
