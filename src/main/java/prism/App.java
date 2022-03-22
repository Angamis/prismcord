package prism;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

public class App {

    @SuppressWarnings({"java:S3655", "java:S106"})
    public static void main(String[] args) {

        FallbackLoggerConfiguration.setDebug(true);

        System.out.println();
        System.out.println("Arguments: BOT_TOKEN GUILD_ID");
        System.out.println();

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

        TimedRename.setUpTimer(selfUser, server);
    }
}
