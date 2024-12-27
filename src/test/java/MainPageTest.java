import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import pages.MainPage;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainPageTest extends TestBase {

    private static MainPage page;

    String text = "Повар";

    @BeforeEach
    public void preconditions() {
        page = new MainPage(driver, wait);
        page.open();
        page.acceptCookiesAndRegion();
    }

    //    @Disabled
    @Test
    @Owner("@d.zakharov")
    @Description("Переключение на Работодателя на главной странице")
    @DisplayName("Переход к Работодателю")
    public void goToEmployer() {
        page.header.tabEmployer.click();
        //assert
        var expectedResult = "Найдите лучшего работника в любой точке России";
        Assertions.assertEquals(expectedResult, page.title.getText(), page.messageInCaseOfFailedTest);
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открывается страница с поисковой выдачей с конкретной вакансий")
    @DisplayName("Поиск конкретной вакансии")
    public void searchVacancyOnMainPage() {
        page.searchVacancy(text);
        //assert
        Assertions.assertTrue(page.titleContainsText(text), "Поисковая выдача не работает");
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открывается страница с поисковой выдачей вакансий")
    @DisplayName("Страница поисковой выдачи вакансий")
    public void goToSearchVacancy() {
        page.header.searchJobLink.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.checkTitle(page.searchVacancy).contains("Работа и вакансии"),
                        "Заголовок страницы не совпадает"),
                () -> Assertions.assertEquals("Поиск вакансий", page.header.getBreadcrumbsText(),
                        "Неверная страница")
        );
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открывается страница с поисковой выдачей резюме")
    @DisplayName("Страница поисковой выдачи резюме")
    public void goToSearchCV() {
        page.header.searchEmployees.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertTrue(page.checkTitle(page.searchCV).contains("Резюме соискателей"),
                        "Неверная страница"),
                () -> Assertions.assertEquals("Поисковая выдача резюме", page.header.getBreadcrumbsText(),
                        "Неверные хлебные крошки")
        );
    }

    //@Disabled
    @ParameterizedTest
    @MethodSource("pages.MainPage#cardIndexProvider")
    //@Test
    @Owner("@d.zakharov")
    @Description("Инфоблок \"Работа для всех\"")
    @DisplayName("Открытие карточек в инфоблоке Работа для всех")
    public void openCardJobsForAll(int i, String url) {
        page.clickOnCard(i);
        //assert
        var allWindows = driver.getWindowHandles();
//        var expectedUrl = "/information-pages/employment-veterans";
        switchToFirstWindow();
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, allWindows.size(),
                        "После клика не открылось новое окно"),
                () -> Assertions.assertTrue(driver.getCurrentUrl().contains(url),
                        "После клика открылась неверная страница")
        );
    }

//    @ParameterizedTest
//    @MethodSource("pages.MainPage#cardIndexProvider")
//    @Owner("@d.zakharov")
//    @Description("Инфоблок \"Работа для всех\"")
//    @DisplayName("Открытие карточек в инфоблоке Работа для всех")
//    public void openCardJobsForAll(int index) {
//        page.clickOnCard(index);
//        //assert
//        var allWindows = driver.getWindowHandles();
//        var expectedUrl = "/information-pages/employment-veterans";
//        switchToFirstWindow();
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(2, allWindows.size(),
//                        "После клика не открылось новое окно"),
//                () -> Assertions.assertTrue(driver.getCurrentUrl().contains(expectedUrl),
//                        "После клика открылась неверная страница")
//        );
//    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открытие карточки вакансии в поисковой выдаче")
    @DisplayName("Карточка вакансии в поисковой выдаче")
    public void openingVacancyCard() {
        //act
        page.header.searchJobLink.click();
        page.searchField.sendKeys(text);
        page.searchBtnOnPage.click();
        page.clickOnTitleVacancyFullCard();
        //asserts
        Assertions.assertAll(
                () -> Assertions.assertEquals(text, page.titleCard.getText(), "Неверная страница"),
                () -> Assertions.assertEquals("Карточка вакансии", page.breadcrumbs.getText(),
                        "Неверные хлебные крошки")
        );
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открытие карточки компании из поисковой выдачи вакансии")
    @DisplayName("Карточка компании в поисковой выдаче")
    public void openingCompanyCardFromSearchVacancy() {
        //act
        page.header.searchJobLink.click();
        page.searchField.sendKeys(text);
        page.searchBtnOnPage.click();
        var expectedResult = page.getCompanyName();
        page.clickOnCompanyNameFullCard();
        //asserts
        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedResult, page.titleCard.getText(), "Неверная страница"),
                () -> Assertions.assertEquals("Карточка компании", page.breadcrumbs.getText(),
                        "Неверные хлебные крошки")
        );
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открытие поисковаой выдачи краткосрочной занятости заданий (вакансий)")
    @DisplayName("Поисковая выдача заданий Краткосрочная занятость")
    public void openingShortJobSearchVacancy() {
        page.header.searchJobLink.click();
        page.shortJobTab.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals("Поиск вакансий", page.header.getBreadcrumbsText(),
                        "Неверная страница"),
                () -> Assertions.assertTrue(page.shortJobTab.getAttribute("class").contains("tabs_active"),
                        "Неверный таб страницы")
                //() -> Assertions.assertTrue(page.titleOnPage("Задания"), "Неверный заголовок страницы")
        );
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открытие карточки резюме соискателя из поисковой выдачи")
    @DisplayName("Карточка резюме в поисковой выдаче")
    public void openingCvCardFromSearch() {
        page.header.searchEmployees.click();
        page.searchField.sendKeys(text);
        page.searchBtnOnPage.click();
        page.clickOnTitleCvFullCard();
        var allWindows = driver.getWindowHandles();
        switchToFirstWindow();
        //asserts
        Assertions.assertAll(
                () -> Assertions.assertEquals(2, allWindows.size(), "После клика не открылась новая вкладка"),
                //() -> Assertions.assertTrue(page.titleCvCard(text), "Неверная страница"),
                () -> Assertions.assertEquals("Карточка резюме", page.breadcrumbs.getText(),
                        "Неверные хлебные крошки")
        );
    }

    @Test
    @Owner("@d.zakharov")
    @Description("Открытие поисковаой выдачи резюме краткосрочной занятости")
    @DisplayName("Поисковая выдача резюме Краткосрочная занятость")
    public void openingShortJobSearchCv() {
        page.header.searchEmployees.click();
        page.shortJobTab.click();
        //assert
        Assertions.assertAll(
                () -> Assertions.assertEquals("Поиск резюме", page.header.getBreadcrumbsText(),
                        "Неверная страница"),
                () -> Assertions.assertTrue(page.titleOnPage("Резюме соискателей"), "Неверный заголовок страницы")
        );
    }
}
