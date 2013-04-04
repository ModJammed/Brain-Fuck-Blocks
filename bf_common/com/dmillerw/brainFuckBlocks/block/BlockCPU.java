package com.dmillerw.brainFuckBlocks.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.interfaces.IIconProvider;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;
import com.dmillerw.brainFuckBlocks.util.TextureCoordinates;

public class BlockCPU extends BlockContainer implements IIconProvider {

	//0 = bottom
	//1 = side_normal
	//2 = side_in
	//3 = side_out
	//4 = top
	//5 = front
	private TextureCoordinates[] textures;
	
	public BlockCPU(int id) {
		super(id, Material.iron);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setHardness(5F);
		setResistance(5F);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IBFWrench) {
			IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
			tile.rotate();
			world.markBlockForRenderUpdate(x, y, z);
			return true;
		}
		
		TileEntityCPU cpu = (TileEntityCPU) world.getBlockTileEntity(x, y, z);
		cpu.updateInstructions();
		
		return false;
	}
	
	public void onNeighborBlockChange(World world, int x, int y, int z, int blockID) {
		if (!world.isRemote) {
			if (world.isBlockIndirectlyGettingPowered(x, y, z)) {
				TileEntityCPU cpu = (TileEntityCPU) world.getBlockTileEntity(x, y, z);
				cpu.updateInstructions();
			}
		}
    }
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living) {
		super.onBlockPlacedBy(world, x, y, z, living);
		ForgeDirection side = PlayerUtil.get2DBlockOrientation(living);
		IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
	}
	
	@Override
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IRotatable blockRotator = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[0].getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == blockRotator.getRotation().getRotation(ForgeDirection.UP)) {
				return textures[3].getTextureIndex();
			} else if (sideForge == blockRotator.getRotation().getOpposite()) {
				return textures[5].getTextureIndex();
			}
			
			return textures[1].getTextureIndex();
		} else {
			return textures[4].getTextureIndex();
		}
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[0].getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == ForgeDirection.EAST) {
				return textures[3].getTextureIndex();
			} else if (sideForge == ForgeDirection.SOUTH) {
				return textures[5].getTextureIndex();
			}
			
			return textures[1].getTextureIndex();
		} else {
			return textures[4].getTextureIndex();
		}
	}
	
	@Override
	public void registerIcons() {
		textures = new TextureCoordinates[6];
		
		textures[0] = new TextureCoordinates(0, 9);
		textures[1] = new TextureCoordinates(1, 9);
		textures[2] = new TextureCoordinates(1, 9);
		textures[3] = new TextureCoordinates(2, 9);
		textures[4] = new TextureCoordinates(3, 9);
		textures[5] = new TextureCoordinates(3, 8);
	}
	
	@Override
	public String getTextureFile() {
		return ModInfo.BLOCK_TEXTURE_LOCATION;
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCPU();
	}
	
}
