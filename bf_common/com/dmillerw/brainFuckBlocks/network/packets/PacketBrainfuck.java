package com.dmillerw.brainFuckBlocks.network.packets;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.packet.Packet;

import com.dmillerw.brainFuckBlocks.network.ProtocalException;
import com.google.common.collect.BiMap;
import com.google.common.collect.ImmutableBiMap;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.PacketDispatcher;
import cpw.mods.fml.relauncher.Side;

public abstract class PacketBrainfuck {

	private static BiMap<Integer, Class<? extends PacketBrainfuck>> idMap;
	
	public static void buildPacketList() {
		ImmutableBiMap.Builder<Integer, Class<? extends PacketBrainfuck>> builder = ImmutableBiMap.builder();
		
		//Default packets go here
		builder.put(0, PacketUpdateTileEntity.class);
		
		idMap = builder.build();
	}
	
	public static PacketBrainfuck constructPacket(int packetID) throws ProtocalException, ReflectiveOperationException {
		Class<? extends PacketBrainfuck> clazz = idMap.get(Integer.valueOf(packetID));
		if (clazz == null) {
			throw new ProtocalException("Unknown Packet ID");
		} else {
			return clazz.newInstance();
		}
	}
	
	public final int getPacketID() {
		if (idMap.inverse().containsKey(getClass())) {
			return idMap.inverse().get(getClass()).intValue();
		} else {
			throw new RuntimeException("Packet "+getClass().getSimpleName()+" is missing a mapping!");
		}
	}
	
	public final Packet makePacket() {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeByte(getPacketID());
		write(out);
		return PacketDispatcher.getPacket("bfBlocks", out.toByteArray());
	}
	
	public abstract void write(ByteArrayDataOutput out);
	
	public abstract void read(ByteArrayDataInput in);
	
	public abstract void execute(EntityPlayer player, Side side);
	
}
