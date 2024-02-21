package guruqa;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;


@DisplayName("Проверка поисковика книжного магазина")
public class DzParametrizedTest {
    @BeforeEach
    void beforeTest(){
        open("https://www.labirint.ru/");
    }

    @ValueSource(strings = {
            "Сияние", "Противостояние", "Тёмная башня"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} отдавать список карточек с книгами")
    @Tag("SMOKE")
    @DisplayName("Проверки отображения карточек с книгами")
    void checkingBookCardsTest(String bookName){
        $("span #search-field").setValue(bookName).pressEnter();
        $$(".search-result").shouldBe(sizeGreaterThan(0));
    }
    @CsvSource(value = {
            "Сияние , Кинг Стивен",
            "Метро 2033 , Глуховский Дмитрий Алексеевич"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть автор {1}")
    @Tag("SMOKE")
    @DisplayName("Проверки соотвответствия книги и автора")
    void checkingBookNameCardsTest(String bookName, String bookAuthor){
        $("span #search-field").setValue(bookName).pressEnter();
        $(".search-tab").should(text(bookAuthor));
    }

    @CsvFileSource(resources = "/testData/checkingBookMatchesPrice.Csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в карточке должен быть издатель {1}")
    @Tag("SMOKE")
    @DisplayName("Проверки соотвответствия книги и издательства")
    void checkingBookMatchesPriceTest(String bookName, String bookPublisher){
        $("span #search-field").setValue(bookName).pressEnter();
        $(".search-tab").shouldHave(text(bookPublisher));
    }
}
