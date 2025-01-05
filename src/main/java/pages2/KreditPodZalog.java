package pages2;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
public class KreditPodZalog {

    private static final ElementsCollection amountOfKredit =$$x("//div[@class=\"InputWrapper-sc-j3a9iz-3 ZPCho\" and @rows=3]//input");
    private static final SelenideElement listOfObjects =$x("//div[@label=\"Объект залога\"]//input[@placeholder=\"Выберите из списка\"]");
    private static final SelenideElement choseObject =$x("//div[@class=\"Wrapper-sc-1vydk7-0 dPQSdP Label-sc-1uyl36s-2 ewlRGZ\"]");
    private static final SelenideElement kreditPersent =$x("//div[@class=\"Wrapper-sc-1vydk7-0 hRquvB\" and not(contains(text(),\"-\"))]");
    private static final SelenideElement kreditPerMonth =$x("//div[@class=\"styled__Cell-sc-1m4bvj-0 jWUAFm\"]//h4");
    private static final SelenideElement chosePlace =$x("//input[@placeholder=\"Введите город / регион\"]");
    private static final SelenideElement chosePlace2 =$x("//li[@id=\"react-autowhatever-1--item-0\"]");
    private static final SelenideElement choseReason = $x("//input[@placeholder=\"Выберите из списка\" and @name=\"creditPurpose\"]");
    private static final SelenideElement choseReason2 = $x("//div[@class=\"Wrapper-sc-1vydk7-0 dPQSdP Label-sc-1uyl36s-2 ewlRGZ\" and contains(text(),\"недвижимости\")]");
    private static final SelenideElement makeKredit = $x("//div[@class=\"Wrapper-sc-1vydk7-0 dsjsau ButtonText-sc-48arcs-2 ivMpRV\"]");

    private static final SelenideElement Fio = $x("//textarea[@placeholder=\"Иванов Иван Иванович\"]");
    private static final SelenideElement FioError=$x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"ФИО\")]");
    private static final SelenideElement birthData = $x("//input[@placeholder=\"дд.мм.гггг\"]");
    private static final SelenideElement birthDataError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"Обязательное\")]");
    private static final SelenideElement number = $x("//input[@type=\"tel\"]");
    private static final SelenideElement numberError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"номер телефона\")]");

    private static final SelenideElement mail = $x("//input[@placeholder=\"mail@example.ru\"]");
    private static final SelenideElement mailError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"электронный адрес\")]");

    private static SelenideElement move = $x("//button[@class=\"Wrapper-sc-48arcs-1 iILffq\"]");
    private static SelenideElement move2 = $x("//button[@class=\"Wrapper-sc-48arcs-1 hdDGkC\"]");

    private static final SelenideElement sogl2=$x("//div[@class=\"CheckIconWrapper-sc-cb89gg-2 laHSjE\"]");
    private static final SelenideElement soglError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"пункт\")]");

    private static final SelenideElement popUp = $x("//button[@class=\"Wrapper-sc-48arcs-1 dCpppl\"]");

    @Step
    public void fullfillKredit(Integer kreditSize, Integer kreditYears) throws InterruptedException {

        amountOfKredit.first().sendKeys(Keys.CONTROL + "a");
        amountOfKredit.first().sendKeys(Keys.DELETE);
        amountOfKredit.first().setValue(kreditSize.toString());
        //System.out.println(amountOfKredit.first().getValue());
        amountOfKredit.last().sendKeys(Keys.CONTROL + "a");
        amountOfKredit.last().sendKeys(Keys.DELETE);
        amountOfKredit.last().setValue(kreditYears.toString());
        listOfObjects.click();
        choseObject.click();

        if (kreditSize<1000000){
            kreditSize=1000000;
        }
        if (kreditSize>15000000){
            kreditSize=15000000;
        }
        if (kreditYears<1){
            kreditYears=1;
        }
        if (kreditYears>15){
            kreditYears=15;
        }
        Double kreditPercent=Double.parseDouble(kreditPersent.getText().substring(0,kreditPersent.getText().indexOf("%")).replace(",","."))/1200;

        //System.out.println(kreditPercent);
        Double perMonth=(Math.pow((1+kreditPercent),12*kreditYears)*kreditPercent)/(Math.pow((1+kreditPercent),12*kreditYears)-1)*kreditSize;
        //System.out.println(perMonth);
        Integer money =(int)Math.ceil(perMonth);
        Assertions.assertEquals(money.toString(),kreditPerMonth.getText().replace(" ","").replace("₽",""));
///=(E4*(1+E4)^60)/((1+E4)^60-1)
        chosePlace.setValue("калинин");
        TimeUnit.SECONDS.sleep(1);

        chosePlace2.click();
        choseReason.click();
        choseReason2.click();
        makeKredit.click();
    }

    @Step
    public void correctData() throws InterruptedException {
        Fio.setValue("а а а ");
        birthData.setValue("12.11.1984");
        number.setValue("9801281212");
        mail.setValue("aa@a.r");
        TimeUnit.SECONDS.sleep(1);
        mail.click();
        mail.sendKeys(Keys.ENTER);

        sogl2.click();
        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();

    }
    @Step
    public void setFio(String fio,boolean exp){
        Fio.setValue(fio);
        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();
        Assertions.assertEquals(exp, FioError.isDisplayed());


    }
    @Step
    public void setNumber(String num,boolean exp){
        number.setValue(num);
        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();
        Assertions.assertEquals(exp,numberError.isDisplayed());
    }

    @Step
    public void setMail(String mai,boolean exp) throws InterruptedException {
        mail.setValue(mai);
//        TimeUnit.SECONDS.sleep(1);
//        mail.click();
//        mail.sendKeys(Keys.ENTER);
        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();
        Assertions.assertEquals(exp,mailError.isDisplayed());
    }
    @Step
    public void setBirthData(String birthData1,boolean exp) {
        birthData.setValue(birthData1);

        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();
        if (popUp.isDisplayed()) {
            popUp.click();
        }
        Assertions.assertEquals(exp,birthDataError.isDisplayed());

    }

    @Step
    public void setSogl() throws InterruptedException {
        if (sogl2.exists()){
            sogl2.click();
        }
        //sogl2.click();
        if (move.isEnabled()&&move.isDisplayed()){
            move.click();

        }
        else move2.click();
        Assertions.assertFalse(soglError.isDisplayed());

    }

    @Step
    public void confirm(){
        if (move.isEnabled()&&move.isDisplayed()){
            move.isEnabled();

        }
        else move2.click();
    }

}
