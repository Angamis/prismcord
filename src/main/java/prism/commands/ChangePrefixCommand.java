package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import static prism.App.getPrefix;
import static prism.App.setPrefix;

public class ChangePrefixCommand implements MessageCreateListener, CommandStructure {

    private final String COMMAND_NAME = "prefix";
    private final String COMMAND_DESCRIPTION = "a command that changes the prefix of the bot";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String completeCommand = getPrefix() + COMMAND_NAME;

        if(event.getMessageContent().contains(completeCommand)) {
            //TODO change prefix and parse message and do sanity checks on it !! .contains is used

            setPrefix("*");
        }

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
