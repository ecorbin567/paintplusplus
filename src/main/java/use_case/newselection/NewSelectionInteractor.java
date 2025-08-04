package use_case.newselection;

import entity.DrawingCanvas;
import entity.SelectionTool;

import java.awt.*;

public class NewSelectionInteractor implements NewSelectionInputBoundary{
    private final DrawingCanvas canvas; // injected
    private final SelectionTool tool; // injected
    private final NewSelectionOutputBoundary presenter; // injected

    public NewSelectionInteractor(DrawingCanvas canvas,
                                    SelectionTool tool,
                                    NewSelectionOutputBoundary presenter){
        this.canvas = canvas;
        this.tool = tool;
        this.presenter = presenter;
    }
     @Override
    public void execute(NewSelectionInputData inputData){
        Point p = new Point(inputData.x(), inputData.y());
        switch (inputData.action()){
            case START ->
                canvas.beginSelection(p);

            case DRAG -> {
                tool.drag(p);
                canvas.updateLiveSelection(tool);
            }
            case COMMIT ->
                canvas.commitSelection(tool);

            case CANCEL -> {
                canvas.cancelSelection();
                tool.cancel();
            }
        }
         presenter.present(
                 new NewSelectionOutputData(
                         tool.hasSelection(),
                         tool.getBounds(),
                         tool.isDragging() ? tool.getLiveImage() : null
                 )
         );
     }
}
