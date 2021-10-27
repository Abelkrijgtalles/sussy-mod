
package net.mcreator.sussymod.item;

import net.minecraft.util.Identifier;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import net.mcreator.sussymod.SussymodMod;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;

public final class SussyTabItemGroup {
	public static ItemGroup get() {
		return ITEM_GROUP;
	}
	private static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.create(new Identifier("sussymod", "sussy_tab")).icon(() -> {
		return new ItemStack(SussymodMod.Sussy_ITEM);
	}).build();
}
