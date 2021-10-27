
package net.mcreator.sussymod.block;

import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.World;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Identifier;
import net.minecraft.structure.rule.RuleTestType;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.loot.context.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.block.Material;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import net.mcreator.sussymod.SussymodMod;

import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;

import java.util.function.Predicate;
import java.util.Random;
import java.util.List;
import java.util.Collections;

public class SussyOreBlock extends Block {
	public SussyOreBlock() {
		super(FabricBlockSettings.of(Material.STONE).sounds(BlockSoundGroup.STONE).strength(3f, 5f).luminance(0)
				.breakByTool(FabricToolTags.PICKAXES, 2).requiresTool());
	}

	@Override
	public List<ItemStack> getDroppedStacks(BlockState state, LootContext.Builder builder) {
		List<ItemStack> dropsOriginal = super.getDroppedStacks(state, builder);
		if (!dropsOriginal.isEmpty())
			return dropsOriginal;
		return Collections.singletonList(new ItemStack(SussymodMod.Sussy_ITEM));
	}
	private static class CustomRuleTest extends RuleTest {
		static final CustomRuleTest INSTANCE = new CustomRuleTest();
		static final com.mojang.serialization.Codec<CustomRuleTest> codec = com.mojang.serialization.Codec.unit(() -> INSTANCE);
		public boolean test(BlockState blockAt, Random random) {
			boolean blockCriteria = false;
			if (blockAt.getBlock() == Blocks.STONE.getDefaultState().getBlock())
				blockCriteria = true;
			return blockCriteria;
		}

		protected RuleTestType<?> getType() {
			return Generation.CUSTOM_MATCH;
		}
	}

	public static class Generation {
		private static final RuleTestType<CustomRuleTest> CUSTOM_MATCH = Registry.register(Registry.RULE_TEST,
				new Identifier("sussymod", "sussy_ore_match"), () -> CustomRuleTest.codec);
		public static final Feature<OreFeatureConfig> feature = Registry.register(Registry.FEATURE, new Identifier("sussymod", "sussy_ore"),
				new OreFeature(OreFeatureConfig.CODEC) {
					@Override
					public boolean generate(StructureWorldAccess worldAccess, ChunkGenerator generator, Random rand, BlockPos pos,
							OreFeatureConfig config) {
						World world = worldAccess.toServerWorld();
						RegistryKey<World> dimensionType = world.getRegistryKey();
						boolean dimensionCriteria = false;
						if (dimensionType == World.OVERWORLD)
							dimensionCriteria = true;
						if (!dimensionCriteria)
							return false;
						return super.generate(worldAccess, generator, rand, pos, config);
					}
				});
		private static final ConfiguredFeature<?, ?> CONFIG_FEATURE = feature
				.configure(new OreFeatureConfig(CustomRuleTest.INSTANCE, SussymodMod.SussyOre_BLOCK.getDefaultState(), 7)).rangeOf(63)
				.spreadHorizontally().repeat(11);
		public static void init() {
			RegistryKey<ConfiguredFeature<?, ?>> configFeatKey = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
					new Identifier("sussymod", "sussy_ore"));
			Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, configFeatKey.getValue(), CONFIG_FEATURE);
			Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.all();
			BiomeModifications.addFeature(biomeSelector, GenerationStep.Feature.UNDERGROUND_ORES, configFeatKey);
		}
	}
}
