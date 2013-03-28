package com.dmillerw.brainFuckBlocks.tileentity;

import com.dmillerw.brainFuckBlocks.interfaces.IRotatable;
import com.dmillerw.brainFuckBlocks.interfaces.ISyncedTile;

import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.ForgeDirection;

public class TileEntityBrainFuckCode extends TileEntity implements IRotatable, ISyncedTile {

	private ForgeDirection rotation;
	
	@Override
	public ForgeDirection getRotation() {
		return rotation;
	}

	@Override
	public void setRotation(ForgeDirection rot) {
		rotation = rot;
	}

	@Override
	public void rotate() {
		if (rotation == ForgeDirection.NORTH) {
			rotation = ForgeDirection.EAST;
		} else if (rotation == ForgeDirection.EAST) {
			rotation = ForgeDirection.SOUTH;
		} else if (rotation == ForgeDirection.SOUTH) {
			rotation = ForgeDirection.WEST;
		} else if (rotation == ForgeDirection.WEST) {
			rotation = ForgeDirection.NORTH;
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
