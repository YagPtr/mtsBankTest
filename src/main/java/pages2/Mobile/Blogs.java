package pages2.Mobile;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Selenide.$x;

public class Blogs {
    private final String baseString=" //div[@class=\"sc-bdfBQB buqMpI\"]//span//div[contains(text(),\"";
    private final SelenideElement article=$x("//div[@class=\"styled__Cell-sc-1m4bvj-0 KxMnx\"]");
    private final SelenideElement moreFilters=$x("//span[@class=\"Wrapper-sc-1vwahr7-0 pMMLf\"]//div[contains(text(),\"+\")]");

    @Step
    public void checkFilter(String type){
        if (moreFilters.isDisplayed()){
            moreFilters.click();
        }
        $x(baseString+type+"\")]").click();
        Assertions.assertEquals(true,article.isDisplayed());
    }
}
