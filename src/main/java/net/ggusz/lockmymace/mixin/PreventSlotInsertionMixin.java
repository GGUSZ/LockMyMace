package net.ggusz.lockmymace.mixin;


import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MaceItem;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;


@Mixin(Slot.class)
public class PreventSlotInsertionMixin {

    @Inject(method = "canInsert", at = @At("HEAD"), cancellable = true)
    private void preventSpecialItemInsertion(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Slot slot = (Slot) (Object) this;

        if (stack.getItem() instanceof MaceItem &&
                !(slot.inventory instanceof PlayerInventory)) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}