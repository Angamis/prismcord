package prism.functions;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.event.connection.ReconnectEvent;
import org.javacord.api.listener.connection.ReconnectListener;

public class UpdatePresenceReconnect implements ReconnectListener {

    @Override
    public void onReconnect(ReconnectEvent event) {
        DiscordApi api = event.getApi();
        api.updateActivity(ActivityType.WATCHING, "FFXIV Server Time");
    }
}
