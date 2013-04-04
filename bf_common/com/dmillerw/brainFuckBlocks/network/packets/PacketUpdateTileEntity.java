package com.dmillerw.brainFuckBlocks.network.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.interfaces.IPayloadReceiver;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;

import cpw.mods.fml.relauncher.Side;

public class PacketUpdateTileEntity extends PacketBlockCoords {

	public int[] payload;
	
	@Override
	public void write(ByteArrayDataOutput out) {
		super.write(out);
		out.writeInt(payload.length);
		for (int load : payload) {
			out.writeInt(load);
		}
	}

	@Override
	public void read(ByteArrayDataInput in) {
		super.read(in);
		int size = in.readInt();
		payload = new int[size];
		
		for (int i=0; i<size; i++) {
			payload[i] = in.readInt();
		}
	}

	@Override
	public void execute(EntityPlayer player, Side side) {
		World world = player.worldObj;
		
		IPayloadReceiver receiver = (IPayloadReceiver) world.getBlockTileEntity(coords.x, coords.y, coords.z);
		receiver.handlePayload(payload);
	}
	
}
