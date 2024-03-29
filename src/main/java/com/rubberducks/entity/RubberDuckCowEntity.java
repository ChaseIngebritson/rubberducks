package com.rubberducks.entity;

import com.rubberducks.RubberDucks;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class RubberDuckCowEntity extends RubberDuckBaseEntity {
  public RubberDuckCowEntity(EntityType<RubberDuckBaseEntity> type, World world) {
    super(type, world);
  }

  @Override
  public ActionResult interact(PlayerEntity player, Hand hand) {
    ItemStack itemStack = player.getStackInHand(hand);

    if (player.shouldCancelInteraction()) {
      return ActionResult.PASS;
    }

    // Play noise
    this.pushAwayFrom(player);
    this.getWorld().playSound(this.getX(), this.getY(), this.getZ(), RubberDucks.SQUEAK1_EVENT,
        this.getSoundCategory(), 1.0f, 0.8f + 0.4f * this.random.nextFloat(), false);

    // Get milk
    if (itemStack.isOf(Items.BUCKET)) {
      ItemStack itemStack2 = ItemUsage.exchangeStack(itemStack, player, Items.MILK_BUCKET.getDefaultStack());
      player.setStackInHand(hand, itemStack2);
      return ActionResult.success(this.getWorld().isClient);
    }

    return ActionResult.SUCCESS;
  }
}
