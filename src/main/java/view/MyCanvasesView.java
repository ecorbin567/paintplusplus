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
 * The View for when the user is accessing all of their canvases.
 */
public class MyCanvasesView extends JPanel implements ActionListener, PropertyChangeListener {

    private final String viewName = "my canvases";
    private final NewCanvasViewModel newCanvasViewModel;

    private final JButton newCanvas;
    private final JButton logOut;
    private final NewCanvasController newCanvasController;

    final JPanel canvasesPanel;

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

        this.canvasesPanel = new JPanel();
        refreshCanvasesPanel(canvasesPanel); // 1st call to refresh = construct it

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

    /**
     * Helper method for refreshCanvasesPanel
     * @param img original image
     * @param newWidth scale to width
     * @param newHeight scale to height
     * @return scaled image
     */
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

    /**
     * Helper method to update specifically the select canvas panel.
     * Used on the first call to initially construct it.
     * @param subPanel the canvas panel to be updated
     */
    private void refreshCanvasesPanel(JPanel subPanel) {

        subPanel.removeAll();

        // Create the sub panel with horizontal layout
        subPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // horizontal with spacing

        int iconWidth = 250; // size of each square
        int iconHeight = 200;

        final NewCanvasState currentState = newCanvasViewModel.getState();
        List<BufferedImage> listOfCanvasImages = newCanvasViewModel.getCanvases();

        for (BufferedImage oldCanvasImage : listOfCanvasImages) {
            JButton canvasIconButton = new JButton();

            ImageIcon icon = new ImageIcon(oldCanvasImage);
            //Image image = icon.getImage().getScaledInstance(iconWidth, iconHeight, java.awt.Image.SCALE_SMOOTH);
            Image image = getScaledImage(icon.getImage(), iconWidth, iconHeight);

            canvasIconButton.setIcon(new ImageIcon(image));

            canvasIconButton.setPreferredSize(new Dimension(iconWidth, iconHeight));
            canvasIconButton.setBackground(Color.LIGHT_GRAY); // or any other color
            canvasIconButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            subPanel.add(canvasIconButton);

            // Execute login with new import image
            canvasIconButton.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(canvasIconButton)) {
                                final NewCanvasState currentState = newCanvasViewModel.getState();

                                // 3 parameter version executes an automatic import!!!
                                newCanvasController.execute(
                                        currentState.getUsername(),
                                        currentState.getPassword(),
                                        oldCanvasImage
                                );
                            }
                        }
                    }
            );
        }

        this.revalidate();
        this.repaint();

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
        //System.out.println(evt.getPropertyName());
        final NewCanvasState state = (NewCanvasState) evt.getNewValue();
        if ("canvases".equals(evt.getPropertyName())) {
            refreshCanvasesPanel(this.canvasesPanel);
        }
    }

    public String getViewName() {
        return viewName;
    }
}
