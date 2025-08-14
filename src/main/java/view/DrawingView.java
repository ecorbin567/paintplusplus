package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.DrawingViewModel;
import interface_adapter.newselection.SelectionViewModel;

public class DrawingView extends JPanel implements MouseListener, MouseMotionListener {
    private final DrawingViewModel viewModel;
    private final CanvasController controller;
    private final CanvasRenderer renderer;
    private final SelectionViewModel selectionViewModel;
    private final float[] antsDash = {4f, 4f}; // dash, gap in px scaled with the canvas
    private float antsPhase = 0f;
    private final javax.swing.Timer antsTimer = new javax.swing.Timer(60, e -> {
        antsPhase = (antsPhase + 1f) % (antsDash[0] + antsDash[1]);
        repaint();
    });

    public DrawingView(CanvasController controller,
                       CanvasRenderer canvasRenderer,
                       SelectionViewModel selectionViewModel,
                       DrawingViewModel drawingViewModel) {

        this.controller = controller;
        this.renderer = canvasRenderer;
        this.selectionViewModel = selectionViewModel;
        this.viewModel = drawingViewModel;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(800, 500));
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2 = (Graphics2D) g.create();
        renderer.resize(g2, viewModel);
        renderer.drawImage(g2, viewModel);
        renderer.renderDraw(g2, viewModel);
        renderer.layeringDraw(g2, viewModel);
        renderer.moveSelectionWindow(g2, selectionViewModel); // draw the content
        renderer.selectionDraw(g2, selectionViewModel); // draw the border on top
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e.getPoint());
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased(e.getPoint(), getImage());
        renderCanvasView();
        renderer.updateAntsTimer(antsTimer, selectionViewModel);
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            controller.handleMousePressed(e.getPoint());
            renderCanvasView();
            renderer.updateAntsTimer(antsTimer, selectionViewModel);
            repaint();
        }
    }

    /**
     * Josh added this to simulate mouse press
     */
    public void simulateMousePress() {
        controller.handleMousePressed(new Point());
        renderCanvasView();
        renderer.updateAntsTimer(antsTimer, selectionViewModel);
        repaint();
    }

    public void renderCanvasView() {
        if (this.viewModel.getRepaintState()) {
            repaint();
        }
    }

    public BufferedImage getImage() {
        // try only base snapshot: no selection overlay, no more overlay
        final BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g2d = image.createGraphics();
        renderer.resize(g2d, viewModel);
        renderer.drawImage(g2d, viewModel); // background strokes
        renderer.renderDraw(g2d, viewModel); // commited strokes
        renderer.layeringDraw(g2d, viewModel); // currentstroke head
        g2d.dispose();
        return image;
    }

    // We don't need these, but must include them:
    @Override
    public void mouseEntered(MouseEvent e) {
        // We don't need these, but must include them
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // We don't need these, but must include them
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // We don't need these, but must include them
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // We don't need these, but must include them
    }

    /**
     * Get username of currently logged-in user
     *
     * @return username as String
     */
    public String getUsername() {
        return this.viewModel.getCurrentUser();
    }

    /**
     * Set username of currently logged-in user
     */
    public void setUsername(String username) {
        this.viewModel.setCurrentUser(username);
    }


}
