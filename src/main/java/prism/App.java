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

        if (args.length < 1) {
            System.out.println("BRRRRRRR: no Token specified");
        }

        FallbackLoggerConfiguration.setDebug(true);

        String token = args[0];

        DiscordApi api = new DiscordApiBuilder()
                .setToken(token)
                .login()
                .join();

        Server server = api.getServerById(821017221170069524L).get();

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

    private static long millisToNextMinute() {
        LocalDateTime nextMinute = LocalDateTime.now().plusMinutes(1).truncatedTo(ChronoUnit.MINUTES);
        return LocalDateTime.now().until(nextMinute, ChronoUnit.MILLIS);
    }
}
