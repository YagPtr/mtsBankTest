import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages2.Mobile.BaseTestMobile;
import pages2.Mobile.KreditPodZalog;
import pages2.Mobile.MainPage;

import java.util.concurrent.TimeUnit;

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
        mainPage.fullfillKredit();
        KreditPodZalog kreditPodZalog = new KreditPodZalog();
        kreditPodZalog.fullfillKredit(size,years);
        kreditPodZalog.setFio(name,array[0]);
        kreditPodZalog.setMail(email,array[1]);

        kreditPodZalog.setNumber(number,array[2]);

        kreditPodZalog.setBirthData(birth,array[3]);


        kreditPodZalog.setSogl();





    }


}
