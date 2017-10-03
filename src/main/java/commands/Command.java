package commands;

import net.dv8tion.jda.core.events.message.MessageReceivedEvent;

import java.io.IOException;
import java.text.ParseException;

/**
 * Butler´s JDA BOT
 * <p>
 * By LordLee at 16.09.2017 11:28
 * <p>
 * <p>
 * <p>
 * Contributors for this class:
 * - github.com/zekrotja
 * <p>
 * © DARK DEVS 2017
 */
public interface Command {

  boolean called(String[] args, MessageReceivedEvent event);
  void action(String[] args, MessageReceivedEvent event) throws ParseException, IOException;
  void executed(boolean success, MessageReceivedEvent event);
  String help();
  String description();
  String commandType();
  int permission();

}
