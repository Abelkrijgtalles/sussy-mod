
package net.mcreator.sussymod.item;

import net.minecraft.world.World;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.Rarity;
import net.minecraft.util.Hand;
import net.minecraft.text.Text;
import net.minecraft.text.LiteralText;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.block.BlockState;

import net.mcreator.sussymod.procedures.SussyLivingEntityIsHitWithItemProcedure;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import com.google.common.collect.Multimap;
import com.google.common.collect.ImmutableMultimap;

public class SussyItem extends Item {
	public SussyItem() {
		super(new FabricItemSettings().group(SussyTabItemGroup.get()).maxCount(64).fireproof().rarity(Rarity.EPIC));
	}

	@Override
	public int getMaxUseTime(ItemStack itemstack) {
		return 0;
	}

	@Override
	public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
		return (float) (1F);
	}

	@Override
	public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
		if (slot == EquipmentSlot.MAINHAND) {
			return ImmutableMultimap.of(EntityAttributes.GENERIC_ATTACK_DAMAGE,
					new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "item_damage", (double) 14.1, EntityAttributeModifier.Operation.ADDITION),
					EntityAttributes.GENERIC_ATTACK_SPEED,
					new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "item_attack_speed", -2.4, EntityAttributeModifier.Operation.ADDITION));
		}
		return super.getAttributeModifiers(slot);
	}

	@Environment(EnvType.CLIENT)
	@Override
	public boolean hasGlint(ItemStack stack) {
		return true;
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
		tooltip.add(new LiteralText("AMONG US"));
	}

	@Override
	public int getEnchantability() {
		return 0;
	}

	@Override
	public TypedActionResult<ItemStack> use(World world, PlayerEntity entity, Hand hand) {
		TypedActionResult<ItemStack> retval = super.use(world, entity, hand);
		ItemStack itemstack = retval.getValue();
		double x = entity.getPos().getX();
		double y = entity.getPos().getY();
		double z = entity.getPos().getZ();
		{
			Map<String, Object> $_dependencies = new HashMap<>();
			$_dependencies.put("entity", entity);
			SussyLivingEntityIsHitWithItemProcedure.executeProcedure($_dependencies);
		}
		return super.use(world, entity, hand);
	}
}
