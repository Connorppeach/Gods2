package blue.sector.gods.commands;

import blue.sector.gods.Plugin;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import blue.sector.gods.Plugin;

public class TestCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        String cmdName = cmd.getName().toLowerCase();

        if (!cmdName.equals("test")) {
            return false;
        }

        sender.sendMessage("yay!");

        return true;
    }
}
