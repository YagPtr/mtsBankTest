package pages2.Mobile;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
public class MainPage {
    private final SelenideElement kredits = $x("//img[@alt=\"Кредиты\"]");
    private final SelenideElement closeCard = $x("//button[@class=\"styled__Close-sc-37ac7ba-1 jaMEbP\"]");
    private final SelenideElement closeCity = $x("//button[@class=\"Wrapper-sc-16137b7a-1 cVZbNd sc-dlfnuX iSGgmM\"]");
    private final SelenideElement kreditPodZalog = $x("//h4//div[contains(text(),\"ПОД ЗАЛОГ\")]/../../..//div[@class=\"Wrapper-sc-1vydk7-0 bQSysR ButtonText-sc-48arcs-2 ivMpRV\"]");

    public MainPage(String URL) {
        Selenide.open(URL);
    }
    @Step
    public void fullfillKredit() throws InterruptedException {
        closeCard.click();
        closeCity.click();
        kredits.click();
        kreditPodZalog.scrollIntoCenter();
        kreditPodZalog.click();

    }
}
