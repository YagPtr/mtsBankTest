package pages2;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class PremiumClient {

    private final static SelenideElement fio= $x("//div[@class=\"TextareaWrapper-sc-1ux9qvi-0 LRpaj\"]//textarea");
    private final static SelenideElement continuee= $x("//button[@class=\"Wrapper-sc-48arcs-1 ixINIZ\"]");
    private final static SelenideElement fioError= $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"ФИО\")]");
    private final static SelenideElement birthData= $x("//input[@placeholder=\"дд.мм.гггг\"]");
    private final static SelenideElement birthDataError= $x("//div[@class=\"styled__Cell-sc-1m4bvj-0 hPFicI\"]//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\"]");
    private final static SelenideElement number = $x("//input[@name=\"phoneNumber\"]");
    private final static SelenideElement numberError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"телефона\")]");
    private final static SelenideElement city = $x("//input[@placeholder=\"Выберите город\"]");
    private final static SelenideElement cityError=$x("//div[@label=\"Город получения карты\"]/../../../../div[@data-testid=\"text\"]");
    private final static SelenideElement choseCity=$x("//ul[@class=\"react-autosuggest__suggestions-list\"]");
    @Step
    public void checkName(String name,boolean exp){
        fio.setValue(name);
        fio.sendKeys(Keys.TAB);
        Assertions.assertEquals(exp,fioError.isDisplayed());
    }

    @Step
    public void checkData(String data,boolean exp){
        //birthData.click();
        birthData.setValue(data);
        birthData.sendKeys(Keys.TAB);
        Assertions.assertEquals(exp,birthDataError.isDisplayed());
    }

    @Step
    public void checkNumber(String numberIn,boolean exp){
        number.setValue(numberIn);
        number.sendKeys(Keys.TAB);
        Assertions.assertEquals(exp,numberError.isDisplayed());
    }
    @Step
    public void checkCity(String cityIn,boolean exp) throws InterruptedException {
        city.setValue(cityIn);
        TimeUnit.SECONDS.sleep(1);

        if (choseCity.isDisplayed()){
            choseCity.click();
        }
        number.click();
        //TimeUnit.SECONDS.sleep(1);
        Assertions.assertEquals(exp,cityError.isDisplayed());
    }
}
