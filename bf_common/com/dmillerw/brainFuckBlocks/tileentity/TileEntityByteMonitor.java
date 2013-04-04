package com.dmillerw.brainFuckBlocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.interfaces.IInputPeripheral;
import com.dmillerw.brainFuckBlocks.interfaces.IPayloadReceiver;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.network.packets.PacketUpdateTileEntity;
import com.dmillerw.brainFuckBlocks.util.BlockCoords;

import cpw.mods.fml.common.network.PacketDispatcher;

public class TileEntityByteMonitor extends TileEntity implements IRotatable, IInputPeripheral, IPayloadReceiver {

	private ForgeDirection rotation;
	public byte storedData;
	
	@Override
	public ForgeDirection getRotation() {
		if (rotation == null) {
			return ForgeDirection.NORTH;
		}
		
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rot) {
		rotation = rot;
	}

	@Override
	public void rotate() {
		setRotation(getRotation().getRotation(ForgeDirection.UP));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
		nbt.setByte("stored", storedData);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		storedData = nbt.getByte("stored");
	}
	
	public Packet getDescriptionPacket() {
		NBTTagCompound nbt = new NBTTagCompound();
		writeToNBT(nbt);
        return new Packet132TileEntityData(this.xCoord, this.yCoord, this.zCoord, 0, nbt);
    }
	
	public void onDataPacket(INetworkManager net, Packet132TileEntityData pkt) {
		if (pkt.xPosition == this.xCoord && pkt.yPosition == this.yCoord && pkt.zPosition == this.zCoord) {
			readFromNBT(pkt.customParam1);
		}
    }
	
	@Override
	public void handleDataInput(byte data) {
		storedData = data;
		
		PacketUpdateTileEntity packet = new PacketUpdateTileEntity();
		packet.coords = new BlockCoords(xCoord, yCoord, zCoord);
		packet.payload = getPayload();
		
		PacketDispatcher.sendPacketToAllAround(xCoord, yCoord, zCoord, 50D, worldObj.provider.dimensionId, packet.makePacket());
	}

	@Override
	public void handlePayload(int[] payload) {
		storedData = (byte) payload[0];
	}

	@Override
	public int[] getPayload() {
		return new int[] {storedData};
	}

}
