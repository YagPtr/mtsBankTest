package pages2.Mobile;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.devtools.v131.page.model.Screenshot;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class Vklads {
    private static final SelenideElement Fio = $x("//textarea[@placeholder=\"Иванов Иван Иванович\"]");
    private static final SelenideElement FioError=$x("//div[contains(text(),\"ФИО\")]");
    private static final SelenideElement birthData = $x("//input[@placeholder=\"дд.мм.гггг\"]");
    private static final SelenideElement birthDataError = $x("//div[@label=\"Дата рождения\"]/../../../div[@data-testid=\"text\"]");
    private static final SelenideElement number = $x("//input[@type=\"tel\"]");
    private static final SelenideElement numberError = $x("//div[contains(text(),\"номер телефона\")]");
    private static final SelenideElement city = $x("//div[@label=\"Город получения карты\"]//input");
    private final static SelenideElement cityError=$x("//div[@label=\"Город получения карты\"]/../../../../div[@data-testid=\"text\"]");
    private final static SelenideElement choseCity=$x("//div[@id=\"react-autowhatever-1\"]");

    @Step
    public void setFio(String fio,boolean exp){
        Fio.scrollIntoCenter();
        Fio.setValue(fio);
        Fio.sendKeys(Keys.TAB);
        if (FioError.isDisplayed()!=exp){
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp, FioError.isDisplayed());


    }
    @Step
    public void setNumber(String num,boolean exp){
        number.scrollIntoCenter();
        //number.setValue(num);
        for(char c:num.toCharArray()){
            number.sendKeys(String.valueOf(c));
            sleep(50);

            //System.out.println(String.valueOf(c));
            //TimeUnit.SECONDS.sleep(1);

        }
        number.sendKeys(Keys.TAB);
        if (numberError.isDisplayed()!=exp){
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,numberError.isDisplayed());
    }

    @Step
    public void setBirthData(String birthData1,boolean exp) throws InterruptedException {
        birthData.scrollIntoCenter();
        //birthData.setValue(birthData1);

        for(char c:birthData1.toCharArray()){
            birthData.sendKeys(String.valueOf(c));
            sleep(50);

            //System.out.println(String.valueOf(c));
            //TimeUnit.SECONDS.sleep(1);

        }

        birthData.sendKeys(Keys.TAB);

        if (birthDataError.isDisplayed()!=exp){
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,birthDataError.isDisplayed());

    }

    @Step
    public void checkCity(String cityIn,boolean exp) throws InterruptedException {
        city.scrollIntoCenter();

        //city.click();
        city.click();
        //choseCity.click();
        for(char c:cityIn.toCharArray()){
            city.sendKeys(String.valueOf(c));
            sleep(50);

            //System.out.println(String.valueOf(c));
            //TimeUnit.SECONDS.sleep(1);

        }
        TimeUnit.SECONDS.sleep(1);


        if (choseCity.isDisplayed()){
            choseCity.scrollIntoCenter();
            choseCity.click();
        }

        city.sendKeys(Keys.TAB);
        //TimeUnit.SECONDS.sleep(1);
        if (cityError.isDisplayed()!=exp){
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,cityError.isDisplayed());
    }


}
