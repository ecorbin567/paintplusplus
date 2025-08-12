package view;

import interface_adapter.newcanvas.NewCanvasController;
import interface_adapter.newcanvas.NewCanvasState;
import interface_adapter.newcanvas.NewCanvasViewModel;

import javax.swing.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * View for listing a user's canvases and creating/opening canvases.
 */
public class MyCanvasesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final NewCanvasViewModel newCanvasViewModel;

    private final JButton newCanvas;
    private final NewCanvasController newCanvasController;

    final JPanel canvasesPanel;

    /**
     * Creates the view, wires listeners, and lays out components.
     *
     * @param newCanvasViewModel view model providing user state and canvases
     * @param controller controller used to create/open canvases and navigate
     */
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
        JButton  logOut = new JButton("Log Out");
        buttons.add(logOut);

        this.canvasesPanel = new JPanel();
        refreshCanvasesPanel(canvasesPanel);

        newCanvas.addActionListener(
                evt -> {
                    if (evt.getSource().equals(newCanvas)) {
                        final NewCanvasState currentState = newCanvasViewModel.getState();
                        newCanvasController.execute(
                                currentState.getUsername(),
                                currentState.getPassword()
                        );
                    }
                }
        );

        logOut.addActionListener(
                evt -> newCanvasController.switchToSignupView()
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
        this.add(canvasesPanel);
    }

    // --- private helpers (no public API Javadoc needed for "bare minimum") ---

    private Image getScaledImage(Image img, int newWidth, int newHeight) {
        int originalWidth = img.getWidth(null);
        int originalHeight = img.getHeight(null);

        if (originalWidth <= 0 || originalHeight <= 0) {
            throw new IllegalArgumentException("Image has invalid dimensions.");
        }

        double scaleX = (double) newWidth / originalWidth;
        double scaleY = (double) newHeight / originalHeight;
        double scale = Math.min(scaleX, scaleY);

        int scaledWidth = (int) (originalWidth * scale);
        int scaledHeight = (int) (originalHeight * scale);

        return img.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    }

    private void refreshCanvasesPanel(JPanel subPanel) {
        subPanel.removeAll();
        subPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        int iconWidth = 250;
        int iconHeight = 200;

        List<BufferedImage> listOfCanvasImages = newCanvasViewModel.getCanvases();

        for (BufferedImage oldCanvasImage : listOfCanvasImages) {
            JButton canvasIconButton = new JButton();

            ImageIcon icon = new ImageIcon(oldCanvasImage);
            Image image = getScaledImage(icon.getImage(), iconWidth, iconHeight);

            canvasIconButton.setIcon(new ImageIcon(image));
            canvasIconButton.setPreferredSize(new Dimension(iconWidth, iconHeight));
            canvasIconButton.setBackground(Color.LIGHT_GRAY);
            canvasIconButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            subPanel.add(canvasIconButton);

            canvasIconButton.addActionListener(
                    evt -> {
                        if (evt.getSource().equals(canvasIconButton)) {
                            final NewCanvasState currentState = newCanvasViewModel.getState();
                            newCanvasController.execute(
                                    currentState.getUsername(),
                                    currentState.getPassword(),
                                    oldCanvasImage
                            );
                        }
                    }
            );
        }

        this.revalidate();
        this.repaint();
    }

    /**
     * Handles button actions for this view.
     *
     * @param evt action event
     */
    @Override
    public void actionPerformed(ActionEvent evt) {
        // No-op
    }

    /**
     * Receives updates from the view model and refreshes the canvases when needed.
     *
     * @param evt property change event
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("canvases".equals(evt.getPropertyName())) {
            refreshCanvasesPanel(this.canvasesPanel);
        }
    }

    /**
     * Returns the logical name of this view.
     *
     * @return view name
     */
    public String getViewName() {
        return "my canvases";
    }
}
