package pages2.Desktop;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Главная страница сайта https://booking.com/
 */

public class MainPage {
    private String menu = "//div[@class='sc-5055b9e7-8 dPjVPg'";


    public MainPage(String URL) {
        Selenide.open(URL);
    }

    /**
     *
     *Заполнение формы запроса поиска
     * @throws InterruptedException
     */
    @Step
    public void KreditPodZalog() throws InterruptedException {
        SelenideElement credits=$x(menu+"]//a[contains(text(),\"Кредиты\")]");
        credits.hover();
        SelenideElement test=$x("//div[@class='sc-5055b9e7-8 dPjVPg']//div//a[contains(text(),\"Кредит под залог недвижимости\")]");
        test.click();


    }

    @Step
    public void PremiumClient() {
        SelenideElement premium = $x(menu + "]//a[contains(text(),\"Премиум\")]");
        premium.click();
    }

    @Step
    public void Cards(){
        SelenideElement cards = $x(menu + "]//a[contains(text(),\"Карты\")]");
        cards.click();
    }
}
