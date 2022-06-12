package me.mars.oilmod.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class OiledEffect extends StatusEffect {

    public OiledEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xFFDF3F);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity.world.isRaining()) {
            if (entity instanceof PlayerEntity) {
                if(!((PlayerEntity) entity).getAbilities().creativeMode) {
                    ((PlayerEntity) entity).getAbilities().allowFlying = true;
                    ((PlayerEntity) entity).getAbilities().flying = true;
                }
            }
        }
    }
}
