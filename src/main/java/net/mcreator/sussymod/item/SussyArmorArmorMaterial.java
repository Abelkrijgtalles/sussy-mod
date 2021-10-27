
package net.mcreator.sussymod.item;

import net.minecraft.util.Identifier;
import net.minecraft.sound.SoundEvent;
import net.minecraft.recipe.Ingredient;
import net.minecraft.item.Item;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ArmorItem;
import net.minecraft.entity.EquipmentSlot;

import net.mcreator.sussymod.SussymodMod;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

public class SussyArmorArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[]{13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[]{2, 5, 6, 2};
	public static final Item HELMET = new ArmorItem(new SussyArmorArmorMaterial(), EquipmentSlot.HEAD,
			new Item.Settings().group(SussyTabItemGroup.get()).fireproof());
	public static final Item CHESTPLATE = new ArmorItem(new SussyArmorArmorMaterial(), EquipmentSlot.CHEST,
			new Item.Settings().group(SussyTabItemGroup.get()).fireproof());
	public static final Item LEGGINGS = new ArmorItem(new SussyArmorArmorMaterial(), EquipmentSlot.LEGS,
			new Item.Settings().group(SussyTabItemGroup.get()).fireproof());
	public static final Item BOOTS = new ArmorItem(new SussyArmorArmorMaterial(), EquipmentSlot.FEET,
			new Item.Settings().group(SussyTabItemGroup.get()).fireproof());
	public int getDurability(EquipmentSlot equipmentSlot_1) {
		return BASE_DURABILITY[equipmentSlot_1.getEntitySlotId()] * 25;
	}

	public int getProtectionAmount(EquipmentSlot equipmentSlot_1) {
		return PROTECTION_VALUES[equipmentSlot_1.getEntitySlotId()];
	}

	public int getEnchantability() {
		return 65;
	}

	public SoundEvent getEquipSound() {
		return new SoundEvent(new Identifier("sussymod:sussysound"));
	}

	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(SussymodMod.Sussy_ITEM);
	}

	@Environment(EnvType.CLIENT)
	public String getName() {
		return "sussyarmortexture";
	}

	public float getToughness() {
		return 0F;
	}

	public float getKnockbackResistance() {
		return 0f;
	}
}
