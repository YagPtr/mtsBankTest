import io.qameta.allure.*;
import jdk.jfr.Label;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages2.Mobile.*;

import static com.codeborne.selenide.Selenide.switchTo;
@Epic("Тестирование мобильной версии сайта")
public class MtsBankMobileTest extends BaseTestMobile {
    private final static String URL= "https://www.mtsbank.ru/";
    @Feature("Проверка формы валидации для кредита под залог")
    //@DisplayName("Проверка формы валидации для кредита под залог")
    @ParameterizedTest
    @CsvSource({
            "'и ', 'aa@a.a', '1231231233','12.12.1984',0,1234567,0",
            "'и ф о', 'a@a.a', '1231231233','12.12.1984',4,1,0",
            "'и ф о', 'aa@a.a', '1231231233','12.12.1984',4,1,0",
            "'и ', 'aa@a.a', '1231231233','12.12.1984',0,1,0",
            "'и ф о', 'aa@a.a', '123','12.12.1984',2,1,0",
            "'и ф о', 'aa@a.a', '1231231233','1.1.1',3,1,0",
            "'и ф о', 'aaasdasru', '1231231233','12.12.1999',1,1,0",
            "'и ф о', 'a@a.a', '1231231233','12.12.1984',4,99999999,0",
            "'и ф о', 'aa@a.a', '1231231233','12.12.1984',4,99999999,0",
            "'и ', 'aa@a.a', '1231231233','12.12.1984',0,99999999,0",
            "'и ф о', 'aa@a.a', '123','12.12.1984',2,99999999,0",
            "'и ф о', 'aa@a.a', '1231231233','1.1.1',3,99999999,0",
            "'и ф о', 'aaasdasru', '1231231233','12.12.1999',1,99999999,0",
            "'и ф о', 'a@a.a', '1231231233','12.12.1984',4,99999999,20",
            "'и ф о', 'aa@a.a', '1231231233','12.12.1984',4,99999999,20",
            "'и ', 'aa@a.a', '1231231233','12.12.1984',0,99999999,20",
            "'и ф о', 'aa@a.a', '123','12.12.1984',2,99999999,20",
            "'и ф о', 'aa@a.a', '1231231233','1.1.1',3,99999999,20",
            "'и ф о', 'aaasdasru', '1231231233','12.12.1999',1,99999999,20",
            "'и ф о', 'a@a.a', '1231231233','12.12.1984',4,1,20",
            "'и ф о', 'aa@a.a', '1231231233','12.12.1984',4,1,20",
            "'и ', 'aa@a.a', '1231231233','12.12.1984',0,1,20",
            "'и ф о', 'aa@a.a', '123','12.12.1984',2,1,20",
            "'и ф о', 'aa@a.a', '1231231233','1.1.1',3,1,20",
            "'и ф о', 'aaasdasru', '1231231233','12.12.1999',1,1,20",
    })
    @Description("Проверка формы валидации данных о пользователе для кредита под залог")
    @Tag("Regression")
    public void test(String name,String email,String number, String birth, Integer whatever, Integer size, Integer years) throws InterruptedException {
        boolean[] array = new boolean[5];
        array[whatever] = true;
        MainPage mainPage = new MainPage(URL);
        mainPage.openKreditPodZalog();
        KreditPodZalog kreditPodZalog = new KreditPodZalog();
        kreditPodZalog.fullfillKredit(size,years);
        kreditPodZalog.setFio(name,array[0]);
        kreditPodZalog.setMail(email,array[1]);

        kreditPodZalog.setNumber(number,array[2]);

        kreditPodZalog.setBirthData(birth,array[3]);

        kreditPodZalog.setSogl();

    }

    @Feature("Проверка формы валидации для премиум клиента")
    //@DisplayName("Проверка формы валидации для премиум клиента")
    @ParameterizedTest
    @CsvSource({
            "'ии фф оо', '11.11.1999', '123123123123123','afasdas',3",
            "'ии фф оо', '11.11.1999', '123123123123123','моск',4",
            "'и ф', '11.11.1999', '123123123123123','моск',4",
            "'им фа', '11.1', '123123123123123','моск',1",
            "'им фа', '11.11.1999', '123','моск',2",
            "'им фа', '11.11.1999', 'чето','моск',2",
            "'eng lish', '11.11.1999', '123123123123','моск',0",
            "'им фа', '11.11.1900', '123123123123','моск',1",

    })
    @Description("Проверка формы валидации данных о пользователе для нового премиум клиента")
    @Tag("Regression")
    public void premiumTest(String name, String data, String number,String city, Integer whatever) throws InterruptedException {
        boolean[] array = new boolean[5];
        array[whatever] = true;
        MainPage mainPage = new MainPage(URL);
        mainPage.openPremium();
        PremiumClient premiumClient = new PremiumClient();
        premiumClient.checkName(name,array[0]);
        premiumClient.checkData(data,array[1]);
        premiumClient.checkNumber(number,array[2]);
        premiumClient.checkCity(city,array[3]);
    }

    @Feature("Проверка фильтров для карт")
    //@DisplayName("Проверка фильтров для карт")
    @ParameterizedTest
    @CsvSource({
            "'Все'",
            "'Кредитные'",
            "'Дебетовые'",
            "'Премиальные'",
            "'Виртуальные'",

    })
    @Description("Проверка соответствия хотя бы одной карты каждому фильтру")
    @Tag("Regression")
    public void kardTest(String type){
        MainPage mainPage = new MainPage(URL);
        mainPage.openCards();
        //MainPage mainPage = new MainPage("https://www.mtsbank.ru/chastnim-licam/karti/all/credit/");
        Kards kards = new Kards();
        kards.SelectKard(type);
    }



    @Feature("Проверка страницы блогов")
    //@DisplayName("Проверка страницы блогов")
    @ParameterizedTest
    @CsvSource({
            "'Все'",
            "'Вклады и счета'",
            "'Кредитование'",
            "'Кредитные карты'",
            "'Дебетовые карты'",
            "'Ипотека'",
            "'Бизнес'",
            "'Премиум'",
            "'Платежи и переводы'",
            "'Инвестиции'",
    })
    @Description("Проверка фильтров для статей")
    @Tag("Regression")
    public void blogTest(String type){
        MainPage mainPage = new MainPage(URL);
        mainPage.openBlogs();
        switchTo().window(1);
        Blogs blogs = new Blogs();
        blogs.checkFilter(type);
    }



    @Feature("Проверка вклада для своих")
    //@DisplayName("Проверка вклада для своих")
    @ParameterizedTest()
    @CsvSource({
            "'им фа', '11.1', '123123123123123','моск',1",
            "'ии фф оо', '11.11.1999', '123123123123123','моск',4",
            "'ии фф оо', '11.11.1999', '123123123123123','afasdas',3",
            "'и ф', '11.11.1999', '123123123123123','моск',4",
            "'им фа', '11.11.1999', '123','моск',2",
            "'им фа', '11.11.1999', 'чето','моск',2",
            "'eng lish', '11.11.1999', '123123123123','моск',0",
            "'им фа', '11.11.1900', '123123123123','моск',1",

    })
    @Description("Проверка формы валидации данных о пользователе для вклада для своих")
    @Tag("Regression")
    public void vkladsTest(String fio,String birth, String number, String city, Integer whatever) throws InterruptedException {
        boolean[] array = new boolean[5];
        array[whatever] = true;
        MainPage mainPage = new MainPage(URL);
        mainPage.openVklads();
        Vklads vklads = new Vklads();
        vklads.setFio(fio, array[0]);
        vklads.setBirthData(birth,array[1]);

        vklads.setNumber(number, array[2]);

        vklads.checkCity(city,array[3]);

    }

}
