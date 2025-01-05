package pages2;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.*;

public class Kards {
    private final String baseXPath="//div[@class=\"TabsBarInner-sc-a22f654e-7 kMifUc\"]//a//div[contains(text(),";
    private final ElementsCollection filters= $$x("//div[@class=\"Wrapper-sc-a808ec06-2 dvTbEp sc-8f5d58d7-1 kMXLWx\"]//div");
    private final SelenideElement type1= $x("//div[@class=\"Row-sc-a26adb6c-0 gfbyqz sc-1ed7672a-0 YzzyW\"]");
    private final SelenideElement type2= $x("//div[@class=\"Row-sc-a26adb6c-0 gfbyqz sc-1ed7672a-0 eXLGDZ\"]");
    @Step
    public void SelectKard(String type){
        type1.hover();
        $x(baseXPath+"\""+type+"\")]").click();

        filters.forEach(
                filter -> {
                    filter.click();
                    Assertions.assertEquals(true,type1.exists() || type2.exists(),"должна быть хотя бы одна карта с данным фильтром: "+filter.getText());
                    filter.click();
                }
        );


    }
}
