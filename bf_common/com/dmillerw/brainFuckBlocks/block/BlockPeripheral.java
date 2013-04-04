package com.dmillerw.brainFuckBlocks.block;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IIconProvider;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityChatData;
import com.dmillerw.brainFuckBlocks.util.TextureCoordinates;

public class BlockPeripheral extends BlockContainer implements IIconProvider {

	public static String[] blockNames = new String[] {"Chat Data Interpreter"};
	
	private TextureCoordinates[] textures;
	
	public BlockPeripheral(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		int meta = world.getBlockMetadata(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[4].getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			return textures[3].getTextureIndex();
		} else {
			return textures[2].getTextureIndex();
		}
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[4].getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			return textures[3].getTextureIndex();
		} else {
			return textures[2].getTextureIndex();
		}
	}
	
	@Override
	public void registerIcons() {
		textures = new TextureCoordinates[5];
		
		textures[0] = new TextureCoordinates(2, 8);
		textures[1] = new TextureCoordinates(1, 8);
		textures[2] = new TextureCoordinates(0, 8);
		textures[3] = new TextureCoordinates(1, 9);
		textures[4] = new TextureCoordinates(0, 9);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public String getTextureFile() {
		return ModInfo.BLOCK_TEXTURE_LOCATION;
	}
	
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityChatData();
	}
	
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
