package guruqa;

import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static com.codeborne.selenide.Condition.text;


public class RegistrationPageObjectsTest extends TestBase {

    RegistrationPage registrationPage = new RegistrationPage();

     @Test
    void successfulRegistrationTest() {
        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Male")
                .setUserNumber("1234567890")
                .setDateOfBirth("30", "July", "2008")
                .setSubjects("Math")
                .setHobbies("Sports")
                .setPicture("snimok.PNG")
                .setAddres("Some address 1")
                .setState("NCR")
                .setCity("Delhi")
                .pressSubmit();
                //results
         registrationPage.checkResult("Student Name Alex Egorov")
                .checkResult("Student Email alex@egorov.com")
                .checkResult("Gender Male")
                .checkResult("Mobile 1234567890")
                .checkResult("Date of Birth 30 July,2008")
                .checkResult("Subjects Maths")
                .checkResult("Hobbies Sports")
                .checkResult("Picture snimok.PNG")
                .checkResult("Address Some address 1")
                .checkResult("State and City NCR Delhi");
    }
    @Test
    void minimalRegistrationTest(){
        registrationPage.openPage()
                .setFirstName("Alex")
                .setLastName("Egorov")
                .setEmail("alex@egorov.com")
                .setGender("Male")
                .setUserNumber("1234567890")
                .pressSubmit();
        registrationPage.checkResult("Student Name Alex Egorov")
                .checkResult("Student Email alex@egorov.com")
                .checkResult("Gender Male")
                .checkResult("Mobile 1234567890");
    }

    @Test
     void negativeRegistrationTest(){
        registrationPage.openPage()
                .pressSubmit();
        registrationPage.registrationFail();
    }
}