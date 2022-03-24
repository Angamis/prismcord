package prism.commands;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;

import java.util.Map;

public class CommandsInfoCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "commands";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ownCommand = App.getPrefix() + COMMAND_NAME;

        if (event.getMessageContent().equalsIgnoreCase(ownCommand)) {
            event.getChannel().sendMessage(getDescriptionOfAllCommandsFormatted());
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

    private String getDescriptionOfAllCommandsFormatted () {

        StringBuilder stringBuilder = new StringBuilder();
        Map<String, String> commandsMap = App.getCommandsMap();

        for (Map.Entry<String, String> entry : commandsMap.entrySet()) {
            stringBuilder.append("> ");
            stringBuilder.append(App.getPrefix());
            stringBuilder.append(entry.getKey());
            stringBuilder.append("\n");
            stringBuilder.append("```");
            stringBuilder.append(entry.getValue());
            stringBuilder.append("``` \n");
        }

        return stringBuilder.toString();
    }
}
