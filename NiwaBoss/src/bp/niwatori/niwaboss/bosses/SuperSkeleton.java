package bp.niwatori.niwaboss.bosses;

import bp.niwatori.niwaboss.skills.LaunchArrow;
import bp.niwatori.niwaboss.skills.NothingSkill;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

/**
 * Created by xNiwatorix on 2016/12/23.
 */
public class SuperSkeleton extends Boss{
    public SuperSkeleton(Location spawnLocation){
        super(EntityType.SKELETON,spawnLocation);
        skills.add(new LaunchArrow());
        skills.add(new NothingSkill(5));
    }
    @Override
    public void run(){
        if(entity.isDead()){
            this.cancel();
            return;
        }
        refreshHP("SuperSkeleton");
        launchSkill();
    }
}
