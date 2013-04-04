package com.dmillerw.brainFuckBlocks.util;

public class TextureCoordinates {

	public int x;
	public int y;
	
	public TextureCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getTextureIndex() {
		return x+y*16;
	}
	
}
