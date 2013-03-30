package com.dmillerw.brainfuckInterpreter;

public class BrainfuckEngine {

	public byte[] data;
	
	public int dataPointer;
	
	public int charPointer;
	
	public BrainfuckEngine(int cells) {
		initate(cells);
	}
	
	public void initate(int cells) {
		data = new byte[cells];
		dataPointer = 0;
		charPointer = 0;
	}
	
	public void interpret(char token) {
		
	}
	
}
