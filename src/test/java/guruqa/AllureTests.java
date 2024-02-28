package guruqa;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;;

public class AllureTests {
    private static final String REPOSITORY = "google/googletest";
    static final String RESULT = "[Bug]: FloatingEqMatcher doesn't work with const float value types";
    @AfterEach
    void afterEach() {
        Selenide.closeWebDriver();
    }
    @Test
    @DisplayName(("Проверка названия issue в репозитории"))
    void listenerTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());

        open("https://github.com/");
        $(".search-input").click();
        $("#query-builder-test").setValue("google/googletest").pressEnter();
        $(byLinkText("google/googletest")).click();
        $("#issues-tab").click();
        $(withText("[Bug]: FloatingEqMatcher doesn't work with const float value types")).shouldBe(visible);
    }
    @Test
    @DisplayName(("Проверка названия issue в репозитории используя Lambda"))
    public void testLambdaStep(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открытие главной страницы GitHub", () -> {
            open("https://github.com/");
        });
        step("Поиск репозитория " + REPOSITORY, () -> {
            $(".search-input").click();
            $("#query-builder-test").setValue(REPOSITORY).pressEnter();
        });
        step("Клик по репозиторию " + REPOSITORY, () -> {
            $(byLinkText(REPOSITORY)).click();
        });
        step("Нажатие на вкладку Issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверка отображения счётчика багов", () -> {
            $(withText(RESULT)).shouldBe(visible);
        });
    }
    @Test
    @DisplayName(("Проверка названия issue в репозитории используя steps"))
    void stepAnnotationTest(){
        SelenideLogger.addListener("allure", new AllureSelenide());
        WebStepsAllureDZ steps = new WebStepsAllureDZ();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.pressIssuesButton();
        steps.checktResult();
    }
}
