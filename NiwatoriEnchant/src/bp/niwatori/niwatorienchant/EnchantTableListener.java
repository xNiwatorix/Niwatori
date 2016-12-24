package bp.niwatori.niwatorienchant;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ItemMergeEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by xNiwatorix on 2016/12/17.
 */
public class EnchantTableListener implements Listener{
    /**
     * エンチャントテーブルを開こうとしたとき、それをキャンセルして空のホッパーのGUIを見せる処理。
     * @param event インベントリを開いたときのイベントクラス
     */
    @EventHandler
    public void onTableOpen(InventoryOpenEvent event){
        if(!event.getView().getType().equals(InventoryType.ENCHANTING))return;
        event.setCancelled(true);
        Inventory customInventory = Bukkit.createInventory(null,InventoryType.HOPPER);
        event.getPlayer().openInventory(customInventory);
    }
    /**
     * ホッパーの中にアイテムが置かれた/取った時の処理。
     * しくみ
     * アイテムを持っていない状態でクリックしているとき、
     * 0.1をたたいた時は絶対サンプルが表示できない
     * 4をたたいた時、サンプルが表示されているときはModを付ける処理
     * その他は何もしない
     * アイテムを持った状態でクリックしているとき、
     * 0,1をたたいた時、条件が整っていればサンプル表示
     * 2,3,4にアイテムを置いちゃいけないからイベントをキャンセル
     * その他は何もしない
     * @param event インベントリのどこかをクリックしたときのイベントクラス
     */
    @EventHandler
    public void onGuiClick(InventoryClickEvent event){
        InventoryView inventoryView = event.getView();
        if(!inventoryView.getType().equals(InventoryType.HOPPER))return;
        if (event.getAction() == InventoryAction.MOVE_TO_OTHER_INVENTORY) {
            event.getWhoClicked().sendMessage(ChatColor.GRAY + "You cannot use Shift-Click while opening an anvil!");
            event.setCancelled(true);
            return;
        }
        ItemStack item0 = inventoryView.getItem(0);
        ItemStack item1 = inventoryView.getItem(1);
        ItemStack holditem = inventoryView.getCursor();
        boolean canPlaceSample = false;
        if(holditem.getType().equals(Material.AIR)) {
            switch (event.getRawSlot()){
                case 0:
                case 1:
                    break;
                case 4://ここにresult処理を！
                    if(inventoryView.getItem(4)==null || !(item0!=null && item0.getType().equals(Material.DIAMOND_AXE) && item1!=null && item1.getType().equals(Material.INK_SACK) && item1.getDurability() == (short) 4)){
                        event.setCancelled(true);
                        return;
                    }
                    event.getWhoClicked().setItemOnCursor(createResult(inventoryView));
                    item0.setAmount(item0.getAmount() - 1);
                    item1.setAmount(item1.getAmount() - 1);
                    event.getInventory().setItem(0, item0);
                    event.getInventory().setItem(1, item1);
                    event.setCancelled(true);
                    break;
                default:
                    return;
            }
        }else{
            switch (event.getRawSlot()) {
                case 0:
                    if (holditem.getType().equals(Material.DIAMOND_AXE) && item1!=null && item1.getType().equals(Material.INK_SACK) && item1.getDurability() == (short) 4) {
                        canPlaceSample = true;
                    }
                    break;
                case 1:
                    if (item0!=null && item0.getType().equals(Material.DIAMOND_AXE) && holditem.getType().equals(Material.INK_SACK) && holditem.getDurability() == (short) 4) {
                        canPlaceSample = true;
                    }
                    break;
                case 2:
                case 3:
                case 4:
                    event.setCancelled(true);
                    return;
                default:
                    return;
            }
        }
        new setSample(inventoryView,canPlaceSample).runTaskLater(NiwatoriEnchant.getInstance(),3);
    }
    /**
     * ホッパーのウインドウの仕様上、アイテムがホッパーのウインドウ上にあるとアイテムが吸われる。
     * そのため、ウインドウを閉じたときにホッパーのウインドウ上のアイテムを戻してあげる処理を書かなくてはならない。
     * @param event インベントリを閉じたときのイベントクラス
     */
    @EventHandler
    public void onGuiClose(InventoryCloseEvent event){
        InventoryView inventoryView = event.getView();
        Player player = (Player)event.getPlayer();
        if(inventoryView.getType().equals(InventoryType.HOPPER)){
            if(inventoryView.getItem(0)!=null) {
                player.getInventory().addItem(inventoryView.getItem(0));
            }
            if(inventoryView.getItem(1)!=null) {
                player.getInventory().addItem(inventoryView.getItem(1));
            }
        }
    }

    /**
     * アイテムにModを付ける処理
     * @param inventoryView ホッパーのInventoryView
     * @return Modがついた(or消えてなくなった)ItemStack
     */
    private ItemStack createResult(InventoryView inventoryView){
        List<Mod> mods = NiwatoriEnchant.getInstance().getNConfig().getMods();
        int probability = new Random().nextInt(mods.get(mods.size()-1).getProbability());
        Mod mod = null;
        for(int i = 0;i<mods.size();i++){
            if(mods.get(i).getProbability()>probability){
                mod = mods.get(i);
                break;
            }
        }
        ItemStack item = inventoryView.getItem(0).clone();
        ItemMeta itemMeta = item.getItemMeta();
        List<String> itemLore = itemMeta.getLore();
        String text = mod.getColor() + mod.getLabel() + ": " + mod.getName();
        if (itemLore == null) {
            itemLore = new ArrayList<>();
            itemLore.add("");
            itemLore.add(text);
        }
        else{
            if(itemLore.size()==0){
                itemLore.add("");
                itemLore.add(text);
            }else if(itemLore.size()==1){
                itemLore.add(text);
            }else if(itemLore.size()>=2){
                itemLore.set(1,text);
            }
        }
        itemMeta.setLore(itemLore);
        item.setItemMeta(itemMeta);
        if(mod.getName().equalsIgnoreCase("delete")){
            item = new ItemStack(Material.AIR);
        }
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), mod.getCommand());
        return item;
    }
    private class setSample extends BukkitRunnable{
        InventoryView inventoryView;
        boolean canPlaceSample;
        public setSample(InventoryView inventoryView,boolean canPlaceSample){
            this.inventoryView = inventoryView;
            this.canPlaceSample =canPlaceSample;
        }
        public void run(){
            if(canPlaceSample){
                inventoryView.setItem(4,rename(inventoryView.getItem(0), NiwatoriEnchant.getInstance().getNConfig().getWarnMsg()));
            }else{
                inventoryView.setItem(4,new ItemStack(Material.AIR));
            }
        }

        /**
         * 4スロット目にサンプルを表示する際のLoreを編集する
         * @param item 編集するアイテム
         * @param name Loreに表示するテキスト
         * @return サンプル用の表示になったアイテム
         */
        private ItemStack rename(ItemStack item,String name){
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(name);
            meta.setLore(lore);
            ItemStack result = item.getData().toItemStack(1);
            result.setItemMeta(meta);
            return result;
        }
    }
}
