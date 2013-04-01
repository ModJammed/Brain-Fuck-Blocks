package com.dmillerw.brainFuckBlocks.util;

import net.minecraft.block.BlockPistonBase;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class PlayerUtil {

	public static ForgeDirection get3DBlockOrientation(World world, int x, int y, int z, EntityLiving entity) {
		return ForgeDirection.getOrientation(BlockPistonBase.determineOrientation(world, x, y, z, entity));
	}
	
	public static ForgeDirection get2DBlockOrientation(EntityLiving living) {
		int l = MathHelper.floor_double((double) (living.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

		ForgeDirection direction = ForgeDirection.UNKNOWN;
		
		if (l == 0) {
			direction = ForgeDirection.getOrientation(2);
		} else if (l == 1) {
			direction = ForgeDirection.getOrientation(5);
		} else if (l == 2) {
			direction = ForgeDirection.getOrientation(3);
		} else if (l == 3) {
			direction = ForgeDirection.getOrientation(4);
		}
		
		return direction.getOpposite();
	}
	
}
