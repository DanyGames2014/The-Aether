package com.gildedgames.aether.entity.animal;

import com.gildedgames.aether.Aether;
import com.gildedgames.aether.entity.base.EntityAetherAnimal;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import net.minecraft.block.BlockBase;
import net.minecraft.class_61;
import net.minecraft.entity.EntityBase;
import net.minecraft.entity.Living;
import net.minecraft.entity.monster.MonsterBase;
import net.minecraft.entity.player.PlayerBase;
import net.minecraft.item.ItemBase;
import net.minecraft.level.Level;
import net.minecraft.util.io.CompoundTag;
import net.minecraft.util.maths.MathHelper;

import java.util.List;

public class EntityAerbunny extends EntityAetherAnimal {
    public int age;
    public int mate;
    public boolean grab;
    public boolean fear;
    public boolean gotrider;
    public EntityBase runFrom;
    public float puffiness;
    
    public EntityAerbunny(final Level level) {
        super(level);
        this.movementSpeed = 2.5f;
        this.texture = "aether:textures/entity/aerbunny.png";
        this.standingEyeHeight = -0.16f;
        this.setSize(0.4f, 0.4f);
        this.health = 6;
        if (this.renderDistanceMultiplier < 5.0) {
            this.renderDistanceMultiplier = 5.0;
        }
        this.age = this.rand.nextInt(64);
        this.mate = 0;
    }

    @Override
    public boolean isInsideWall() {
        return false; // stops them from dying while on your head, going through doors etc
    }

    @Override
    public void tick() {
        if (this.gotrider) {
            this.gotrider = false;
            if (this.vehicle == null) {
                final PlayerBase entityplayer = (PlayerBase)this.findPlayerToRunFrom();
                if (entityplayer != null && this.distanceTo(entityplayer) < 2.0f && entityplayer.passenger == null) {
                    this.startRiding(entityplayer);
                }
            }
        }
        if (this.age < 1023) {
            ++this.age;
        }
        else if (this.mate < 127) {
            ++this.mate;
        }
        else {
            int i = 0;
            final List list = this.level.getEntities(this, this.boundingBox.expand(16.0, 16.0, 16.0));
            for (int j = 0; j < list.size(); ++j) {
                final EntityBase entity = (EntityBase)list.get(j);
                if (entity instanceof EntityAerbunny) {
                    ++i;
                }
            }
            if (i > 12) {
                this.proceed();
                return;
            }
            final List list2 = this.level.getEntities(this, this.boundingBox.expand(1.0, 1.0, 1.0));
            boolean flag = false;
            for (int k = 0; k < list.size(); ++k) {
                final EntityBase entity2 = (EntityBase)list2.get(k);
                if (entity2 instanceof EntityAerbunny) {
                    if (entity2 != this) {
                        final EntityAerbunny entitybunny = (EntityAerbunny)entity2;
                        if (entitybunny.vehicle == null) {
                            if (entitybunny.age >= 1023) {
                                final EntityAerbunny entitybunny2 = new EntityAerbunny(this.level);
                                entitybunny2.setPosition(this.x, this.y, this.z);
                                this.level.spawnEntity(entitybunny2);
                                this.level.playSound((EntityBase)this, "mob.chickenplop", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
                                this.proceed();
                                entitybunny.proceed();
                                flag = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!flag) {
                this.mate = this.rand.nextInt(16);
            }
        }
        if (this.puffiness > 0.0f) {
            this.puffiness -= 0.1f;
        }
        else {
            this.puffiness = 0.0f;
        }
        super.tick();
    }
    
    @Override
    protected void handleFallDamage(final float height) {
    }
    
    @Override
    public void writeCustomDataToTag(final CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.put("Fear", this.fear);
        if (this.passenger != null) {
            this.gotrider = true;
        }
        tag.put("GotRider", this.gotrider);
        tag.put("RepAge", (short)this.age);
        tag.put("RepMate", (short)this.mate);
    }
    
    @Override
    public void readCustomDataFromTag(final CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.fear = tag.getBoolean("Fear");
        this.gotrider = tag.getBoolean("GotRider");
        this.age = tag.getShort("RepAge");
        this.mate = tag.getShort("RepMate");
    }
    
    @Override
    protected void tickHandSwing() {
        if (this.onGround) {
            if (this.field_1029 != 0.0f) {
                this.jump();
            }
        }
        else if (this.vehicle != null) {
            if (this.vehicle.removed) {
                this.startRiding(this.vehicle);
            }
            else if (!this.vehicle.onGround && !this.vehicle.method_1393()) {
                ((EntityBaseAccessor)this.vehicle).setFallDistance(0.0f);
                final EntityBase vehicle = this.vehicle;
                vehicle.velocityY += 0.05000000074505806;
                if (this.vehicle.velocityY < -0.22499999403953552 && this.vehicle instanceof Living && ((LivingAccessor)this.vehicle).getJumping()) {
                    this.vehicle.velocityY = 0.125;
                    this.cloudPoop();
                    this.puffiness = 1.15f;
                }
            }
        }
        else if (!this.grab) {
            if (this.field_1029 != 0.0f) {
                final int j = MathHelper.floor(this.x);
                final int i1 = MathHelper.floor(this.boundingBox.minY);
                final int k1 = MathHelper.floor(this.boundingBox.minY - 0.5);
                final int l1 = MathHelper.floor(this.z);
                if ((this.level.getTileId(j, i1 - 1, l1) != 0 || this.level.getTileId(j, k1 - 1, l1) != 0) && this.level.getTileId(j, i1 + 2, l1) == 0 && this.level.getTileId(j, i1 + 1, l1) == 0) {
                    if (this.velocityY < 0.0) {
                        this.cloudPoop();
                        this.puffiness = 0.9f;
                    }
                    this.velocityY = 0.2;
                }
            }
            if (this.velocityY < -0.1) {
                this.velocityY = -0.1;
            }
        }
        if (!this.grab) {
            super.tickHandSwing();
            if (this.fear && this.rand.nextInt(4) == 0) {
                if (this.runFrom != null) {
                    this.runLikeHell();
                    this.level.addParticle("splash", this.x, this.y, this.z, 0.0, 0.0, 0.0);
                    if (!this.method_633()) {
                        this.method_924(this.runFrom, 30.0f, 30.0f);
                    }
                    if (this.runFrom.removed || this.distanceTo(this.runFrom) > 16.0f) {
                        this.runFrom = null;
                    }
                }
                else {
                    this.runFrom = this.findPlayerToRunFrom();
                }
            }
        }
        else if (this.onGround) {
            this.grab = false;
            this.level.playSound((EntityBase)this, "aether:aether.sound.mobs.aerbunny.aerbunnyland", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
            final List list = this.level.getEntities(this, this.boundingBox.expand(12.0, 12.0, 12.0));
            for (int m = 0; m < list.size(); ++m) {
                final EntityBase entity = (EntityBase)list.get(m);
                if (entity instanceof MonsterBase) {
                    final MonsterBase entitymobs = (MonsterBase)entity;
                    entitymobs.method_636(this);
                }
            }
        }
        if (this.method_1393()) {
            this.jump();
        }
    }
    
    public void cloudPoop() {
        final double a = this.rand.nextFloat() - 0.5f;
        final double c = this.rand.nextFloat() - 0.5f;
        final double d = this.x + a * 0.4000000059604645;
        final double e = this.boundingBox.minY;
        final double f = this.z + a * 0.4000000059604645;
        this.level.addParticle("explode", d, e, f, 0.0, -0.07500000298023224, 0.0);
    }
    
    @Override
    public boolean damage(final EntityBase target, final int amount) {
        final boolean flag = super.damage(target, amount);
        if (flag && target instanceof PlayerBase) {
            this.fear = true;
        }
        return flag;
    }
    
    @Override
    public boolean method_932() {
        return this.field_1029 != 0.0f;
    }
    
    protected EntityBase findPlayerToRunFrom() {
        final PlayerBase entityplayer = this.level.getClosestPlayerTo(this, 12.0);
        if (entityplayer != null && this.method_928(entityplayer)) {
            return entityplayer;
        }
        return null;
    }
    
    public void runLikeHell() {
        final double a = this.x - this.runFrom.x;
        final double b = this.z - this.runFrom.z;
        double crazy = Math.atan2(a, b);
        crazy += (this.rand.nextFloat() - this.rand.nextFloat()) * 0.75;
        final double c = this.x + Math.sin(crazy) * 8.0;
        final double d = this.z + Math.cos(crazy) * 8.0;
        final int x = MathHelper.floor(c);
        final int y = MathHelper.floor(this.boundingBox.minY);
        final int z = MathHelper.floor(d);
        for (int q = 0; q < 16; ++q) {
            final int i = x + this.rand.nextInt(4) - this.rand.nextInt(4);
            final int j = y + this.rand.nextInt(4) - this.rand.nextInt(4) - 1;
            final int k = z + this.rand.nextInt(4) - this.rand.nextInt(4);
            if (j > 4 && (this.level.getTileId(i, j, k) == 0 || this.level.getTileId(i, j, k) == BlockBase.SNOW.id) && this.level.getTileId(i, j - 1, k) != 0) {
                final class_61 dogs = this.level.method_189(this, i, j, k, 16.0f);
                this.setTarget(dogs);
                break;
            }
        }
    }
    
    @Override
    public boolean interact(final PlayerBase entityplayer) {
        this.yaw = entityplayer.yaw;
        if (this.vehicle != null) {
            this.field_1012 = this.vehicle.yaw;
            this.yaw = this.vehicle.yaw;
        }
        this.startRiding(entityplayer);
        if (this.vehicle == null) {
            this.grab = true;
        }
        else {
            this.level.playSound((EntityBase)this, "aether:aether.sound.mobs.aerbunny.aerbunnylift", 1.0f, (this.rand.nextFloat() - this.rand.nextFloat()) * 0.2f + 1.0f);
        }
        this.jumping = false;
        this.field_1029 = 0.0f;
        this.field_1060 = 0.0f;
        this.setTarget(null);
        this.velocityX = entityplayer.velocityX * 5.0;
        this.velocityY = entityplayer.velocityY / 2.0 + 0.5;
        this.velocityZ = entityplayer.velocityZ * 5.0;
        return true;
    }
    
    @Override
    public double getHeightOffset() {
        if (this.vehicle instanceof PlayerBase) {
            return this.standingEyeHeight - 1.15f;
        }
        return this.standingEyeHeight;
    }
    
    @Override
    protected String getAmbientSound() {
        return null;
    }
    
    @Override
    protected void getDrops() {
       if (Aether.equippedSkyrootSword()) {
            this.dropItem(ItemBase.string.id, 2);
        }
        else {
            this.dropItem(ItemBase.string.id, 1);
        }
    }
    
    public void proceed() {
        this.mate = 0;
        this.age = this.rand.nextInt(64);
    }
    
    @Override
    protected boolean canClimb() {
        return this.onGround;
    }
    
    @Override
    protected String getHurtSound() {
        return "aether:aether.sound.mobs.aerbunny.aerbunnyhurt";
    }
    
    @Override
    protected String getDeathSound() {
        return "aether:aether.sound.mobs.aerbunny.aerbunnydeath";
    }
    
    @Override
    public boolean canSpawn() {
        return super.canSpawn();
    }
}
