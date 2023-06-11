package earth.terrarium.ad_astra.mixin;

import earth.terrarium.ad_astra.common.config.SpaceSuitConfig;
import earth.terrarium.ad_astra.common.config.VehiclesConfig;
import earth.terrarium.ad_astra.common.entity.vehicle.Rocket;
import earth.terrarium.ad_astra.common.item.armor.JetSuit;
import earth.terrarium.ad_astra.common.item.armor.NetheriteSpaceSuit;
import earth.terrarium.ad_astra.common.util.ModKeyBindings;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin {

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    public void ad_astra$damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        Player player = ((Player) (Object) this);
        if (SpaceSuitConfig.netheriteSpaceSuitHasFireResistance) {
            if (source.is(DamageTypeTags.IS_FIRE) || source.is(DamageTypes.HOT_FLOOR)) {
                if (NetheriteSpaceSuit.hasFullSet(player)) {
                    player.setRemainingFireTicks(0);
                    cir.setReturnValue(false);
                }
            }
        }
        if (!VehiclesConfig.RocketConfig.takeDamageInRocket && player.getVehicle() instanceof Rocket) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "tick", at = @At("TAIL"))
    public void ad_astra$tick(CallbackInfo ci) {
        if (SpaceSuitConfig.enableJetSuitFlight) {
            Player player = ((Player) (Object) this);
            if (!player.level().isClientSide && !player.isPassenger()) {
                ItemStack chest = player.getItemBySlot(EquipmentSlot.CHEST);
                if (chest.getItem() instanceof JetSuit jetSuit) {
                    if (ModKeyBindings.jumpKeyDown(player)) {
                        if (JetSuit.hasFullSet(player)) {
                            jetSuit.fly(player, chest);
                        }
                    } else {
                        jetSuit.setFallFlying(false);
                        jetSuit.setEmitParticles(false);
                        if (!player.level().isClientSide) {
                            chest.getOrCreateTag().putBoolean("SpawnParticles", false);
                        }
                    }
                }
            }
        }
    }
}