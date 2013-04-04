package com.dmillerw.brainFuckBlocks.network;

@SuppressWarnings("serial")
public class ProtocalException extends Exception {

	public ProtocalException() {
		
	}
	
	public ProtocalException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ProtocalException(String message) {
		super(message);
	}
	
	public ProtocalException(Throwable cause) {
		super(cause);
	}
	
}
