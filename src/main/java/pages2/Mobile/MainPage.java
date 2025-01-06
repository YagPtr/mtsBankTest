package pages2.Mobile;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;
public class MainPage {
    private final SelenideElement kredits = $x("//img[@alt=\"Кредиты\"]");
    private final SelenideElement closeCard = $x("//button[@class=\"styled__Close-sc-37ac7ba-1 jaMEbP\"]");
    private final SelenideElement closeCity = $x("//button[@class=\"Wrapper-sc-16137b7a-1 cVZbNd sc-dlfnuX iSGgmM\"]");
    private final SelenideElement kreditPodZalog = $x("//h4//div[contains(text(),\"ПОД ЗАЛОГ\")]/../../..//div[@class=\"Wrapper-sc-1vydk7-0 bQSysR ButtonText-sc-48arcs-2 ivMpRV\"]");
    private final SelenideElement cards = $x("//img[@alt=\"Дебетовые\"]");

    private final SelenideElement premium = $x("//img[@alt=\"Премиум\"]");
    public MainPage(String URL) {
        Selenide.open(URL);
    }
    @Step
    public void openKreditPodZalog() throws InterruptedException {
        closeCard.click();
        closeCity.click();
        kredits.click();
        kreditPodZalog.scrollIntoCenter();
        kreditPodZalog.click();
    }

    @Step
    public void openPremium(){
        closeCard.click();
        closeCity.click();
        premium.scrollIntoCenter();
        premium.click();

    }

    @Step
    public void openCards(){
        closeCard.click();
        closeCity.click();
        cards.scrollIntoCenter();
        cards.click();
    }

}
