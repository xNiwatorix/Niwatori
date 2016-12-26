package bp.niwatori.niwaboss.skills;

import bp.niwatori.niwaboss.bosses.Boss;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

/**
 * Created by xNiwatorix on 2016/12/26.
 */
public class HighJump implements Skill{
    @Override
    public void launch(Boss boss) {
        LivingEntity entity = boss.getEntity();
        if(entity.isOnGround()){
            entity.setVelocity(entity.getVelocity().add(new Vector(0,2,0)));
        }
        boss.setSkillReady(true);
    }
}
