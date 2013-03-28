package com.dmillerw.brainFuckBlocks.interfaces;

import net.minecraftforge.common.ForgeDirection;

public interface IRotatable {

	public ForgeDirection getRotation();
	
	public void setRotation(ForgeDirection rot);
	
	public void rotate();
	
}
