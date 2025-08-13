package use_case.mouseui;

import entity.Drawable;

import java.util.Deque;

public record MouseUIOutputData(Deque<Drawable> drawables, boolean state, Drawable drawable) {
}
