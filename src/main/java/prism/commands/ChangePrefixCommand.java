package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;

public class ChangePrefixCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "prefix";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ownCommand = App.getPrefix() + COMMAND_NAME;
        String messageContent = event.getMessageContent();

        if(messageContent.toLowerCase().contains(ownCommand.toLowerCase())
                && messageContent.substring(0, ownCommand.length()).equalsIgnoreCase(ownCommand)) {

            String newPossiblePrefix;

            if (messageContent.length() == ownCommand.length() + 2) {
                newPossiblePrefix = messageContent.substring(ownCommand.length() + 1);

                if(newPossiblePrefix.matches("[`~!@#$%^&*_|+\\-=?;:'\",.<>\\\\]")) {
                    App.setPrefix(newPossiblePrefix);
                    event.getChannel().sendMessage("New Prefix: " + newPossiblePrefix);
                } else {
                    event.getChannel().sendMessage("'" + newPossiblePrefix + "' is no valid Prefix!");
                }
            } else {
                String errorMessage = "The new command must be one character only and must be given in the format: \n" + ownCommand + " new_prefix";
                event.getChannel().sendMessage(errorMessage);
            }
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return "a command that changes the prefix of the bot";
    }
}
