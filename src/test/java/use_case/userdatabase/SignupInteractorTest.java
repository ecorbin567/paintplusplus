package use_case.userdatabase;

import data_access.SupabaseAccountRepository;
import entity.CommonUserFactory;
import entity.User;
import entity.UserFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import org.junit.jupiter.api.Test;
import use_case.signup.SignupInputData;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupUserDataAccessInterface;

import static org.junit.jupiter.api.Assertions.*;

public class SignupInteractorTest {
    // SupabaseAccountRepository service = new SupabaseAccountRepository();
    String testUserName = "JaneDoe12345";
    String testPswd = "security1";

    ViewManagerModel viewManagerModel = new ViewManagerModel();
    LoginViewModel loginViewModel = new LoginViewModel();
    SignupViewModel signupViewModel = new SignupViewModel();

    SignupUserDataAccessInterface userDataAccessInterface = new SupabaseAccountRepository();
    SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
            viewManagerModel,
            signupViewModel,
            loginViewModel
    );
    UserFactory userFactory = new CommonUserFactory();

    SignupInteractor signupInteractor = new SignupInteractor(
            userDataAccessInterface,
            signupOutputBoundary,
            userFactory
    );

    @Test
    public void signupInteractorTest(){
        signupInteractor.execute(new SignupInputData(
                testUserName,
                testPswd,
                testPswd
        ));

        // get the user from the database
        User retrievedUser = userDataAccessInterface.getUser(testUserName);

        assertNotNull(retrievedUser);
        assertEquals(testUserName, retrievedUser.getUsername());
        assertEquals(testPswd, retrievedUser.password());

        assertTrue(userDataAccessInterface.verifyCredentials(
                testUserName,
                testPswd
        ));

        userDataAccessInterface.deleteUser(testUserName);
    }
}
