package net.ggusz.lockmymace.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.HopperBlockEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;



@Mixin(HopperBlockEntity.class)
public class PreventHopperInsertMixin {
    @Inject(method = "transfer(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/item/ItemStack;ILnet/minecraft/util/math/Direction;)Lnet/minecraft/item/ItemStack;",
            at = @At("HEAD"), cancellable = true)
    private static void preventSpecialItemTransfer(Inventory from, Inventory to, ItemStack stack,
                                                   int slot, Direction side,
                                                   CallbackInfoReturnable<ItemStack> cir) {
        if (stack.getItem() instanceof MaceItem) {
            cir.setReturnValue(stack);
            cir.cancel();
        }
    }
    @Inject(method = "transfer(Lnet/minecraft/inventory/Inventory;Lnet/minecraft/inventory/Inventory;Lnet/minecraft/item/ItemStack;Lnet/minecraft/util/math/Direction;)Lnet/minecraft/item/ItemStack;",
            at = @At("HEAD"), cancellable = true)
    private static void preventSpecialItemTransfer(Inventory from, Inventory _to, ItemStack stack, Direction side, CallbackInfoReturnable<ItemStack> cir) {
        if (stack.getItem() instanceof MaceItem) {
            cir.setReturnValue(stack);
        }
    }
}
