package net.mcreator.sussymod.procedures;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.Entity;

import java.util.Map;

public class SussyLivingEntityIsHitWithItemProcedure {
	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			System.err.println("Failed to load dependency entity for procedure SussyLivingEntityIsHitWithItem!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		entity.damage(DamageSource.ON_FIRE, (float) 10000000000000000F);
	}
}
