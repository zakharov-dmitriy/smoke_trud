package pages;

import org.junit.jupiter.params.provider.Arguments;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
    public static List<WebElement> cardBtn;

    //a[contains(@class,"tile-card")]/..

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

    @FindBy(xpath = "//a[.='Краткосрочная занятость']")
    public WebElement shortJobTab;

    @FindBy(xpath = "//a[contains(@class,\"tile-card\")]/..")
    public List<WebElement> list;

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

    public void clickOnCard(int i) {
//        for (WebElement webElement : cardBtn) {
//            jsExecutor.executeScript("arguments[0].click(true)", cardBtn.get(0));
//        }
        jsExecutor.executeScript("arguments[0].click(true)", cardBtn.get(i));
    }

//    public static Stream<Integer> cardIndexProvider() {
//        // Возвращаем поток индексов от 0 до размера списка cardBtn
//        return IntStream.range(0, cardBtn.size()).boxed();
//    }

    public static Stream<Arguments> cardIndexProvider() {
        return Stream.of(
                Arguments.of(0, "/information-pages/employment-veterans"),
                Arguments.of(1,"/information-pages/fareast-job"),
                Arguments.of(2,"/information-pages/arrivals"),
                Arguments.of(3,"/information-pages/atlasremoteprof"),
                Arguments.of(4,"/information-pages/it-residence"),
                Arguments.of(5,"/students-employment"),
                Arguments.of(6,"/vacancy/search?page=0&busyType=REMOTE"),
                Arguments.of(7,"/czn?_page=0&_regionCode=2400000000000"),
                Arguments.of(8,"/information-pages/special"),
                Arguments.of(9,"/temporary-jobs"),
                Arguments.of(10,"/experienced-personnel"),
                Arguments.of(11,"/information-pages/standard-documents#candidates"),
                Arguments.of(12,"/private-employment-agency"),
                Arguments.of(13,"/professions"),
                Arguments.of(14,"/proforientation")
        );
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
