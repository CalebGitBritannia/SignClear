package signclearer.signclearer;

import org.bukkit.plugin.java.JavaPlugin;

public final class SignClearer extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("signclear").setExecutor(new Command());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
