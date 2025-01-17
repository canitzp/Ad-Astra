package earth.terrarium.adastra.common.items;

import dev.architectury.injectables.annotations.PlatformOnly;
import earth.terrarium.adastra.api.systems.GravityApi;
import earth.terrarium.adastra.common.constants.ConstantComponents;
import earth.terrarium.adastra.common.constants.PlanetConstants;
import earth.terrarium.adastra.common.registry.ModFluids;
import earth.terrarium.adastra.common.tags.ModFluidTags;
import earth.terrarium.adastra.common.utils.FluidUtils;
import earth.terrarium.adastra.common.utils.TooltipUtils;
import earth.terrarium.botarium.common.fluid.FluidConstants;
import earth.terrarium.botarium.common.fluid.base.BotariumFluidItem;
import earth.terrarium.botarium.common.fluid.base.FluidContainer;
import earth.terrarium.botarium.common.fluid.base.FluidHolder;
import earth.terrarium.botarium.common.fluid.impl.SimpleFluidContainer;
import earth.terrarium.botarium.common.fluid.impl.WrappedItemFluidContainer;
import earth.terrarium.botarium.common.fluid.utils.ClientFluidHooks;
import earth.terrarium.botarium.common.item.ItemStackHolder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ZipGunItem extends Item implements BotariumFluidItem<WrappedItemFluidContainer> {

    public ZipGunItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, Player player, @NotNull InteractionHand usedHand) {
        ItemStack stack = player.getItemInHand(usedHand);
        if (FluidUtils.hasFluid(stack) || player.isCreative()) {
            player.startUsingItem(usedHand);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void onUseTick(@NotNull Level level, @NotNull LivingEntity entity, @NotNull ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, entity, stack, remainingUseDuration);
        if (!(entity instanceof Player player)) return;

        ItemStack mainHandItem = entity.getMainHandItem();
        ItemStack offhandItem = entity.getOffhandItem();

        long fuelUsage = 1;
        boolean mainHandBoost = consumeFuel(player, mainHandItem, fuelUsage);
        boolean offHandBoost = consumeFuel(player, offhandItem, fuelUsage);
        if (!mainHandBoost && !offHandBoost && !player.isCreative()) {
            player.stopUsingItem();
            return;
        }

        double maxSpeed = 0.35;
        double particleSpeed = 1.5;
        double propelForce = 0.2;
        double propelYForce = 0.2;
        int particleChance = 4;

        if (GravityApi.API.getGravity(player) <= PlanetConstants.ZERO_GRAVITY_THRESHOLD) {
            propelForce *= 0.1;
            propelYForce *= 0.1;
            maxSpeed *= 20.0;
            entity.fallDistance *= 0.9f;
        } else {
            propelYForce *= 0.2;
            propelYForce *= 1.0 - Math.min(1.0, entity.getY() / 90.0);
        }

        if (mainHandBoost && offHandBoost) {
            propelForce *= 1.4;
            propelYForce *= 1.25;
            maxSpeed *= 1.5;
            particleSpeed *= 1.5;
            entity.fallDistance *= 0.9f;
            particleChance -= 2;
        }

        var lookAngle = entity.getLookAngle();
        if (entity.getDeltaMovement().length() < maxSpeed) {
            var propelRot = lookAngle.multiply(propelForce, propelYForce, propelForce);
            entity.addDeltaMovement(propelRot);
        }

        if (level.random.nextInt(particleChance) == 0) {
            level.addParticle(
                ParticleTypes.SNOWFLAKE,
                entity.getX(),
                entity.getY() + 1.0,
                entity.getZ(),
                lookAngle.x * particleSpeed + level.random.nextGaussian() * 0.03,
                lookAngle.y * particleSpeed + level.random.nextGaussian() * 0.03,
                lookAngle.z * particleSpeed + level.random.nextGaussian() * 0.03
            );
        }
    }

    public boolean consumeFuel(Player player, ItemStack stack, long amount) {
        if (!(stack.getItem() instanceof ZipGunItem)) return false;
        if (player.isCreative()) return true;
        ItemStackHolder holder = new ItemStackHolder(stack);
        var container = FluidContainer.of(holder);
        if (container == null) return false;
        FluidHolder extracted = container.extractFluid(FluidHolder.ofMillibuckets(container.getFirstFluid().getFluid(), FluidConstants.fromMillibuckets(amount)), false);
        stack.setTag(holder.getStack().getTag());
        return extracted.getFluidAmount() > 0;
    }

    @Override
    public @NotNull UseAnim getUseAnimation(@NotNull ItemStack stack) {
        return UseAnim.BLOCK;
    }

    public int getUseDuration(@NotNull ItemStack stack) {
        return 72_000;
    }

    @Override
    public WrappedItemFluidContainer getFluidContainer(ItemStack holder) {
        return new WrappedItemFluidContainer(
            holder,
            new SimpleFluidContainer(
                getCapacity(),
                1,
                (t, f) -> f.is(ModFluidTags.ZIP_GUN_PROPELLANTS)) {
            });
    }

    public long getCapacity() {
        return FluidConstants.fromMillibuckets(3000);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, @NotNull TooltipFlag isAdvanced) {
        tooltipComponents.add(TooltipUtils.getFluidComponent(
            FluidUtils.getTank(stack),
            FluidUtils.getTankCapacity(stack),
            ModFluids.OXYGEN.get()));
        TooltipUtils.addDescriptionComponent(tooltipComponents, ConstantComponents.ZIP_GUN_INFO);
    }

    @Override
    public boolean isBarVisible(@NotNull ItemStack stack) {
        return FluidUtils.hasFluid(stack);
    }

    @Override
    public int getBarWidth(@NotNull ItemStack stack) {
        var fluidContainer = getFluidContainer(stack);
        return (int) (((double) fluidContainer.getFirstFluid().getFluidAmount() / fluidContainer.getTankCapacity(0)) * 13);
    }

    @Override
    public int getBarColor(@NotNull ItemStack stack) {
        return ClientFluidHooks.getFluidColor(FluidUtils.getTank(stack));
    }

    // Fabric disabling of nbt change animation
    @SuppressWarnings("unused")
    @PlatformOnly(PlatformOnly.FABRIC)
    public boolean allowNbtUpdateAnimation(Player player, InteractionHand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    // Forge disabling of nbt change animation
    @SuppressWarnings("unused")
    @PlatformOnly(PlatformOnly.FORGE)
    public boolean shouldCauseReequipAnimation(ItemStack oldStack, ItemStack newStack, boolean slotChanged) {
        return false;
    }
}
