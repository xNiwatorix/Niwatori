package bp.niwatori.niwaboss;

import bp.niwatori.niwaboss.bosses.Boss;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class NiwaBoss extends JavaPlugin{
    private static NiwaBoss instance;
    public static NiwaBoss getInstance(){
        return instance;
    }
    @Override
    public void onEnable(){
        instance = this;
    }
    /**
     * /spawnBoss [name] [world] [x] [y] [z]
     * @return
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(command.getName().equalsIgnoreCase("spawnBoss")){
            if(sender instanceof Player){
                sender.sendMessage("プレイヤーからは実行できません。");
                return true;
            }
            if(args.length != 5){
                sender.sendMessage("引数は5つです。");
                return true;
            }
            try {
                World world = Bukkit.getWorld(args[1]);
                Location location = new Location(world, Integer.parseInt(args[2]), Integer.parseInt(args[3]), Integer.parseInt(args[4]));
                Class<? extends Boss> clazz = Class.forName("bp.niwatori.niwaboss.bosses."+args[0]).asSubclass(Boss.class);
                Boss boss = clazz.getConstructor(Location.class).newInstance(location);
                boss.runTaskTimer(this,0,2);
            }catch(ClassNotFoundException exception){
                exception.printStackTrace();
            }catch(InstantiationException exception){
                exception.printStackTrace();
            }catch (IllegalAccessException exception){
                exception.printStackTrace();
            }catch (NoSuchMethodException exception){
                exception.printStackTrace();
            }catch (InvocationTargetException exception){
                exception.printStackTrace();
            }
        }

        return false;
    }
}
