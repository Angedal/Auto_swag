import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FilterTest {
    @BeforeEach
    void setUp(){
        Configuration.holdBrowserOpen=true;
        open("https://www.saucedemo.com/");
        SelenideElement form = $("form");
        form.$("[data-test=username]").setValue("standard_user");
        form.$("[data-test=password]").setValue("secret_sauce");
        form.$("[type=submit]").click();
    }

    @Test
    void shouldSortByZAName(){
        SelenideElement filter = $("[data-test=product_sort_container]");
        filter.click();
        filter.$("[value=za]").click();
    }

    @Test
    void shouldSortByAZName(){
        SelenideElement filter = $("[data-test=product_sort_container]");
        filter.click();
        filter.$("[value=az]").click();
    }

    @Test
    void shouldSortByLowHighPrice(){
        SelenideElement filter = $("[data-test=product_sort_container]");
        filter.click();
        filter.$("[value=lohi]").click();
    }

    @Test
    void shouldSortByHighLowPrice(){
        SelenideElement filter = $("[data-test=product_sort_container]");
        filter.click();
        filter.$("[value=hilo]").click();
    }
}
