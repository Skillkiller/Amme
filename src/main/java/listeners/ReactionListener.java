package listeners;

import commands.chat.Vote;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


import java.io.IOException;

public class ReactionListener extends ListenerAdapter{

    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {

        try {
            Vote.reactVote(event.getReaction().getEmote().getName().replace(":", ""),event);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
