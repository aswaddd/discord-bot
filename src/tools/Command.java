package tools;

import java.util.ArrayList;

public class Command extends Message {
    ArrayList<TextPair> options = new ArrayList<>();
    public Command(String commandString) {
        super(commandString);
    }
    public void addOption(String name, String value) {
        options.add(new TextPair(name, value));
    }
}
