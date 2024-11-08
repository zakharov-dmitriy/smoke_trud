import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

public class CheckStandsTest extends TestBase {

    private final By title = By.xpath("//*[contains(@class, 'content__title')]");
    String message = "Стенд не работает";

    private static Stream<Arguments> openStand_TestsData() {
        return Stream.of(
                arguments("https://web-hotfix-test01.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test02.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test03.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test04.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test05.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test06.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test07.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test08.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test09.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test010.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test11.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test12.k8s.trudvsem.ru/"),
                arguments("https://web-hotfix-test13.k8s.trudvsem.ru/")
        );
    }

    @ParameterizedTest
    @MethodSource("openStand_TestsData")
    @DisplayName("Открытие стендов")
    public void openStand_Success(String url) {
        driver.navigate().to(url);
        var actualResult = driver.findElement(title).getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.findElement(title).isDisplayed(), message),
                () -> Assertions.assertEquals("Найдите работу вашей мечты в любой точке России",
                        actualResult, message)
        );
    }

    @ParameterizedTest
    @DisplayName("Открытие стендов")
    @CsvFileSource(files = "./src/test/resources/stands.csv")
    public void secondTest(String url) {
        driver.navigate().to(url);
        var actualResult = driver.findElement(title).getText();
        Assertions.assertAll(
                () -> Assertions.assertTrue(driver.findElement(title).isDisplayed(), message),
                () -> Assertions.assertEquals("Найдите работу вашей мечты в любой точке России",
                        actualResult, message)
        );
    }

}
