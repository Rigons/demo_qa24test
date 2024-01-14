package guruqa;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestGH {
    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
    }

    @Test
    void checkEnterprise() {
        open("https://github.com/");
        $(new ByText("Solutions")).hover();
        $(new ByText("Enterprise")).click();
        $("#hero-section-brand-heading").shouldHave(text("The AI-powered\n" +
                "developer platform."));
    }
}
