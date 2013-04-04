package com.dmillerw.brainFuckBlocks.item;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.util.TextureCoordinates;

public class ItemCraftingComponent extends Item {

	public static String[] itemNames = new String[] {"Metal Spool", "Strip of Paper", "Circut Board", "Advanced Circut Board", "Data Tape"};
	private String[] subNames = new String[] {"metalSpool", "paperStrip", "circut", "advancedCircut", "dataStrip"};
	
	public ItemCraftingComponent(int id) {
		super(id);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	public int getIconFromDamage(int damage) {
        return new TextureCoordinates(damage, 1).getTextureIndex();
    }
	
	@Override
	public String getItemNameIS(ItemStack stack) {
		return super.getItemName() + "." + subNames[stack.getItemDamage()];
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i=0; i<subNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
    @Override
    public String getTextureFile() {
    	return ModInfo.ITEM_TEXTURE_LOCATION;
    }
	
}
