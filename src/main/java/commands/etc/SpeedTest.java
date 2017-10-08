package commands.etc;

import commands.Command;
import core.CoreCommands;
import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import util.Logger;
import util.STATICS;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 28.09.2017 17:12
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public class SpeedTest implements Command{
    @Override
    public boolean called(String[] args, MessageReceivedEvent event) {
        return false;
    }

    @Override
    public void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException {
        SpeedTestSocket Dspeed = new SpeedTestSocket();
        SpeedTestSocket Uspeed = new SpeedTestSocket();
        StringBuilder sb = new StringBuilder();
        event.getMessage().delete().queue();
        Message msg = event.getTextChannel().sendMessage(new EmbedBuilder().setDescription("**Speed test starting...**\n\nTesting downstream...").build()).complete();

        Dspeed.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport report) {
                sb.append("Downstream:  " + Math.round(report.getTransferRateBit().floatValue() / 1024 / 1024) + " MBit/s\n");
                msg.editMessage(new EmbedBuilder().setDescription("**Speed test starting...**\n\nTesting upstream...").build()).queue();
                Uspeed.startUpload("http://2.testdebit.info/", 1000000);
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
            }

            @Override
            public void onError(SpeedTestError speedTestError, String s) {
                System.out.println(speedTestError);
            }

        });

        Uspeed.addSpeedTestListener(new ISpeedTestListener() {
            @Override
            public void onCompletion(SpeedTestReport report) {
                sb.append("Upstream:    " + Math.round(report.getTransferRateBit().floatValue() / 1024 / 1024) + " MBit/s");
                msg.editMessage(new EmbedBuilder().setColor(new Color(72, 244, 66)).setDescription("**Test finished.**\n\n```" + sb.toString() + "```").build()).queue();
            }

            @Override
            public void onProgress(float v, SpeedTestReport speedTestReport) {

            }

            @Override
            public void onError(SpeedTestError speedTestError, String s) {
                System.out.println(speedTestError);
            }

        });

        Dspeed.startDownload("http://2.testdebit.info/10M.iso");

    }

    @Override
    public void executed(boolean success, MessageReceivedEvent event) {
        Logger.logCommand("speedtest", event);
        System.out.println(CoreCommands.getCurrentSystemTime() + " [Info] [Commands]: Command '" + event.getMessage().getContent() + "' was executed by '" + event.getAuthor().getName() + "' (" + event.getGuild().getName() + ") in (" + event.getTextChannel().getId() + ") ");
    }

    @Override
    public String help() {
        return "USAGE: -speedtest";
    }

    @Override
    public String description() {
        return "Do a Speedtest of the Bots connection.";
    }

    @Override
    public String commandType() {
        return STATICS.CMDTYPE.etc;
    }

    @Override
    public int permission() {
        return 0;
    }
}
