package com.dmillerw.brainFuckBlocks.client.gui;

import java.text.NumberFormat;
import java.text.ParsePosition;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.interfaces.IPayloadReceiver;
import com.dmillerw.brainFuckBlocks.network.packets.PacketUpdateTileEntity;
import com.dmillerw.brainFuckBlocks.util.BlockCoords;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiInput extends GuiScreen {

	private GuiButton saveButton;
	private GuiButton typeToggle;
	private GuiButton cancelButton;
	
	private GuiTextField inputField;
	
	private World world;
	
	private IPayloadReceiver payloadReceiver;
	
	private String[] buttonLabels = new String[] {"Byte", "Character"};
	
	private int x;
	private int y;
	private int z;
	
	public GuiInput(World world, int x, int y, int z) {
		this.world = world;
		this.x = x;
		this.y = y;
		this.z = z;
		
		payloadReceiver = (IPayloadReceiver) world.getBlockTileEntity(x, y, z);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void initGui() {
		super.initGui();

		saveButton = new GuiButton(0, this.width / 2 - 100, this.height / 2 - 5, "Save");
		typeToggle = new GuiButton(1, this.width / 2 - 100, this.height / 2 + 30, buttonLabels[payloadReceiver.getPayload()[1]]);
		cancelButton = new GuiButton(2, this.width / 2 - 100, this.height / 2 + 55, "Cancel");
		
		this.controlList.add(saveButton);
		this.controlList.add(typeToggle);
		this.controlList.add(cancelButton);
		
		inputField = new GuiTextField(FMLClientHandler.instance().getClient().fontRenderer, this.width / 2 - 100, 70, 200, 20);
		if (payloadReceiver.getPayload()[1] == 0) {
			inputField.setText(""+payloadReceiver.getPayload()[0]);
			inputField.setMaxStringLength(3);
		} else {
			inputField.setText(""+(char)payloadReceiver.getPayload()[0]);
			inputField.setMaxStringLength(1);
		}
	}
	
	protected void keyTyped(char par1, int par2) {
		if (payloadReceiver.getPayload()[1] == 1) {
			inputField.textboxKeyTyped(par1, par2);
		} else {
			if (isNumeric(par1)) {
				inputField.textboxKeyTyped(par1, par2);
			}
		}
		
		((GuiButton) this.controlList.get(0)).enabled = this.inputField.getText().trim().length() > 0;

		if (par1 == 13) {
			this.actionPerformed((GuiButton) this.controlList.get(2));
		}
	}
	
	protected void mouseClicked(int par1, int par2, int par3) {
		super.mouseClicked(par1, par2, par3);
		this.inputField.mouseClicked(par1, par2, par3);
	}
	
	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		if (par1GuiButton.enabled) {
			if (par1GuiButton.id == 2) {
				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (par1GuiButton.id == 0) {
				send();
				world.markBlockForRenderUpdate(x, y, z);
				this.mc.displayGuiScreen((GuiScreen) null);
			} else if (par1GuiButton.id == 1) {
				toggle();
			}
		}
	}
	
	private void toggle() {
		int type = payloadReceiver.getPayload()[1];
		int newType = 0;
		
		if (type == 0) {
			inputField.setMaxStringLength(1);
			newType = 1;
		} else if (type == 1) {
			inputField.setMaxStringLength(3);
			newType = 0;
		}
		
		typeToggle.displayString = buttonLabels[newType];
		
		PacketUpdateTileEntity packet = new PacketUpdateTileEntity();
		packet.coords = new BlockCoords(x, y, z);
		packet.payload = new int[] {payloadReceiver.getPayload()[0], newType};
		
		PacketDispatcher.sendPacketToServer(packet.makePacket());
	}
	
	private void send() {
		PacketUpdateTileEntity packet = new PacketUpdateTileEntity();
		packet.coords = new BlockCoords(x, y, z);
		
		byte type = (byte) payloadReceiver.getPayload()[1];
		byte data = 0;
		
		if (inputField.getText().length() > 0) {
			if (payloadReceiver.getPayload()[1] == 0) {
				data = Byte.parseByte(inputField.getText());
			} else {
				data = (byte) inputField.getText().charAt(0);
			}
		}
		
		packet.payload = new int[] {data, type};
		
		payloadReceiver.handlePayload(packet.payload);
		PacketDispatcher.sendPacketToServer(packet.makePacket());
	}
	
	public static boolean isNumeric(char chr) {
		String str = ""+chr;
		NumberFormat formatter = NumberFormat.getInstance();
		ParsePosition pos = new ParsePosition(0);
		formatter.parse(str, pos);
		return str.length() == pos.getIndex();
	}
	
	@Override
	public void drawScreen(int par1, int par2, float par3) {
		drawDefaultBackground();
		this.drawCenteredString(this.fontRenderer, "Input should be interpreted as:", this.width / 2, this.height / 2 + 20, 0xFFFFFF);
		super.drawScreen(par1, par2, par3);
		inputField.drawTextBox();
	}
	
	public boolean doesGuiPauseGame() {
        return false;
    }
	
}
