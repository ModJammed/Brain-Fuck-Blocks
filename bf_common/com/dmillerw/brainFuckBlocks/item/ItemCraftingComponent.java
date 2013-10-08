package com.dmillerw.brainFuckBlocks.item;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;

public class ItemCraftingComponent extends Item {

	public static String[] itemNames = new String[] {"Metal Spool", "Strip of Paper", "Circut Board", "Advanced Circut Board", "Data Tape"};
	private String[] subNames = new String[] {"metalSpool", "paperStrip", "circut", "advancedCircut", "dataStrip"};
	
	private Icon[] textures;
	
	public ItemCraftingComponent(int id) {
		super(id);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setMaxDamage(0);
		setHasSubtypes(true);
	}
	
	public Icon getIconFromDamage(int damage) {
        return textures[damage];
    }
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + "." + subNames[stack.getItemDamage()];
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[subNames.length];
		
		for (int i=0; i<subNames.length; i++) {
			textures[i] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":craftingComponents/"+subNames[i]);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(int id, CreativeTabs tab, List list) {
		for (int i=0; i<subNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
}
