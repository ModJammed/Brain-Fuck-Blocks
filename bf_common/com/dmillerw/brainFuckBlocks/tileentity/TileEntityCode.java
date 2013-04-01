package com.dmillerw.brainFuckBlocks.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet132TileEntityData;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.interfaces.IBrainfuckSymbol;
import com.dmillerw.brainFuckBlocks.interfaces.IConnection;
import com.dmillerw.brainFuckBlocks.interfaces.IInputPeripheral;
import com.dmillerw.brainFuckBlocks.interfaces.IOutputPeripheral;
import com.dmillerw.brainFuckBlocks.interfaces.IPeripheral;
import com.dmillerw.brainFuckBlocks.interfaces.IPeripheralConnector;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.util.Position;
import com.dmillerw.brainfuckInterpreter.Token;

public class TileEntityCode extends TileEntity implements IRotatable, IConnection, IBrainfuckSymbol, IPeripheralConnector {

	private ForgeDirection rotation;
	private ForgeDirection outputSide;
	
	private int type;
	
	private byte active = 1;
	
	public TileEntityCode(int type) {
		this.type = type;
	}
	
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
		outputSide = rot.getRotation(ForgeDirection.UP);
	}

	@Override
	public void rotate() {
		setRotation(getRotation().getRotation(ForgeDirection.UP));
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		nbt.setByte("rotation", (byte) rotation.ordinal());
		nbt.setInteger("type", type);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
		type = nbt.getInteger("type");
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

	public char getSymbol() {
		return Token.getToken(type);
	}
	
	@Override
	public ForgeDirection getOutput() {
		return outputSide;
	}

	@Override
	public Position getPosition() {
		return new Position(xCoord, yCoord, zCoord);
	}

	@Override
	public void setAccessingFlag(byte flag) {
		active = flag;
		worldObj.markBlockForRenderUpdate(xCoord, yCoord, zCoord);
	}

	@Override
	public byte getAccessingFlag() {
		return active;
	}

	@Override
	public IPeripheral[] getConnectedPeripherals() {
		List<IPeripheral> connected = new ArrayList<IPeripheral>();
		
		for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity tile = worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ);
			
			if (tile instanceof IPeripheral) {
				System.out.println("Found and added peripheral");
				connected.add((IPeripheral) tile);
			}
		}
		
		IPeripheral[] toReturn = new IPeripheral[connected.size()];
		for (int i=0; i<connected.size(); i++) {
			toReturn[i] = connected.get(i);
		}
		
		return toReturn;
	}
	
}
