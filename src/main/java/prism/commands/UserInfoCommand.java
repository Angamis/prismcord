package prism.commands;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

public class UserInfoCommand implements MessageCreateListener, CommandStructure {

    private static final String COMMAND_NAME = "userInfo";

    @Override
    public void onMessageCreate(MessageCreateEvent event) {

        String completeCommand = ChangePrefixCommand.getPrefix() + COMMAND_NAME;

        if (event.getMessageContent().equalsIgnoreCase(completeCommand)) {
            MessageAuthor author = event.getMessage().getAuthor();
            EmbedBuilder embed = new EmbedBuilder()
                    .setTitle("User Info")
                    .addField("Display name", author.getDisplayName(), true)
                    .addField("Name & Discriminator", author.getDiscriminatedName(), true)
                    .addField("User Id", author.getIdAsString(), true)
                    .setAuthor(author);

            author.asUser().ifPresent(user -> {
                embed.addField("Online Status", user.getStatus().getStatusString(), true);
            });

            event.getMessage().getServer()
                    .ifPresent(server -> embed.addField("Server Admin", author.isServerAdmin() ? "yes" : "no", true));

            event.getChannel().sendMessage(embed)
                    .exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
        }
    }

    public static String getCommandName() {
        return COMMAND_NAME;
    }

    public static String getCommandDescription() {
        return "a command that shows some user information of the invoking user";
    }
}
