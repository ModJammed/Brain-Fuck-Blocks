package com.dmillerw.brainFuckBlocks.tileentity;

import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.interfaces.ISyncedTile;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityCPU extends TileEntity implements IRotatable, ISyncedTile {

	private ForgeDirection rotation;
	private ForgeDirection inputSide;
	private ForgeDirection outputSide;
	
	@Override
	public ForgeDirection getRotation() {
		if (rotation == null) {
			return ForgeDirection.NORTH;
		}
		
		return rotation;
	}

	//TODO Functionality
		
	@Override
	public void setRotation(ForgeDirection rot) {
		rotation = rot;
		outputSide = rot.getRotation(ForgeDirection.NORTH);
		inputSide = outputSide.getOpposite();
	}

	@Override
	public void rotate() {
		setRotation(getRotation().getRotation(ForgeDirection.UP));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		rotation = ForgeDirection.getOrientation(nbt.getByte("rotation"));
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
	public int[] getPayload() {
		return null;
	}

	@Override
	public void handlePayload(int[] payload) {
		
	}
}
