package app;

import java.awt.*;

import javax.swing.*;

import app.midmenufactory.imagefactory.CropUseCaseFactory;
import app.midmenufactory.imagefactory.ImportUseCaseFactory;
import app.midmenufactory.imagefactory.ResizeUseCaseFactory;
import app.midmenufactory.imagefactory.RotateUseCaseFactory;
import app.topmenufactory.HistoryControllerFactory;
import app.topmenufactory.ResizeCanvasControllerFactory;
import app.topmenufactory.SaveControllerFactory;
import com.formdev.flatlaf.FlatLightLaf;
import data_access.ImageSaveGateway;
import data_access.LocalImageLoader;
import data_access.SupabaseAccountRepository;
import data_access.SupabaseCanvasRepository;
import entity.CanvasState;
import interface_adapter.newselection.SelectionViewModel;
import com.formdev.flatlaf.FlatLightLaf;
import data_access.*;
import interface_adapter.newselection.SelectionViewModel;
import interface_adapter.ViewManagerModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.changecolor.ColorController;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.login.LoginViewModel;
import interface_adapter.midmenu.image.ImageFacade;
import interface_adapter.midmenu.image.ImageFacadeImple;
import interface_adapter.midmenu.image.crop.CropController;
import interface_adapter.midmenu.image.import_image.ImportController;
import interface_adapter.midmenu.image.resize.ResizeController;
import interface_adapter.midmenu.image.rotate.RotateController;
import interface_adapter.newcanvas.NewCanvasViewModel;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.topmenu.TopMenuFacade;
import interface_adapter.topmenu.TopMenuFacadeImpl;
import interface_adapter.topmenu.canvassize.ResizeCanvasController;
import interface_adapter.topmenu.history.HistoryController;
import interface_adapter.topmenu.save.SaveController;
import view.*;

/**
 * The main class for starting the program.
 */
public class Main {

    /**
     * The main method for starting the program.
     *
     * @param args input to main
     */
    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        try {
            // Set FlatLaf Light look and feel
            UIManager.setLookAndFeel(new FlatLightLaf());
        }
        catch (UnsupportedLookAndFeelException e) {
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
        final SelectionViewModel selectionViewModel = new SelectionViewModel();

        final SupabaseAccountRepository userDataAccessObject = new SupabaseAccountRepository();

        final SupabaseCanvasRepository canvasDataAccessObject = new SupabaseCanvasRepository();

        // Entity Layer
        final CanvasState canvasState = new CanvasState();

        // Presentation
        final DrawingViewModel drawingViewModel = new DrawingViewModel();

        // Gateways
        final LocalImageLoader localImageLoader = new LocalImageLoader();
        final ImageSaveGateway imageSaveGateway = new ImageSaveGateway();

        // TopMenu
        final HistoryController historyController = HistoryControllerFactory.create(
                canvasState, drawingViewModel);
        final ResizeCanvasController resizeCanvasController = ResizeCanvasControllerFactory.create(
                canvasState, drawingViewModel);
        final SaveController saveController = SaveControllerFactory.create(
                canvasState, imageSaveGateway, canvasDataAccessObject);
        final TopMenuFacade topMenuFacade = new TopMenuFacadeImpl(
                resizeCanvasController, saveController, historyController);
        // MidMenu

        // Presentation Layer
        final CropController cropcontroller = CropUseCaseFactory.create(
                canvasState, drawingViewModel);
        final ImportController importController = ImportUseCaseFactory.create(
                canvasState, drawingViewModel, localImageLoader);
        final ResizeController resizeController = ResizeUseCaseFactory.create(
                canvasState, drawingViewModel);
        final RotateController rotateController = RotateUseCaseFactory.create(
                canvasState, drawingViewModel);
        final CanvasController canvasController = CanvasControllerFactory.createCanvasController(
                canvasState, drawingViewModel, selectionViewModel);
        final ColorController colorController = ColorUseCaseFactory.create(canvasState);

        final ImageFacade imageFacade = new ImageFacadeImple(resizeController, rotateController,
                                                        importController, cropcontroller);

        // Renderer
        final CanvasRenderer canvasRenderer = new CanvasRenderer();

        final SignupView signupView = SignupUseCaseFactory.create(viewManagerModel, loginViewModel,
                signupViewModel, userDataAccessObject);
        views.add(signupView, signupView.getViewName());

        final LoginView loginView = LoginUseCaseFactory.create(viewManagerModel, loginViewModel,
                newCanvasViewModel, userDataAccessObject,
                canvasDataAccessObject, drawingViewModel, goBackViewModel);
        views.add(loginView, loginView.getViewName());

        final DrawingView drawingView = DrawingViewUseCaseFactory.create(canvasController, canvasRenderer,
                selectionViewModel, drawingViewModel);

        final CanvasView canvasView = CanvasUseCaseFactory.create(viewManagerModel, goBackViewModel,
                newCanvasViewModel, signupViewModel, imageFacade,
                colorController, canvasDataAccessObject,
                drawingView, canvasController, canvasViewModel, topMenuFacade, historyController);

        views.add(canvasView, canvasView.getViewName());

        final MyCanvasesView myCanvasesView = NewCanvasUseCaseFactory.create(
                viewManagerModel, newCanvasViewModel,
                canvasViewModel, signupViewModel, canvasDataAccessObject);

        views.add(myCanvasesView, myCanvasesView.getViewName());

        viewManagerModel.setState(signupView.getViewName());
        viewManagerModel.firePropertyChanged();

        application.pack();
        application.setVisible(true);
    }
}
