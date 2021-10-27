/*
 *    MCreator note:
 *
 *    If you lock base mod element files, you can edit this file and the proxy files
 *    and they won't get overwritten. If you change your mod package or modid, you
 *    need to apply these changes to this file MANUALLY.
 *
 *
 *    If you do not lock base mod element files in Workspace settings, this file
 *    will be REGENERATED on each build.
 *
 */
package net.mcreator.sussymod;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.minecraft.util.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItem;
import net.minecraft.block.Block;

import net.mcreator.sussymod.item.SussyTabItemGroup;
import net.mcreator.sussymod.item.SussyItem;
import net.mcreator.sussymod.item.SussyArmorArmorMaterial;
import net.mcreator.sussymod.block.SussyOreBlock;
import net.mcreator.sussymod.block.SussyBlockBlock;

import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.api.ModInitializer;

public class SussymodMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final Identifier sussysound_ID = id("sussysound");
	public static final SoundEvent sussysoundEvent = new SoundEvent(sussysound_ID);
	public static final Item Sussy_ITEM = Registry.register(Registry.ITEM, id("sussy"), new SussyItem());
	public static final ItemGroup SussyTab = SussyTabItemGroup.get();
	public static final Block SussyOre_BLOCK = Registry.register(Registry.BLOCK, id("sussy_ore"), new SussyOreBlock());
	public static final BlockItem SussyOre_ITEM = Registry.register(Registry.ITEM, id("sussy_ore"),
			new BlockItem(SussyOre_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final Block SussyBlock_BLOCK = Registry.register(Registry.BLOCK, id("sussy_block"), new SussyBlockBlock());
	public static final BlockItem SussyBlock_ITEM = Registry.register(Registry.ITEM, id("sussy_block"),
			new BlockItem(SussyBlock_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
	public static final Item SussyArmor_HELMET = Registry.register(Registry.ITEM, id("sussy_armor_helmet"), SussyArmorArmorMaterial.HELMET);
	public static final Item SussyArmor_CHESTPLATE = Registry.register(Registry.ITEM, id("sussy_armor_chestplate"),
			SussyArmorArmorMaterial.CHESTPLATE);
	public static final Item SussyArmor_LEGGINGS = Registry.register(Registry.ITEM, id("sussy_armor_leggings"), SussyArmorArmorMaterial.LEGGINGS);
	public static final Item SussyArmor_BOOTS = Registry.register(Registry.ITEM, id("sussy_armor_boots"), SussyArmorArmorMaterial.BOOTS);
	@Override
	public void onInitialize() {
		LOGGER.info("Initializing SussymodMod");
		SussyOreBlock.Generation.init();
		Registry.register(Registry.SOUND_EVENT, SussymodMod.sussysound_ID, SussymodMod.sussysoundEvent);
		CommandRegistrationCallback.EVENT.register((dispatcher, dedicated) -> {
		});
	}

	public static final Identifier id(String s) {
		return new Identifier("sussymod", s);
	}
}
