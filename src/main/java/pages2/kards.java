package pages2;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;
public class kards {
    private final SelenideElement all= $x("//div[@class=\"TabsBarInner-sc-a22f654e-7 kMifUc\"]//a//div[contains(text(),\"Все\")]");

}
