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
            case START -> {
                tool.start(p);
                canvas.beginSelection(p);
            }
            case DRAG -> {
                tool.drag(p);
                canvas.updateLiveSelection(tool);
            }
            case COMMIT -> {
                tool.finish(p);
                canvas.commitSelection(tool);
                tool.cancel();
            }

            case DRAG_EXISTING ->{
                Point d = new Point(inputData.w(), inputData.h());
                canvas.dragExisting(d);
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
