package me.mars.oilmod;

import me.mars.oilmod.registry.ModItems;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;

public class OilPotion extends Item {

    int duration;
    boolean useBucket;

    public OilPotion(Settings settings, int duration, boolean useBucket) {
        super(settings);
        this.duration = duration;
        this.useBucket = useBucket;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity serverPlayerEntity) {
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            user.addStatusEffect(new StatusEffectInstance(ModItems.OILED, duration));
        }

        if(!useBucket)
        {
            return stack.isEmpty() ? new ItemStack(Items.GLASS_BOTTLE) : stack;
        }
        else{
            return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 16;
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public SoundEvent getDrinkSound() {
        return SoundEvents.ITEM_BUCKET_EMPTY;
    }

    @Override
    public SoundEvent getEatSound() {
        return SoundEvents.ITEM_BUCKET_EMPTY;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return ItemUsage.consumeHeldItem(world, user, hand);
    }
}
