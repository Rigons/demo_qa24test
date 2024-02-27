package guruqa;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byLinkText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WebStepsAllureDZ {
    @Step("Открываем главную страницу")
    public void openMainPage() {
        open("https://github.com");
    }

    @Step("Ищем репозиторий {repo}")
    public void searchForRepository(String repo) {
        $(".search-input").click();
        $("#query-builder-test").setValue(repo).pressEnter();
    }

    @Step("Кликаем по ссылке репозитория {repo}")
    public void clickOnRepositoryLink(String repo) {
        $(byLinkText(repo)).click();
    }

    @Step("Нажатие на вкладку Issues")
    public void pressIssuesButton() {
        $("#issues-tab").click();
    }

    @Step("Проверка отображения счётчика багов")
    public void checktResult() {
        $(".flex-auto").shouldBe(visible);
    }
}
