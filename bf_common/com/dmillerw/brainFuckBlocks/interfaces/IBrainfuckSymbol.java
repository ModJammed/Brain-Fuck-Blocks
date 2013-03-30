package com.dmillerw.brainFuckBlocks.interfaces;

import com.dmillerw.brainFuckBlocks.enums.EnumToken;

public interface IBrainfuckSymbol {

	/**
	 * @param array The "tape" array
	 * @param pointerPos Current pointer position
	 * @return A new pointer position if required
	 */
	public int interpret(char[] array, int pointerPos);
	
	public EnumToken getSymbol();
	
}
