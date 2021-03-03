package autotests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import io.qameta.allure.Step;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LogoutTests {

    @Before
    public void setup() {
        Configuration.baseUrl="http://automationpractice.com";
    }

    @Step
    public void performLogin(final String username, final String password){
        $(By.className("login")).click();
        $(By.id("email")).setValue(username);
        $(By.id("passwd")).setValue(password);
        $(By.id("SubmitLogin")).click();
    }

    @Step
    public void logoutByUI(){
        $(By.className("logout")).click();
    }

    @Step
    public void logoutByURL(){
        open("/index.php?mylogout=");
    }

    @Step
    public void checkLogoutIsSuccessful(){
        $(By.cssSelector("div.header_user_info")).shouldHave(Condition.text("Sign in"));
    }

    @Test
    public void logoutTestActionByUI() {
        open("/index.php");
        performLogin("automationpractice999@mailinator.com", "automationpractice999");
        logoutByUI();
        checkLogoutIsSuccessful();
    }

    @Test
    public void logoutTestActionByURL() {
        open("/index.php");
        performLogin("automationpractice999@mailinator.com", "automationpractice999");
        logoutByURL();
        checkLogoutIsSuccessful();
    }
}
