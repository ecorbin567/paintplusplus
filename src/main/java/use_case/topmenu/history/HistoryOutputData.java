package use_case.topmenu.history;

import java.util.Stack;

import entity.Drawable;

public record HistoryOutputData(Stack<Drawable> drawableStack, boolean stackEmpty, Drawable currentDrawable) {
}
