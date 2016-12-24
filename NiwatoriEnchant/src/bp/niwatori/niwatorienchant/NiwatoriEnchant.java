package bp.niwatori.niwatorienchant;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

/**
 * Created by xNiwatorix on 2016/12/17.
 * にわとりエンチャントの司令塔クラス(リスナ登録とコンフィグ読み込みするだけ)
 */
public class NiwatoriEnchant extends JavaPlugin{
    private static NiwatoriEnchant instance;
    public static NiwatoriEnchant getInstance(){
        return  instance;
    }
    private Config nconfig;
    public Config getNConfig() {
        return nconfig;
    }
    public void onEnable(){
        instance = this;
        saveDefaultConfig();
        nconfig = new Config(this);
        getServer().getPluginManager().registerEvents(new EnchantTableListener(),this);
        getLogger().info("NiwatoriEnchant Enabled!");
    }
    public void onDisable(){

    }
}
