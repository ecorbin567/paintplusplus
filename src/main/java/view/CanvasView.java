package view;



import interface_adapter.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackState;
import interface_adapter.goback.GoBackViewModel;
import interface_adapter.image.crop.CropController;
import interface_adapter.changecolor.ColorController;

import interface_adapter.image.import_image.ImportController;
import interface_adapter.image.resize.ResizeController;
import interface_adapter.image.rotate.RotateController;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.MenuActionListener;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class CanvasView extends JPanel implements ActionListener, MenuActionListener{
    private final GoBackViewModel goBackViewModel;
    private final GoBackController goBackController;

    public CanvasView(GoBackViewModel goBackViewModel,
                      GoBackController goBackController,
                      CropController cropController,
                      ImportController importController,
                      ResizeController resizeController,
                      RotateController rotateController,
                      ColorController colorController,
                      DrawingView drawingView,
                      CanvasController controller) {

        this.goBackViewModel = goBackViewModel;
        this.goBackController = goBackController;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));

        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(drawingView, controller);
        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
        topMenuBarBuilder.setMenuActionListener(this);
        this.add(menuBar, BorderLayout.NORTH);

        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(controller, cropController, importController,
                resizeController, rotateController, colorController, drawingView);
        JPanel panel = midMenuBarBuilder.getPanel();
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(panel);
        bottomPanel.add(drawingView);
        this.add(bottomPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
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
}
