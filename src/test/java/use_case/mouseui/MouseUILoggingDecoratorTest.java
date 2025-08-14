package use_case.mouseui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

import static org.junit.jupiter.api.Assertions.*;

class MouseUILoggingDecoratorTest {

    private static class LogCollector extends Handler {
        private final List<LogRecord> records = new ArrayList<>();
        @Override public void publish(LogRecord record) { records.add(record); }
        @Override public void flush() {}
        @Override public void close() throws SecurityException {}
        List<LogRecord> getRecords() { return records; }
    }

    private static class RecordingInner implements MouseUIUseInputBoundary {
        boolean pressedCalled;
        boolean draggedCalled;
        boolean releasedCalled;
        MouseUIInputData lastInput;

        @Override public void mouseIsPressed(MouseUIInputData inputData) {
            pressedCalled = true; lastInput = inputData;
        }
        @Override public void mouseIsDragged(MouseUIInputData inputData) {
            draggedCalled = true; lastInput = inputData;
        }
        @Override public void mouseIsReleased(MouseUIInputData inputData) {
            releasedCalled = true; lastInput = inputData;
        }
    }

    private Logger logger;
    private Level originalLevel;
    private Handler[] originalHandlers;
    private boolean originalUseParentHandlers;
    private LogCollector logCollector;

    @BeforeEach
    void setUpLogger() {
        logger = Logger.getLogger(MouseUILoggingDecorator.class.getName());
        originalLevel = logger.getLevel();
        originalHandlers = logger.getHandlers();
        originalUseParentHandlers = logger.getUseParentHandlers();
        logger.setUseParentHandlers(false);
        for (Handler h : originalHandlers) logger.removeHandler(h);
        logCollector = new LogCollector();
        logCollector.setLevel(Level.ALL);
        logger.addHandler(logCollector);
        logger.setLevel(Level.ALL);
    }

    @AfterEach
    void tearDownLogger() {
        logger.removeHandler(logCollector);
        for (Handler h : originalHandlers) logger.addHandler(h);
        logger.setLevel(originalLevel);
        logger.setUseParentHandlers(originalUseParentHandlers);
    }

    @Test
    void mouseIsPressed() {
        RecordingInner inner = new RecordingInner();
        MouseUILoggingDecorator deco = new MouseUILoggingDecorator(inner);
        MouseUIInputData input = new MouseUIInputData(new Point(10, 20));
        deco.mouseIsPressed(input);
        assertTrue(inner.pressedCalled);
        assertSame(input, inner.lastInput);
        List<LogRecord> logs = logCollector.getRecords();
        assertEquals(2, logs.size());
        assertEquals(Level.FINE, logs.get(0).getLevel());
        assertEquals("mouseIsPressed: point={0}", logs.get(0).getMessage());
        assertArrayEquals(new Object[]{new Point(10, 20)}, logs.get(0).getParameters());
        assertEquals(Level.INFO, logs.get(1).getLevel());
        assertEquals("mouseIsPressed completed in {0} ns", logs.get(1).getMessage());
        assertNotNull(logs.get(1).getParameters()[0]);
    }

    @Test
    void mouseIsDragged() {
        RecordingInner inner = new RecordingInner();
        MouseUILoggingDecorator deco = new MouseUILoggingDecorator(inner);
        deco.mouseIsDragged(null);
        assertTrue(inner.draggedCalled);
        assertNull(inner.lastInput);
        List<LogRecord> logs = logCollector.getRecords();
        assertEquals(2, logs.size());
        assertEquals(Level.FINER, logs.get(0).getLevel());
        assertEquals("mouseIsDragged: point={0}", logs.get(0).getMessage());
        assertArrayEquals(new Object[]{null}, logs.get(0).getParameters());
        assertEquals(Level.FINE, logs.get(1).getLevel());
        assertEquals("mouseIsDragged completed in {0} ns", logs.get(1).getMessage());
        assertNotNull(logs.get(1).getParameters()[0]);
    }

    @Test
    void mouseIsReleased() {
        RecordingInner inner = new RecordingInner();
        MouseUILoggingDecorator deco = new MouseUILoggingDecorator(inner);
        MouseUIInputData input = new MouseUIInputData(new Point(5, 7));
        deco.mouseIsReleased(input);
        assertTrue(inner.releasedCalled);
        assertSame(input, inner.lastInput);
        List<LogRecord> logs = logCollector.getRecords();
        assertEquals(2, logs.size());
        assertEquals(Level.FINE, logs.get(0).getLevel());
        assertEquals("mouseIsReleased: point={0}", logs.get(0).getMessage());
        assertArrayEquals(new Object[]{new Point(5, 7)}, logs.get(0).getParameters());
        assertEquals(Level.INFO, logs.get(1).getLevel());
        assertEquals("mouseIsReleased completed in {0} ns", logs.get(1).getMessage());
        assertNotNull(logs.get(1).getParameters()[0]);
    }
}
