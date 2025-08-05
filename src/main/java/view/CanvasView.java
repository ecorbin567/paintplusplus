package view;


import entity.Drawable;
import entity.StrokeRecord;
import interface_adapter.canvas.CanvasController;
import interface_adapter.canvas.CanvasRenderer;
import interface_adapter.canvas.CanvasViewModel;
import interface_adapter.goback.GoBackController;
import interface_adapter.goback.GoBackState;
import interface_adapter.goback.GoBackViewModel;
import view.MidMenuBar.MidMenuBarBuilder;
import view.TopMenuBar.MenuActionListener;
import view.TopMenuBar.TopMenuBarBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Objects;

public class CanvasView extends JPanel implements ActionListener, MenuActionListener, MouseListener, MouseMotionListener {
    private final String viewName = "canvas";
    private final GoBackViewModel goBackViewModel;
    private final GoBackController goBackController;
    private final Color backgroundColor = Color.WHITE;
    private final CanvasViewModel viewModel;
    private final CanvasController controller;
    private final CanvasRenderer renderer;

    public CanvasView(GoBackViewModel goBackViewModel,
                      GoBackController goBackController,
                      CanvasController controller,
                      CanvasRenderer canvasRenderer) {
        this.goBackViewModel = goBackViewModel;
        this.viewModel = new CanvasViewModel();
        this.goBackController = goBackController;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));
        this.controller = controller;
        this.renderer = canvasRenderer;

        setBackground(backgroundColor);
        addMouseListener(this);
        addMouseMotionListener(this);
        setPreferredSize(new Dimension(800, 600));
//        TopMenuBarBuilder topMenuBarBuilder = new TopMenuBarBuilder(canvas);
//        JMenuBar menuBar = topMenuBarBuilder.getMenuBar();
//        topMenuBarBuilder.setMenuActionListener(this);
//        this.add(menuBar, BorderLayout.NORTH);
//
//        MidMenuBarBuilder midMenuBarBuilder = new MidMenuBarBuilder(canvas);
//        JPanel panel = midMenuBarBuilder.getPanel();
//        JPanel bottomPanel = new JPanel();
//        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
//        bottomPanel.add(panel);
//        bottomPanel.add(canvas);
//        this.add(bottomPanel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    public String getViewName() {
        return viewName;
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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        renderer.renderDraw(g2, viewModel);
        g2.dispose();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e.getPoint());
        renderCanvasView();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased(e.getPoint());
        renderCanvasView();
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
            this.viewModel.shouldRepaint(false);
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
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseMoved(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
