package bp.niwatori.niwaboss.skills;

import bp.niwatori.niwaboss.Boss;
import bp.niwatori.niwaboss.NiwaBoss;
import org.bukkit.scheduler.BukkitRunnable;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class NothingSkill implements Skill {
    private int pause;
    public NothingSkill(int pause){
        this.pause = pause;
    }
    @Override
    public void launch(Boss boss) {
        new BukkitRunnable(){
            @Override
            public void run() {
                boss.setSkillReady(true);
            }
        }.runTaskLater(NiwaBoss.getInstance(),pause);
    }
}
