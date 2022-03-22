package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class ChangeTimeZoneCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "timeZone";
    private static final String COMMAND_DESCRIPTION = "a command that changes the time zone of the bot";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //TODO
    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }

    public static String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
