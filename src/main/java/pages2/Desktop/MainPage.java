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
    @Step("Переход на страницу кредита под залог")
    public void KreditPodZalog() throws InterruptedException {
        SelenideElement credits=$x(menu+"]//a[contains(text(),\"Кредиты\")]");
        credits.hover();
        SelenideElement test=$x("//div[@class='sc-5055b9e7-8 dPjVPg']//div//a[contains(text(),\"Кредит под залог недвижимости\")]");
        test.click();


    }

    @Step("Переход на страницу премиум услуг")
    public void PremiumClient() {
        SelenideElement premium = $x(menu + "]//a[contains(text(),\"Премиум\")]");
        premium.click();
    }

    @Step("Переход на страницу предлагаемых карт")
    public void Cards(){
        SelenideElement cards = $x(menu + "]//a[contains(text(),\"Карты\")]");
        cards.click();

    }

    @Step("Переход на страницу блогов")
    public void Blogs(){
        SelenideElement blogs=$x("//div[contains(text(),\"Полезная информация\")]");
        blogs.click();
    }

    @Step("Переход на страницу партнеров")
    public void Partners(){
        SelenideElement partners = $x(menu + "]//a[contains(text(),\"Private\")]");
        partners.click();
        $x("//div[contains(text(),\"предложения\")]").click();
    }
}
