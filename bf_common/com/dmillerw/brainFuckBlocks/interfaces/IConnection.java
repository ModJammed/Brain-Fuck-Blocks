package com.dmillerw.brainFuckBlocks.interfaces;

import net.minecraftforge.common.ForgeDirection;

import com.dmillerw.brainFuckBlocks.util.Position;

public interface IConnection {

	public ForgeDirection getOutput();
	
	public Position getPosition();
	
}
