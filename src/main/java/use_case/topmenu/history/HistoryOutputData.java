package use_case.topmenu.history;

import entity.Drawable;

import java.util.Stack;

public record HistoryOutputData(Stack<Drawable> drawableStack, boolean stackEmpty, Drawable currentDrawable) {
}
