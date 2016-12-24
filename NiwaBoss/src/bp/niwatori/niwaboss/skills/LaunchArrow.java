package bp.niwatori.niwaboss.skills;

import bp.niwatori.niwaboss.bosses.Boss;
import org.bukkit.entity.Arrow;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class LaunchArrow implements Skill{
    @Override
    public void launch(Boss boss) {
        boss.getEntity().launchProjectile(Arrow.class);
        boss.setSkillReady(true);
    }
}
