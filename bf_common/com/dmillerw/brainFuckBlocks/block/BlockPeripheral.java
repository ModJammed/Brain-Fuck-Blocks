package com.dmillerw.brainFuckBlocks.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityChatData;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneData;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneInput;

public class BlockPeripheral extends BlockContainer {

	public static String[] blockNames = new String[] {"Redstone Data Interpreter", "Redstone Input Interpreter", "Chat Data Interpreter"};
	
	private Icon[] textures;
	
	public BlockPeripheral(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[4];
		} else if (sideForge != ForgeDirection.UP) {
			return textures[3];
		} else {
			return textures[meta];
		}
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[4];
		} else if (sideForge != ForgeDirection.UP) {
			return textures[3];
		} else {
			return textures[meta];
		}
	}
	
	@Override
	public int isProvidingWeakPower(IBlockAccess world, int x, int y, int z, int side) {
		if (world.getBlockMetadata(x, y, z) == 0) {
			TileEntityRedstoneData periph = (TileEntityRedstoneData) world.getBlockTileEntity(x, y, z);
			return periph.rsOutput;
		} else {
			return 0;
		}
	}
	
	public static void updateSurroundingBlocks(World world, int x, int y, int z) {
    	world.notifyBlocksOfNeighborChange(x, y, z, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x - 1, y, z, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x + 1, y, z, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x, y, z - 1, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x, y, z + 1, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x, y - 1, z, BlockIDs.bfPeripheralID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, BlockIDs.bfPeripheralID);
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[5];
		
		textures[0] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":rsperipheral/rs_data");
		textures[1] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":rsperipheral/rs_input");
		textures[2] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":chatperipheral/chat_data");
		textures[3] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_side");
		textures[4] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_bottom");
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	public TileEntity createTileEntity(World world, int meta) {
		if (meta == 0) {
			return new TileEntityRedstoneData();
		} else if (meta == 1) {
			return new TileEntityRedstoneInput();
		} else {
			return new TileEntityChatData();
		}
	}
	
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
