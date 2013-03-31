package com.dmillerw.brainfuckInterpreter;

import java.util.ArrayList;
import java.util.List;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;

public class BrainfuckEngine {

	public byte[] data;
	
	public int cells;
	public int dataPointer;
	public int charPointer;
	
	public List<Character> storedSymbols;
	
	public List<Integer> loopStack;
	
	private TileEntityCPU cpu;
	
	public BrainfuckEngine(int cells, TileEntityCPU cpu) {
		initate(cells);
		this.cpu = cpu;
	}
	
	public void initate(int cells) {
		data = new byte[cells];
		dataPointer = 0;
		charPointer = 0;
		this.cells = cells;
		storedSymbols = new ArrayList<Character>();
		loopStack = new ArrayList<Integer>();
	}
	
	public void clear() {
		data = new byte[cells];
		dataPointer = 0;
		charPointer = 0;
		storedSymbols.clear();
		loopStack.clear();
	}
	
	public void store(char token) {
		storedSymbols.add(token);
	}
	
	public void interpret() {
		for (char token : storedSymbols) {
			charPointer++;
			interpret(token, toCharArray(storedSymbols));
		}
	}
	
	//TODO Implement loop parsing
	private void interpret(char token, char[] chars) {
		if (token == Token.DATA_INC) {
			if (dataPointer + 1 <= data.length) {
				dataPointer++;
			}
		} else if (token == Token.DATA_DEC) {
			if (dataPointer - 1 >= 0) {
				dataPointer--;
			}
		} else if (token == Token.BYTE_INC) {
			if (data[dataPointer] + 1 <= Byte.MAX_VALUE) {
				data[dataPointer]++;
			}
		} else if (token == Token.BYTE_DEC) {
			if (data[dataPointer] - 1 >= 0) {
				data[dataPointer]--;
			}
		} else if (token == Token.BYTE_IN) {
			data[dataPointer] = cpu.getInput();
		} else if (token == Token.BYTE_OUT) {
			System.out.println("Debug Output @ "+dataPointer+" >>> "+data[dataPointer]);
			cpu.sendOutput(data[dataPointer]);
		} else if (token == Token.BRACKET_OPEN) {
			
		} else if (token == Token.BRACKET_CLOSE) {
			
		}
	}
	
	private char[] toCharArray(List<Character> charList) {
		Object[] array = charList.toArray();
		char[] charArray = new char[array.length];
		
		for (int i=0; i<array.length; i++) {
			charArray[i] = (char) array[i];
		}
		
		return charArray;
	}
	
}
