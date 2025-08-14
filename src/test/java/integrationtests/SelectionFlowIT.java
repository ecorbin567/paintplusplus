package integrationtests;

import entity.CanvasState;
import entity.ToolEnum;
import interface_adapter.newselection.SelectionPresenter;
import interface_adapter.newselection.SelectionViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.newselection.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

class SelectionFlowIT {

    private CanvasState cs;
    private SelectionViewModel vm;
    private NewSelectionInteractor interactor;

    @BeforeEach
    void setUp() {
        cs = new CanvasState();
        cs.setToolState(ToolEnum.SELECT);

        vm = new SelectionViewModel();
        interactor = new NewSelectionInteractor(cs, new SelectionPresenter(vm));
    }

    private static BufferedImage base(int w, int h) {
        BufferedImage img = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setColor(Color.WHITE); g.fillRect(0,0,w,h);
        g.dispose();
        return img;
    }

    @Test
    void makesSelection_andPresenterUpdatesViewModel() {
        BufferedImage canvas = base(200, 200);

        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.START,  new Point(20, 30), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.DRAG,   new Point(80,  90), null));
        interactor.execute(new NewSelectionInputData(NewSelectionInputData.Action.COMMIT, new Point(80,  90), canvas));

        assertTrue(vm.getHasSelection(), "VM should reflect an active selection");
        assertEquals(new Rectangle(20,30,60,60), vm.getSelectionBounds(), "VM should have selection bounds");
        assertNotNull(vm.getSelectionImage(), "VM should hold selection bitmap");
        assertFalse(vm.getIsDrawing(), "VM should not be in drawing state after commit");
    }
}
