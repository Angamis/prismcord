package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;

public class CommandsInfoCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "commands";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ownCommand = App.getPrefix() + COMMAND_NAME;

        if (event.getMessageContent().equalsIgnoreCase(ownCommand)) {
            event.getChannel().sendMessage(App.getCommandsMap().toString());
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return "You just used this command \uD83D\uDE03";
    }
}
