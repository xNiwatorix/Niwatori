package bp.niwatori.niwatorienchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xNiwatorix on 2016/12/17.
 */
public class Config {
    private String warnMsg;
    private List<Mod> mods;
    private int probSum;

    public List<Mod> getMods() {
        return mods;
    }
    public String getWarnMsg() {
        return warnMsg;
    }

    /**
     * コンフィグファイルから、Modの情報や警告文の情報を取得
     * Modの確率の処理に関しては、EnchantTableListener#createResult(InventoryView inventoryView)を参照のこと
     * @param plugin
     */
    protected Config(JavaPlugin plugin){
        FileConfiguration configFile = plugin.getConfig();
        warnMsg = ChatColor.translateAlternateColorCodes('&', configFile.getString("warnMessage", "&4!!!! WARNING !!!!! 33% CHANCE TO DESTROY THIS ITEM"));
        mods = new ArrayList<>();
        probSum = 0;

        for(String s:configFile.getStringList("mods")){
            String[] split = s.trim().split(",");
            if (split.length < 5) {
                Bukkit.getLogger().info("Error occured!");
                return;
            }
            Bukkit.getLogger().info("test");
            Mod mod = new Mod(split[0], split[1], probSum+Integer.parseInt(split[2]), split[3], ChatColor.getByChar(split[4]));
            Bukkit.getLogger().info(mod.getLabel()+mod.getName()+mod.getCommand()+mod.getColor().toString());
            mods.add(mod);
            probSum += Integer.parseInt(split[2]);
        }
    }
}
