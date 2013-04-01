package com.dmillerw.brainFuckBlocks.item;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.dmillerw.brainFuckBlocks.BrainFuckBlocks;
import com.dmillerw.brainFuckBlocks.block.BlockCode;
import com.dmillerw.brainFuckBlocks.block.BlockHandler;
import com.dmillerw.brainFuckBlocks.client.gui.GuiDocumentation;
import com.dmillerw.brainFuckBlocks.lib.ModInfo;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCodeWriter extends Item {

	private int blockID = BlockHandler.bfCode.blockID;
	
	private Icon[] textures;
	
	public ItemCodeWriter(int id) {
		super(id);
		setCreativeTab(BrainFuckBlocks.creativeTabBF);
		setMaxDamage(0);
		setMaxStackSize(1);
		setHasSubtypes(true);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean show) {
		list.add(BlockCode.blockNames[getCodeWriterMeta(stack)]);
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		if (par2World.isRemote) {
			return par1ItemStack;
		}
		
		int cwMeta = getCodeWriterMeta(par1ItemStack) + 1;
		
		System.out.println(cwMeta);
		
		if (par3EntityPlayer.isSneaking()) {
			if (cwMeta <= 7) {
				par3EntityPlayer.addChatMessage("Mode Changed: "+BlockCode.blockNames[cwMeta]);
				setCodeWriterMeta(par1ItemStack, cwMeta);
			} else if (cwMeta == 8) {
				par3EntityPlayer.addChatMessage("Mode Changed: Documentation");
				setCodeWriterMeta(par1ItemStack, cwMeta);
			} else {
				par3EntityPlayer.addChatMessage("Mode Changed: "+BlockCode.blockNames[0]);
				setCodeWriterMeta(par1ItemStack, 0);
			}
		} else if (cwMeta == 9) {
			FMLClientHandler.instance().getClient().displayGuiScreen(new GuiDocumentation());
		}
		
		return par1ItemStack;
	}
	
	@Override
	public Icon getIconFromDamage(int damage) {
        return textures[damage];
    }
	
	public int getCodeWriterMeta(ItemStack stack) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
			return 0;
		}
		
		return stack.stackTagCompound.getInteger("meta");
	}
	
	public void setCodeWriterMeta(ItemStack stack, int meta) {
		if (stack.stackTagCompound == null) {
			stack.setTagCompound(new NBTTagCompound());
		}
		
		stack.setItemDamage(meta);
		
		stack.stackTagCompound.setInteger("meta", meta);
	}
	
	public boolean onItemUse(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, World par3World, int par4, int par5, int par6, int par7, float par8, float par9, float par10)
    {
		if (par2EntityPlayer.isSneaking()) {
			return false;
		}
		
		if (par1ItemStack.getItemDamage() == 8) {
			return false;
		}
		
        int i1 = par3World.getBlockId(par4, par5, par6);

        if (i1 == Block.snow.blockID && (par3World.getBlockMetadata(par4, par5, par6) & 7) < 1)
        {
            par7 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(par3World, par4, par5, par6)))
        {
            if (par7 == 0)
            {
                --par5;
            }

            if (par7 == 1)
            {
                ++par5;
            }

            if (par7 == 2)
            {
                --par6;
            }

            if (par7 == 3)
            {
                ++par6;
            }

            if (par7 == 4)
            {
                --par4;
            }

            if (par7 == 5)
            {
                ++par4;
            }
        }

        if (par1ItemStack.stackSize == 0)
        {
            return false;
        }
        else if (!par2EntityPlayer.canPlayerEdit(par4, par5, par6, par7, par1ItemStack))
        {
            return false;
        }
        else if (par5 == 255 && Block.blocksList[this.blockID].blockMaterial.isSolid())
        {
            return false;
        }
        else if (par3World.canPlaceEntityOnSide(this.blockID, par4, par5, par6, false, par7, par2EntityPlayer, par1ItemStack))
        {
            Block block = Block.blocksList[this.blockID];
            int j1 = getCodeWriterMeta(par1ItemStack);
            int k1 = Block.blocksList[this.blockID].onBlockPlaced(par3World, par4, par5, par6, par7, par8, par9, par10, j1);

            if (placeBlockAt(par1ItemStack, par2EntityPlayer, par3World, par4, par5, par6, par7, par8, par9, par10, k1))
            {
                par3World.playSoundEffect((double)((float)par4 + 0.5F), (double)((float)par5 + 0.5F), (double)((float)par6 + 0.5F), block.stepSound.getPlaceSound(), (block.stepSound.getVolume() + 1.0F) / 2.0F, block.stepSound.getPitch() * 0.8F);
                par1ItemStack.damageItem(1, par2EntityPlayer);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @SideOnly(Side.CLIENT)
    public boolean canPlaceItemBlockOnSide(World par1World, int par2, int par3, int par4, int par5, EntityPlayer par6EntityPlayer, ItemStack par7ItemStack)
    {
        int i1 = par1World.getBlockId(par2, par3, par4);

        if (i1 == Block.snow.blockID)
        {
            par5 = 1;
        }
        else if (i1 != Block.vine.blockID && i1 != Block.tallGrass.blockID && i1 != Block.deadBush.blockID
                && (Block.blocksList[i1] == null || !Block.blocksList[i1].isBlockReplaceable(par1World, par2, par3, par4)))
        {
            if (par5 == 0)
            {
                --par3;
            }

            if (par5 == 1)
            {
                ++par3;
            }

            if (par5 == 2)
            {
                --par4;
            }

            if (par5 == 3)
            {
                ++par4;
            }

            if (par5 == 4)
            {
                --par2;
            }

            if (par5 == 5)
            {
                ++par2;
            }
        }

        return par1World.canPlaceEntityOnSide(this.blockID, par2, par3, par4, false, par5, (Entity)null, par7ItemStack);
    }
	
    public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata) {
       if (!world.setBlock(x, y, z, this.blockID, getCodeWriterMeta(stack), 3)) {
           return false;
       }

       if (world.getBlockId(x, y, z) == this.blockID) {
           Block.blocksList[this.blockID].onBlockPlacedBy(world, x, y, z, player, stack);
           Block.blocksList[this.blockID].onPostBlockPlaced(world, x, y, z, getCodeWriterMeta(stack));
       }

       return true;
    }
	
    @Override
    public void updateIcons(IconRegister register) {
    	textures = new Icon[9];
    	
    	for (int i=0; i<8; i++) {
    		textures[i] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":codewriter/cw_"+BlockCode.blockFileNames[i]);
    	}
    	
    	textures[8] = register.registerIcon(ModInfo.MOD_ID.toLowerCase()+":codewriter/cw_documentation");
    }
    
}
