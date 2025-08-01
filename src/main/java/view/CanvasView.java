package view;


import interface_adapter.canvas.CanvasController;
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

    public CanvasView(GoBackViewModel goBackViewModel, GoBackController goBackController, CanvasController controller) {
        this.goBackViewModel = goBackViewModel;
        this.viewModel = new CanvasViewModel();
        this.goBackController = goBackController;
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(800, 600));
        this.controller = controller;

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
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        controller.handleMouseDragged(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        controller.handleMouseReleased(e.getPoint());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (SwingUtilities.isLeftMouseButton(e)) {
            controller.handleMousePressed(e.getPoint());
        }
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
