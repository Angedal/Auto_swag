import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class SmokeTest {

    @Test
    void smokeTestToBuy(){
        Configuration.holdBrowserOpen=true;
        open("https://www.saucedemo.com/");
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("standard_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        $("[data-test=add-to-cart-sauce-labs-backpack]").click();
        $("#shopping_cart_container").click();
        $("[data-test=checkout]").click();

        SelenideElement formOfOrder= $(".checkout_info");
        formOfOrder.$("[data-test=firstName]").setValue("Julia");
        formOfOrder.$("[data-test=lastName]").setValue("Kostomarova");
        formOfOrder.$("[data-test=postalCode]").setValue("123456");
        $("[data-test=continue]").click();
        $("[data-test=finish]").click();

        String expected = "Thank you for your order!";
        String actual = $(".complete-header").getText();
        assertEquals(expected, actual);
    }
}
