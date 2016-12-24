package bp.niwatori.niwaboss.bosses;

import bp.niwatori.niwaboss.skills.LaunchKnockBackBall;
import bp.niwatori.niwaboss.skills.NothingSkill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class VeryKurusimimas extends Boss{
    public VeryKurusimimas(Location spawnLocation){
        super(EntityType.ZOMBIE,spawnLocation);
        entity.getEquipment().setHelmet(new ItemStack(Material.APPLE));
        entity.setMaxHealth(2048);
        entity.setHealth(2048);
        entity.addPotionEffect(new PotionEffect(PotionEffectType.SPEED,3600*20,5,true));
        skills.add(new LaunchKnockBackBall());
        skills.add(new NothingSkill(6));
    }
    @Override
    public void run(){
        if(entity.isDead()){
            this.cancel();
            return;
        }
        refreshHP("クリスマスが今年もやってくる");
        launchSkill();
    }
}
