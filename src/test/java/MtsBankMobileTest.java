import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages2.Mobile.*;

import static com.codeborne.selenide.Selenide.switchTo;

public class MtsBankMobileTest extends BaseTestMobile {
    private final static String URL= "https://www.mtsbank.ru/";

    @ParameterizedTest
    @CsvSource({
            "'а ', 'aa@a.ru', '1231231233','12.12.1984',0,1234567,0",
            "'а а а', 'a@a.ru', '1231231233','12.12.1984',4,1,0",
            "'а а а', 'aa@a.ru', '1231231233','12.12.1984',4,1,0",
            "'а ', 'aa@a.ru', '1231231233','12.12.1984',0,1,0",
            "'а а а', 'aa@a.ru', '123','12.12.1984',2,1,0",
            "'а а а', 'aa@a.ru', '1231231233','1.1.1',3,1,0",
            "'а а а', 'aaasdasru', '1231231233','12.12.1999',1,1,0",
            "'а а а', 'a@a.ru', '1231231233','12.12.1984',4,99999999,0",
            "'а а а', 'aa@a.ru', '1231231233','12.12.1984',4,99999999,0",
            "'а ', 'aa@a.ru', '1231231233','12.12.1984',0,99999999,0",
            "'а а а', 'aa@a.ru', '123','12.12.1984',2,99999999,0",
            "'а а а', 'aa@a.ru', '1231231233','1.1.1',3,99999999,0",
            "'а а а', 'aaasdasru', '1231231233','12.12.1999',1,99999999,0",
            "'а а а', 'a@a.ru', '1231231233','12.12.1984',4,99999999,20",
            "'а а а', 'aa@a.ru', '1231231233','12.12.1984',4,99999999,20",
            "'а ', 'aa@a.ru', '1231231233','12.12.1984',0,99999999,20",
            "'а а а', 'aa@a.ru', '123','12.12.1984',2,99999999,20",
            "'а а а', 'aa@a.ru', '1231231233','1.1.1',3,99999999,20",
            "'а а а', 'aaasdasru', '1231231233','12.12.1999',1,99999999,20",
            "'а а а', 'a@a.ru', '1231231233','12.12.1984',4,1,20",
            "'а а а', 'aa@a.ru', '1231231233','12.12.1984',4,1,20",
            "'а ', 'aa@a.ru', '1231231233','12.12.1984',0,1,20",
            "'а а а', 'aa@a.ru', '123','12.12.1984',2,1,20",
            "'а а а', 'aa@a.ru', '1231231233','1.1.1',3,1,20",
            "'а а а', 'aaasdasru', '1231231233','12.12.1999',1,1,20",
    })
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


    @ParameterizedTest
    @CsvSource({
            "'аа аа аа', '11.11.1999', '123123123123123','afasdas',3",
            "'аа аа аа', '11.11.1999', '123123123123123','моск',4",
            "'а а', '11.11.1999', '123123123123123','моск',4",
            "'аа аа', '11.1', '123123123123123','моск',1",
            "'аа аа', '11.11.1999', '123','моск',2",
            "'аа аа', '11.11.1999', 'чето','моск',2",
            "'eng lish', '11.11.1999', '123123123123','моск',0",
            "'аа аа', '11.11.1900', '123123123123','моск',1",

    })
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

    @ParameterizedTest
    @CsvSource({
            "'Все'",
            "'Кредитные'",
            "'Дебетовые'",
            "'Премиальные'",
            "'Виртуальные'",

    })
    public void kardTest(String type){
        MainPage mainPage = new MainPage(URL);
        mainPage.openCards();
        //MainPage mainPage = new MainPage("https://www.mtsbank.ru/chastnim-licam/karti/all/credit/");
        Kards kards = new Kards();
        kards.SelectKard(type);
    }

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
    public void blogTest(String type){
        MainPage mainPage = new MainPage(URL);
        mainPage.openBlogs();
        switchTo().window(1);
        Blogs blogs = new Blogs();
        blogs.checkFilter(type);
    }

    @ParameterizedTest
    @CsvSource({
            "'аа аа', '11.1', '123123123123123','моск',1",

            "'аа аа аа', '11.11.1999', '123123123123123','моск',4",

            "'аа аа аа', '11.11.1999', '123123123123123','afasdas',3",
            "'а а', '11.11.1999', '123123123123123','моск',4",
            "'аа аа', '11.11.1999', '123','моск',2",
            "'аа аа', '11.11.1999', 'чето','моск',2",
            "'eng lish', '11.11.1999', '123123123123','моск',0",
            "'аа аа', '11.11.1900', '123123123123','моск',1",

    })
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
