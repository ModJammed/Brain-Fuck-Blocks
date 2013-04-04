package com.dmillerw.brainFuckBlocks.network;

import java.util.logging.Level;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

import com.dmillerw.brainFuckBlocks.helper.LogHelper;
import com.dmillerw.brainFuckBlocks.network.packets.PacketFoundry;
import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteStreams;

import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class BFPacketHandler implements IPacketHandler {

	@Override
	public void onPacketData(INetworkManager manager, Packet250CustomPayload packet, Player player) {
		try {
			EntityPlayer entityPlayer = (EntityPlayer)player;
			ByteArrayDataInput in = ByteStreams.newDataInput(packet.data);
			int packetID = in.readUnsignedByte();
			PacketFoundry packetFoundry = PacketFoundry.constructPacket(packetID);
			
			packetFoundry.read(in);
			packetFoundry.execute(entityPlayer, entityPlayer.worldObj.isRemote ? Side.CLIENT : Side.SERVER);
		} catch (ProtocalException e) {
			if (player instanceof EntityPlayerMP) {
				((EntityPlayerMP)player).playerNetServerHandler.kickPlayerFromServer("Protocal Exception!");
				EntityPlayer entityPlayer = (EntityPlayer)player;
				LogHelper.log(Level.WARNING, "Player "+ entityPlayer.username +" caused a Protocal Exception!");
			}
		} catch (ReflectiveOperationException e) {
			throw new RuntimeException("Unexpected Reflection exception during Packet construction!");
		}
	}

}
