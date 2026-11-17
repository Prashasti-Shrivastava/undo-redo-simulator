package src.com.editor.action;

import src.com.editor.model.TextBuffer;

public class InsertAction<T> implements Action {
    private final TextBuffer textBuffer;
    private final String value;

    public InsertAction(TextBuffer textBuffer, T data) {
        this.textBuffer = textBuffer;
        this.value = String.valueOf(data); // Safely converts char, String, int, etc.
    }

    @Override
    public void execute() {
        textBuffer.append(value);
    }

    @Override
    public void undo() {
        textBuffer.removeLast(value.length());
    }
}