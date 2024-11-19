import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

import org.example.pom.ProfilePage;
import org.junit.Before;
import org.junit.Test;

public class ProfilePageTest {

    private ProfilePage profilePage;

    @Before
    public void setUp() {
        // Логин в приложение
        open("URL_вашего_приложения");
        $("input[name='username']").setValue("ваш_логин");
        $("input[name='password']").setValue("ваш_пароль");
        $("button[type='submit']").click();

        // Навигация на страницу профиля
        $("a[href='/profile']").click();
        profilePage = new ProfilePage();
    }

    @Test
    public void testEditBirthdate() {
        // Открыть модальное окно редактирования
        $("button.edit-button").click();

        // Изменить дату рождения
        $("input[name='birthdate']").setValue("01/01/2000");

        // Нажать на кнопку Save и закрыть модальное окно
        $("button.save-button").click();
        $("button.close-modal").click();

        // Проверить, что изменения применились
        String expectedBirthdate = "01/01/2000";
        $("div.additional-info .birthdate").shouldHave(text(expectedBirthdate));
    }
}