package pl.seleniumdemo.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pl.seleniumdemo.pages.HotelSearchPage;
import pl.seleniumdemo.pages.LoggedUserPage;
import pl.seleniumdemo.pages.SignUpPage;

import java.util.List;

public class SignUpTest extends BaseTest {
    @Test
    public void SignUpTest() {
        String lastName = "Nowak";
        int randomNumber = (int) (Math.random() * 1000);

        LoggedUserPage loggedUserPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bartek")
                .setLastName("Nowak")
                .setPhone("987656879")
                .setEmail("tester" + randomNumber + "@tester.pl")
                .setPassword("Test12345")
                .Confirmpassword("Test12345")
                .signup();

        Assert.assertTrue(loggedUserPage.getHeadingText().contains(lastName));
        Assert.assertEquals(loggedUserPage.getHeadingText(), "Hi, Bartek Nowak");
        driver.quit();
    }


    @Test
    public void SignUpEmptyFormTest() {
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm();
        signUpPage.signup();

        List<String> errors = signUpPage.getErrors();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(errors.contains("The Email field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The Password field is required."));
        softAssert.assertTrue(errors.contains("The First name field is required."));
        softAssert.assertTrue(errors.contains("The Last Name field is required."));
        softAssert.assertAll();
    }

    @Test
    public void SignUpInvalidEmail() {
        SignUpPage signUpPage = new HotelSearchPage(driver)
                .openSignUpForm()
                .setFirstName("Bartek")
                .setLastName("Testowy")
                .setPhone("987656879")
                .setEmail("email")
                .setPassword("Test12345")
                .Confirmpassword("Test12345");
        signUpPage.signup();


        Assert.assertTrue(signUpPage.getErrors().contains("The Email field must contain a valid email address."));
    }
}
