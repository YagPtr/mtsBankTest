package pages2;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

/**
 * Страница, на которые перенаправляет после поискового запроса
 */
public class SearchPage {
    private final SelenideElement StarFilter=$x("//input[contains(@aria-label,'5 звезд')]");
    private final ElementsCollection stars=$$x("//div[@class='b3f3c831be']");
    private final SelenideElement Input=$x("//input[@name='ss']");
    private final SelenideElement Cookie=$x("//button[@id='onetrust-reject-all-handler']");

    /**
     * Применение фильтрации отелей по 5 звездам
     */
    @Step
    public void Filter(){
        StarFilter.click();
    }

    /**
     * Проверка действительно ли был применен поиск по заданному значению переменной search
     * @param search
     * @return
     */
    @Step
    public boolean CheckSearch(String search){
        Cookie.click();
        Assertions.assertTrue(Input.getAttribute("value").equals(search),"Переданный для поиска отелей город не соответствует запросу");
        return Input.getAttribute("value").equals(search);
    }

    /**
     * Проверка на соответствие всех отелей 5 звездам
     * @return
     */
    @Step
    public boolean CheckFilter(){
        boolean flag=true;
        for (SelenideElement element:stars){
            if (!element.getAttribute("aria-label").equals("5 из 5")){
                flag=false;
            }
        }
        Assertions.assertTrue(flag,"Вывелись не только пятизвездочные отели");
        return flag;
    }

}
