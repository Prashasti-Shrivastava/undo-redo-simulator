package src.com.editor.manager;

import src.com.editor.action.Action;
import java.util.Stack;

public class UndoRedoManager {
    private final Stack<Action> undoStack;
    private final Stack<Action> redoStack;

    public UndoRedoManager() {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
    }

    public void perform(Action action) {
        action.execute();
        undoStack.push(action);
        redoStack.clear(); // Wipe old redo branch when a new action is performed
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Message: No undo operation available.");
            return;
        }
        Action lastAction = undoStack.pop();
        lastAction.undo();
        redoStack.push(lastAction);
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Message: No redo operation available.");
            return;
        }
        Action nextAction = redoStack.pop();
        nextAction.execute();
        undoStack.push(nextAction);
    }
}