package use_case.mouseui;

import entity.Drawable;

import java.util.Stack;

public record MouseUIOutputData(Stack<Drawable> drawables, boolean state, Drawable drawable) {
}
