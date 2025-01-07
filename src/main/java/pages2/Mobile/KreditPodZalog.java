package pages2.Mobile;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;

import java.io.ByteArrayInputStream;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.sleep;

public class KreditPodZalog {
    private final SelenideElement amountOfKredit=$x("//div[@label=\"Сумма кредита\"]//input");
    private final SelenideElement lenght=$x("//div[@label=\"Срок кредита\"]//input");
    private final SelenideElement income=$x("//input[@placeholder=\"Выберите из списка\"]");
    private final SelenideElement city=$x("//input[@placeholder=\"Введите город / регион\"]");
    private final SelenideElement cityChose=$x("//div[@id=\"react-autowhatever-1\"]//div");
    private final SelenideElement reason=$x("//div[@label=\"Цель кредита\"]//input");
    private final SelenideElement choseReason=$x("//div[@class=\"Wrapper-sc-1vydk7-0 dPQSdP Label-sc-1uyl36s-2 ewlRGZ\"]");
    private final SelenideElement kreditPersent=$x("//div[@class=\"styled__Cell-sc-1m4bvj-0 juipeB\"]//div[@class=\"Wrapper-sc-1vydk7-0 fWmnTq\" and not(contains(text(),\"-\"))]");
    private final SelenideElement kreditPerMonth =$x("//div[@class=\"styled__Cell-sc-1m4bvj-0 jWUAFm\"]//h4");
    private final SelenideElement makeKredit=$x("//div[@class=\"Wrapper-sc-1vydk7-0 dsjsau ButtonText-sc-48arcs-2 ivMpRV\"]");


    private static final SelenideElement Fio = $x("//textarea[@placeholder=\"Иванов Иван Иванович\"]");
    private static final SelenideElement FioError=$x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"ФИО\")]");
    private static final SelenideElement birthData = $x("//input[@placeholder=\"дд.мм.гггг\"]");
    private static final SelenideElement birthDataError = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"дату\")]");
    private static final SelenideElement birthDataError2 = $x("//div[@class=\"Wrapper-sc-1vydk7-0 gnJtJP HelperText-sc-jsokzo-0 hByJHf\" and contains(text(),\"Обязательное поле\")]");
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
        amountOfKredit.scrollIntoCenter();
        amountOfKredit.click();
        amountOfKredit.sendKeys(Keys.CONTROL + "a");
        amountOfKredit.sendKeys(Keys.DELETE);
        amountOfKredit.scrollIntoCenter();
        amountOfKredit.click();
        //TimeUnit.SECONDS.sleep(1);

        for (char c:kreditSize.toString().toCharArray()){
            amountOfKredit.sendKeys(String.valueOf(c));
            sleep(50);

        }

        kreditSize=((int)(kreditSize/10000))*10000;
        if (kreditSize<1000000){
            kreditSize=1000000;
        }
        if (kreditSize>15000000){
            kreditSize=15000000;
        }


        lenght.scrollIntoCenter();
        lenght.clear();
        lenght.sendKeys(Keys.CONTROL + "a");
        lenght.sendKeys(Keys.DELETE);
        lenght.click();


        lenght.setValue(kreditYears.toString());


        if (kreditYears<1){
            kreditYears=1;
        }
        if (kreditYears>15){
            kreditYears=15;
        }

        city.scrollIntoCenter();
        city.setValue("с");


        cityChose.click();
        reason.scrollIntoCenter();
        reason.click();
        choseReason.scrollIntoCenter();
        choseReason.click();

        Assertions.assertEquals(true,true);

        Double kreditPercent=Double.parseDouble(kreditPersent.getText().substring(0,kreditPersent.getText().indexOf("%")).replace(",","."))/1200;



        Double perMonth=(Math.pow((1+kreditPercent),12*kreditYears)*kreditPercent)/(Math.pow((1+kreditPercent),12*kreditYears)-1)*kreditSize;

        Integer money =(int)Math.ceil(perMonth);
        if (!(money.toString().equals(kreditPerMonth.getText().replace(" ","").replace("₽","")))){
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(money.toString(),kreditPerMonth.getText().replace(" ","").replace("₽",""));
///=(E4*(1+E4)^60)/((1+E4)^60-1)

        makeKredit.scrollIntoCenter();
        makeKredit.click();
    }

    @Step
    public void setFio(String fio,boolean exp){
        Fio.scrollIntoCenter();
        Fio.setValue(fio);
        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();
            move.click();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }

        if (FioError.isDisplayed()!=exp){
            Fio.scrollIntoCenter();
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp, FioError.isDisplayed());


    }
    @Step
    public void setNumber(String num,boolean exp){
        number.scrollIntoCenter();
        number.setValue(num);
        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();

            move.click();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }
        if (numberError.isDisplayed()!=exp){
            number.scrollIntoCenter();

            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,numberError.isDisplayed());
    }

    @Step
    public void setMail(String mai,boolean exp) throws InterruptedException {
        mail.scrollIntoCenter();
        mail.setValue(mai);

        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();

            move.click();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }
        if (mailError.isDisplayed()!=exp){
            mail.scrollIntoCenter();
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);

            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,mailError.isDisplayed());
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

        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();

            move.click();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }
        if (popUp.isDisplayed()) {
            popUp.click();
        }
        if ((birthDataError.isDisplayed()||birthDataError2.isDisplayed())!=exp){
            birthData.scrollIntoCenter();
            sleep(1000);
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertEquals(exp,birthDataError.isDisplayed()||birthDataError2.isDisplayed());

    }

    @Step
    public void setSogl() throws InterruptedException {
        if (sogl2.exists()){
            sogl2.scrollIntoCenter();
            sogl2.click();
        }
        //sogl2.click();
        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();

            move.click();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }
        if (soglError.isDisplayed()!=false){
            soglError.scrollIntoCenter();
            byte[] screenshot = Selenide.screenshot(OutputType.BYTES);
            Allure.addAttachment("image.png",new ByteArrayInputStream(screenshot));
        }
        Assertions.assertFalse(soglError.isDisplayed());

    }

    @Step
    public void confirm(){
        if (move.isEnabled()&&move.isDisplayed()){
            move.scrollIntoCenter();

            move.isEnabled();

        }
        else {
            move2.scrollIntoCenter();
            move2.click();
        }
    }


}
