package view;

import interface_adapter.SelectionViewModel;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.DrawingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class DrawingView extends JPanel implements MouseListener, MouseMotionListener {
    private final DrawingViewModel viewModel;
    private final CanvasController controller;
    private final CanvasRenderer renderer;
    private final SelectionViewModel selectionViewModel;
    private final float[] ANTS_DASH = {4f, 4f}; // dash, gap in px scaled with the canvas
    private float antsPhase = 0f;
    private final javax.swing.Timer antsTimer = new javax.swing.Timer(60, e -> {
        antsPhase = (antsPhase +1f) % (ANTS_DASH[0] + ANTS_DASH[1]);
        repaint();
    });


    public DrawingView (CanvasController controller,
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
        Graphics2D g2 = (Graphics2D) g.create();
        renderer.resize(g2, viewModel);
        renderer.drawImage(g2, viewModel);
        renderer.renderDraw(g2, viewModel);
        renderer.layeringDraw(g2, viewModel);
        renderer.selectionDraw(g2, selectionViewModel);
        renderer.moveSelectionWindow(g2, selectionViewModel);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e.getPoint());
        renderCanvasView();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased(e.getPoint(), getImage());
        renderCanvasView();
        renderer.updateAntsTimer(antsTimer, selectionViewModel);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            controller.handleMousePressed(e.getPoint());
            renderCanvasView();
        }
    }

    public void renderCanvasView(){
        if (this.viewModel.getRepaintState()){
            repaint();
        }
    }

    public BufferedImage getImage() {
        BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = image.createGraphics();
        this.paint(g2d);
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
}
