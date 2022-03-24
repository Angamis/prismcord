package prism;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;
import prism.commands.ChangePrefixCommand;
import prism.commands.ChangeTimeZoneCommand;
import prism.commands.CommandsInfoCommand;
import prism.commands.UserInfoCommand;
import prism.functions.TimedBotName;
import prism.functions.UpdatePresenceReconnect;

import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class App {

    private static String prefix = "*";
    private static final Map<String, String> commandsMap = new HashMap<>();
    private static String presenceString = "FFXIV Server Time";

    public static void setPrefix(String newPrefix) {
        prefix = newPrefix;
    }

    public static String getPrefix() {
        return prefix;
    }

    public static Map<String, String> getCommandsMap() {
        return commandsMap;
    }

    public static void setPresenceString(String string) {
        presenceString = string;
    }

    public static String getPresenceString() {
        return presenceString;
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

        //INITIALIZE BOT WITH STATUS AND NAME CHANGE
        TimedBotName.setUpTimer(selfUser, server, TimeZone.getTimeZone("GMT"));
        api.updateActivity(ActivityType.WATCHING, "FFXIV Server Time");

        //ADD LISTENERS
        api.addReconnectListener(new UpdatePresenceReconnect());

        ChangePrefixCommand changePrefixCommand = new ChangePrefixCommand();
        api.addMessageCreateListener(changePrefixCommand);

        ChangeTimeZoneCommand changeTimeZoneCommand = new ChangeTimeZoneCommand();
        api.addMessageCreateListener(changeTimeZoneCommand);

        CommandsInfoCommand commandsInfoCommand = new CommandsInfoCommand();
        api.addMessageCreateListener(commandsInfoCommand);

        UserInfoCommand userInfoCommand = new UserInfoCommand();
        api.addMessageCreateListener(userInfoCommand);

        //REGISTER COMMANDS FOR USER QUERY
        commandsMap.put(changePrefixCommand.getCommandName(), changePrefixCommand.getCommandDescription());
        commandsMap.put(changeTimeZoneCommand.getCommandName(), changeTimeZoneCommand.getCommandDescription());
        commandsMap.put(commandsInfoCommand.getCommandName(), commandsInfoCommand.getCommandDescription());
        commandsMap.put(userInfoCommand.getCommandName(), userInfoCommand.getCommandDescription());
    }
}
