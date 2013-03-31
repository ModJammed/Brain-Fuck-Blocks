package com.dmillerw.brainFuckBlocks.tileentity;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.interfaces.IConnection;
import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.util.Position;

public class TileEntityWire extends TileEntity implements IRotatable, IConnection {

	public ForgeDirection rotation;
	public ForgeDirection outputSide;
	
	@Override
	public ForgeDirection getOutput() {
		return outputSide;
	}

	@Override
	public Position getPosition() {
		return new Position(xCoord, yCoord, zCoord);
	}

	@Override
	public ForgeDirection getRotation() {
		return rotation;
	}

	//TODO Make wire rotate in all three dimensions
	@Override
	public void setRotation(ForgeDirection rot) {
		rotation = rot;
		outputSide = rot;
	}

	@Override
	public void rotate() {
		setRotation(rotation.getRotation(ForgeDirection.UP));
	}

}
