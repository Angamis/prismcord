package prism.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.server.Server;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;
import prism.functions.TimedBotName;

import java.util.Optional;
import java.util.TimeZone;

public class ChangeTimeZoneCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "timeZone";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ownCommand = App.getPrefix() + COMMAND_NAME;

        if (event.getMessageContent().toLowerCase().contains(ownCommand.toLowerCase())
                && event.getMessageContent().substring(0, ownCommand.length()).equalsIgnoreCase(ownCommand)) {

            String timeZoneName = event.getMessageContent().substring(ownCommand.length() + 1);
            TimeZone timeZone = TimeZone.getTimeZone(timeZoneName);

            DiscordApi api = event.getApi();
            Optional<Server> serverOptional = event.getServer();

            serverOptional.ifPresent(server -> TimedBotName.setUpTimer(api.getYourself(), server, timeZone));
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return "a command that changes the time zone of the bot, use Java time zone IDs";
    }
}
