package use_case.mouseui;

import java.util.Stack;

import entity.Drawable;

/**
 * Output data for the mouse ui use case.
 *
 * @param drawables
 * @param state
 * @param drawable
 */
public record MouseUIOutputData(Stack<Drawable> drawables, boolean state, Drawable drawable) {
}
