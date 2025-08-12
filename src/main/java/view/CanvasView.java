package view;

import interface_adapter.canvas.CanvasController;

import interface_adapter.canvas.CanvasUserState;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackState;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.midmenu.image.ImageFacade;
import interface_adapter.changecolor.ColorController;

import interface_adapter.midmenu.image.import_image.ImportController;
import interface_adapter.topmenu.TopMenuFacade;
import view.midmenubar.imagebar.ImportButton;
import view.midmenubar.MidMenuBarBuilder;
import view.topmenubar.MenuActionListener;
import view.topmenubar.TopMenuBarBuilder;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;

/**
 * The View for when the user is drawing on a canvas.
 */
public class CanvasView extends JPanel implements ActionListener, MenuActionListener,
        PropertyChangeListener {
    private final String viewName = "canvas";
    private final CanvasViewModel canvasViewModel;

    private final GoBackViewModel goBackViewModel;
    private final GoBackController goBackController;

    private final ImportButton importButton;

    public CanvasView(CanvasViewModel canvasViewModel,
                      GoBackViewModel goBackViewModel,
                      GoBackController goBackController,
                      ImageFacade imageFacade,
                      ColorController colorController,
                      DrawingView drawingView,
                      CanvasController controller,
                      TopMenuFacade controllers) {
        // TODO: Josh: I don't know a better way to import when getting canvases
        // store the import button and then press it when we log in with an existing canvas.

        this.goBackViewModel = goBackViewModel;
        this.goBackController = goBackController;

        // JOSH: listen for changes in canvas state
        this.canvasViewModel = canvasViewModel;
        this.canvasViewModel.addPropertyChangeListener(this);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(drawingView, controllers);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        topMenuBarBuilder.setMenuActionListener(this);
        this.add(menuBar, BorderLayout.NORTH);

        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(controller, imageFacade, colorController, drawingView);
        JPanel panel = midMenuBarBuilder.getPanel();
        // store import button for use in login (in property change)
        this.importButton = midMenuBarBuilder.getImportButtonObject();

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(panel);
        bottomPanel.add(drawingView);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent evt) {
    }

    public String getViewName() {
        return "canvas";
    }

    @Override
    public void onMenuItemSelected(String command) {
        if ("goBack".equals(command) || "logOut".equals(command)) {
            final GoBackState currentState = goBackViewModel.getState();

            goBackController.execute(
                    currentState.getUsername(),
                    currentState.getPassword(),
                    command
            );
        }
    }

    /**
     * Helper for propertyChange(). Directly accesses the import controller with a specific image
     */
    private void pressImportButton(ImportButton importButtonObject, BufferedImage initialImportedImage) {
        ImportController controller = importButtonObject.getController();
        File outputFile = new File("imageToImport.png");
        try {
            ImageIO.write(initialImportedImage, "png", outputFile);
        } catch (IOException e) {
            System.out.println("Failed to import initial image.");
            throw new RuntimeException(e);
        }

        controller.execute(outputFile);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // System.out.println(evt.getPropertyName());
        final CanvasUserState state = (CanvasUserState) evt.getNewValue();
        if ("import".equals(evt.getPropertyName())) {
            pressImportButton(this.importButton, state.getInitialImportedImage());
        }
    }
}
