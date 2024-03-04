package guruqa;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
@DisplayName("Заполнение формы регистрации студента")
public class TextBoxTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    @Tag("demoqa")
    void fillFormTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть форму", () -> {
                    open("/automation-practice-form");
                    executeJavaScript("$('#fixedban').remove()");
                    executeJavaScript("$('footer').remove()");
                });
        step("Ввод данных", () -> {
                    $("#firstName").setValue("Alex");
                    $("#lastName").setValue("Pirogov");
                    $("#userEmail").setValue("alex@pirogov.com");
                    $(".custom-control-label").click(); // выбор Gender = male
                    $("#userNumber").setValue("0123456789");
                    $("#dateOfBirthInput").click();
                    $(".react-datepicker__month-select").selectOption(4);
                    $(".react-datepicker__year-select").selectOption(92);
                    $(".react-datepicker__day--013").click();
                    $("#subjectsInput").setValue("e");
                    $("#subjectsWrapper").find(byText("English")).click();
                    $("#subjectsInput").setValue("h");
                    $("#subjectsWrapper").find(byText("History")).click();
                    $("#hobbiesWrapper").$(byText("Sports")).click();
                    $("#uploadPicture").uploadFromClasspath("snimok.PNG");
                    $("#currentAddress").setValue("Some street 1");
                    $("#state").click();
                    $(byText("NCR")).click();
                    $("#city").click();
                    $(byText("Delhi")).click();
                    $("#submit").click();
                });
        step("Проверка результата", () -> {
            $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
            $(".table-responsive").shouldHave(text("Alex Pirogov"));
            $(".table-responsive").shouldHave(text("alex@pirogov.com"));
            $(".table-responsive").shouldHave(text("Male"));
            $(".table-responsive").shouldHave(text("0123456789"));
            $(".table-responsive").shouldHave(text("13 May,1992"));
            $(".table-responsive").shouldHave(text("English, History"));
            $(".table-responsive").shouldHave(text("Sports"));
            $(".table-responsive").shouldHave(text("snimok.PNG"));
            $(".table-responsive").shouldHave(text("Some street 1"));
            $(".table-responsive").shouldHave(text("NCR Delhi"));
        });
    }
}