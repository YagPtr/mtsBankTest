package pages2.Desktop;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class Blogs {
    private final String baseString=" //div[@class=\"sc-bdfBQB buqMpI\"]//span//div[contains(text(),\"";
    private final SelenideElement article=$x("//div[@class=\"styled__Cell-sc-1m4bvj-0 KxMnx\"]");
    @Step
    public void checkFilter(String type){
        $x(baseString+type+"\")]").click();
        Assertions.assertEquals(true,article.isDisplayed());
    }
}
