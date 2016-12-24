package bp.niwatori.niwaboss;

import bp.niwatori.niwaboss.skills.Skill;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by xNiwatorix on 2016/12/23.
 * サブクラスのコンストラクタでしなきゃいけないこと
 * ・entityの初期化
 * ・skillsの初期化,Skillの追加
 * ・entityのCustomNameVisible = trueに
 */
public abstract class Boss extends BukkitRunnable{
    protected  LivingEntity entity;
    protected ArrayList<Skill> skills = new ArrayList<>();
    protected  boolean isSkillReady =true;

    public void setSkillReady(boolean skillReady) {
        isSkillReady = skillReady;
    }

    public LivingEntity getEntity() {

        return entity;
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
}
