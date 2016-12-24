package bp.niwatori.niwatorienchant;

import org.bukkit.ChatColor;

/**
 * Created by xNiwatorix on 2016/12/17.
 */
public class Mod {
    private String label;
    private String name;
    private  int probability;
    private  String command;
    private ChatColor color;

    public ChatColor getColor() { return color; }

    public String getLabel() {
        return label;
    }

    public String getName() {
        return name;
    }

    public int getProbability() {
        return probability;
    }

    public String getCommand() {
        return command;
    }



    protected Mod(String label, String name, int probability, String command, ChatColor color){

        this.label = label;
        this.name = name;
        this.probability = probability;
        this.command = command;
        this.color = color;
    }
}
