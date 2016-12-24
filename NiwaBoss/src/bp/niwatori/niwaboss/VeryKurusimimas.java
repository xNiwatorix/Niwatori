package bp.niwatori.niwaboss;

import bp.niwatori.niwaboss.skills.LaunchArrow;
import bp.niwatori.niwaboss.skills.LaunchKnockBackBall;
import bp.niwatori.niwaboss.skills.NothingSkill;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class VeryKurusimimas extends Boss{
    public VeryKurusimimas(Location spawnLocation){
        entity = (LivingEntity) spawnLocation.getWorld().spawnEntity(spawnLocation, EntityType.ZOMBIE);
        entity.setCustomNameVisible(true);
        entity.getEquipment().setHelmet(new ItemStack(Material.ROTTEN_FLESH));
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
