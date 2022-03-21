package prism;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.util.logging.FallbackLoggerConfiguration;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class App {

    public static void main(String[] args) {

        System.out.println();
        System.out.println("Arguments: BOT_TOKEN GUILD_ID");
        System.out.println();

        if (args.length < 2) {
            System.out.println("NOT ENOUGH ARGUMENTS");
        }

        FallbackLoggerConfiguration.setDebug(true);

        String token = args[0];

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login()
                .join();

        Server server = api.getServerById(Long.parseLong(args[1])).get();

        User selfUser = api.getYourself();

        TimeZone.setDefault(TimeZone.getTimeZone("GMT"));

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                String time = new SimpleDateFormat("HH : mm").format(new Date());

                selfUser.updateNickname(server, time);
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, millisToNextMinute(),  60000);
    }

    //have 5 second as buffer
    private static long millisToNextMinute() {
        LocalDateTime nextMinute = LocalDateTime.now().plusMinutes(1).plusSeconds(5).truncatedTo(ChronoUnit.MINUTES);
        return LocalDateTime.now().until(nextMinute, ChronoUnit.MILLIS);
    }
}
