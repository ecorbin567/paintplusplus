package use_case.mouseui;

import entity.Drawable;

import java.util.Stack;

/**
 * Output data for the mouse ui use case.
 * @param drawables
 * @param state
 * @param drawable
 */
public record MouseUIOutputData(Stack<Drawable> drawables, boolean state, Drawable drawable) {
}
