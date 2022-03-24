package prism.commands;

import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import prism.App;
import prism.CommandStructure;

public class MentionCommand implements MessageCreateListener, CommandStructure {
    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        DiscordApi api = event.getApi();

        if (event.getMessageContent().contains(String.valueOf(api.getYourself().getId()))) {
            event.getChannel().sendMessage(getMentionMessage());
        }
    }

    @Override
    public String getCommandName() {
        return "Mention me";
    }

    @Override
    public String getCommandDescription() {
        return "I will say hi and inform you about my current prefix";
    }

    private String getMentionMessage () {

        return "Hi \uD83D\uDC4B \n\n" +

                "> My current prefix is: " + App.getPrefix() + "\n" +
                "```Try this: " + App.getPrefix() + "commands" + "```";
    }
}
