package bp.niwatori.niwaboss.bosses;

import bp.niwatori.niwaboss.skills.HighJump;
import bp.niwatori.niwaboss.skills.NothingSkill;
import bp.niwatori.niwaboss.skills.ThrowChildZombie;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by xNiwatorix on 2016/12/26.
 */
public class SuperZombie extends Boss{
    public SuperZombie(Location location){
        super(EntityType.ZOMBIE,location,500,"SuperZombie");
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,3600*20,2));
        ((Zombie)entity).setBaby(false);
        entity.getEquipment().setHelmet(new ItemStack(Material.YELLOW_FLOWER));
        skills.add(new NothingSkill(30));
        skills.add(new HighJump());
        skills.add(new ThrowChildZombie());
    }
    @Override
    public void run() {
        super.run();
        World world = entity.getWorld();
        world.playEffect(entity.getEyeLocation(), Effect.MOBSPAWNER_FLAMES,0);
    }
}
