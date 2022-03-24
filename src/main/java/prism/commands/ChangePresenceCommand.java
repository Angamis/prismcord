package prism.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;

public class ChangePresenceCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "presence";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String ownCommand = App.getPrefix() + COMMAND_NAME;
        String messageContent = event.getMessageContent();
        boolean validCommand = messageContent.toLowerCase().contains(ownCommand.toLowerCase())
                && messageContent.substring(0, ownCommand.length()).equalsIgnoreCase(ownCommand);

        if (validCommand && messageContent.length() > (ownCommand.length() + 1)) {

            ActivityType activityType = getActivityType(messageContent);
            DiscordApi api = event.getApi();

            if (activityType ==  null) {
                event.getChannel().sendMessage("No valid activity type was given, use a number from 1 to 4.");
            } else if (messageContent.length() > (ownCommand.length() + 2)) {
                api.updateActivity(activityType, messageContent.substring(ownCommand.length() + 2));
            } else {
                api.unsetActivity();
            }
        } else if (validCommand) {
            event.getChannel().sendMessage(getExplanatoryMessage());
        }
    }

    @Override
    public String getCommandName() {
        return COMMAND_NAME;
    }

    @Override
    public String getCommandDescription() {
        return "updates the presence for the bot";
    }

    private ActivityType getActivityType(String userInput) {

        String givenActivity = userInput.substring((App.getPrefix() + COMMAND_NAME).length() + 1,
                (App.getPrefix() + COMMAND_NAME).length() + 2);

        return switch (givenActivity) {
            case "1" -> ActivityType.WATCHING;
            case "2" -> ActivityType.COMPETING;
            case "3" -> ActivityType.LISTENING;
            case "4" -> ActivityType.PLAYING;
            default -> null;
        };
    }

    private String getExplanatoryMessage() {

        return "Update my presence as follows: \n" +
                App.getPrefix() + COMMAND_NAME + " NUMBER TEXT\n\n" +

                "> 1 = Watching\n" +
                "> 2 = Competing in\n" +
                "> 3 = Listening to\n" +
                "> 4 = playing\n\n" +

                "if no TEXT is given, the presence will be removed";
    }
}
