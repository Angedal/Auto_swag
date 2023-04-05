import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginTest {

    @BeforeEach
    void setUp() {
        Configuration.holdBrowserOpen=true;
        open("https://www.saucedemo.com/");
    }

    @Test
    void shouldLogInWithStandardUser(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("standard_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        String currentURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(currentURL, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    void shouldNotLoginWithLockedUser(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("locked_out_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        form.$("[data-test=error]").shouldHave(exactText("Epic sadface: Sorry, this user has been locked out."));
    }

    @Test
    void shouldLoginProblemUser(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("problem_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        String testURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(testURL, "https://www.saucedemo.com/inventory.html");
    }

    @Test
    void shouldLoginPerformanceGlitch(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("performance_glitch_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        String testURL = WebDriverRunner.getWebDriver().getCurrentUrl();
        assertEquals(testURL, "https://www.saucedemo.com/inventory.html");
    }

    //    Негативные тест-кейсы
    @Test
    void shouldNotLoginWithoutName(){
        SelenideElement form = $("form");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
        form.$("[data-test=error]").shouldHave(exactText("Epic sadface: Username is required"));
    }

    @Test
    void shouldNotLoginWithoutPassword(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("standard_user");
        form.$("[type=submit]").click();
        form.$("[data-test=error]").shouldHave(exactText("Epic sadface: Password is required"));
    }

    @Test
    void shouldNotLoginWithWrongPassword(){
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("standard_user");
        form.$("[data-test=password]").setValue("ggreetm765");
        form.$("[type=submit]").click();
        form.$("[data-test=error]").shouldHave(exactText("Epic sadface: Username and password do not match any user in this service\n"));
    }
}
