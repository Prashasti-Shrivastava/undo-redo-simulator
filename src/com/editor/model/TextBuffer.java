package src.com.editor.model;

public class TextBuffer {
    private final StringBuilder buffer;

    public TextBuffer() {
        this.buffer = new StringBuilder();
    }

    public void append(String text) {
        buffer.append(text);
    }

    public void removeLast(int length) {
        int currentLength = buffer.length();
        if (length > 0 && currentLength >= length) {
            buffer.delete(currentLength - length, currentLength);
        }
    }

    public String getContent() {
        return buffer.toString();
    }
}