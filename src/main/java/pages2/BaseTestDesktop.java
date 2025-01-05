package pages2;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.manager.SeleniumManager;


/**
 * Абстрактный класс для создания полноценного тестировочного класс
 */
abstract public class BaseTestDesktop {
    public static WebDriverManager driver;
    public void setUp(){
       // WebDriverManager.chromedriver().timeout(1);

        WebDriverManager.chromedriver().setup();
        //System.setProperty("chromeoptions.mobileEmulation", "deviceName=Nexus 5");
        Configuration.browser = "chrome";
        Configuration.browserSize= "1920x1080";
        Configuration.headless = true;
        Configuration.pageLoadTimeout=60000;

    }

    @BeforeEach
    public void init(){
        setUp();
    }
    @AfterEach
    public void tearDown(){
        Selenide.closeWebDriver();
    }

}
