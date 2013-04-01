package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.interfaces.IConnection;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityWire;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;

public class BlockWire extends BlockContainer {

	private Icon sideTexture;
	private Icon outTexture;
	
	public BlockWire(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IBFWrench) {
			IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
			tile.rotate();
			world.markBlockForRenderUpdate(x, y, z);
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		ForgeDirection side = PlayerUtil.get3DBlockOrientation(world, x, y, z, living);
		IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IConnection connection = (IConnection) world.getBlockTileEntity(x, y, z);
		
		if (sideForge == connection.getOutput()) {
			return outTexture;
		} else {
			return sideTexture;
		}
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge == ForgeDirection.EAST) {
			return outTexture;
		} else {
			return sideTexture;
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		sideTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wire_side");
		outTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":wire_out");
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityWire();
	}
	
}
