package com.dmillerw.brainFuckBlocks.block;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.lib.UserPreferences;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCode;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;

public class BlockCode extends BlockContainer {

	public static Icon[][] textures;
	public static Icon bottomTexture;
	public static Icon topTexture;
	public static Icon[] sideTexture;
	
	public static String[] blockNames = new String[] {"Increment Pointer", "Decrement Pointer", "Increment Byte", "Decrement Byte", "Output Byte", "Input Byte", "Bracket Open", "Bracket Close", "Machine Casing"};
	
	public static String[] blockFileNames = new String[] {"datainc", "datadec", "byteinc", "bytedec", "byteout", "bytein", "bracketopen", "bracketclose", "machineCasing"};
	
	private static String[] symbolTextureRotations = new String[] {"north", "east", "south", "west"};
	
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
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		ForgeDirection side = PlayerUtil.get2DBlockOrientation(living);
		TileEntityCode tile = (TileEntityCode) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
		tile.type = world.getBlockMetadata(x, y, z);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		int meta = world.getBlockMetadata(x, y, z);
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		IRotatable blockRotator = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		if (sideForge == ForgeDirection.DOWN) {
			return bottomTexture;
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == blockRotator.getRotation().getRotation(ForgeDirection.UP)) {
				return sideTexture[2];
			}
			
			return sideTexture[0];
		} else {
			return textures[meta][getTextureIndexFromRotation(blockRotator.getRotation())];
		}
	}
	
	@Override
	public Icon getIcon(int side, int meta) {
		ForgeDirection sideForge = ForgeDirection.getOrientation(side);
		
		if (meta == 8) {
			if (sideForge == ForgeDirection.DOWN) {
				return bottomTexture;
			} else if (sideForge != ForgeDirection.UP) {
				return sideTexture[0];
			} else {
				return topTexture;
			}
		}
		
		if (sideForge == ForgeDirection.DOWN) {
			return bottomTexture;
		} else if (sideForge != ForgeDirection.UP) {
			if (sideForge == ForgeDirection.EAST) {
				return sideTexture[2];
			}
			
			return sideTexture[0];
		} else {
			return textures[meta][0];
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
	public void registerIcons(IconRegister register) {
		textures = new Icon[16][4];
		
		bottomTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_bottom");
		topTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_top");
		sideTexture = new Icon[3];
		
		sideTexture[0] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_side");
		sideTexture[1] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_side_in");
		sideTexture[2] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":code_side_out");
		
		for (int i=0; i<8; i++) {
			for (int j=0; j<4; j++) {
				textures[i][j] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":"+blockFileNames[i]+"/code_" + blockFileNames[i] + "_" + symbolTextureRotations[j] + "_off");
			}
		}
	}
	
	private int getTextureIndexFromRotation(ForgeDirection rot) {
		if (rot == ForgeDirection.NORTH) {
			return 0;
		} else if (rot == ForgeDirection.EAST) {
			return 1;
		} else if (rot == ForgeDirection.SOUTH) {
			return 2;
		} else if (rot == ForgeDirection.WEST) {
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
	
	/* IGNORE */
	public TileEntity createNewTileEntity(World world) {
		return null;
	}

}
