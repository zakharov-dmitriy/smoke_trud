import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Set;
import java.util.stream.Collectors;

public class TestBase {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public String initialWindow;

    public Set<String> getAllWindows() {
        return driver.getWindowHandles();
    }

    public void switchToFirstWindow() {
        var newWindows = getAllWindows().stream().filter(w -> !w.equals(initialWindow)).collect(Collectors.toSet());
        driver.switchTo().window(newWindows.stream().findFirst().get());
    }

    public void switchToWindow(String windowId) {
        driver.switchTo().window(windowId);
    }

    @BeforeEach
    public void setUp() {
        var options = new ChromeOptions();
        options.addArguments("--start-maximized", "--ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
//        options.addArguments("--incognito");
//        options.addArguments("--headless"); //без браузера

        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
//        WebDriverManager.chromedriver().setup();
//        WebDriverManager.chromedriver().driverVersion("136.0.7103.113").setup();
//        driver = new ChromeDriver(options);

        initialWindow = driver.getWindowHandle();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("./src/test/resources/screens/screen.png"));
//        driver.quit();
        if (driver != null) {
            driver.quit();
        }
    }

//    @AfterEach
//    public void tearDown() throws IOException {
//        if (driver != null) {
//            try {
//                var sourceFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//                FileUtils.copyFile(sourceFile, new File("./src/test/resources/screens/screen.png"));
//            } catch (Exception e) {
//                System.err.println("Не удалось сделать скриншот: " + e.getMessage());
//            }
//
//            try {
//                driver.quit(); // или driver.close()
//            } catch (Exception e) {
//                System.err.println("Ошибка при закрытии драйвера: " + e.getMessage());
//            }
//
//            // Принудительно убей оставшиеся процессы
//            Runtime.getRuntime().exec("pkill -f chrome");
//            Runtime.getRuntime().exec("pkill -f chromedriver");
//        }
//    }
}
