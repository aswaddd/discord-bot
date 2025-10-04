package bot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import net.dv8tion.jda.api.interactions.commands.build.*;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;


import java.util.ArrayList;
import java.util.List;

import tools.*;

public abstract class CommandBot extends AbstractBot {

    protected List<OptionData> options = new ArrayList<>();
    private boolean ephemeral;

    protected abstract String respond(Command command);
    protected abstract String getCommand();
    protected abstract String getCommandHelp();

    protected CommandBot() {
        this.ephemeral = false;
    }

    @Override
    public final void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getUser().isBot()) return;
        if (event.getName().equals(getCommand())) {
            Command command = new Command(event.getCommandString());
            for (OptionMapping o : event.getOptions()) {
                command.addOption(o.getName(), o.getAsString());
            }
            String replyMessage = respond(command);
            event.reply(replyMessage).setEphemeral(true).queue();
        }
    }

    public final CommandData getCommands() {
        SlashCommandData c = Commands.slash(getCommand(), getCommandHelp());
        for (OptionData o : options) {
            c.addOption(o.getType(), o.getName(), o.getDescription(), o.isRequired());
        }
        return c;
    }
}
