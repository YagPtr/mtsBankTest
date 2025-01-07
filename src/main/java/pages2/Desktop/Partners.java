package pages2.Desktop;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.Keys;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class Partners {
    private final ElementsCollection partners=$$x("//div[@class=\"sc-dwcupg QurQi\"]");
    private final SelenideElement closeModal=$x("//button[@class=\"ButtonClose-sc-15wbuq5-5 clHMXW\"]");
    private final SelenideElement modal=$x("//div[@class=\"ModalBody-sc-15wbuq5-3 gyTkxA\"]");

    public void checkPartners(){

        partners.forEach(partner -> {
            if (partner.isEnabled() & partner.isDisplayed()) {
                String name =partner.lastChild().getText();
                partner.click();
                Assertions.assertEquals(true, modal.isDisplayed(),"Элемент с описанием "+ name+" не открывает модальное окно");
                closeModal.click();
            }
        });
    }


}
