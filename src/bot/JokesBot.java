package bot;

import tools.Command;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class JokesBot extends CommandBot {

    public static final String JOKESFILE = "jokes.csv";
    private ArrayList<String> jokes;
    public JokesBot() {
        jokes = new ArrayList<>();
        File inputFile = new File(JOKESFILE);
        Scanner fileReader = null;
        try {
            fileReader = new Scanner(inputFile);
            while (fileReader.hasNextLine()) {
                jokes.add(fileReader.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the jokes file.");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
        finally {
            if (fileReader != null)
                fileReader.close();
        }
    }
    @Override
    public void actionPerform() {
        // not needed
    }

    @Override
    protected String respond(Command command) {
        return jokes.get(ThreadLocalRandom.current().nextInt(0, jokes.size()));
    }

    @Override
    protected String getCommand() {
        return "jokes";
    }

    @Override
    protected String getCommandHelp() {
        return "this bot returns jokes";
    }
}
