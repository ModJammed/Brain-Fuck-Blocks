package com.dmillerw.brainFuckBlocks.interfaces;

import net.minecraftforge.common.ForgeDirection;

public interface IConnection {

	public ForgeDirection getOutput();
	
	public ForgeDirection getInput();
	
}
