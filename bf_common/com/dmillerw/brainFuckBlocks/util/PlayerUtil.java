package com.dmillerw.brainFuckBlocks.util;

import net.minecraftforge.common.ForgeDirection;

public class PlayerUtil {

	public static ForgeDirection get2dOrientation(double angle) {
		if (angle < 45 || angle > 315)
			return ForgeDirection.EAST;
		else if (angle < 135)
			return ForgeDirection.SOUTH;
		else if (angle < 225)
			return ForgeDirection.WEST;
		else
			return ForgeDirection.NORTH;
	}

	public static ForgeDirection get2dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dz = pos1.z - pos2.z;
		double angle = Math.atan2(Dz, Dx) / Math.PI * 180 + 180;
		
		return get2dOrientation(angle);
	}
	
	public static ForgeDirection get3dOrientation(Position pos1, Position pos2) {
		double Dx = pos1.x - pos2.x;
		double Dy = pos1.y - pos2.y;
		double angle = Math.atan2(Dy, Dx) / Math.PI * 180 + 180;

		if (angle > 45 && angle < 135)
			return ForgeDirection.UP;
		else if (angle > 225 && angle < 315)
			return ForgeDirection.DOWN;
		else
			return get2dOrientation(pos1, pos2);
	}

	
}
