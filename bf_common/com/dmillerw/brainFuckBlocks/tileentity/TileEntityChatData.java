package com.dmillerw.brainFuckBlocks.tileentity;

import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.tileentity.TileEntity;

import com.dmillerw.brainFuckBlocks.interfaces.IInputPeripheral;

import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityChatData extends TileEntity implements IInputPeripheral {

	@Override
	public void handleDataInput(byte data) {
		if (worldObj.isRemote) {
			return;
		}
		
		PacketDispatcher.sendPacketToAllInDimension(new Packet3Chat("BrainFuck Output: "+data), worldObj.provider.dimensionId);
	}

}
