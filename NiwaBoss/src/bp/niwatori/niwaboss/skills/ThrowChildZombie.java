package bp.niwatori.niwaboss.skills;

import bp.niwatori.niwaboss.bosses.Boss;
import org.bukkit.World;
import org.bukkit.entity.*;

/**
 * Created by xNiwatorix on 2016/12/26.
 */
public class ThrowChildZombie implements  Skill {
    @Override
    public void launch(Boss boss) {
        LivingEntity entity = boss.getEntity();
        World world = entity.getWorld();
        Zombie zombie = (Zombie) world.spawnEntity(entity.getEyeLocation(), EntityType.ZOMBIE);
        zombie.setBaby(true);
        zombie.setVelocity(entity.getEyeLocation().getDirection().multiply(3));
        boss.setSkillReady(true);
    }
}
