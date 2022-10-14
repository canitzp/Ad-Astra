package com.github.alexnijjar.ad_astra.registry;

import com.github.alexnijjar.ad_astra.AdAstra;
import com.github.alexnijjar.ad_astra.blocks.machines.entity.SolarPanelBlockEntity;
import com.github.alexnijjar.ad_astra.items.*;
import com.github.alexnijjar.ad_astra.items.armour.JetSuit;
import com.github.alexnijjar.ad_astra.items.armour.NetheriteSpaceSuit;
import com.github.alexnijjar.ad_astra.items.armour.SpaceSuit;
import com.github.alexnijjar.ad_astra.items.vehicles.RocketItem;
import com.github.alexnijjar.ad_astra.items.vehicles.RoverItem;
import com.github.alexnijjar.ad_astra.util.ModIdentifier;
import dev.architectury.core.item.ArchitecturyBucketItem;
import dev.architectury.core.item.ArchitecturySpawnEggItem;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.block.Block;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.*;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Supplier;

@SuppressWarnings("unused")
public class ModItems {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(AdAstra.MOD_ID, Registry.ITEM_KEY);
	public static final Set<Item> items = new HashSet<>();

	// Vehicles Items
	public static final RegistrySupplier<Item> TIER_1_ROCKET = register("tier_1_rocket", () -> new RocketItem<>(ModEntityTypes.TIER_1_ROCKET.get(), 1, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1).fireproof()));
	public static final RegistrySupplier<Item> TIER_2_ROCKET = register("tier_2_rocket", () -> new RocketItem<>(ModEntityTypes.TIER_2_ROCKET.get(), 2, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1).fireproof()));
	public static final RegistrySupplier<Item> TIER_3_ROCKET = register("tier_3_rocket", () -> new RocketItem<>(ModEntityTypes.TIER_3_ROCKET.get(), 3, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1).fireproof()));
	public static final RegistrySupplier<Item> TIER_4_ROCKET = register("tier_4_rocket", () -> new RocketItem<>(ModEntityTypes.TIER_4_ROCKET.get(), 4, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1).fireproof()));
	public static final RegistrySupplier<Item> TIER_1_ROVER = register("tier_1_rover", () -> new RoverItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1).fireproof()));

	public static final RegistrySupplier<Item> OXYGEN_TANK = register("oxygen_tank", () -> new OxygenTankItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).maxCount(1)));

	public static final RegistrySupplier<Item> ASTRODUX = register("astrodux", () -> new AstroduxItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL)));

	public static final RegistrySupplier<Item> SPACE_PAINTING = register("space_painting", () -> new SpacePaintingItem(ModEntityTypes.SPACE_PAINTING.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).rarity(Rarity.UNCOMMON)));

	public static final RegistrySupplier<Item> CHEESE = register("cheese", () -> new Item(new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).food(new FoodComponent.Builder().hunger(4).saturationModifier(1.0f).build())));

	public static final RegistrySupplier<Item> LAUNCH_PAD = register("launch_pad", () -> new ModRenderedBlockItem(ModBlocks.LAUNCH_PAD.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_BLOCKS)));

	// Buckets
	public static final RegistrySupplier<Item> OIL_BUCKET = register("oil_bucket", () -> new ArchitecturyBucketItem(ModFluids.OIL_STILL, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).recipeRemainder(Items.BUCKET).maxCount(1)));
	public static final RegistrySupplier<Item> FUEL_BUCKET = register("fuel_bucket", () -> new ArchitecturyBucketItem(ModFluids.FUEL_STILL, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).recipeRemainder(Items.BUCKET).maxCount(1)));
	public static final RegistrySupplier<Item> CRYO_FUEL_BUCKET = register("cryo_fuel_bucket", () -> new ArchitecturyBucketItem(ModFluids.CRYO_FUEL_STILL, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).recipeRemainder(Items.BUCKET).maxCount(1)));
	public static final RegistrySupplier<Item> OXYGEN_BUCKET = register("oxygen_bucket", () -> new ArchitecturyBucketItem(ModFluids.OXYGEN_STILL, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).recipeRemainder(Items.BUCKET).maxCount(1)) {
		@Override
		public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
			ItemStack itemStack = user.getStackInHand(hand);
			return TypedActionResult.fail(itemStack);
		}
	});

	// Spacesuit
	public static final RegistrySupplier<SpaceSuit> SPACE_HELMET = register("space_helmet", () -> new SpaceSuit(ModArmour.SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL)));
	public static final RegistrySupplier<SpaceSuit> SPACE_SUIT = register("space_suit", () -> new SpaceSuit(ModArmour.SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL)));
	public static final RegistrySupplier<SpaceSuit> SPACE_PANTS = register("space_pants", () -> new SpaceSuit(ModArmour.SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL)));
	public static final RegistrySupplier<SpaceSuit> SPACE_BOOTS = register("space_boots", () -> new SpaceSuit(ModArmour.SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL)));

	// Netherite Spacesuit
	public static final RegistrySupplier<NetheriteSpaceSuit> NETHERITE_SPACE_HELMET = register("netherite_space_helmet", () -> new NetheriteSpaceSuit(ModArmour.NETHERITE_SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<NetheriteSpaceSuit> NETHERITE_SPACE_SUIT = register("netherite_space_suit", () -> new NetheriteSpaceSuit(ModArmour.NETHERITE_SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<NetheriteSpaceSuit> NETHERITE_SPACE_PANTS = register("netherite_space_pants", () -> new NetheriteSpaceSuit(ModArmour.NETHERITE_SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<NetheriteSpaceSuit> NETHERITE_SPACE_BOOTS = register("netherite_space_boots", () -> new NetheriteSpaceSuit(ModArmour.NETHERITE_SPACE_SUIT_ARMOUR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));

	// Jet Suit
	public static final RegistrySupplier<JetSuit> JET_SUIT_HELMET = register("jet_suit_helmet", () -> new JetSuit(ModArmour.JET_SUIT_ARMOUR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<JetSuit> JET_SUIT = register("jet_suit", () -> new JetSuit(ModArmour.JET_SUIT_ARMOUR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<JetSuit> JET_SUIT_PANTS = register("jet_suit_pants", () -> new JetSuit(ModArmour.JET_SUIT_ARMOUR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));
	public static final RegistrySupplier<JetSuit> JET_SUIT_BOOTS = register("jet_suit_boots", () -> new JetSuit(ModArmour.JET_SUIT_ARMOUR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().group(ModItemGroups.ITEM_GROUP_NORMAL).fireproof()));

	// Machines
	public static final RegistrySupplier<Item> COAL_GENERATOR = register("coal_generator", () -> new MachineBlockItem(ModBlocks.COAL_GENERATOR.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			tooltip.add((Text.translatable("item.ad_astra.generator_energy.tooltip", AdAstra.CONFIG.coalGenerator.energyPerTick).setStyle(Style.EMPTY.withColor(Formatting.BLUE))));
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.coal_generator.tooltip").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> COMPRESSOR = register("compressor", () -> new MachineBlockItem(ModBlocks.COMPRESSOR.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.compressor.tooltip").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> NASA_WORKBENCH = register("nasa_workbench", () -> new MachineBlockItem(ModBlocks.NASA_WORKBENCH.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.nasa_workbench.tooltip").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> FUEL_REFINERY = register("fuel_refinery", () -> new MachineBlockItem(ModBlocks.FUEL_REFINERY.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.fuel_refinery.tooltip").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> OXYGEN_LOADER = register("oxygen_loader", () -> new MachineBlockItem(ModBlocks.OXYGEN_LOADER.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.oxygen_loader.tooltip[0]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
					tooltip.add((Text.translatable("item.ad_astra.oxygen_loader.tooltip[1]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> SOLAR_PANEL = register("solar_panel", () -> new SolarPanelBlockItem(ModBlocks.SOLAR_PANEL.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			tooltip.add((Text.translatable("item.ad_astra.generator_energy.tooltip", SolarPanelBlockEntity.getEnergyForDimension(world)).setStyle(Style.EMPTY.withColor(Formatting.BLUE))));
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.solar_panel.tooltip[0]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
					tooltip.add((Text.translatable("item.ad_astra.solar_panel.tooltip[1]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> OXYGEN_DISTRIBUTOR = register("oxygen_distributor", () -> new MachineBlockItem(ModBlocks.OXYGEN_DISTRIBUTOR.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.oxygen_distributor.tooltip[0]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
					tooltip.add((Text.translatable("item.ad_astra.oxygen_distributor.tooltip[1]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> WATER_PUMP = register("water_pump", () -> new MachineBlockItem(ModBlocks.WATER_PUMP.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			tooltip.add((Text.translatable("item.ad_astra.fluid_transfer_rate.tooltip", AdAstra.CONFIG.waterPump.transferPerTick)).setStyle(Style.EMPTY.withColor(Formatting.BLUE)));
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.water_pump.tooltip[0]", AdAstra.CONFIG.waterPump.transferPerTick)).setStyle(Style.EMPTY.withColor(Formatting.GREEN)));
					tooltip.add((Text.translatable("item.ad_astra.water_pump.tooltip[1]", AdAstra.CONFIG.waterPump.transferPerTick)).setStyle(Style.EMPTY.withColor(Formatting.GREEN)));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> ENERGIZER = register("energizer", () -> new EnergizerBlockItem(ModBlocks.ENERGIZER.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES).maxCount(1)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			super.appendTooltip(stack, world, tooltip, context);
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.energizer.tooltip[0]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
					tooltip.add((Text.translatable("item.ad_astra.energizer.tooltip[1]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> CRYO_FREEZER = register("cryo_freezer", () -> new MachineBlockItem(ModBlocks.CRYO_FREEZER.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES).maxCount(1)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.cryo_freezer.tooltip").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});
	public static final RegistrySupplier<Item> OXYGEN_SENSOR = register("oxygen_sensor", () -> new MachineBlockItem(ModBlocks.OXYGEN_SENSOR.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES).maxCount(1)) {
		@Override
		public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
			if (world != null && world.isClient) {
				if (Screen.hasShiftDown()) {
					tooltip.add((Text.translatable("item.ad_astra.oxygen_sensor.tooltip[0]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
					tooltip.add((Text.translatable("item.ad_astra.oxygen_sensor.tooltip[1]").setStyle(Style.EMPTY.withColor(Formatting.GREEN))));
				} else {
					tooltip.add((Text.translatable("tooltip.ad_astra.hold_shift").setStyle(Style.EMPTY.withColor(Formatting.GRAY))));
				}
			}
		}
	});

	public static final RegistrySupplier<Item> WRENCH = register("wrench", () -> new WrenchItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES).maxCount(1)));
	public static final RegistrySupplier<Item> HAMMER = register("hammer", () -> new HammerItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_BASICS).maxCount(1).maxDamage(AdAstra.CONFIG.general.hammerDurability)));

	public static final RegistrySupplier<Item> IRON_ROD = registerItem("iron_rod", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> OXYGEN_GEAR = registerItem("oxygen_gear", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> WHEEL = registerItem("wheel", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> ENGINE_FRAME = registerItem("engine_frame", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> ENGINE_FAN = registerItem("engine_fan", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> ROCKET_NOSE_CONE = registerItem("rocket_nose_cone", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> STEEL_ENGINE = registerItem("steel_engine", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> DESH_ENGINE = registerItem("desh_engine", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> OSTRUM_ENGINE = registerItem("ostrum_engine", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> CALORITE_ENGINE = registerItem("calorite_engine", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> STEEL_TANK = registerItem("steel_tank", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> DESH_TANK = registerItem("desh_tank", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> OSTRUM_TANK = registerItem("ostrum_tank", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> CALORITE_TANK = registerItem("calorite_tank", ModItemGroups.ITEM_GROUP_BASICS);
	public static final RegistrySupplier<Item> ROCKET_FIN = registerItem("rocket_fin", ModItemGroups.ITEM_GROUP_BASICS);

	// Torch items
	public static final RegistrySupplier<Item> EXTINGUISHED_TORCH = register("extinguished_torch", () -> new WallStandingBlockItem(ModBlocks.EXTINGUISHED_TORCH.get(), ModBlocks.WALL_EXTINGUISHED_TORCH.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_BASICS)));
	public static final RegistrySupplier<Item> EXTINGUISHED_LANTERN = registerBlockItem("extinguished_lantern", ModBlocks.EXTINGUISHED_LANTERN, ModItemGroups.ITEM_GROUP_BASICS);

	public static final RegistrySupplier<Item> STEEL_INGOT = registerItem("steel_ingot", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> DESH_INGOT = registerItem("desh_ingot", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> OSTRUM_INGOT = registerItem("ostrum_ingot", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> CALORITE_INGOT = registerItem("calorite_ingot", ModItemGroups.ITEM_GROUP_MATERIALS);

	public static final RegistrySupplier<Item> ICE_SHARD = registerItem("ice_shard", ModItemGroups.ITEM_GROUP_MATERIALS);

	public static final RegistrySupplier<Item> IRON_PLATE = registerItem("iron_plate", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> STEEL_PLATE = registerItem("steel_plate", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> DESH_PLATE = registerItem("desh_plate", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> OSTRUM_PLATE = registerItem("ostrum_plate", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> CALORITE_PLATE = registerItem("calorite_plate", ModItemGroups.ITEM_GROUP_MATERIALS);

	public static final RegistrySupplier<Item> STEEL_NUGGET = registerItem("steel_nugget", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> DESH_NUGGET = registerItem("desh_nugget", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> OSTRUM_NUGGET = registerItem("ostrum_nugget", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> CALORITE_NUGGET = registerItem("calorite_nugget", ModItemGroups.ITEM_GROUP_MATERIALS);

	public static final RegistrySupplier<Item> RAW_DESH = registerItem("raw_desh", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> RAW_OSTRUM = registerItem("raw_ostrum", ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> RAW_CALORITE = registerItem("raw_calorite", ModItemGroups.ITEM_GROUP_MATERIALS);

	public static final RegistrySupplier<Item> STROPHAR_MUSHROOM = registerBlockItem("strophar_mushroom", ModBlocks.STROPHAR_MUSHROOM, ModItemGroups.ITEM_GROUP_MATERIALS);
	public static final RegistrySupplier<Item> AERONOS_MUSHROOM = registerBlockItem("aeronos_mushroom", ModBlocks.AERONOS_MUSHROOM, ModItemGroups.ITEM_GROUP_MATERIALS);

	// Flags
	public static final RegistrySupplier<Item> WHITE_FLAG = registerFlag("white_flag", ModBlocks.WHITE_FLAG);
	public static final RegistrySupplier<Item> ORANGE_FLAG = registerFlag("orange_flag", ModBlocks.ORANGE_FLAG);
	public static final RegistrySupplier<Item> MAGENTA_FLAG = registerFlag("magenta_flag", ModBlocks.MAGENTA_FLAG);
	public static final RegistrySupplier<Item> LIGHT_BLUE_FLAG = registerFlag("light_blue_flag", ModBlocks.LIGHT_BLUE_FLAG);
	public static final RegistrySupplier<Item> YELLOW_FLAG = registerFlag("yellow_flag", ModBlocks.YELLOW_FLAG);
	public static final RegistrySupplier<Item> LIME_FLAG = registerFlag("lime_flag", ModBlocks.LIME_FLAG);
	public static final RegistrySupplier<Item> PINK_FLAG = registerFlag("pink_flag", ModBlocks.PINK_FLAG);
	public static final RegistrySupplier<Item> GRAY_FLAG = registerFlag("gray_flag", ModBlocks.GRAY_FLAG);
	public static final RegistrySupplier<Item> LIGHT_GRAY_FLAG = registerFlag("light_gray_flag", ModBlocks.LIGHT_GRAY_FLAG);
	public static final RegistrySupplier<Item> CYAN_FLAG = registerFlag("cyan_flag", ModBlocks.CYAN_FLAG);
	public static final RegistrySupplier<Item> PURPLE_FLAG = registerFlag("purple_flag", ModBlocks.PURPLE_FLAG);
	public static final RegistrySupplier<Item> BLUE_FLAG = registerFlag("blue_flag", ModBlocks.BLUE_FLAG);
	public static final RegistrySupplier<Item> BROWN_FLAG = registerFlag("brown_flag", ModBlocks.BROWN_FLAG);
	public static final RegistrySupplier<Item> GREEN_FLAG = registerFlag("green_flag", ModBlocks.GREEN_FLAG);
	public static final RegistrySupplier<Item> RED_FLAG = registerFlag("red_flag", ModBlocks.RED_FLAG);
	public static final RegistrySupplier<Item> BLACK_FLAG = registerFlag("black_flag", ModBlocks.BLACK_FLAG);

	// Globes
	public static final RegistrySupplier<Item> EARTH_GLOBE = registerGlobe("earth_globe", ModBlocks.EARTH_GLOBE);
	public static final RegistrySupplier<Item> MOON_GLOBE = registerGlobe("moon_globe", ModBlocks.MOON_GLOBE);
	public static final RegistrySupplier<Item> MARS_GLOBE = registerGlobe("mars_globe", ModBlocks.MARS_GLOBE);
	public static final RegistrySupplier<Item> MERCURY_GLOBE = registerGlobe("mercury_globe", ModBlocks.MERCURY_GLOBE);
	public static final RegistrySupplier<Item> VENUS_GLOBE = registerGlobe("venus_globe", ModBlocks.VENUS_GLOBE);
	public static final RegistrySupplier<Item> GLACIO_GLOBE = registerGlobe("glacio_globe", ModBlocks.GLACIO_GLOBE);

	// Cables
	public static final RegistrySupplier<Item> STEEL_CABLE = register("steel_cable", () -> new BlockItem(ModBlocks.STEEL_CABLE.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)));
	public static final RegistrySupplier<Item> DESH_CABLE = register("desh_cable", () -> new BlockItem(ModBlocks.DESH_CABLE.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)));

	// Fluid pipes
	public static final RegistrySupplier<Item> DESH_FLUID_PIPE = register("desh_fluid_pipe", () -> new BlockItem(ModBlocks.DESH_FLUID_PIPE.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)));
	public static final RegistrySupplier<Item> OSTRUM_FLUID_PIPE = register("ostrum_fluid_pipe", () -> new BlockItem(ModBlocks.OSTRUM_FLUID_PIPE.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_MACHINES)));

	// Blocks
	// Iron
	public static final RegistrySupplier<Item> IRON_PLATING = registerBlockItem("iron_plating", ModBlocks.IRON_PLATING);
	public static final RegistrySupplier<Item> IRON_PLATING_STAIRS = registerBlockItem("iron_plating_stairs", ModBlocks.IRON_PLATING_STAIRS);
	public static final RegistrySupplier<Item> IRON_PLATING_SLAB = registerBlockItem("iron_plating_slab", ModBlocks.IRON_PLATING_SLAB);
	public static final RegistrySupplier<Item> IRON_PILLAR = registerBlockItem("iron_pillar", ModBlocks.IRON_PILLAR);
	public static final RegistrySupplier<Item> IRON_PLATING_BUTTON = registerBlockItem("iron_plating_button", ModBlocks.IRON_PLATING_BUTTON);
	public static final RegistrySupplier<Item> IRON_PLATING_PRESSURE_PLATE = registerBlockItem("iron_plating_pressure_plate", ModBlocks.IRON_PLATING_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> MARKED_IRON_PILLAR = registerBlockItem("marked_iron_pillar", ModBlocks.MARKED_IRON_PILLAR);
	public static final RegistrySupplier<Item> GLOWING_IRON_PILLAR = registerBlockItem("glowing_iron_pillar", ModBlocks.GLOWING_IRON_PILLAR);
	public static final RegistrySupplier<Item> IRON_SLIDING_DOOR = registerBlockItem("iron_sliding_door", ModBlocks.IRON_SLIDING_DOOR);

	// Steel
	public static final RegistrySupplier<Item> STEEL_BLOCK = registerBlockItem("steel_block", ModBlocks.STEEL_BLOCK);
	public static final RegistrySupplier<Item> STEEL_PLATING = registerBlockItem("steel_plating", ModBlocks.STEEL_PLATING);
	public static final RegistrySupplier<Item> STEEL_PLATING_STAIRS = registerBlockItem("steel_plating_stairs", ModBlocks.STEEL_PLATING_STAIRS);
	public static final RegistrySupplier<Item> STEEL_PLATING_SLAB = registerBlockItem("steel_plating_slab", ModBlocks.STEEL_PLATING_SLAB);
	public static final RegistrySupplier<Item> STEEL_PILLAR = registerBlockItem("steel_pillar", ModBlocks.STEEL_PILLAR);
	public static final RegistrySupplier<Item> STEEL_PLATING_BUTTON = registerBlockItem("steel_plating_button", ModBlocks.STEEL_PLATING_BUTTON);
	public static final RegistrySupplier<Item> STEEL_PLATING_PRESSURE_PLATE = registerBlockItem("steel_plating_pressure_plate", ModBlocks.STEEL_PLATING_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> GLOWING_STEEL_PILLAR = registerBlockItem("glowing_steel_pillar", ModBlocks.GLOWING_STEEL_PILLAR);
	public static final RegistrySupplier<Item> STEEL_SLIDING_DOOR = registerBlockItem("steel_sliding_door", ModBlocks.STEEL_SLIDING_DOOR);
	public static final RegistrySupplier<Item> AIRLOCK = registerBlockItem("airlock", ModBlocks.AIRLOCK);
	public static final RegistrySupplier<Item> REINFORCED_DOOR = registerBlockItem("reinforced_door", ModBlocks.REINFORCED_DOOR);
	public static final RegistrySupplier<Item> STEEL_DOOR = registerBlockItem("steel_door", ModBlocks.STEEL_DOOR);
	public static final RegistrySupplier<Item> STEEL_TRAPDOOR = registerBlockItem("steel_trapdoor", ModBlocks.STEEL_TRAPDOOR);

	// Moon
	public static final RegistrySupplier<Item> CHEESE_BLOCK = registerBlockItem("cheese_block", ModBlocks.CHEESE_BLOCK);
	public static final RegistrySupplier<Item> DESH_BLOCK = registerBlockItem("desh_block", ModBlocks.DESH_BLOCK);
	public static final RegistrySupplier<Item> RAW_DESH_BLOCK = registerBlockItem("raw_desh_block", ModBlocks.RAW_DESH_BLOCK);
	public static final RegistrySupplier<Item> DESH_PLATING = registerBlockItem("desh_plating", ModBlocks.DESH_PLATING);
	public static final RegistrySupplier<Item> DESH_PLATING_STAIRS = registerBlockItem("desh_plating_stairs", ModBlocks.DESH_PLATING_STAIRS);
	public static final RegistrySupplier<Item> DESH_PLATING_SLAB = registerBlockItem("desh_plating_slab", ModBlocks.DESH_PLATING_SLAB);
	public static final RegistrySupplier<Item> DESH_PILLAR = registerBlockItem("desh_pillar", ModBlocks.DESH_PILLAR);
	public static final RegistrySupplier<Item> DESH_PLATING_BUTTON = registerBlockItem("desh_plating_button", ModBlocks.DESH_PLATING_BUTTON);
	public static final RegistrySupplier<Item> DESH_PLATING_PRESSURE_PLATE = registerBlockItem("desh_plating_pressure_plate", ModBlocks.DESH_PLATING_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> GLOWING_DESH_PILLAR = registerBlockItem("glowing_desh_pillar", ModBlocks.GLOWING_DESH_PILLAR);
	public static final RegistrySupplier<Item> DESH_SLIDING_DOOR = registerBlockItem("desh_sliding_door", ModBlocks.DESH_SLIDING_DOOR);

	// Mars
	public static final RegistrySupplier<Item> OSTRUM_BLOCK = registerBlockItem("ostrum_block", ModBlocks.OSTRUM_BLOCK);
	public static final RegistrySupplier<Item> RAW_OSTRUM_BLOCK = registerBlockItem("raw_ostrum_block", ModBlocks.RAW_OSTRUM_BLOCK);
	public static final RegistrySupplier<Item> OSTRUM_PLATING = registerBlockItem("ostrum_plating", ModBlocks.OSTRUM_PLATING);
	public static final RegistrySupplier<Item> OSTRUM_PLATING_STAIRS = registerBlockItem("ostrum_plating_stairs", ModBlocks.OSTRUM_PLATING_STAIRS);
	public static final RegistrySupplier<Item> OSTRUM_PLATING_SLAB = registerBlockItem("ostrum_plating_slab", ModBlocks.OSTRUM_PLATING_SLAB);
	public static final RegistrySupplier<Item> OSTRUM_PILLAR = registerBlockItem("ostrum_pillar", ModBlocks.OSTRUM_PILLAR);
	public static final RegistrySupplier<Item> OSTRUM_PLATING_BUTTON = registerBlockItem("ostrum_plating_button", ModBlocks.OSTRUM_PLATING_BUTTON);
	public static final RegistrySupplier<Item> OSTRUM_PLATING_PRESSURE_PLATE = registerBlockItem("ostrum_plating_pressure_plate", ModBlocks.OSTRUM_PLATING_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> GLOWING_OSTRUM_PILLAR = registerBlockItem("glowing_ostrum_pillar", ModBlocks.GLOWING_OSTRUM_PILLAR);
	public static final RegistrySupplier<Item> OSTRUM_SLIDING_DOOR = registerBlockItem("ostrum_sliding_door", ModBlocks.OSTRUM_SLIDING_DOOR);

	// Venus
	public static final RegistrySupplier<Item> CALORITE_BLOCK = registerBlockItem("calorite_block", ModBlocks.CALORITE_BLOCK);
	public static final RegistrySupplier<Item> RAW_CALORITE_BLOCK = registerBlockItem("raw_calorite_block", ModBlocks.RAW_CALORITE_BLOCK);
	public static final RegistrySupplier<Item> CALORITE_PLATING = registerBlockItem("calorite_plating", ModBlocks.CALORITE_PLATING);
	public static final RegistrySupplier<Item> CALORITE_PLATING_STAIRS = registerBlockItem("calorite_plating_stairs", ModBlocks.CALORITE_PLATING_STAIRS);
	public static final RegistrySupplier<Item> CALORITE_PLATING_SLAB = registerBlockItem("calorite_plating_slab", ModBlocks.CALORITE_PLATING_SLAB);
	public static final RegistrySupplier<Item> CALORITE_PILLAR = registerBlockItem("calorite_pillar", ModBlocks.CALORITE_PILLAR);
	public static final RegistrySupplier<Item> CALORITE_PLATING_BUTTON = registerBlockItem("calorite_plating_button", ModBlocks.CALORITE_PLATING_BUTTON);
	public static final RegistrySupplier<Item> CALORITE_PLATING_PRESSURE_PLATE = registerBlockItem("calorite_plating_pressure_plate", ModBlocks.CALORITE_PLATING_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> GLOWING_CALORITE_PILLAR = registerBlockItem("glowing_calorite_pillar", ModBlocks.GLOWING_CALORITE_PILLAR);
	public static final RegistrySupplier<Item> CALORITE_SLIDING_DOOR = registerBlockItem("calorite_sliding_door", ModBlocks.CALORITE_SLIDING_DOOR);

	// Earth blocks
	public static final RegistrySupplier<Item> SKY_STONE = registerBlockItem("sky_stone", ModBlocks.SKY_STONE);

	// Moon blocks
	public static final RegistrySupplier<Item> MOON_SAND = registerBlockItem("moon_sand", ModBlocks.MOON_SAND);
	public static final RegistrySupplier<Item> MOON_STONE = registerBlockItem("moon_stone", ModBlocks.MOON_STONE);
	public static final RegistrySupplier<Item> MOON_STONE_STAIRS = registerBlockItem("moon_stone_stairs", ModBlocks.MOON_STONE_STAIRS);
	public static final RegistrySupplier<Item> MOON_STONE_SLAB = registerBlockItem("moon_stone_slab", ModBlocks.MOON_STONE_SLAB);
	public static final RegistrySupplier<Item> MOON_COBBLESTONE = registerBlockItem("moon_cobblestone", ModBlocks.MOON_COBBLESTONE);
	public static final RegistrySupplier<Item> MOON_COBBLESTONE_STAIRS = registerBlockItem("moon_cobblestone_stairs", ModBlocks.MOON_COBBLESTONE_STAIRS);
	public static final RegistrySupplier<Item> MOON_COBBLESTONE_SLAB = registerBlockItem("moon_cobblestone_slab", ModBlocks.MOON_COBBLESTONE_SLAB);
	public static final RegistrySupplier<Item> MOON_STONE_BRICKS = registerBlockItem("moon_stone_bricks", ModBlocks.MOON_STONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_MOON_STONE_BRICKS = registerBlockItem("cracked_moon_stone_bricks", ModBlocks.CRACKED_MOON_STONE_BRICKS);
	public static final RegistrySupplier<Item> MOON_STONE_BRICK_SLAB = registerBlockItem("moon_stone_brick_slab", ModBlocks.MOON_STONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> MOON_STONE_BRICK_STAIRS = registerBlockItem("moon_stone_brick_stairs", ModBlocks.MOON_STONE_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MOON_STONE_BRICKS = registerBlockItem("chiseled_moon_stone_bricks", ModBlocks.CHISELED_MOON_STONE_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_MOON_STONE_STAIRS = registerBlockItem("chiseled_moon_stone_stairs", ModBlocks.CHISELED_MOON_STONE_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MOON_STONE_SLAB = registerBlockItem("chiseled_moon_stone_slab", ModBlocks.CHISELED_MOON_STONE_SLAB);
	public static final RegistrySupplier<Item> POLISHED_MOON_STONE = registerBlockItem("polished_moon_stone", ModBlocks.POLISHED_MOON_STONE);
	public static final RegistrySupplier<Item> POLISHED_MOON_STONE_STAIRS = registerBlockItem("polished_moon_stone_stairs", ModBlocks.POLISHED_MOON_STONE_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_MOON_STONE_SLAB = registerBlockItem("polished_moon_stone_slab", ModBlocks.POLISHED_MOON_STONE_SLAB);
	public static final RegistrySupplier<Item> MOON_PILLAR = registerBlockItem("moon_pillar", ModBlocks.MOON_PILLAR);
	public static final RegistrySupplier<Item> MOON_STONE_BRICK_WALL = registerBlockItem("moon_stone_brick_wall", ModBlocks.MOON_STONE_BRICK_WALL);

	public static final RegistrySupplier<Item> MOON_CHEESE_ORE = registerBlockItem("moon_cheese_ore", ModBlocks.MOON_CHEESE_ORE);
	public static final RegistrySupplier<Item> MOON_DESH_ORE = registerBlockItem("moon_desh_ore", ModBlocks.MOON_DESH_ORE);
	public static final RegistrySupplier<Item> DEEPSLATE_DESH_ORE = registerBlockItem("deepslate_desh_ore", ModBlocks.DEEPSLATE_DESH_ORE);
	public static final RegistrySupplier<Item> MOON_IRON_ORE = registerBlockItem("moon_iron_ore", ModBlocks.MOON_IRON_ORE);
	public static final RegistrySupplier<Item> MOON_ICE_SHARD_ORE = registerBlockItem("moon_ice_shard_ore", ModBlocks.MOON_ICE_SHARD_ORE);

	public static final RegistrySupplier<Item> STROPHAR_CAP = registerBlockItem("strophar_cap", ModBlocks.STROPHAR_CAP);
	public static final RegistrySupplier<Item> STROPHAR_DOOR = registerBlockItem("strophar_door", ModBlocks.STROPHAR_DOOR);
	public static final RegistrySupplier<Item> STROPHAR_TRAPDOOR = registerBlockItem("strophar_trapdoor", ModBlocks.STROPHAR_TRAPDOOR);
	public static final RegistrySupplier<Item> STROPHAR_PLANKS = registerBlockItem("strophar_planks", ModBlocks.STROPHAR_PLANKS);
	public static final RegistrySupplier<Item> STROPHAR_STAIRS = registerBlockItem("strophar_stairs", ModBlocks.STROPHAR_STAIRS);
	public static final RegistrySupplier<Item> STROPHAR_SLAB = registerBlockItem("strophar_slab", ModBlocks.STROPHAR_SLAB);
	public static final RegistrySupplier<Item> STROPHAR_FENCE = registerBlockItem("strophar_fence", ModBlocks.STROPHAR_FENCE);
	public static final RegistrySupplier<Item> STROPHAR_FENCE_GATE = registerBlockItem("strophar_fence_gate", ModBlocks.STROPHAR_FENCE_GATE);
	public static final RegistrySupplier<Item> STROPHAR_STEM = registerBlockItem("strophar_stem", ModBlocks.STROPHAR_STEM);
	public static final RegistrySupplier<Item> STROPHAR_CHEST = register("strophar_chest", () -> new ModRenderedBlockItem(ModBlocks.STROPHAR_CHEST.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_BLOCKS)));
	public static final RegistrySupplier<Item> STROPHAR_LADDER = registerBlockItem("strophar_ladder", ModBlocks.STROPHAR_LADDER);

	public static final RegistrySupplier<Item> AERONOS_CAP = registerBlockItem("aeronos_cap", ModBlocks.AERONOS_CAP);
	public static final RegistrySupplier<Item> AERONOS_DOOR = registerBlockItem("aeronos_door", ModBlocks.AERONOS_DOOR);
	public static final RegistrySupplier<Item> AERONOS_TRAPDOOR = registerBlockItem("aeronos_trapdoor", ModBlocks.AERONOS_TRAPDOOR);
	public static final RegistrySupplier<Item> AERONOS_PLANKS = registerBlockItem("aeronos_planks", ModBlocks.AERONOS_PLANKS);
	public static final RegistrySupplier<Item> AERONOS_STAIRS = registerBlockItem("aeronos_stairs", ModBlocks.AERONOS_STAIRS);
	public static final RegistrySupplier<Item> AERONOS_SLAB = registerBlockItem("aeronos_slab", ModBlocks.AERONOS_SLAB);
	public static final RegistrySupplier<Item> AERONOS_FENCE = registerBlockItem("aeronos_fence", ModBlocks.AERONOS_FENCE);
	public static final RegistrySupplier<Item> AERONOS_FENCE_GATE = registerBlockItem("aeronos_fence_gate", ModBlocks.AERONOS_FENCE_GATE);
	public static final RegistrySupplier<Item> AERONOS_STEM = registerBlockItem("aeronos_stem", ModBlocks.AERONOS_STEM);
	public static final RegistrySupplier<Item> AERONOS_CHEST = register("aeronos_chest", () -> new ModRenderedBlockItem(ModBlocks.AERONOS_CHEST.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_BLOCKS)));
	public static final RegistrySupplier<Item> AERONOS_LADDER = registerBlockItem("aeronos_ladder", ModBlocks.AERONOS_LADDER);

	// Mars blocks
	public static final RegistrySupplier<Item> MARS_SAND = registerBlockItem("mars_sand", ModBlocks.MARS_SAND);
	public static final RegistrySupplier<Item> MARS_STONE = registerBlockItem("mars_stone", ModBlocks.MARS_STONE);
	public static final RegistrySupplier<Item> MARS_STONE_STAIRS = registerBlockItem("mars_stone_stairs", ModBlocks.MARS_STONE_STAIRS);
	public static final RegistrySupplier<Item> MARS_STONE_SLAB = registerBlockItem("mars_stone_slab", ModBlocks.MARS_STONE_SLAB);
	public static final RegistrySupplier<Item> MARS_COBBLESTONE = registerBlockItem("mars_cobblestone", ModBlocks.MARS_COBBLESTONE);
	public static final RegistrySupplier<Item> MARS_COBBLESTONE_STAIRS = registerBlockItem("mars_cobblestone_stairs", ModBlocks.MARS_COBBLESTONE_STAIRS);
	public static final RegistrySupplier<Item> MARS_COBBLESTONE_SLAB = registerBlockItem("mars_cobblestone_slab", ModBlocks.MARS_COBBLESTONE_SLAB);
	public static final RegistrySupplier<Item> MARS_STONE_BRICKS = registerBlockItem("mars_stone_bricks", ModBlocks.MARS_STONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_MARS_STONE_BRICKS = registerBlockItem("cracked_mars_stone_bricks", ModBlocks.CRACKED_MARS_STONE_BRICKS);
	public static final RegistrySupplier<Item> MARS_STONE_BRICK_SLAB = registerBlockItem("mars_stone_brick_slab", ModBlocks.MARS_STONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> MARS_STONE_BRICK_STAIRS = registerBlockItem("mars_stone_brick_stairs", ModBlocks.MARS_STONE_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MARS_STONE_BRICKS = registerBlockItem("chiseled_mars_stone_bricks", ModBlocks.CHISELED_MARS_STONE_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_MARS_STONE_STAIRS = registerBlockItem("chiseled_mars_stone_stairs", ModBlocks.CHISELED_MARS_STONE_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MARS_STONE_SLAB = registerBlockItem("chiseled_mars_stone_slab", ModBlocks.CHISELED_MARS_STONE_SLAB);
	public static final RegistrySupplier<Item> POLISHED_MARS_STONE = registerBlockItem("polished_mars_stone", ModBlocks.POLISHED_MARS_STONE);
	public static final RegistrySupplier<Item> POLISHED_MARS_STONE_STAIRS = registerBlockItem("polished_mars_stone_stairs", ModBlocks.POLISHED_MARS_STONE_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_MARS_STONE_SLAB = registerBlockItem("polished_mars_stone_slab", ModBlocks.POLISHED_MARS_STONE_SLAB);
	public static final RegistrySupplier<Item> MARS_PILLAR = registerBlockItem("mars_pillar", ModBlocks.MARS_PILLAR);
	public static final RegistrySupplier<Item> MARS_STONE_BRICK_WALL = registerBlockItem("mars_stone_brick_wall", ModBlocks.MARS_STONE_BRICK_WALL);

	public static final RegistrySupplier<Item> CONGLOMERATE = registerBlockItem("conglomerate", ModBlocks.CONGLOMERATE);
	public static final RegistrySupplier<Item> POLISHED_CONGLOMERATE = registerBlockItem("polished_conglomerate", ModBlocks.POLISHED_CONGLOMERATE);

	public static final RegistrySupplier<Item> DEEPSLATE_ICE_SHARD_ORE = registerBlockItem("deepslate_ice_shard_ore", ModBlocks.DEEPSLATE_ICE_SHARD_ORE);
	public static final RegistrySupplier<Item> MARS_IRON_ORE = registerBlockItem("mars_iron_ore", ModBlocks.MARS_IRON_ORE);
	public static final RegistrySupplier<Item> MARS_DIAMOND_ORE = registerBlockItem("mars_diamond_ore", ModBlocks.MARS_DIAMOND_ORE);
	public static final RegistrySupplier<Item> MARS_OSTRUM_ORE = registerBlockItem("mars_ostrum_ore", ModBlocks.MARS_OSTRUM_ORE);
	public static final RegistrySupplier<Item> DEEPSLATE_OSTRUM_ORE = registerBlockItem("deepslate_ostrum_ore", ModBlocks.DEEPSLATE_OSTRUM_ORE);
	public static final RegistrySupplier<Item> MARS_ICE_SHARD_ORE = registerBlockItem("mars_ice_shard_ore", ModBlocks.MARS_ICE_SHARD_ORE);

	// Venus blocks
	public static final RegistrySupplier<Item> VENUS_SANDSTONE = registerBlockItem("venus_sandstone", ModBlocks.VENUS_SANDSTONE);
	public static final RegistrySupplier<Item> VENUS_SANDSTONE_BRICKS = registerBlockItem("venus_sandstone_bricks", ModBlocks.VENUS_SANDSTONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_VENUS_SANDSTONE_BRICKS = registerBlockItem("cracked_venus_sandstone_bricks", ModBlocks.CRACKED_VENUS_SANDSTONE_BRICKS);
	public static final RegistrySupplier<Item> VENUS_SANDSTONE_BRICK_SLAB = registerBlockItem("venus_sandstone_brick_slab", ModBlocks.VENUS_SANDSTONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> VENUS_SANDSTONE_BRICK_STAIRS = registerBlockItem("venus_sandstone_brick_stairs", ModBlocks.VENUS_SANDSTONE_BRICK_STAIRS);

	public static final RegistrySupplier<Item> VENUS_SAND = registerBlockItem("venus_sand", ModBlocks.VENUS_SAND);
	public static final RegistrySupplier<Item> VENUS_STONE = registerBlockItem("venus_stone", ModBlocks.VENUS_STONE);
	public static final RegistrySupplier<Item> VENUS_STONE_STAIRS = registerBlockItem("venus_stone_stairs", ModBlocks.VENUS_STONE_STAIRS);
	public static final RegistrySupplier<Item> VENUS_STONE_SLAB = registerBlockItem("venus_stone_slab", ModBlocks.VENUS_STONE_SLAB);
	public static final RegistrySupplier<Item> VENUS_COBBLESTONE = registerBlockItem("venus_cobblestone", ModBlocks.VENUS_COBBLESTONE);
	public static final RegistrySupplier<Item> VENUS_COBBLESTONE_STAIRS = registerBlockItem("venus_cobblestone_stairs", ModBlocks.VENUS_COBBLESTONE_STAIRS);
	public static final RegistrySupplier<Item> VENUS_COBBLESTONE_SLAB = registerBlockItem("venus_cobblestone_slab", ModBlocks.VENUS_COBBLESTONE_SLAB);
	public static final RegistrySupplier<Item> VENUS_STONE_BRICKS = registerBlockItem("venus_stone_bricks", ModBlocks.VENUS_STONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_VENUS_STONE_BRICKS = registerBlockItem("cracked_venus_stone_bricks", ModBlocks.CRACKED_VENUS_STONE_BRICKS);
	public static final RegistrySupplier<Item> VENUS_STONE_BRICK_SLAB = registerBlockItem("venus_stone_brick_slab", ModBlocks.VENUS_STONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> VENUS_STONE_BRICK_STAIRS = registerBlockItem("venus_stone_brick_stairs", ModBlocks.VENUS_STONE_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_VENUS_STONE_BRICKS = registerBlockItem("chiseled_venus_stone_bricks", ModBlocks.CHISELED_VENUS_STONE_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_VENUS_STONE_STAIRS = registerBlockItem("chiseled_venus_stone_stairs", ModBlocks.CHISELED_VENUS_STONE_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_VENUS_STONE_SLAB = registerBlockItem("chiseled_venus_stone_slab", ModBlocks.CHISELED_VENUS_STONE_SLAB);
	public static final RegistrySupplier<Item> POLISHED_VENUS_STONE = registerBlockItem("polished_venus_stone", ModBlocks.POLISHED_VENUS_STONE);
	public static final RegistrySupplier<Item> POLISHED_VENUS_STONE_STAIRS = registerBlockItem("polished_venus_stone_stairs", ModBlocks.POLISHED_VENUS_STONE_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_VENUS_STONE_SLAB = registerBlockItem("polished_venus_stone_slab", ModBlocks.POLISHED_VENUS_STONE_SLAB);
	public static final RegistrySupplier<Item> VENUS_PILLAR = registerBlockItem("venus_pillar", ModBlocks.VENUS_PILLAR);
	public static final RegistrySupplier<Item> VENUS_STONE_BRICK_WALL = registerBlockItem("venus_stone_brick_wall", ModBlocks.VENUS_STONE_BRICK_WALL);

	public static final RegistrySupplier<Item> VENUS_COAL_ORE = registerBlockItem("venus_coal_ore", ModBlocks.VENUS_COAL_ORE);
	public static final RegistrySupplier<Item> VENUS_GOLD_ORE = registerBlockItem("venus_gold_ore", ModBlocks.VENUS_GOLD_ORE);
	public static final RegistrySupplier<Item> VENUS_DIAMOND_ORE = registerBlockItem("venus_diamond_ore", ModBlocks.VENUS_DIAMOND_ORE);
	public static final RegistrySupplier<Item> VENUS_CALORITE_ORE = registerBlockItem("venus_calorite_ore", ModBlocks.VENUS_CALORITE_ORE);
	public static final RegistrySupplier<Item> DEEPSLATE_CALORITE_ORE = registerBlockItem("deepslate_calorite_ore", ModBlocks.DEEPSLATE_CALORITE_ORE);

	public static final RegistrySupplier<Item> INFERNAL_SPIRE_BLOCK = registerBlockItem("infernal_spire_block", ModBlocks.INFERNAL_SPIRE_BLOCK);

	// Mercury blocks
	public static final RegistrySupplier<Item> MERCURY_STONE = registerBlockItem("mercury_stone", ModBlocks.MERCURY_STONE);
	public static final RegistrySupplier<Item> MERCURY_STONE_STAIRS = registerBlockItem("mercury_stone_stairs", ModBlocks.MERCURY_STONE_STAIRS);
	public static final RegistrySupplier<Item> MERCURY_STONE_SLAB = registerBlockItem("mercury_stone_slab", ModBlocks.MERCURY_STONE_SLAB);
	public static final RegistrySupplier<Item> MERCURY_COBBLESTONE = registerBlockItem("mercury_cobblestone", ModBlocks.MERCURY_COBBLESTONE);
	public static final RegistrySupplier<Item> MERCURY_COBBLESTONE_STAIRS = registerBlockItem("mercury_cobblestone_stairs", ModBlocks.MERCURY_COBBLESTONE_STAIRS);
	public static final RegistrySupplier<Item> MERCURY_COBBLESTONE_SLAB = registerBlockItem("mercury_cobblestone_slab", ModBlocks.MERCURY_COBBLESTONE_SLAB);
	public static final RegistrySupplier<Item> MERCURY_STONE_BRICKS = registerBlockItem("mercury_stone_bricks", ModBlocks.MERCURY_STONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_MERCURY_STONE_BRICKS = registerBlockItem("cracked_mercury_stone_bricks", ModBlocks.CRACKED_MERCURY_STONE_BRICKS);
	public static final RegistrySupplier<Item> MERCURY_STONE_BRICK_SLAB = registerBlockItem("mercury_stone_brick_slab", ModBlocks.MERCURY_STONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> MERCURY_STONE_BRICK_STAIRS = registerBlockItem("mercury_stone_brick_stairs", ModBlocks.MERCURY_STONE_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MERCURY_STONE_BRICKS = registerBlockItem("chiseled_mercury_stone_bricks", ModBlocks.CHISELED_MERCURY_STONE_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_MERCURY_STONE_STAIRS = registerBlockItem("chiseled_mercury_stone_stairs", ModBlocks.CHISELED_MERCURY_STONE_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_MERCURY_STONE_SLAB = registerBlockItem("chiseled_mercury_stone_slab", ModBlocks.CHISELED_MERCURY_STONE_SLAB);
	public static final RegistrySupplier<Item> POLISHED_MERCURY_STONE = registerBlockItem("polished_mercury_stone", ModBlocks.POLISHED_MERCURY_STONE);
	public static final RegistrySupplier<Item> POLISHED_MERCURY_STONE_STAIRS = registerBlockItem("polished_mercury_stone_stairs", ModBlocks.POLISHED_MERCURY_STONE_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_MERCURY_STONE_SLAB = registerBlockItem("polished_mercury_stone_slab", ModBlocks.POLISHED_MERCURY_STONE_SLAB);
	public static final RegistrySupplier<Item> MERCURY_PILLAR = registerBlockItem("mercury_pillar", ModBlocks.MERCURY_PILLAR);
	public static final RegistrySupplier<Item> MERCURY_STONE_BRICK_WALL = registerBlockItem("mercury_stone_brick_wall", ModBlocks.MERCURY_STONE_BRICK_WALL);

	public static final RegistrySupplier<Item> MERCURY_IRON_ORE = registerBlockItem("mercury_iron_ore", ModBlocks.MERCURY_IRON_ORE);

	// Glacio blocks
	public static final RegistrySupplier<Item> GLACIO_STONE = registerBlockItem("glacio_stone", ModBlocks.GLACIO_STONE);
	public static final RegistrySupplier<Item> GLACIO_STONE_STAIRS = registerBlockItem("glacio_stone_stairs", ModBlocks.GLACIO_STONE_STAIRS);
	public static final RegistrySupplier<Item> GLACIO_STONE_SLAB = registerBlockItem("glacio_stone_slab", ModBlocks.GLACIO_STONE_SLAB);
	public static final RegistrySupplier<Item> GLACIO_COBBLESTONE = registerBlockItem("glacio_cobblestone", ModBlocks.GLACIO_COBBLESTONE);
	public static final RegistrySupplier<Item> GLACIO_COBBLESTONE_STAIRS = registerBlockItem("glacio_cobblestone_stairs", ModBlocks.GLACIO_COBBLESTONE_STAIRS);
	public static final RegistrySupplier<Item> GLACIO_COBBLESTONE_SLAB = registerBlockItem("glacio_cobblestone_slab", ModBlocks.GLACIO_COBBLESTONE_SLAB);
	public static final RegistrySupplier<Item> GLACIO_STONE_BRICKS = registerBlockItem("glacio_stone_bricks", ModBlocks.GLACIO_STONE_BRICKS);
	public static final RegistrySupplier<Item> CRACKED_GLACIO_STONE_BRICKS = registerBlockItem("cracked_glacio_stone_bricks", ModBlocks.CRACKED_GLACIO_STONE_BRICKS);
	public static final RegistrySupplier<Item> GLACIO_STONE_BRICK_SLAB = registerBlockItem("glacio_stone_brick_slab", ModBlocks.GLACIO_STONE_BRICK_SLAB);
	public static final RegistrySupplier<Item> GLACIO_STONE_BRICK_STAIRS = registerBlockItem("glacio_stone_brick_stairs", ModBlocks.GLACIO_STONE_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_GLACIO_STONE_BRICKS = registerBlockItem("chiseled_glacio_stone_bricks", ModBlocks.CHISELED_GLACIO_STONE_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_GLACIO_STONE_STAIRS = registerBlockItem("chiseled_glacio_stone_stairs", ModBlocks.CHISELED_GLACIO_STONE_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_GLACIO_STONE_SLAB = registerBlockItem("chiseled_glacio_stone_slab", ModBlocks.CHISELED_GLACIO_STONE_SLAB);
	public static final RegistrySupplier<Item> POLISHED_GLACIO_STONE = registerBlockItem("polished_glacio_stone", ModBlocks.POLISHED_GLACIO_STONE);
	public static final RegistrySupplier<Item> POLISHED_GLACIO_STONE_STAIRS = registerBlockItem("polished_glacio_stone_stairs", ModBlocks.POLISHED_GLACIO_STONE_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_GLACIO_STONE_SLAB = registerBlockItem("polished_glacio_stone_slab", ModBlocks.POLISHED_GLACIO_STONE_SLAB);
	public static final RegistrySupplier<Item> GLACIO_PILLAR = registerBlockItem("glacio_pillar", ModBlocks.GLACIO_PILLAR);
	public static final RegistrySupplier<Item> GLACIO_STONE_BRICK_WALL = registerBlockItem("glacio_stone_brick_wall", ModBlocks.GLACIO_STONE_BRICK_WALL);

	public static final RegistrySupplier<Item> GLACIO_ICE_SHARD_ORE = registerBlockItem("glacio_ice_shard_ore", ModBlocks.GLACIO_ICE_SHARD_ORE);
	public static final RegistrySupplier<Item> GLACIO_COAL_ORE = registerBlockItem("glacio_coal_ore", ModBlocks.GLACIO_COAL_ORE);
	public static final RegistrySupplier<Item> GLACIO_COPPER_ORE = registerBlockItem("glacio_copper_ore", ModBlocks.GLACIO_COPPER_ORE);
	public static final RegistrySupplier<Item> GLACIO_IRON_ORE = registerBlockItem("glacio_iron_ore", ModBlocks.GLACIO_IRON_ORE);
	public static final RegistrySupplier<Item> GLACIO_LAPIS_ORE = registerBlockItem("glacio_lapis_ore", ModBlocks.GLACIO_LAPIS_ORE);

	public static final RegistrySupplier<Item> PERMAFROST = registerBlockItem("permafrost", ModBlocks.PERMAFROST);
	public static final RegistrySupplier<Item> PERMAFROST_BRICKS = registerBlockItem("permafrost_bricks", ModBlocks.PERMAFROST_BRICKS);
	public static final RegistrySupplier<Item> PERMAFROST_BRICK_STAIRS = registerBlockItem("permafrost_brick_stairs", ModBlocks.PERMAFROST_BRICK_STAIRS);
	public static final RegistrySupplier<Item> PERMAFROST_BRICK_SLAB = registerBlockItem("permafrost_brick_slab", ModBlocks.PERMAFROST_BRICK_SLAB);
	public static final RegistrySupplier<Item> CRACKED_PERMAFROST_BRICKS = registerBlockItem("cracked_permafrost_bricks", ModBlocks.CRACKED_PERMAFROST_BRICKS);
	public static final RegistrySupplier<Item> PERMAFROST_TILES = registerBlockItem("permafrost_tiles", ModBlocks.PERMAFROST_TILES);
	public static final RegistrySupplier<Item> CHISELED_PERMAFROST_BRICKS = registerBlockItem("chiseled_permafrost_bricks", ModBlocks.CHISELED_PERMAFROST_BRICKS);
	public static final RegistrySupplier<Item> CHISELED_PERMAFROST_BRICK_STAIRS = registerBlockItem("chiseled_permafrost_brick_stairs", ModBlocks.CHISELED_PERMAFROST_BRICK_STAIRS);
	public static final RegistrySupplier<Item> CHISELED_PERMAFROST_BRICK_SLAB = registerBlockItem("chiseled_permafrost_brick_slab", ModBlocks.CHISELED_PERMAFROST_BRICK_SLAB);
	public static final RegistrySupplier<Item> POLISHED_PERMAFROST = registerBlockItem("polished_permafrost", ModBlocks.POLISHED_PERMAFROST);
	public static final RegistrySupplier<Item> POLISHED_PERMAFROST_STAIRS = registerBlockItem("polished_permafrost_stairs", ModBlocks.POLISHED_PERMAFROST_STAIRS);
	public static final RegistrySupplier<Item> POLISHED_PERMAFROST_SLAB = registerBlockItem("polished_permafrost_slab", ModBlocks.POLISHED_PERMAFROST_SLAB);
	public static final RegistrySupplier<Item> PERMAFROST_PILLAR = registerBlockItem("permafrost_pillar", ModBlocks.PERMAFROST_PILLAR);
	public static final RegistrySupplier<Item> PERMAFROST_BRICK_WALL = registerBlockItem("permafrost_brick_wall", ModBlocks.PERMAFROST_BRICK_WALL);

	public static final RegistrySupplier<Item> GLACIAN_LOG = registerBlockItem("glacian_log", ModBlocks.GLACIAN_LOG);
	public static final RegistrySupplier<Item> STRIPPED_GLACIAN_LOG = registerBlockItem("stripped_glacian_log", ModBlocks.STRIPPED_GLACIAN_LOG);
	public static final RegistrySupplier<Item> GLACIAN_LEAVES = registerBlockItem("glacian_leaves", ModBlocks.GLACIAN_LEAVES);
	public static final RegistrySupplier<Item> GLACIAN_PLANKS = registerBlockItem("glacian_planks", ModBlocks.GLACIAN_PLANKS);
	public static final RegistrySupplier<Item> GLACIAN_STAIRS = registerBlockItem("glacian_stairs", ModBlocks.GLACIAN_STAIRS);
	public static final RegistrySupplier<Item> GLACIAN_SLAB = registerBlockItem("glacian_slab", ModBlocks.GLACIAN_SLAB);
	public static final RegistrySupplier<Item> GLACIAN_DOOR = registerBlockItem("glacian_door", ModBlocks.GLACIAN_DOOR);
	public static final RegistrySupplier<Item> GLACIAN_TRAPDOOR = registerBlockItem("glacian_trapdoor", ModBlocks.GLACIAN_TRAPDOOR);
	public static final RegistrySupplier<Item> GLACIAN_FENCE = registerBlockItem("glacian_fence", ModBlocks.GLACIAN_FENCE);
	public static final RegistrySupplier<Item> GLACIAN_FENCE_GATE = registerBlockItem("glacian_fence_gate", ModBlocks.GLACIAN_FENCE_GATE);
	public static final RegistrySupplier<Item> GLACIAN_BUTTON = registerBlockItem("glacian_button", ModBlocks.GLACIAN_BUTTON);
	public static final RegistrySupplier<Item> GLACIAN_PRESSURE_PLATE = registerBlockItem("glacian_pressure_plate", ModBlocks.GLACIAN_PRESSURE_PLATE);
	public static final RegistrySupplier<Item> GLACIAN_SIGN = register("glacian_sign", () -> new SignItem(new Item.Settings().group(ModItemGroups.ITEM_GROUP_BLOCKS).maxCount(16), ModBlocks.GLACIAN_SIGN.get(), ModBlocks.GLACIAN_WALL_SIGN.get()));
	public static final RegistrySupplier<Item> GLACIAN_FUR = registerBlockItem("glacian_fur", ModBlocks.GLACIAN_FUR);

	// Spawn eggs
	// Moon
	public static final RegistrySupplier<Item> LUNARIAN_SPAWN_EGG = register("lunarian_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.LUNARIAN, -13382401, -11650781, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> CORRUPTED_LUNARIAN_SPAWN_EGG = register("corrupted_lunarian_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.CORRUPTED_LUNARIAN, -14804199, -16740159, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> STAR_CRAWLER_SPAWN_EGG = register("star_crawler_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.STAR_CRAWLER, -13421773, -16724788, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));

	// Mars
	public static final RegistrySupplier<Item> MARTIAN_RAPTOR_SPAWN_EGG = register("martian_raptor_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.MARTIAN_RAPTOR, 5349438, -13312, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));

	// Venus
	public static final RegistrySupplier<Item> PYGRO_SPAWN_EGG = register("pygro_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.PYGRO, -3381760, -6750208, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> ZOMBIFIED_PYGRO_SPAWN_EGG = register("zombified_pygro_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.ZOMBIFIED_PYGRO, 8473125, 6131271, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> PYGRO_BRUTE_SPAWN_EGG = register("pygro_brute_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.PYGRO_BRUTE, -3381760, -67208, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> MOGLER_SPAWN_EGG = register("mogler_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.MOGLER, -13312, -3407872, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> ZOMBIFIED_MOGLER_SPAWN_EGG = register("zombified_mogler_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.ZOMBIFIED_MOGLER, 12537409, 7988821, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));
	public static final RegistrySupplier<Item> SULFUR_CREEPER_SPAWN_EGG = register("sulfur_creeper_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.SULFUR_CREEPER, 13930288, 11303196, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));

	// Glacio
	public static final RegistrySupplier<Item> GLACIAN_RAM_SPAWN_EGG = register("glacian_ram_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.GLACIAN_RAM, 16770815, 4406589, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));

	// Other
	public static final RegistrySupplier<Item> LUNARIAN_WANDERING_TRADER_SPAWN_EGG = register("lunarian_wandering_trader_spawn_egg", () -> new ArchitecturySpawnEggItem(ModEntityTypes.LUNARIAN_WANDERING_TRADER, 5993415, 8537301, new Item.Settings().group(ModItemGroups.ITEM_GROUP_SPAWN_EGGS)));

	public static void register() {
		ITEMS.register();
	}

	public static RegistrySupplier<Item> registerFlag(String id, Supplier<Block> flag) {
		Supplier<Item> item = () -> new TallBlockItem(flag.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_FLAGS));
		return register(id, item);
	}

	public static RegistrySupplier<Item> registerGlobe(String id, Supplier<Block> globe) {
		Supplier<Item> item = () -> new BlockItem(globe.get(), new Item.Settings().group(ModItemGroups.ITEM_GROUP_GLOBES).maxCount(1).rarity(Rarity.RARE));
		return register(id, item);
	}

	public static RegistrySupplier<Item> registerBlockItem(String id, Supplier<Block> block) {
		return registerBlockItem(id, block, ModItemGroups.ITEM_GROUP_BLOCKS);
	}

	public static RegistrySupplier<Item> registerBlockItem(String id, Supplier<Block> block, ItemGroup group) {
		Supplier<Item> item = () -> new BlockItem(block.get(), new Item.Settings().group(group));
		return register(id, item);
	}

	public static RegistrySupplier<Item> registerItem(String id, ItemGroup group) {
		return register(id, () -> new Item(new Item.Settings().group(group)));
	}

	public static <T extends Item> RegistrySupplier<T> register(String id, Supplier<T> item) {
		return register(new ModIdentifier(id), item);
	}

	public static <T extends Item> RegistrySupplier<T> register(Identifier id, Supplier<T> item) {
		//		items.add(item);
		return ITEMS.register(id.getPath(), item);
	}
}