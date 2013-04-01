package com.dmillerw.brainFuckBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;

import com.dmillerw.brainFuckBlocks.block.BlockHandler;
import com.dmillerw.brainFuckBlocks.block.BlockIDs;
import com.dmillerw.brainFuckBlocks.core.CommonProxy;
import com.dmillerw.brainFuckBlocks.core.CreativeTabBrainFuck;
import com.dmillerw.brainFuckBlocks.helper.LogHelper;
import com.dmillerw.brainFuckBlocks.item.ItemHandler;
import com.dmillerw.brainFuckBlocks.item.ItemIDs;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.lib.UserPreferences;
import com.dmillerw.brainFuckBlocks.network.BFPacketHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION)
@NetworkMod(serverSideRequired=false, clientSideRequired=true, packetHandler=BFPacketHandler.class)
public class BrainFuckBlocks {
	@Instance(ModInfo.MOD_ID)
	public static BrainFuckBlocks instance;
	@SidedProxy(serverSide="com.dmillerw.brainFuckBlocks.CommonProxy", clientSide="com.dmillerw.brainFuckBlocks.client.ClientProxy")
	public static CommonProxy proxy;
	
	public static CreativeTabs creativeTabBF = new CreativeTabBrainFuck("brainFuck");
	
	public static int wireRenderID = 0;
	
	@PreInit
	public void preInit(FMLPreInitializationEvent e) {
		//Config stuffs
		Configuration config = new Configuration(e.getSuggestedConfigurationFile());
		try {
			config.load();
			
			BlockIDs.bfCodeID = config.getBlock("brainFuckCodeBlockID", BlockIDs.bfCodeDefaultID).getInt();
			BlockIDs.bfCPUID = config.getBlock("bfCPU", BlockIDs.bfCPUDefaultID).getInt();
			BlockIDs.bfWireID = config.getBlock("bfWire", BlockIDs.bfWireDefaultID).getInt();
			BlockIDs.bfPeripheralRedstoneID = config.getBlock("bfPeripheral", BlockIDs.bfPeriphperalRedstoneDefaultID).getInt();
			
			Property craftingEnable = config.get(Configuration.CATEGORY_GENERAL, "codeBlockCraftingEnabled", UserPreferences.codeBlockCraftingEnableDefault);
			craftingEnable.comment = "Should code blocks have crafting recipes? If enabled, the blocks will drop themselves when broken. If false, they'll drop nothing and a code block creation item will exist.";
			UserPreferences.codeBlockCraftingEnable = craftingEnable.getBoolean(UserPreferences.codeBlockCraftingEnableDefault);
			
			ItemIDs.bfWrenchID = config.getItem("bfWrench", ItemIDs.bfWrenchDefaultID).getInt();
			ItemIDs.bfCodeWriterID = config.getItem("bfCodeWriter", ItemIDs.bfCodeWriterDefaultID).getInt();
			ItemIDs.bfCraftingComponentID = config.getItem("bfCraftingComponent", ItemIDs.bfCraftingComponentDefaultID).getInt();
		} catch(Exception ex) {
			LogHelper.log("Failed to load config. Assuming defaults!");
			
			BlockIDs.bfCodeID = BlockIDs.bfCodeDefaultID;
			BlockIDs.bfCPUID = BlockIDs.bfCPUDefaultID;
			BlockIDs.bfWireID = BlockIDs.bfWireDefaultID;
			BlockIDs.bfPeripheralRedstoneID = BlockIDs.bfPeriphperalRedstoneDefaultID;
			
			UserPreferences.codeBlockCraftingEnable = UserPreferences.codeBlockCraftingEnableDefault;
			
			ItemIDs.bfWrenchID = ItemIDs.bfWrenchDefaultID;
			ItemIDs.bfCodeWriterID = ItemIDs.bfCodeWriterDefaultID;
			ItemIDs.bfCraftingComponentID = ItemIDs.bfCraftingComponentDefaultID;
		} finally {
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
	
	@Init
	public void init(FMLInitializationEvent e) {
		//Initializing logger
		LogHelper.init();
		
		//Adds proper localization string for creative tab
		LanguageRegistry.instance().addStringLocalization("itemGroup.brainFuck", "BrainFuck Blocks");
		
		//Initializes blocks
		BlockHandler.init();
		
		//Initializes items
		ItemHandler.init();
		
		//Initializes TileEntities
		proxy.registerTileEntities();
	}
	
}
