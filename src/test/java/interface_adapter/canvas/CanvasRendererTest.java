package interface_adapter.canvas;

import entity.Image;
import interface_adapter.newselection.SelectionViewModel;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class CanvasRendererTest {

    @Test
    void updateAntsTimer() {
        CanvasRenderer renderer = new CanvasRenderer();
        SelectionViewModel selectionViewModel = new SelectionViewModel();
        javax.swing.Timer timer = new javax.swing.Timer(10, e -> {});

        // nothing to draw -> timer stays stopped
        selectionViewModel.setIsDrawing(false);
        selectionViewModel.setHasSelection(false);
        renderer.updateAntsTimer(timer, selectionViewModel);
        assertFalse(timer.isRunning());

        // drawing -> timer starts
        selectionViewModel.setIsDrawing(true);
        renderer.updateAntsTimer(timer, selectionViewModel);
        assertTrue(timer.isRunning());

        // stop drawing/selection -> timer stops
        selectionViewModel.setIsDrawing(false);
        selectionViewModel.setHasSelection(false);
        renderer.updateAntsTimer(timer, selectionViewModel);
        assertFalse(timer.isRunning());
    }

    @Test
    void renderDraw() {
        CanvasRenderer renderer = new CanvasRenderer();
        DrawingViewModel drawingViewModel = new DrawingViewModel();

        // repaint off -> nothing happens (should not throw)
        drawingViewModel.shouldRepaint(false);
        drawingViewModel.setDrawables(new Stack<>());
        BufferedImage canvas = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        renderer.renderDraw(g2, drawingViewModel);
        g2.dispose();

        // repaint on with empty stack -> still fine (no exception)
        drawingViewModel.shouldRepaint(true);
        g2 = canvas.createGraphics();
        renderer.renderDraw(g2, drawingViewModel);
        g2.dispose();
    }

    @Test
    void resize() {
        CanvasRenderer renderer = new CanvasRenderer();
        DrawingViewModel drawingViewModel = new DrawingViewModel();
        drawingViewModel.setScale(2.0f);

        BufferedImage canvas = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();

        // initial identity scale
        assertEquals(1.0, g2.getTransform().getScaleX(), 1e-6);
        assertEquals(1.0, g2.getTransform().getScaleY(), 1e-6);

        renderer.resize(g2, drawingViewModel);

        assertEquals(2.0, g2.getTransform().getScaleX(), 1e-6);
        assertEquals(2.0, g2.getTransform().getScaleY(), 1e-6);
        g2.dispose();
    }

    @Test
    void drawImage() {
        CanvasRenderer renderer = new CanvasRenderer();
        DrawingViewModel drawingViewModel = new DrawingViewModel();

        // source image filled with red
        BufferedImage src = new BufferedImage(8, 8, BufferedImage.TYPE_INT_ARGB);
        Graphics2D sg = src.createGraphics();
        sg.setColor(Color.RED);
        sg.fillRect(0, 0, 8, 8);
        sg.dispose();

        Image img = new Image(src);
        var images = new ArrayList<Image>();
        images.add(img);
        drawingViewModel.setImageList(images);

        // destination canvas starts white
        BufferedImage canvas = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 16, 16);

        renderer.drawImage(g2, drawingViewModel);
        g2.dispose();

        // a pixel should be red after draw
        int rgb = canvas.getRGB(0, 0);
        assertEquals(Color.RED.getRGB(), rgb);
    }

    @Test
    void layeringDraw() {
        CanvasRenderer renderer = new CanvasRenderer();
        DrawingViewModel drawingViewModel = new DrawingViewModel();

        // null drawable
        drawingViewModel.setDrawable(null);
        BufferedImage canvas = new BufferedImage(10, 10, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        renderer.layeringDraw(g2, drawingViewModel);
        g2.dispose();
        assertNull(drawingViewModel.getDrawable());

        // If your Drawable head is one of StrokeRecord/PasteRecord/etc.,
        // set it here and call layeringDraw again. This test ensures no exception with null.
    }

    @Test
    void moveSelectionWindow() {
        CanvasRenderer renderer = new CanvasRenderer();
        SelectionViewModel selectionViewModel = new SelectionViewModel();

        // build a blue 5x5 selection image
        BufferedImage sel = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D sg = sel.createGraphics();
        sg.setColor(Color.BLUE);
        sg.fillRect(0, 0, 5, 5);
        sg.dispose();

        // destination canvas starts white
        BufferedImage canvas = new BufferedImage(30, 20, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 30, 20);

        selectionViewModel.setHasSelection(true);
        selectionViewModel.setBuffedImage(sel);
        selectionViewModel.setSelectionBounds(new Rectangle(10, 6, 5, 5));

        renderer.moveSelectionWindow(g2, selectionViewModel);
        g2.dispose();

        // a pixel inside the placed selection should be blue
        int rgb = canvas.getRGB(12, 7);
        assertEquals(Color.BLUE.getRGB(), rgb);
    }

    @Test
    void selectionDraw() {
        CanvasRenderer renderer = new CanvasRenderer();
        SelectionViewModel selectionViewModel = new SelectionViewModel();

        BufferedImage canvas = new BufferedImage(40, 30, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = canvas.createGraphics();
        // paint background white so border pixels differ
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, 40, 30);

        // draw a selection rectangle
        selectionViewModel.setIsDrawing(false);
        selectionViewModel.setHasSelection(true);
        selectionViewModel.setSelectionBounds(new Rectangle(5, 4, 12, 10));

        renderer.selectionDraw(g2, selectionViewModel);
        g2.dispose();

        // Check one border pixel changed from white (dark/ light dashed border)
        int border = canvas.getRGB(5, 4);
        assertNotEquals(Color.WHITE.getRGB(), border);
    }
}
