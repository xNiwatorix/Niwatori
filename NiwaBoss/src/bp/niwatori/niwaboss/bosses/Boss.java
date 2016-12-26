package bp.niwatori.niwaboss.bosses;

import bp.niwatori.niwaboss.skills.Skill;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by xNiwatorix on 2016/12/23.
 * サブクラスのコンストラクタの引数はLocationの一つのみにしなくてはならない.
 * サブクラスでは、Skillの追加の記述だけしとけばとりあえず動きます.
 * 他のことしたい場合はSuperZombieクラス辺りが参考になります.
 */
public abstract class Boss extends BukkitRunnable{
    protected  LivingEntity entity;
    protected ArrayList<Skill> skills = new ArrayList<>();
    protected  boolean isSkillReady =true;
    protected String name = new String();

    public void setSkillReady(boolean skillReady) {
        isSkillReady = skillReady;
    }

    public LivingEntity getEntity() {
        return entity;
    }

    public Boss(EntityType type,Location location,int hp,String name){
        entity = (LivingEntity) location.getWorld().spawnEntity(location,type);
        entity.setCustomNameVisible(true);
        entity.setMaxHealth(hp);
        entity.setHealth(hp);
        this.name = name;
    }
    protected void refreshHP(String bossName){
        String name = bossName.concat("[");
        double percent = Math.ceil(entity.getHealth()/entity.getMaxHealth()*10);
        for(int i = 0; i<10;i++){
            if(i<percent){
                name = name.concat("0");
            }else{
                name = name.concat(".");
            }
        }
        name = name.concat("]");
        entity.setCustomName(name);
    }
    protected void launchSkill(){
        if(isSkillReady){
            int slot = new Random().nextInt(skills.size());
            isSkillReady = false;
            skills.get(slot).launch(this);
        }
    }
    @Override
    public void run() {
        if(entity.isDead()){
            this.cancel();
            return;
        }
        refreshHP(name);
        launchSkill();
    }
}
