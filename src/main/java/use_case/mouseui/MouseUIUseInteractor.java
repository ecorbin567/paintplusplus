package use_case.mouseui;

import entity.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Stack;


public class MouseUIUseInteractor implements MouseUIUseInputBoundary {
    private final CanvasState canvasState;
    private final MouseUIOutputBoundary mouseUIOutputBoundary;
    private final ActionHistory actionHistory;
    private final SelectOutputBoundary selectOutputBoundary;
    private final SelectionTool selectionTool;
    private final List<Rectangle> clearRegions;
    private final java.util.List<CanvasState.Pair<BufferedImage, Rectangle>> commitedSelections;

    public MouseUIUseInteractor(CanvasState canvasState,
                                MouseUIOutputBoundary mouseUIOutputBoundary,
                                SelectOutputBoundary selectOutputBoundary) {
        this.canvasState = canvasState;
        this.mouseUIOutputBoundary = mouseUIOutputBoundary;
        this.actionHistory = canvasState.getActionHistory();
        this.selectOutputBoundary = selectOutputBoundary;
        this.selectionTool = canvasState.getSelectionTool();
        this.clearRegions = canvasState.getClearRegions();
        this.commitedSelections = canvasState.getCommitedSelections();
    }

    @Override
    public void mouseIsPressed(MouseUIInputData inputData) {
        ToolEnum toolstate = canvasState.getToolState();
        switch(toolstate) {
            case PENCIL, ERASER, CHANGECOLOR -> handleDrawingTool(toolstate, inputData);
            case SELECT -> {
                handleSelectTool(inputData);
                sendSelectionOutputData();
            }
        }
        sendMouseOutputData();
    }

    private void handleSelectTool(MouseUIInputData inputData) {
        Point p = inputData.getPoint();
        boolean hasSelection = this.canvasState.getHasSelection();
        Rectangle selectionBounds = this.canvasState.getSelectionBounds();

        if (hasSelection && selectionBounds.contains(p)) {
            selectPrexist(p, selectionBounds);
        } else if (hasSelection) {
            deselect(selectionBounds);
        } else {
            drawNewSelect(p);
        }
    }

    private void drawNewSelect(Point p) {
        this.canvasState.setDraggingSelection(false);
        selectionTool.start(p);
        this.canvasState.setIsDrawing(true);
    }

    private void deselect(Rectangle selectionBounds) {
        this.canvasState.setDraggingSelection(false);
        this.canvasState.setHasCutOut(false);
        if (actionHistory.getCurrentState() instanceof CutRecord) {
            actionHistory.undo();
        }

        Rectangle dest = new Rectangle(selectionBounds);
        Rectangle src = new Rectangle(this.canvasState.getSelectionOriginalBounds());
        BufferedImage selectionImage = this.canvasState.getSelectionImage();
        actionHistory.push(new MoveRecord(selectionImage, src, dest));
        commitedSelections.add(new CanvasState.Pair<>(selectionImage, dest));
        clearRegions.add(src);

        this.canvasState.setHasSelection(false);
        this.canvasState.setSelectionImage(null);
        this.canvasState.setSelectionBounds(null);
        selectionTool.cancel();
        this.canvasState.setIsDrawing(false);
    }

    private void selectPrexist(Point p, Rectangle selectionBounds) {
        this.canvasState.setDraggingSelection(true);
        this.canvasState.setHasCutOut(false);
        Point p2 = new Point(p.x - selectionBounds.x,
                p.y - selectionBounds.y);
        this.canvasState.setDragAnchor(p2);
    }

    @Override
    public void mouseIsDragged(MouseUIInputData inputData) {
        ToolEnum toolstate = canvasState.getToolState();
        switch(toolstate) {
            case PENCIL, ERASER -> mouseDragPencilEraser(inputData);
            case SELECT -> {
                mouseDragSelect(inputData);
                sendSelectionOutputData();
            }
        }
        sendMouseOutputData();
    }

    private void mouseDragSelect(MouseUIInputData inputData) {
        Point p = inputData.getPoint();
        boolean draggingSelection = this.canvasState.getDraggingSelection();
        boolean hasCutOut = this.canvasState.getHasCutOut();

        if (draggingSelection) {
            if(!hasCutOut) {
                Rectangle hole = new Rectangle(this.canvasState.getSelectionBounds());
                clearRegions.add(hole);
                actionHistory.push(new CutRecord(hole));
                this.canvasState.setHasCutOut(true);
            }

            this.canvasState.getSelectionBounds().x = p.x-this.canvasState.getDragAnchor().x;
            this.canvasState.getSelectionBounds().y = p.y-this.canvasState.getDragAnchor().y;
        }else{
            selectionTool.drag(p);
        }
    }

    private void handleDrawingTool(ToolEnum toolstate, MouseUIInputData inputData) {
        Color color = Color.BLACK;
        float size = 3f;

        if (toolstate == ToolEnum.PENCIL || toolstate == ToolEnum.CHANGECOLOR) {
            Paintbrush paintbrush = this.canvasState.getPaintbrush();
            color = paintbrush.getColour();
            size = paintbrush.getWidth();
        }
        else if (toolstate == ToolEnum.ERASER) {
            Eraser eraser = this.canvasState.getEraser();
            color = Color.WHITE;
            size = eraser.getWidth();
        }

        StrokeRecord currentStroke = new StrokeRecord(color, size);
        currentStroke.pts.add(inputData.getPoint());
        actionHistory.push(currentStroke);
    }

    @Override
    public void mouseIsReleased(MouseUIInputData inputData) {
        ToolEnum toolstate = canvasState.getToolState();
        Point p = inputData.getPoint();
        boolean draggingSelection = this.canvasState.getDraggingSelection();
        BufferedImage image = inputData.getImage();

        if (toolstate == ToolEnum.SELECT){
            releasingMouse(draggingSelection, selectionTool, p, image);
            sendSelectionOutputData();
        }

        sendMouseOutputData();
    }

    private void releasingMouse(boolean draggingSelection, SelectionTool tool, Point p, BufferedImage image) {
        if(!draggingSelection) {
            tool.finish(p);
            this.canvasState.setIsDrawing(false);
            Rectangle rect = tool.getBounds();

            if (rect.width>0&&rect.height>0) {
                this.canvasState.setSelectionImage(image.getSubimage(rect.x, rect.y, rect.width, rect.height));
                this.canvasState.setSelectionBounds(new Rectangle(rect));
                this.canvasState.setSelectionOriginalBounds(new Rectangle(rect));
                this.canvasState.setHasSelection(true);
            }
        }
        this.canvasState.setDraggingSelection(false);
        tool.cancel();
    }


    private void mouseDragPencilEraser(MouseUIInputData inputData) {
        Drawable drawable = actionHistory.getCurrentState();
        if (drawable instanceof StrokeRecord strokeRecord) {
            strokeRecord.pts.add(inputData.getPoint());
        }
    }
    private void sendSelectionOutputData() {
        selectOutputBoundary.setIsDraggingSelection(getSelectOutputData());
        selectOutputBoundary.setIsDrawing(getSelectOutputData());
        selectOutputBoundary.setSelectionToolBounds(getSelectOutputData());
        selectOutputBoundary.setHasSelection(getSelectOutputData());
        selectOutputBoundary.setSelectionImage(getSelectOutputData());
        selectOutputBoundary.setSelectionBounds(getSelectOutputData());
    }

    private SelectOutputData getSelectOutputData() {
        BufferedImage image = canvasState.getSelectionImage();
        Rectangle bounds = canvasState.getSelectionBounds();
        boolean hasSelection = canvasState.getHasSelection();
        boolean draggingSelection = canvasState.getDraggingSelection();
        boolean isDrawing = canvasState.getIsDrawing();
        Rectangle selectionToolBounds = canvasState.getSelectionTool().getBounds();
        return new SelectOutputData(image,
                bounds, hasSelection, draggingSelection, isDrawing, selectionToolBounds);
    }

    private void sendMouseOutputData() {
        Stack<Drawable> undoStack = this.actionHistory.getUndoStack();
        boolean state = !undoStack.isEmpty();
        Drawable currentDrawable = actionHistory.getCurrentState();
        MouseUIOutputData outputData = new MouseUIOutputData(undoStack, state, currentDrawable);
        mouseUIOutputBoundary.setDrawableState(outputData);
        mouseUIOutputBoundary.setRepaintState(outputData);
        mouseUIOutputBoundary.setCurrentDrawable(outputData);
    }
}
