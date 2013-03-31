package com.dmillerw.brainFuckBlocks.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneData;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityRedstoneInput;

public class BlockPeripheralRedstone extends BlockContainer {

	public static String[] blockNames = new String[] {"Redstone Data Interpreter", "Redstone Input Interpreter"};
	
	public BlockPeripheralRedstone(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
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
    	world.notifyBlocksOfNeighborChange(x, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x - 1, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x + 1, y, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y, z - 1, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y, z + 1, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y - 1, z, BlockIDs.bfPeripheralRedstoneID);
        world.notifyBlocksOfNeighborChange(x, y + 1, z, BlockIDs.bfPeripheralRedstoneID);
    }
	
	@Override
	public boolean canProvidePower() {
		return true;
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
		} else {
			return new TileEntityRedstoneInput();
		}
	}
	
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
