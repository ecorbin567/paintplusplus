package interface_adapter.canvas;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.util.Stack;

import javax.swing.Timer;

import entity.CutRecord;
import entity.Drawable;
import entity.Image;
import entity.MoveRecord;
import entity.PasteRecord;
import entity.StrokeRecord;
import interface_adapter.newselection.SelectionViewModel;

public class CanvasRenderer {
    private final float[] antsDash = {4f, 4f}; // dash, gap in px scaled with the canvas
    private static final float ANTS_PHASE = 0f;

    public CanvasRenderer() {
        // Don't need to instantiate
    }

    public void updateAntsTimer(Timer timer, SelectionViewModel selectionViewModel) {
        final boolean isDrawing = selectionViewModel.getIsDrawing();
        final boolean hasSelection = selectionViewModel.getHasSelection();
        final boolean shouldRun = (isDrawing || hasSelection);
        if (shouldRun && !timer.isRunning()) {
            timer.start();
        }
        if (!shouldRun && timer.isRunning()) {
            timer.stop();
        }
    }

    public void renderDraw(Graphics2D graphics2D, DrawingViewModel drawingViewModel) {
        if (drawingViewModel.getRepaintState()) {
            final Stack<Drawable> drawables = drawingViewModel.getDrawables();
            for (Drawable drawable : drawables) {
                drawDrawable(graphics2D, drawable);
            }
        }
    }

    public void resize(Graphics2D graphics2D, DrawingViewModel drawingViewModel) {
        graphics2D.scale(drawingViewModel.getScale(), drawingViewModel.getScale());
    }

    public void drawImage(Graphics2D graphics2D, DrawingViewModel drawingViewModel) {
        for (Image image : drawingViewModel.getImageList()) {
            image.draw(graphics2D);
        }
    }

    public void layeringDraw(Graphics2D graphics2D, DrawingViewModel drawingViewModel) {
        final Drawable head = drawingViewModel.getDrawable();
        if (head != null) {
            drawDrawable(graphics2D, head);
        }
    }

    public void moveSelectionWindow(Graphics2D graphics2D, SelectionViewModel selectionViewModel) {
        if (selectionViewModel.getHasSelection() && selectionViewModel.getSelectionImage() != null
                && selectionViewModel.getSelectionBounds() != null) {
            graphics2D.drawImage(
                    selectionViewModel.getSelectionImage(),
                    selectionViewModel.getSelectionBounds().x,
                    selectionViewModel.getSelectionBounds().y,
                    selectionViewModel.getSelectionBounds().width,
                    selectionViewModel.getSelectionBounds().height,
                    null
            );
        }
    }

    public void selectionDraw(Graphics2D graphics2D, SelectionViewModel selectionViewModel) {
        // selection tool rendering logic
        Rectangle r = null;

        if (selectionViewModel.getIsDrawing()) {
            r = selectionViewModel.getSelectionToolBounds();
        }
        else {
            if (selectionViewModel.getHasSelection() && selectionViewModel.getSelectionBounds() != null) {
                r = selectionViewModel.getSelectionBounds();
            }
        }

        if (r != null && r.getWidth() > 0 && r.height > 0) {
            final Stroke oldStroke = graphics2D.getStroke();
            final Color oldColor = graphics2D.getColor();
            // dark dashes
            final Stroke dark = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10f, antsDash, ANTS_PHASE);
            graphics2D.setStroke(dark);
            graphics2D.setColor(Color.DARK_GRAY);
            graphics2D.drawRect(r.x, r.y, r.width - 1, r.height - 1);

            // light dashes shifted by one dash length to fill the gaps
            final Stroke light = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                    10f, antsDash, ANTS_PHASE + antsDash[0]);
            graphics2D.setStroke(light);
            graphics2D.setColor(new Color(230, 230, 230)); // very light gray
            graphics2D.drawRect(r.x, r.y, r.width - 1, r.height - 1);

            graphics2D.setStroke(oldStroke);
            graphics2D.setColor(oldColor);
        }
    }

    private void drawDrawable(Graphics2D g2, Drawable d) {
        if (d instanceof StrokeRecord s) {
            g2.setColor(s.getColour());
            g2.setStroke(new BasicStroke(s.getWidth(),
                    BasicStroke.CAP_ROUND,
                    BasicStroke.JOIN_ROUND));
            for (int i = 1; i < s.getPts().size(); i++) {
                final Point p1 = s.getPts().get(i - 1);
                final Point p2 = s.getPts().get(i);
                g2.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
        else {
            if (d instanceof PasteRecord pr) {
                g2.drawImage(pr.image,
                        pr.bounds.x, pr.bounds.y,
                        pr.bounds.width, pr.bounds.height,
                        null);
            }
            else {
                if (d instanceof CutRecord cr) {
                    g2.setColor(Color.WHITE);
                    g2.fillRect(cr.bounds.x, cr.bounds.y,
                            cr.bounds.width, cr.bounds.height);

                }
                else {
                    if (d instanceof MoveRecord mr) {
                        /* blank the old rectangle */
                        g2.setColor(Color.WHITE);
                        g2.fillRect(mr.from().x, mr.from().y, mr.from().width, mr.from().height);

                        /* draw the bitmap at its new spot */
                        g2.drawImage(mr.image(),
                                mr.to().x, mr.to().y,
                                mr.to().width, mr.to().height,
                                null);
                    }
                }
            }
        }
    }
}
