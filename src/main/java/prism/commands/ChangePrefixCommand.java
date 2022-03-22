package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

public class ChangePrefixCommand implements MessageCreateListener, CommandStructure {

    private static String prefix = "*";
    private static final String COMMAND_NAME = "prefix";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String completeCommand = getPrefix() + COMMAND_NAME;

        if(event.getMessageContent().contains(completeCommand)) {
            //TODO change prefix and parse message and do sanity checks on it !! .contains is used
        }

    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }
//TODO
    public static String getCommandDescription() {
        return "null";
    }

    public static String getPrefix() {
        return prefix;
    }
}
