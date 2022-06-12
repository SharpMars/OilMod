package me.mars.oilmod.mixin;

import me.mars.oilmod.registry.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {

    @Shadow protected boolean isSubmergedInWater;

    @Shadow public abstract void jump();

    @Shadow public abstract PlayerAbilities getAbilities();

    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(at = @At("HEAD"), method = "tick")
    private void tick(CallbackInfo info)
    {
        if(this.hasStatusEffect(ModItems.OILED)) {
            if(this.isSubmergedInWater)
            {
                this.jump();
            }
        }
        if(!this.getAbilities().creativeMode)
        {
            if(!world.isRaining()){
                this.getAbilities().allowFlying = false;
                this.getAbilities().flying = false;
            }
            else if(!this.hasStatusEffect(ModItems.OILED)) {
                this.getAbilities().allowFlying = false;
                this.getAbilities().flying = false;
            }
        }
    }
}
