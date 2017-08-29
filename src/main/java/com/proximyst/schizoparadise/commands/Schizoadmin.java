package com.proximyst.schizoparadise.commands;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.annotation.*;
import co.aikar.commands.contexts.OnlinePlayer;
import com.proximyst.schizoparadise.Paradise;
import com.proximyst.schizoparadise.Utilities;
import com.proximyst.schizoparadise.data.SchizophrenicPlayer;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.stream.Stream;

import static com.proximyst.schizoparadise.Utilities.colour;

@CommandAlias("schizo|schizoparadise|schizophrenia|schadmin")
@CommandPermission("schizoparadise.commands.admin")
public class Schizoadmin extends BaseCommand {
    private final String helpMessage;
    private final Paradise paradise;

    public Schizoadmin(final Paradise paradise) {
        super("schizoadmin");
        StringBuilder builder = new StringBuilder();
        // TODO: Custom help message.
        Stream.of(
                "&c----&e Schizophrenic Paradise&c ----",
                "&a/schizoadmin&e : The help message (this).",
                "&a/schizoadmin help&e : The help message (this).",
                "&a/schizoadmin trigger [Player]&e : Triggers a schizophrenic symptom for the player."
        ).map(Utilities::colour).forEach(it -> builder.append(it).append('\n').append(ChatColor.RESET));
        helpMessage = builder.toString().split("\n$", 2)[0]; // Drop the last \n.
        this.paradise = paradise;
    }

    @Default
    public void rootHelp(CommandSender sender) {
        sender.sendMessage(helpMessage);
    }

    @Subcommand("help")
    public void help(CommandSender sender) {
        sender.sendMessage(helpMessage);
    }

    @Subcommand("trigger")
    @CommandAlias("force")
    @Syntax("[Player]")
    public void trigger(CommandSender sender, @Optional OnlinePlayer player, @Default("-1") Integer chance, @Default("-1") Integer index) {
        if (!(sender instanceof Player) && player == null) {
            sender.sendMessage(colour("&cOnly players are allowed to trigger a symptom on themselves."));
            return;
        } else if (player == null) {
            player = new OnlinePlayer((Player) sender);
        }
        sender.sendMessage(colour("&aTrying to apply a symptom to &e", player.getPlayer().getName(), "&a."));
        SchizophrenicPlayer wrapped = SchizophrenicPlayer.getPlayer(player.getPlayer());
        if (chance == null || chance < 0)
            wrapped.trySymptom(paradise);
        else if (index == null || index < 0)
            wrapped.trySymptom(paradise, chance);
        else
            wrapped.trySymptom(paradise, chance, index);
    }
}
