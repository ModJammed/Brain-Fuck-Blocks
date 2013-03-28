package com.dmillerw.brainFuckBlocks.helper;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.dmillerw.brainFuckBlocks.lib.ModInfo;

import cpw.mods.fml.common.FMLLog;

public class LogHelper {

	public static Logger bfLog;
	
	public static void init() {
		bfLog = Logger.getLogger(ModInfo.MOD_NAME);
		bfLog.setParent(FMLLog.getLogger());
	}
	
	public static void log(Level level, String msg) {
		bfLog.log(level, msg);
	}
	
	public static void log(String msg) {
		log(Level.INFO, msg);
	}
	
}
