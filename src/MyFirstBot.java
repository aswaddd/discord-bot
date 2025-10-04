import bot.*;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;



public class MyFirstBot {
    private static final String token = "";
    public static void main(String[] args) {


        JDA jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.MESSAGE_CONTENT).build();
        CommandListUpdateAction c = jda.updateCommands();

        AbstractBot[] bots = new AbstractBot[] {new JokesBot()};


        for (AbstractBot b : bots) {
            jda.addEventListener(b);
            if (b != null && b instanceof CommandBot)
                c.addCommands(((CommandBot)b).getCommands());
        }
        c.queue();

        new Thread(() -> {
            while (true)
                try {
                    Thread.sleep(10000);
                    for (AbstractBot b : bots)
                        b.actionPerform();
                } catch (InterruptedException e) {
                    System.err.println(e);
                }
        }).start();

    }

}