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
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.util.Position;
import com.dmillerw.brainfuckInterpreter.BrainfuckEngine;

public class TileEntityCPU extends TileEntity implements IRotatable, IConnection {

	private ForgeDirection rotation;
	private ForgeDirection outputSide;
	
	private BrainfuckEngine engine;
	
	private List<String> instructionPositions;
	
	private List<IPeripheral> connectedPeripherals;
	
	public TileEntityCPU() {
		engine = new BrainfuckEngine(30000, this);
	}
	
	@Override
	public ForgeDirection getRotation() {
		if (rotation == null) {
			return ForgeDirection.NORTH;
		}
		
		return rotation;
	}

	public void updateInstructions() {
		if (this.worldObj.isRemote) {
			return;
		}
		
		instructionPositions = new ArrayList<String>();
		
		engine.clear();
		
		updateConnectedPeripherals();
				
		int currXOffset = xCoord + outputSide.offsetX;
		int currYOffset = yCoord + outputSide.offsetY;
		int currZOffset = zCoord + outputSide.offsetZ;
		
		boolean keepSearching = true;
		
		while (keepSearching) {
			if (worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset) instanceof IConnection) {
				IConnection connection = (IConnection) worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset);
				
				if (instructionPositions.contains(connection.getPosition().toString())) {
					keepSearching = false;
					break;
				} else {
					instructionPositions.add(connection.getPosition().toString());
				}
				
				if (worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset) instanceof IBrainfuckSymbol) {
					IBrainfuckSymbol symbol = (IBrainfuckSymbol) worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset);
					engine.store(new Position(worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset)), symbol.getSymbol());
				}
				
				if (worldObj.getBlockTileEntity(currXOffset, currYOffset, currZOffset) instanceof TileEntityCPU) {
					keepSearching = false;
					break;
				}
				
				currXOffset = currXOffset + connection.getOutput().offsetX;
				currYOffset = currYOffset + connection.getOutput().offsetY;
				currZOffset = currZOffset + connection.getOutput().offsetZ;
			} else {
				keepSearching = false;
			}
		}
		
//		for (char token : engine.storedSymbols) {
//			System.out.println(token);
//		}
		
		engine.interpret();
	}
	
	private void updateConnectedPeripherals() {
		connectedPeripherals = new ArrayList<IPeripheral>();
		
		for (ForgeDirection side : ForgeDirection.VALID_DIRECTIONS) {
			if (worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ) instanceof IPeripheral) {
				connectedPeripherals.add((IPeripheral) worldObj.getBlockTileEntity(xCoord + side.offsetX, yCoord + side.offsetY, zCoord + side.offsetZ));
			}
		}
	}
	
	public void sendOutput(byte data) {
		for (IPeripheral periph : connectedPeripherals) {
			if (periph instanceof IInputPeripheral) {
				((IInputPeripheral)periph).handleDataInput(data);
			}
		}
	}
	
	//TODO potential issue with multiple inputs?
	public byte getInput() {
		for (IPeripheral periph : connectedPeripherals) {
			if (periph instanceof IOutputPeripheral) {
				return ((IOutputPeripheral)periph).handleDataOutput();
			}
		}
		
		return 0;
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
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		setRotation(ForgeDirection.getOrientation(nbt.getByte("rotation")));
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
	public ForgeDirection getOutput() {
		return outputSide;
	}

	@Override
	public Position getPosition() {
		return new Position(xCoord, yCoord, zCoord);
	}
	
}
