package view;

import interface_adapter.newcanvas.NewCanvasController;
import interface_adapter.newcanvas.NewCanvasState;
import interface_adapter.newcanvas.NewCanvasViewModel;
import view.MidMenuBar.ImageBar.RotateButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logging into the program.
 */
public class MyCanvasesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "my canvases";
    private final NewCanvasViewModel newCanvasViewModel;

    private final JButton newCanvas;
    private final JButton logOut;
    private final NewCanvasController newCanvasController;

    public MyCanvasesView(NewCanvasViewModel newCanvasViewModel, NewCanvasController controller) {
        this.setPreferredSize(new Dimension(400, 400));
        this.newCanvasViewModel = newCanvasViewModel;
        this.newCanvasViewModel.addPropertyChangeListener(this);
        this.newCanvasController = controller;

        final JLabel title = new JLabel("My Canvases");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        this.newCanvas = new JButton("New Canvas");
        buttons.add(newCanvas);
        this.logOut = new JButton("Log Out");
        buttons.add(logOut);

        final JPanel canvasesPanel = constructCanvasesPanel();

        newCanvas.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(newCanvas)) {
                            final NewCanvasState currentState = newCanvasViewModel.getState();

                            newCanvasController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword()
                            );
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        newCanvasController.switchToSignupView();
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(canvasesPanel);
    }

    private JPanel constructCanvasesPanel() {
        // Create the sub panel with horizontal layout
        JPanel subPanel = new JPanel();
        subPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // horizontal with spacing

        int squareSize = 100; // size of each square

        for (int i = 0; i < 3; i++) {
            JButton square = new JButton();

            ImageIcon icon = new ImageIcon(RotateButton.class.getResource("/images/RotateIcon.png"));
            java.awt.Image image = icon.getImage().getScaledInstance(squareSize, squareSize, java.awt.Image.SCALE_SMOOTH);
            square.setIcon(new ImageIcon(image));

            square.setPreferredSize(new Dimension(squareSize, squareSize));
            square.setBackground(Color.LIGHT_GRAY); // or any other color
            square.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            subPanel.add(square);
        }

        return subPanel;
    }

    /**
     * React to a button click that results in evt.
     * @param evt the ActionEvent to react to
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final NewCanvasState state = (NewCanvasState) evt.getNewValue();
    }

    public String getViewName() {
        return viewName;
    }
}
