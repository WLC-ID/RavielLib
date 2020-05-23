package me.raviel.lib.locale;

import me.raviel.lib.compatibility.ServerVersion;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * The Message object. This holds the message to be sent
 * as well as the plugins prefix so that they can both be
 * easily manipulated then deployed
 */
public class Message {

    private static boolean canActionBar = false;
    static {
        try {
			Class.forName("net.md_5.bungee.api.ChatMessageType");
            Class.forName("net.md_5.bungee.api.chat.TextComponent");
            Player.Spigot.class.getDeclaredMethod("sendMessage", net.md_5.bungee.api.ChatMessageType.class, net.md_5.bungee.api.chat.TextComponent.class);
			canActionBar = true;
		} catch (Exception ex) {
        }
    }
    private String prefix = null;
    private String message;

    /**
     * create a new message
     *
     * @param message the message text
     */
    public Message(String message) {
        this.message = message;
    }

    /**
     * Format and send the held message to a player
     *
     * @param player player to send the message to
     */
    public void sendMessage(Player player) {
        player.sendMessage(this.getMessage());
    }

    /**
     * Format and send the held message with the
     * appended plugin prefix to a player
     *
     * @param player player to send the message to
     */
    public void sendPrefixedMessage(Player player) {
        player.sendMessage(this.getPrefixedMessage());
    }

    /**
     * Format and send the held message to a player
     *
     * @param sender command sender to send the message to
     */
    public void sendMessage(CommandSender sender) {
        sender.sendMessage(this.getMessage());
    }

    /**
     * Format and send the held message to a player as a title message
     *
     * @param sender command sender to send the message to
     */
    public void sendTitle(CommandSender sender) {
        if (sender instanceof Player) {
            if (ServerVersion.isServerVersionAtLeast(ServerVersion.V1_11)) {
                ((Player) sender).sendTitle("", getMessage(), 10, 30, 10);
            } else {
                ((Player) sender).sendTitle("", getMessage());
            }
        } else {
            sender.sendMessage(this.getMessage());
        }
    }

    /**
     * Format and send the held message to a player as an actionbar message
     *
     * @param sender command sender to send the message to
     */
    public void sendActionBar(CommandSender sender) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.getMessage());
        } else if (!canActionBar) {
            sendTitle(sender);
        } else {
            ((Player) sender).spigot().sendMessage(net.md_5.bungee.api.ChatMessageType.ACTION_BAR,
                    new net.md_5.bungee.api.chat.TextComponent(getMessage()));
        }
    }

    /**
     * Format and send the held message with the
     * appended plugin prefix to a command sender
     *
     * @param sender command sender to send the message to
     */
    public void sendPrefixedMessage(CommandSender sender) {
        sender.sendMessage(this.getPrefixedMessage());
    }

    /**
     * Format the held message and append the plugins
     * prefix
     *
     * @return the prefixed message
     */
    public String getPrefixedMessage() {
        return ChatColor.translateAlternateColorCodes('&',(prefix == null ? "" : this.prefix)
                + " " +  this.message);
    }

    /**
     * Get and format the held message
     *
     * @return the message
     */
    public String getMessage() {
        return ChatColor.translateAlternateColorCodes('&', this.message);
    }

    /**
     * Get and format the held message
     *
     * @return the message
     */
    public List<String> getMessageLines() {
        return Arrays.asList(ChatColor.translateAlternateColorCodes('&', this.message).split("\n|\\|"));
    }

    /**
     * Get the held message
     *
     * @return the message
     */
    public String getUnformattedMessage() {
        return this.message;
    }

    /**
     * Replace the provided placeholder with the provided object. <br />
     * Interchangeably Supports {@code %value%} and {@code {value}}
     *
     * @param placeholder the placeholder to replace
     * @param replacement the replacement object
     * @return the modified Message
     */
    public Message processPlaceholder(String placeholder, Object replacement) {
        final String place = Matcher.quoteReplacement(placeholder);
        this.message = message.replaceAll("%" + place + "%|\\{" + place +"\\}", replacement == null ? "" : Matcher.quoteReplacement(replacement.toString()));
        return this;
    }

    Message setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    @Override
    public String toString() {
        return this.message;
    }
}