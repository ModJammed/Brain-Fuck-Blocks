package com.dmillerw.brainFuckBlocks.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.interfaces.IOutputPeripheral;
import com.dmillerw.brainFuckBlocks.interfaces.IPayloadReceiver;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;

public class TileEntityInput extends TileEntity implements IRotatable, IOutputPeripheral, IPayloadReceiver {

	private ForgeDirection rotation;
	public byte storedData = 0;
	public byte type = 0;
	
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
		nbt.setByte("type", type);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		storedData = nbt.getByte("stored");
		type = nbt.getByte("type");
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
	public byte handleDataOutput() {
		return storedData;
	}

	@Override
	public void handlePayload(int[] payload) {
		storedData = (byte) payload[0];
		type = (byte) payload[1];
	}

	@Override
	public int[] getPayload() {
		return new int[] {storedData, type};
	}

}
