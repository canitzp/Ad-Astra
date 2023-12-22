package earth.terrarium.adastra.client;

import com.mojang.blaze3d.platform.InputConstants;
import earth.terrarium.adastra.AdAstra;
import earth.terrarium.adastra.client.config.AdAstraConfigClient;
import earth.terrarium.adastra.client.models.entities.vehicles.LanderModel;
import earth.terrarium.adastra.client.models.entities.vehicles.RocketModel;
import earth.terrarium.adastra.client.models.entities.vehicles.RoverModel;
import earth.terrarium.adastra.client.particle.LargeFlameParticle;
import earth.terrarium.adastra.client.particle.OxygenBubbleParticle;
import earth.terrarium.adastra.client.particle.SmallFlameParticle;
import earth.terrarium.adastra.client.radio.screen.RadioScreen;
import earth.terrarium.adastra.client.renderers.blocks.EnergizerBlockEntityRenderer;
import earth.terrarium.adastra.client.renderers.blocks.FlagBlockEntityRenderer;
import earth.terrarium.adastra.client.renderers.blocks.GlobeBlockEntityRenderer;
import earth.terrarium.adastra.client.renderers.blocks.SlidingDoorBlockEntityRenderer;
import earth.terrarium.adastra.client.renderers.entities.vehicles.LanderRenderer;
import earth.terrarium.adastra.client.renderers.entities.vehicles.RocketRenderer;
import earth.terrarium.adastra.client.renderers.entities.vehicles.RoverRenderer;
import earth.terrarium.adastra.client.screens.PlanetsScreen;
import earth.terrarium.adastra.client.screens.machines.*;
import earth.terrarium.adastra.client.screens.player.OverlayScreen;
import earth.terrarium.adastra.client.screens.vehicles.LanderScreen;
import earth.terrarium.adastra.client.screens.vehicles.RocketScreen;
import earth.terrarium.adastra.client.screens.vehicles.RoverScreen;
import earth.terrarium.adastra.common.constants.ConstantComponents;
import earth.terrarium.adastra.common.entities.base.RadioHolder;
import earth.terrarium.adastra.common.items.EtrionicCapacitorItem;
import earth.terrarium.adastra.common.registry.*;
import earth.terrarium.botarium.client.ClientHooks;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.particle.SplashParticle;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.entity.NoopRenderer;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class AdAstraClient {

    public static final KeyMapping KEY_TOGGLE_SUIT_FLIGHT = new KeyMapping(
        ConstantComponents.TOGGLE_SUIT_FLIGHT_KEY.getString(),
        InputConstants.KEY_V,
        ConstantComponents.AD_ASTRA_CATEGORY.getString());

    public static final KeyMapping KEY_OPEN_RADIO = new KeyMapping(
        ConstantComponents.OPEN_RADIO_KEY.getString(),
        InputConstants.KEY_R,
        ConstantComponents.AD_ASTRA_CATEGORY.getString());

    public static void init() {
        AdAstra.CONFIGURATOR.registerConfig(AdAstraConfigClient.class);
        registerScreens();
        registerBlockEntityRenderers();
        registerEntityRenderers();
        registerItemRenderers();
        registerItemProperties();
    }

    private static void registerScreens() {
        MenuScreens.register(ModMenus.COMPRESSOR.get(), CompressorScreen::new);
        MenuScreens.register(ModMenus.COAL_GENERATOR.get(), CoalGeneratorScreen::new);
        MenuScreens.register(ModMenus.OXYGEN_LOADER.get(), OxygenLoaderScreen::new);
        MenuScreens.register(ModMenus.FUEL_REFINERY.get(), FuelRefineryScreen::new);
        MenuScreens.register(ModMenus.WATER_PUMP.get(), WaterPumpScreen::new);
        MenuScreens.register(ModMenus.SOLAR_PANEL.get(), SolarPanelScreen::new);
        MenuScreens.register(ModMenus.OXYGEN_DISTRIBUTOR.get(), OxygenDistributorScreen::new);
        MenuScreens.register(ModMenus.CRYO_FREEZER.get(), CryoFreezerScreen::new);
        MenuScreens.register(ModMenus.NASA_WORKBENCH.get(), NasaWorkbenchScreen::new);

        MenuScreens.register(ModMenus.ROCKET.get(), RocketScreen::new);
        MenuScreens.register(ModMenus.ROVER.get(), RoverScreen::new);
        MenuScreens.register(ModMenus.LANDER.get(), LanderScreen::new);

        MenuScreens.register(ModMenus.PLANETS.get(), PlanetsScreen::new);
    }

    private static void registerBlockEntityRenderers() {
        ClientHooks.registerBlockEntityRenderers(ModBlockEntityTypes.ENERGIZER.get(), c -> new EnergizerBlockEntityRenderer());
        ClientHooks.registerBlockEntityRenderers(ModBlockEntityTypes.GLOBE.get(), c -> new GlobeBlockEntityRenderer());
        ClientHooks.registerBlockEntityRenderers(ModBlockEntityTypes.FLAG.get(), c -> new FlagBlockEntityRenderer());
        ClientHooks.registerBlockEntityRenderers(ModBlockEntityTypes.SLIDING_DOOR.get(), c -> new SlidingDoorBlockEntityRenderer());
    }

    private static void registerEntityRenderers() {
        ClientHooks.registerEntityRenderer(ModEntityTypes.AIR_VORTEX, NoopRenderer::new);
        ClientHooks.registerEntityRenderer(ModEntityTypes.ROVER, RoverRenderer::new);
        ClientHooks.registerEntityRenderer(ModEntityTypes.TIER_1_ROCKET, c -> new RocketRenderer(c, RocketModel.TIER_1_LAYER, RocketRenderer.TIER_1_TEXTURE));
        ClientHooks.registerEntityRenderer(ModEntityTypes.TIER_2_ROCKET, c -> new RocketRenderer(c, RocketModel.TIER_2_LAYER, RocketRenderer.TIER_2_TEXTURE));
        ClientHooks.registerEntityRenderer(ModEntityTypes.TIER_3_ROCKET, c -> new RocketRenderer(c, RocketModel.TIER_3_LAYER, RocketRenderer.TIER_3_TEXTURE));
        ClientHooks.registerEntityRenderer(ModEntityTypes.TIER_4_ROCKET, c -> new RocketRenderer(c, RocketModel.TIER_4_LAYER, RocketRenderer.TIER_4_TEXTURE));
        ClientHooks.registerEntityRenderer(ModEntityTypes.LANDER, c -> new LanderRenderer(c, LanderModel.LAYER, LanderRenderer.TEXTURE));
    }

    public static void onRegisterEntityLayers(ClientPlatformUtils.LayerDefinitionRegistry consumer) {
        consumer.register(RoverModel.LAYER, RoverModel::createBodyLayer);
        RocketModel.register(consumer);
        consumer.register(LanderModel.LAYER, LanderModel::createBodyLayer);
    }

    private static void registerItemRenderers() {
    }

    private static void registerItemProperties() {
        ClientHooks.registerItemProperty(ModItems.ETRIONIC_CAPACITOR.get(), new ResourceLocation(AdAstra.MOD_ID, "toggled"), (stack, level, entity, i) -> EtrionicCapacitorItem.active(stack) ? 0 : 1);
    }

    public static void onRegisterParticles(BiConsumer<ParticleType<SimpleParticleType>, ClientPlatformUtils.SpriteParticleRegistration<SimpleParticleType>> consumer) {
        consumer.accept(ModParticleTypes.VENUS_RAIN.get(), SplashParticle.Provider::new);
        consumer.accept(ModParticleTypes.LARGE_FLAME.get(), LargeFlameParticle.Provider::new);
        consumer.accept(ModParticleTypes.LARGE_SMOKE.get(), LargeFlameParticle.Provider::new);
        consumer.accept(ModParticleTypes.SMALL_FLAME.get(), SmallFlameParticle.Provider::new);
        consumer.accept(ModParticleTypes.SMALL_SMOKE.get(), SmallFlameParticle.Provider::new);
        consumer.accept(ModParticleTypes.OXYGEN_BUBBLE.get(), OxygenBubbleParticle.Provider::new);
    }

    public static void onRegisterModels(Consumer<ResourceLocation> register) {
        ModBlocks.GLOBES.stream().forEach(b -> register.accept(new ResourceLocation(AdAstra.MOD_ID, "block/%s_cube".formatted(b.getId().getPath()))));
        register.accept(new ResourceLocation(AdAstra.MOD_ID, "block/%s_flipped".formatted(ModBlocks.AIRLOCK.getId().getPath())));
        register.accept(new ResourceLocation(AdAstra.MOD_ID, "block/%s_flipped".formatted(ModBlocks.REINFORCED_DOOR.getId().getPath())));
    }

    public static void onRegisterItemRenderers(BiConsumer<ItemLike, BlockEntityWithoutLevelRenderer> consumer) {
        ModItems.GLOBES.stream().forEach(item -> consumer.accept(item.get(), new GlobeBlockEntityRenderer.ItemRenderer()));
        consumer.accept(ModItems.ROVER.get(), new RoverRenderer.ItemRenderer());
        consumer.accept(ModItems.TIER_1_ROCKET.get(), new RocketRenderer.ItemRenderer(RocketModel.TIER_1_LAYER, RocketRenderer.TIER_1_TEXTURE));
        consumer.accept(ModItems.TIER_2_ROCKET.get(), new RocketRenderer.ItemRenderer(RocketModel.TIER_2_LAYER, RocketRenderer.TIER_2_TEXTURE));
        consumer.accept(ModItems.TIER_3_ROCKET.get(), new RocketRenderer.ItemRenderer(RocketModel.TIER_3_LAYER, RocketRenderer.TIER_3_TEXTURE));
        consumer.accept(ModItems.TIER_4_ROCKET.get(), new RocketRenderer.ItemRenderer(RocketModel.TIER_4_LAYER, RocketRenderer.TIER_4_TEXTURE));
    }

    public static void onRegisterHud(Consumer<ClientPlatformUtils.RenderHud> consumer) {
        consumer.accept(OverlayScreen::render);
    }

    public static void clientTick(Minecraft minecraft) {
        LocalPlayer player = minecraft.player;
        if (player == null) return;

        if (KEY_OPEN_RADIO.consumeClick() && player.getVehicle() instanceof RadioHolder) {
            Minecraft.getInstance().setScreen(new RadioScreen());
        }
    }
}