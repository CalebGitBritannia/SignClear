package signclearer.signclearer;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command implements CommandExecutor {

    private String prefix = ("§7[§eSign Clearer§7]§r: ");
    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if (!(sender instanceof Player)) return true;

        String comparison = null;
        if (args.length >= 2) {
            comparison = args[1];
            for (int i = 2; i<args.length; i++) {
                comparison += " " + args[i];
            }
        }
        int size;
        try {
            size = Integer.parseInt(args[0]);
        } catch (Exception e) {
            sender.sendMessage(prefix + "Please Enter A Valid Number For The Range " + args[0] + " is not recognised " +
                    "as a valid " +
                    "number");
            return true;
        }
        
        int total = 0;

        Player player = (Player) sender;
        for (int i = size; i * 4 >=0 ; i--) {
            for (int b = size; b * 4 >=0; b--) {
                for (int y = size; y * 4 >=0; y--) {
                    Block block = player.getLocation().getWorld().getBlockAt(player.getLocation().add(i - (size / 2), y - (size / 2), b - (size / 2)));
                    if (!(block.getState() instanceof Sign)) continue;
                    Sign sign = (Sign) block.getState();
                    if(comparison == null) {
                        block.setType(Material.AIR);
                        total +=1;
                        continue;
                    }
                    for (String curLine : sign.getLines()) {
                        if (curLine.replace("§", "&").equals(comparison)){
                            block.setType(Material.AIR);
                            total +=1;
                        }
                    }
                }
            }
        }

        player.sendMessage(prefix + "Success, in a radius of " + size  + " a total of " + total + " signs were removed");

        return true;
    }
}
