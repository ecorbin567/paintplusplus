package entity;

/**
 * Enumerates the tools available in the canvas.
 *
 * PENCIL – freehand drawing using the current {@link Paintbrush} settings.</li>
 * ERASER – removes pixels (typically painting background color/clear).</li>
 * SELECT – rectangular marquee to cut/move/paste a region.</li>
 * CHANGECOLOR – UI mode to change the paintbrush color (not a drawing action).</li>
 *
 */
public enum ToolEnum {
    PENCIL,
    ERASER,
    SELECT,
    CHANGECOLOR
}
