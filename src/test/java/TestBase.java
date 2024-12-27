import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.FluentWait;
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
        WebDriverManager.chromedriver().setup();
        var options = new ChromeOptions();
        options.addArguments("--start-maximized", "--ignore-certificate-errors"); //"--incognito",

        //---- установлен chrome через flatpack ----
        //options.setBinary("/var/lib/flatpak/app/com.google.Chrome/x86_64/stable/c74cbaeb05235d9e7a27c5ffbfeb200843761c3138c3992ac0221d71c3dfb91a/export/bin/com.google.Chrome"); //принудительно запусткает браузер установленный по другому пути
        //options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-debugging-port=9222");

        //options.addArguments("--headless"); //без браузера
        driver = new ChromeDriver(options);
        initialWindow = driver.getWindowHandle();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterEach
    public void tearDown() throws IOException {
        var sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(sourceFile, new File("./src/test/resources/screens/screen.png"));
        driver.quit();
    }
}
