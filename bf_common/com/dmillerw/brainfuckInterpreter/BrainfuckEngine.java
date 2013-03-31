package com.dmillerw.brainfuckInterpreter;

import java.util.ArrayList;
import java.util.List;

import com.dmillerw.brainFuckBlocks.tileentity.TileEntityCPU;

public class BrainfuckEngine {

	public byte[] data;
	
	public int cells;
	public int dataPointer;
	public int charPointer;
	
	private List<Character> storedSymbols;
	
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
	}
	
	public void clear() {
		data = new byte[cells];
		dataPointer = 0;
		charPointer = 0;
		storedSymbols = new ArrayList<Character>();
	}
	
	public void store(char token) {
		storedSymbols.add(token);
	}
	
	public void interpret() {
		for (char token : storedSymbols) {
			interpret(token);
		}
	}
	
	//TODO Implement loop parsing
	private void interpret(char token) {
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
		}
	}
	
}
