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
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityBrainFuckCode;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;
import com.dmillerw.brainFuckBlocks.util.Position;

public class BlockCPU extends BlockContainer {

	//0 = bottom
	//1 = side_normal
	//2 = side_in
	//3 = side_out
	//4 = top
	//5 = front
	private Icon[] textures;
	
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
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		EntityPlayer player = (EntityPlayer)living;
		ForgeDirection side = PlayerUtil.get2dOrientation(new Position(player.posX, player.posY, player.posZ), new Position(x,y, z ));
		IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IRotatable blockRotator = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[0];
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == blockRotator.getRotation().getRotation(ForgeDirection.UP)) {
				return textures[3];
			} else if (sideForge == blockRotator.getRotation().getRotation(ForgeDirection.UP).getOpposite()) {
				return textures[2];
			} else if (sideForge == blockRotator.getRotation().getOpposite()) {
				return textures[5];
			}
			
			return textures[1];
		} else {
			return textures[4];
		}
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (sideForge == ForgeDirection.DOWN) {
			return textures[0];
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == ForgeDirection.WEST) {
				return textures[2];
			} else if (sideForge == ForgeDirection.EAST) {
				return textures[3];
			} else if (sideForge == ForgeDirection.SOUTH) {
				return textures[5];
			}
			
			return textures[1];
		} else {
			return textures[4];
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		textures = new Icon[6];
		
		textures[0] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_bottom");
		textures[1] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_side");
		textures[2] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_side_in");
		textures[3] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_side_out");
		textures[4] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_top");
		textures[5] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":cpu/cpu_front");
	}
	
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityCPU();
	}
	
}
