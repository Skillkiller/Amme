package audioCore;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import net.dv8tion.jda.core.entities.Member;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 24.09.2017 13:08
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class AudioInfo {

    private final AudioTrack TRACK;
    private final Member AUTHOR;

    public AudioInfo(AudioTrack track, Member author){
        this.TRACK = track;
        this.AUTHOR = author;
    }

    public AudioTrack getTrack() {
        return TRACK;
    }

    public Member getAuthor() {
        return AUTHOR;
    }
}
