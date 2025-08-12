package use_case.erase;

import entity.*;
import interface_adapter.canvas.CanvasPresenter;
import interface_adapter.canvas.DrawingViewModel;
import org.junit.jupiter.api.Test;
import use_case.mouseui.MouseUIInputData;
import use_case.mouseui.MouseUIOutputBoundary;
import use_case.mouseui.MouseUIUseInteractor;

import java.awt.*;
import java.util.Stack;

import static entity.ToolEnum.ERASER;
import static org.junit.jupiter.api.Assertions.*;

public class EraseInteractorTest {
    DrawingViewModel drawingViewModel = new DrawingViewModel();
    CanvasState canvasState = new CanvasState();
    MouseUIOutputBoundary mouseUIOutputBoundary = new CanvasPresenter(drawingViewModel);
    MouseUIUseInteractor mouseInteractor = new MouseUIUseInteractor(
            canvasState, mouseUIOutputBoundary
    );

    @Test
    void mouseIsPressed() {
        this.canvasState.setToolState(ERASER);
        mouseInteractor.setCurrentState(new StrokeRecord(Color.BLACK, 3f));
        mouseInteractor.mouseIsPressed(new MouseUIInputData(new Point(10, 10)));
        assertSame(ERASER, canvasState.getToolState());
        Drawable currentState = mouseInteractor.getActionHistory().getCurrentState();
        Stack<Drawable> undoStack = mouseInteractor.getActionHistory().getUndoStack();
        assertNotNull(currentState);
        assertFalse(undoStack.isEmpty());
    }

    @Test
    void mouseIsDragged() {
        this.canvasState.setToolState(ERASER);
        mouseInteractor.setCurrentState(new StrokeRecord(Color.BLACK, 3f));
        mouseInteractor.mouseIsPressed(new MouseUIInputData(new Point(10, 10)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(11, 11)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(12, 12)));
        assertSame(ERASER, canvasState.getToolState());
        Drawable currentState = mouseInteractor.getActionHistory().getCurrentState();
        Stack<Drawable> undoStack = mouseInteractor.getActionHistory().getUndoStack();
        assertNotNull(currentState);
        assertFalse(undoStack.isEmpty());
    }

    @Test
    void mouseIsReleased() {
        this.canvasState.setToolState(ERASER);
        mouseInteractor.setCurrentState(new StrokeRecord(Color.BLACK, 3f));
        mouseInteractor.mouseIsPressed(new MouseUIInputData(new Point(10, 10)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(11, 11)));
        mouseInteractor.mouseIsDragged(new MouseUIInputData(new Point(12, 12)));
        mouseInteractor.mouseIsReleased(new MouseUIInputData(new Point(13, 13)));
        assertSame(ERASER, canvasState.getToolState());
        Drawable currentState = mouseInteractor.getActionHistory().getCurrentState();
        Stack<Drawable> undoStack = mouseInteractor.getActionHistory().getUndoStack();
        assertNotNull(currentState);
        assertFalse(undoStack.isEmpty());
    }
}
