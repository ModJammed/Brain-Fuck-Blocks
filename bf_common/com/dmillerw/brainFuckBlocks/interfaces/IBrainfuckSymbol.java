package com.dmillerw.brainFuckBlocks.interfaces;

public interface IBrainfuckSymbol {

	public char getSymbol();

	public void setAccessingFlag(byte flag);
	
	public byte getAccessingFlag();
	
}
