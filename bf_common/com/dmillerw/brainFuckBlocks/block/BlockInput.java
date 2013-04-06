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
import com.dmillerw.brainFuckBlocks.client.gui.GuiInput;
import com.dmillerw.brainFuckBlocks.interfaces.IBFWrench;
import com.dmillerw.brainFuckBlocks.interfaces.IPayloadReceiver;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;
import com.dmillerw.brainFuckBlocks.network.packets.PacketUpdateTileEntity;
import com.dmillerw.brainFuckBlocks.tileentity.TileEntityInput;
import com.dmillerw.brainFuckBlocks.util.BlockCoords;
import com.dmillerw.brainFuckBlocks.util.PlayerUtil;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.common.network.Player;

public class BlockInput extends BlockContainer {

	private Icon topTexture;
	private Icon bottomTexture;
	private Icon sideTexture;
	private Icon frontTexture;
	
	public BlockInput(int id) {
		super(id, Material.iron);
		setHardness(1F);
		setResistance(1F);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.getHeldItem() != null && player.getHeldItem().getItem() instanceof IBFWrench) {
			IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
			tile.rotate();
			world.markBlockForRenderUpdate(x, y, z);
			return true;
		}
		
		if (!player.isSneaking()) {
			if (!world.isRemote) {
				PacketUpdateTileEntity packet = new PacketUpdateTileEntity();
				packet.coords = new BlockCoords(x, y, z);
				packet.payload = ((IPayloadReceiver)world.getBlockTileEntity(x, y, z)).getPayload();
				
				PacketDispatcher.sendPacketToPlayer(packet.makePacket(), (Player) player);
			}
			
			FMLClientHandler.instance().getClient().displayGuiScreen(new GuiInput(world, x, y, z));
			return true;
		}
		
		return false;
	}
	
	@Override
	public void onBlockPlacedBy(World world, int x, int y, int z, EntityLiving living, ItemStack stack) {
		super.onBlockPlacedBy(world, x, y, z, living, stack);
		ForgeDirection side = PlayerUtil.get2DBlockOrientation(living);
		IRotatable tile = (IRotatable) world.getBlockTileEntity(x, y, z);
		tile.setRotation(side);
	}
	
	@Override
	public Icon getBlockTexture(IBlockAccess world, int x, int y, int z, int side) {
		ForgeDirection forgeSide = ForgeDirection.getOrientation(side);
		IRotatable rotate = (IRotatable) world.getBlockTileEntity(x, y, z);
		
		if (forgeSide == ForgeDirection.UP) {
			return topTexture;
		} else if (forgeSide == ForgeDirection.DOWN) {
			return bottomTexture;
		} else if (forgeSide != rotate.getRotation().getOpposite()) {
			return sideTexture;
		} else {
			return frontTexture;
		}
	}
	
	@Override
	public Icon getBlockTextureFromSideAndMetadata(int side, int meta) {
		ForgeDirection forgeSide = ForgeDirection.getOrientation(side);
		
		if (forgeSide == ForgeDirection.UP) {
			return topTexture;
		} else if (forgeSide == ForgeDirection.DOWN) {
			return bottomTexture;
		} else if (forgeSide != ForgeDirection.SOUTH) {
			return sideTexture;
		} else {
			return frontTexture;
		}
	}
	
	@Override
	public void registerIcons(IconRegister register) {
		topTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":input/input_top");
		sideTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":input/input_side");
		bottomTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":input/input_bottom");
		frontTexture = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":input/input_front");;
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		return new TileEntityInput();
	}

}
