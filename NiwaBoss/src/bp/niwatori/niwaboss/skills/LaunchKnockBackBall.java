package bp.niwatori.niwaboss.skills;

import bp.niwatori.niwaboss.Boss;
import org.bukkit.entity.Snowball;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class LaunchKnockBackBall implements Skill{
    @Override
    public void launch(Boss boss) {
        Snowball ball = boss.getEntity().launchProjectile(Snowball.class);
        ball.setGravity(false);
        boss.setSkillReady(true);
    }
}
