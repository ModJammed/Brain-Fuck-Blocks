package com.dmillerw.brainFuckBlocks.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.interfaces.IIconProvider;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.lib.UserPreferences;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCode;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;
import com.dmillerw.brainFuckBlocks.util.TextureCoordinates;

public class BlockCode extends BlockContainer implements IIconProvider {

	public static TextureCoordinates bottomTexture;
	public static TextureCoordinates topTexture;
	public static TextureCoordinates sideTexture;
	public static TextureCoordinates sideOutTexture;
	
	public static String[] blockNames = new String[] {"Increment Pointer", "Decrement Pointer", "Increment Byte", "Decrement Byte", "Output Byte", "Input Byte", "Bracket Open", "Bracket Close", "Machine Casing"};
	
	protected BlockCode(int id) {
		super(id, Material.rock);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setHardness(1F);
		setResistance(1F);
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living) {
		super.onBlockPlacedBy(world, x, y, z, living);
		ForgeDirection side = PlayerUtil.get2DBlockOrientation(living);
		TileEntityCode tile = (TileEntityCode) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
		tile.type = world.getBlockMetadata(x, y, z);
	}
	
	@Override
	public int getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IRotatable blockRotator = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return bottomTexture.getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == blockRotator.getRotation().getRotation(ForgeDirection.UP)) {
				return sideOutTexture.getTextureIndex();
			}
			return sideTexture.getTextureIndex();
		} else {
			return new TextureCoordinates(getTextureIndexFromRotation(blockRotator.getRotation()), meta).getTextureIndex();
		}
	}
	
	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (meta == 8) {
			if (sideForge == ForgeDirection.DOWN) {
				return bottomTexture.getTextureIndex();
			} else if (sideForge != ForgeDirection.UP) {
				return sideTexture.getTextureIndex();
			} else {
				return topTexture.getTextureIndex();
			}
		}
		
		if (sideForge == ForgeDirection.DOWN) {
			return bottomTexture.getTextureIndex();
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == ForgeDirection.EAST) {
				return sideTexture.getTextureIndex();
			}
			return sideTexture.getTextureIndex();
		} else {
			return new TextureCoordinates(0, meta).getTextureIndex();
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubBlocks(int id, CreativeTabs tab, List list) {
		for (int i=0; i<blockNames.length; i++) {
			list.add(new ItemStack(id, 1, i));
		}
	}
	
	@Override
	public void registerIcons() {
		bottomTexture = new TextureCoordinates(0, 9);
		topTexture = new TextureCoordinates(3, 9);
		sideTexture = new TextureCoordinates(1, 9);
		sideOutTexture = new TextureCoordinates(2, 9);
	}
	
	private int getTextureIndexFromRotation(ForgeDirection rot) {
		if (rot == ForgeDirection.NORTH) {
			return 0;
		} else if (rot == ForgeDirection.WEST) {
			return 1;
		} else if (rot == ForgeDirection.SOUTH) {
			return 2;
		} else if (rot == ForgeDirection.EAST) {
			return 3;
		}
		
		return 0;
	}
	
	@Override
	public int idPicked(World world, int x, int y, int z) {
        return this.blockID;
    }
	
	@Override
    public boolean canDropFromExplosion(Explosion explosion) {
        return UserPreferences.codeBlockCraftingEnable;
    }
    
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
        if (UserPreferences.codeBlockCraftingEnable) {
        	return 1;
        }
        
        return 0;
    }
	
	public TileEntity createTileEntity(World world, int meta) {
		return new TileEntityCode();
	}
	
	@Override
	public String getTextureFile() {
		return ModInfo.BLOCK_TEXTURE_LOCATION;
	}
	
	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
