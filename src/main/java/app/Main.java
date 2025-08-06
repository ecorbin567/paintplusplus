package app;

import com.formdev.flatlaf.FlatLightLaf;
import data_access.*;
import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.newcanvas.NewCanvasViewModel;
import view.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    /**
     * The main method for starting the program.
     * @param args input to main
     */
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        try {
            // Set FlatLaf Light look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Failed to initialize FlatLaf Light L&F");
        }

        // The main application window.
        final JFrame application = new JFrame("Paint++");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        application.setSize(600, 400);

        final CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        final JPanel views = new JPanel(cardLayout);
        application.add(views);

        // This keeps track of and manages which view is currently showing.
        final ViewManagerModel viewManagerModel = new ViewManagerModel();
        new ViewManager(views, cardLayout, viewManagerModel);

        // The data for the views, such as username and password, are in the ViewModels.
        // This information will be changed by a presenter object that is reporting the
        // results from the use case. The ViewModels are "observable", and will
        // be "observed" by the Views.
        final LoginViewModel loginViewModel = new LoginViewModel();
        final SignupViewModel signupViewModel = new SignupViewModel();
        final CanvasViewModel canvasViewModel = new CanvasViewModel();
        final NewCanvasViewModel newCanvasViewModel = new NewCanvasViewModel();
        final GoBackViewModel goBackViewModel = new GoBackViewModel();

         final SupabaseAccountRepository userDataAccessObject = new SupabaseAccountRepository();

         final SupabaseCanvasRepository canvasDataAccessObject = new SupabaseCanvasRepository();

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                                                                  signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                                                               newCanvasViewModel, userDataAccessObject,
                canvasDataAccessObject);
        views.add(loginView, loginView.getViewName());

        final CanvasView canvasView = CanvasUseCaseFactory.create(viewManagerModel, goBackViewModel,
                                                                newCanvasViewModel, signupViewModel,
                userDataAccessObject);

        views.add(canvasView, canvasView.getViewName());

        final MyCanvasesView myCanvasesView = NewCanvasUseCaseFactory.create(viewManagerModel, newCanvasViewModel,
                canvasViewModel, signupViewModel, canvasDataAccessObject);

        views.add(myCanvasesView, myCanvasesView.getViewName());

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
