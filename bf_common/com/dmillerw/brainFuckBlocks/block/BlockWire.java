package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;

public class BlockWire extends BlockContainer {

	private Icon defaultTexture;
	private Icon inputTexture;
	private Icon outputTexture;
	
	public BlockWire(int id) {
		super(id, Material.cloth);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		//TODO Interaction with TE
		return defaultTexture;
	}
	
	@Override
	public int getRenderType() {
		return BrainFuckBlocks.wireRenderID;
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		defaultTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wire_side");
		inputTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wire_in");
		outputTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wire_out");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return null;
	}
	
}
