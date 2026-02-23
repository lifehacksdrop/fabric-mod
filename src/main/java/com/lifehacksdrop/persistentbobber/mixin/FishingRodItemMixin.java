package com.lifehacksdrop.persistentbobber.mixin;

import com.lifehacksdrop.persistentbobber.RodEventHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public class FishingRodItemMixin {

    @Inject(at = @At("HEAD"), method = "use", cancellable = true)
    public void onRodUse(World world, PlayerEntity player, Hand hand,
                         CallbackInfoReturnable<ActionResult> cir) {

        // Make sure to use the method call isClient()
        if (!world.isClient()) {
            ItemStack rod = player.getStackInHand(hand);
            RodEventHandler.spawnBobberOnFirstCast(player, rod);
        }
    }
}