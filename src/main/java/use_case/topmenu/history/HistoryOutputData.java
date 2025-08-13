package use_case.topmenu.history;

import entity.Drawable;

import java.util.Deque;

public record HistoryOutputData(Deque<Drawable> drawableStack, boolean stackEmpty, Drawable currentDrawable) {
}
