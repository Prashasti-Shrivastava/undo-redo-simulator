package src.com.editor;

import src.com.editor.action.Action;
import src.com.editor.action.InsertAction;
import src.com.editor.manager.UndoRedoManager;
import src.com.editor.model.TextBuffer;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        TextBuffer buffer = new TextBuffer();
        UndoRedoManager manager = new UndoRedoManager();

        while (true) {
            System.out.println("\nCurrent Text: \"" + buffer.getContent() + "\"");
            System.out.println("1. Add Character | 2. Add Word | 3. Add Number | 4. Undo | 5. Redo | 6. Exit");
            System.out.print("Enter choice: ");

            if (!scn.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                scn.next();
                continue;
            }

            int choice = scn.nextInt();
            scn.nextLine(); // Clear newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter character: ");
                    String charInput = scn.nextLine();
                    if (!charInput.isEmpty()) {
                        char ch = charInput.charAt(0);
                        Action charAction = new InsertAction<Character>(buffer, ch);
                        manager.perform(charAction);
                    }
                    break;

                case 2:
                    System.out.print("Enter word: ");
                    String word = scn.nextLine();
                    Action wordAction = new InsertAction<String>(buffer, word + " ");
                    manager.perform(wordAction);
                    break;

                case 3:
                    System.out.print("Enter integer: ");
                    if (scn.hasNextInt()) {
                        int num = scn.nextInt();
                        scn.nextLine();
                        Action numAction = new InsertAction<Integer>(buffer, num);
                        manager.perform(numAction);
                    } else {
                        System.out.println("Invalid integer.");
                        scn.nextLine();
                    }
                    break;

                case 4:
                    manager.undo();
                    break;

                case 5:
                    manager.redo();
                    break;

                case 6:
                    System.out.println("Exiting simulator...");
                    scn.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}