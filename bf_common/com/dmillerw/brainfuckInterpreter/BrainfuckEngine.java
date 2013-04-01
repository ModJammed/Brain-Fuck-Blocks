package com.dmillerw.brainfuckInterpreter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;
import com.dmillerw.brainFuckBlocks.util.Position;

public class BrainfuckEngine {

	public byte[] data;
	
	public int cells;
	public int dataPointer;
	public int charPointer;
	
	public Map<Position, Character> storedSymbols;
	
	public List<Integer> charStack;
	
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
		storedSymbols = new HashMap<Position, Character>();
		charStack = new ArrayList<Integer>();
	}
	
	public void clear() {
		data = new byte[cells];
		dataPointer = 0;
		charPointer = 0;
		storedSymbols.clear();
		charStack.clear();
	}
	
	public void store(Position position, char token) {
		storedSymbols.put(position, token);
	}
	
	public void interpret() {
		for (; charPointer<storedSymbols.size(); charPointer++) {
			interpret(storedSymbols.get(charPointer), toCharArray(storedSymbols.values()));
		}
	}
	
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
			System.out.println("Data @ Output >>> "+data[dataPointer]);
			cpu.sendOutput(data[dataPointer]);
		} else if (token == Token.BRACKET_OPEN) {
			if (data[dataPointer] - 1 > 0) {
				charStack.add(charPointer - 1);
			}
		} else if (token == Token.BRACKET_CLOSE) {
			if (charStack.size() > 0) {
				charPointer = charStack.get(charStack.size() - 1);
				charStack.remove(charStack.size() - 1);
			}
		}
	}
	
	private char[] toCharArray(Collection<Character> collection) {
		Object[] array = collection.toArray();
		char[] charArray = new char[array.length];
		
		for (int i=0; i<array.length; i++) {
			charArray[i] = (char) array[i];
		}
		
		return charArray;
	}
	
}
