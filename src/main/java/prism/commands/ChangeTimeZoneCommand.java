package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class ChangeTimeZoneCommand implements MessageCreateListener, CommandStructure {

    private final String COMMAND_NAME = "timeZone";
    private final String COMMAND_DESCRIPTION = "a command that changes the time zone of the bot";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        //TODO
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return COMMAND_DESCRIPTION;
    }
}
