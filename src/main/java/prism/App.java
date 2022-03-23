package prism;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import prism.commands.ChangeTimeZoneCommand;
import prism.commands.UserInfoCommand;

import java.util.TimeZone;

public class App {

    private static String prefix = "*";

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static String getPrefix() {
        return prefix;
    }

    @SuppressWarnings({"java:S3655", "java:S106"})
    public static void main(String[] args) {

        FallbackLoggerConfiguration.setDebug(true);

        if (args.length < 2) {
            System.out.println("NOT ENOUGH ARGUMENTS");
        }

        String token = args[0];
        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login()
                .join();

        Server server = api.getServerById(Long.parseLong(args[1])).get();
        User selfUser = api.getYourself();

        TimedBotName.setUpTimer(selfUser, server, TimeZone.getTimeZone("GMT"));
        api.updateActivity(ActivityType.WATCHING, "FFXIV Server Time");

        //ADD LISTENERS
        api.addReconnectListener(new UpdatePresenceReconnect());

        UserInfoCommand userInfoCommand = new UserInfoCommand();
        api.addMessageCreateListener(userInfoCommand);

        ChangeTimeZoneCommand changeTimeZoneCommand = new ChangeTimeZoneCommand();
        api.addMessageCreateListener(changeTimeZoneCommand);
    }
}
