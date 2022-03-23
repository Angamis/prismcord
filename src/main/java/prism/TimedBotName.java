package prism;

import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class TimedBotName {

    private static final Timer timer = new Timer();

    private TimedBotName() {

    }

    /**
     * Sets up a task to update the bots name every minute to the current time in GMT
     *
     * @param selfUser the BOT User
     * @param server the server the BOT should change its name on
     */
    public static void setUpTimer(User selfUser, Server server, TimeZone timeZone) {

        TimeZone.setDefault(timeZone);
        updateNameToTimeNow(selfUser, server);

        timer.scheduleAtFixedRate(runTimerTask(selfUser, server), millisToNextMinute(),  60000);
    }

    private static TimerTask runTimerTask(User selfUser , Server server) {

        return new TimerTask() {
            @Override
            public void run() {
                updateNameToTimeNow(selfUser, server);
            }
        };
    }

    private static void updateNameToTimeNow(User selfUser , Server server) {

        String time = new SimpleDateFormat("EEE hh:mm a", Locale.ENGLISH).format(new Date());

        selfUser.updateNickname(server, time);
    }

    /**
     *
     * @return milliseconds + 1000 to the next full minute
     */
    private static long millisToNextMinute() {
        LocalDateTime nextMinute = LocalDateTime.now().plusMinutes(1).truncatedTo(ChronoUnit.MINUTES).plusSeconds(1);
        return LocalDateTime.now().until(nextMinute, ChronoUnit.MILLIS);
    }
}
