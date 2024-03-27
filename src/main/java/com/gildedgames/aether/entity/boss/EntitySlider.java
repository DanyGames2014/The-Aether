package com.gildedgames.aether.entity.boss;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.NameGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

//TODO: add render
public class EntitySlider extends FlyingEntity implements IAetherBoss, MobSpawnDataProvider
{
    public int moveTimer;
    public int dennis;
    public int rennis;
    public int chatTime;
    public Entity target;
    public boolean awake;
    public boolean gotMovement;
    public boolean crushed;
    public float speedy;
    public float harvey;
    public int direction;
    private int dungeonX;
    private int dungeonY;
    private int dungeonZ;
    public String bossName;

    public EntitySlider(final World level)
    {
        super(level);
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.setBoundingBoxSpacing(2.0f, 2.0f);
        this.health = 500;
        this.dennis = 1;
        this.texture = "aether:textures/entity/sliderSleep.png";
        this.chatTime = 60;
        this.bossName = NameGen.gen();
    }

    public void initDataTracker()
    {
        super.initDataTracker();
        this.x = Math.floor(this.x + 0.5);
        this.y = Math.floor(this.y + 0.5);
        this.z = Math.floor(this.z + 0.5);
    }

    public boolean method_940()
    {
        return false;
    }

    @Override
    protected String method_911()
    {
        return "ambient.cave.cave";
    }

    @Override
    protected String method_912()
    {
        return "step.stone";
    }

    @Override
    protected String method_913()
    {
        return "aether:aether.sound.bosses.slider.sliderdeath";
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putFloat("Speedy", this.speedy);
        tag.putShort("MoveTimer", (short) this.moveTimer);
        tag.putShort("Direction", (short) this.direction);
        tag.putBoolean("GotMovement", this.gotMovement);
        tag.putBoolean("Awake", this.awake);
        tag.putInt("DungeonX", this.dungeonX);
        tag.putInt("DungeonY", this.dungeonY);
        tag.putInt("DungeonZ", this.dungeonZ);
        tag.putBoolean("IsCurrentBoss", this.isCurrentBoss());
        tag.putString("BossName", this.bossName);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.speedy = tag.getFloat("Speedy");
        this.moveTimer = tag.getShort("MoveTimer");
        this.direction = tag.getShort("Direction");
        this.gotMovement = tag.getBoolean("GotMovement");
        this.awake = tag.getBoolean("Awake");
        this.dungeonX = tag.getInt("DungeonX");
        this.dungeonY = tag.getInt("DungeonY");
        this.dungeonZ = tag.getInt("DungeonZ");
        if (tag.getBoolean("IsCurrentBoss"))
        {
            AetherMod.currentBoss = this;
        }
        this.bossName = tag.getString("BossName");
        if (this.awake)
        {
            if (this.criticalCondition())
            {
                this.texture = "aether:textures/entity/sliderAwake_red.png";
            }
            else
            {
                this.texture = "aether:textures/entity/sliderAwake.png";
            }
        }
    }

    public boolean criticalCondition()
    {
        return this.health <= 125;
    }

    @Override
    public void tick()
    {
        super.tick();
        final float field_1012 = 0.0f;
        this.yaw = field_1012;
        this.pitch = field_1012;
        this.field_1012 = field_1012;
        if (this.awake)
        {
            if (this.target != null && this.target instanceof LivingEntity)
            {
                final LivingEntity e1 = (LivingEntity) this.target;
                if (e1.health <= 0)
                {
                    this.awake = false;
                    AetherMod.currentBoss = null;
                    this.target = null;
                    this.texture = "aether:textures/entity/sliderSleep.png";
                    this.stop();
                    this.openDoor();
                    this.moveTimer = 0;
                    return;
                }
            }
            else
            {
                if (this.target != null && this.target.dead)
                {
                    this.awake = false;
                    AetherMod.currentBoss = null;
                    this.target = null;
                    this.texture = "aether:textures/entity/sliderSleep.png";
                    this.stop();
                    this.openDoor();
                    this.moveTimer = 0;
                    return;
                }
                if (this.target == null)
                {
                    this.target = this.world.method_186(this, -1.0);
                    if (this.target == null)
                    {
                        this.awake = false;
                        AetherMod.currentBoss = null;
                        this.target = null;
                        this.texture = "aether:textures/entity/sliderSleep.png";
                        this.stop();
                        this.openDoor();
                        this.moveTimer = 0;
                        return;
                    }
                }
            }
            if (this.gotMovement)
            {
                if (this.field_1626)
                {
                    final double x = this.x - 0.5;
                    final double y = this.boundingBox.minY + 0.75;
                    final double z = this.z - 0.5;
                    this.crushed = false;
                    if (y < 124.0 && y > 4.0)
                    {
                        if (this.direction == 0)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + 1.5), (int) (z + b));
                            }
                        }
                        else if (this.direction == 1)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y - 1.5), (int) (z + b));
                            }
                        }
                        else if (this.direction == 2)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + 1.5), (int) (y + a), (int) (z + b));
                            }
                        }
                        else if (this.direction == 3)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x - 1.5), (int) (y + a), (int) (z + b));
                            }
                        }
                        else if (this.direction == 4)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + b), (int) (z + 1.5));
                            }
                        }
                        else if (this.direction == 5)
                        {
                            for (int i = 0; i < 25; ++i)
                            {
                                final double a = (i / 5 - 2) * 0.75;
                                final double b = (i % 5 - 2) * 0.75;
                                this.blockCrush((int) (x + a), (int) (y + b), (int) (z - 1.5));
                            }
                        }
                    }
                    if (this.crushed)
                    {
                        this.world.playSound(this.x, this.y, this.z, "random.explode", 3.0f, (0.625f + (this.world.field_214.nextFloat() - this.world.field_214.nextFloat()) * 0.2f) * 0.7f);
                        this.world.playSound((Entity) this, "aether:aether.sound.bosses.slider.slidercollide", 2.5f, 1.0f / (this.random.nextFloat() * 0.2f + 0.9f));
                    }
                    this.stop();
                }
                else
                {
                    if (this.speedy < 2.0f)
                    {
                        this.speedy += (this.criticalCondition() ? 0.0325f : 0.025f);
                    }
                    this.velocityX = 0.0;
                    this.velocityY = 0.0;
                    this.velocityZ = 0.0;
                    if (this.direction == 0)
                    {
                        this.velocityY = this.speedy;
                        if (this.boundingBox.minY > this.target.boundingBox.minY + 0.35)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 1)
                    {
                        this.velocityY = -this.speedy;
                        if (this.boundingBox.minY < this.target.boundingBox.minY - 0.25)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 2)
                    {
                        this.velocityX = this.speedy;
                        if (this.x > this.target.x + 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 3)
                    {
                        this.velocityX = -this.speedy;
                        if (this.x < this.target.x - 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 4)
                    {
                        this.velocityZ = this.speedy;
                        if (this.z > this.target.z + 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                    else if (this.direction == 5)
                    {
                        this.velocityZ = -this.speedy;
                        if (this.z < this.target.z - 0.125)
                        {
                            this.stop();
                            this.moveTimer = 8;
                        }
                    }
                }
            }
            else if (this.moveTimer > 0)
            {
                --this.moveTimer;
                if (this.criticalCondition() && this.random.nextInt(2) == 0)
                {
                    --this.moveTimer;
                }
                this.velocityX = 0.0;
                this.velocityY = 0.0;
                this.velocityZ = 0.0;
            }
            else
            {
                final double a2 = Math.abs(this.x - this.target.x);
                final double b2 = Math.abs(this.boundingBox.minY - this.target.boundingBox.minY);
                final double c = Math.abs(this.z - this.target.z);
                if (a2 > c)
                {
                    this.direction = 2;
                    if (this.x > this.target.x)
                    {
                        this.direction = 3;
                    }
                }
                else
                {
                    this.direction = 4;
                    if (this.z > this.target.z)
                    {
                        this.direction = 5;
                    }
                }
                if ((b2 > a2 && b2 > c) || (b2 > 0.25 && this.random.nextInt(5) == 0))
                {
                    this.direction = 0;
                    if (this.y > this.target.y)
                    {
                        this.direction = 1;
                    }
                }
                this.world.playSound((Entity) this, "aether:aether.sound.bosses.slider.slidermove", 2.5f, 1.0f / (this.random.nextFloat() * 0.2f + 0.9f));
                this.gotMovement = true;
            }
        }
        if (this.harvey > 0.01f)
        {
            this.harvey *= 0.8f;
        }
        if (this.chatTime > 0)
        {
            --this.chatTime;
        }
    }

    private void openDoor()
    {
        final int x = this.dungeonX + 15;
        for (int y = this.dungeonY + 1; y < this.dungeonY + 5; ++y)
        {
            for (int z = this.dungeonZ + 6; z < this.dungeonZ + 10; ++z)
            {
                this.world.method_200(x, y, z, 0);
            }
        }
    }

    public void method_1353(final Entity entity)
    {
        if (this.awake && this.gotMovement)
        {
            final boolean flag = entity.damage(this, 6);
            if (flag && entity instanceof LivingEntity)
            {
                this.world.playSound((Entity) this, "aether:aether.sound.bosses.slider.slidercollide", 2.5f, 1.0f / (this.random.nextFloat() * 0.2f + 0.9f));
                if (entity instanceof MobEntity || entity instanceof PlayerEntity)
                {
                    final LivingEntity living;
                    final LivingEntity ek = living = (LivingEntity) entity;
                    living.velocityY += 0.35;
                    final LivingEntity living2 = ek;
                    living2.velocityX *= 2.0;
                    final LivingEntity living3 = ek;
                    living3.velocityZ *= 2.0;
                }
                this.stop();
            }
        }
    }

    @Override
    protected void method_933()
    {
        for (int i = 0; i < 7 + this.random.nextInt(3); ++i)
        {
            this.method_1339(AetherBlocks.DUNGEON_STONE.id, 1);
        }
        this.method_1327(new ItemStack(AetherItems.Key, 1, 0), 0.0f);
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.world.getBlockId(i, j - 1, k) == Block.GRASS_BLOCK.id && this.world.method_252(i, j, k) > 8 && super.canSpawn();
    }

    public void stop()
    {
        this.gotMovement = false;
        this.moveTimer = 12;
        this.direction = 0;
        this.speedy = 0.0f;
        this.velocityX = 0.0;
        this.velocityY = 0.0;
        this.velocityZ = 0.0;
    }

    private void chatItUp(final String s)
    {
        // todo: make this neater, change to something that isn't jank?
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
            return;

        if (this.chatTime <= 0)
        {
            ((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.addChatMessage(s);
            this.chatTime = 60;
        }
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target == null || !(target instanceof PlayerEntity))
        {
            return false;
        }
        final PlayerEntity p1 = (PlayerEntity) target;
        final ItemStack stack = p1.getHand();
        if (stack == null || stack.getItem() == null)
        {
            return false;
        }
        if (!(stack.getItem() instanceof PickaxeItem))
        {
            this.chatItUp("Hmm. Perhaps I need to attack it with a Pickaxe?");
            return false;
        }
        final PickaxeItem tool = (PickaxeItem) stack.getItem();
        if (!tool.isSuitableFor(Block.STONE))
        {
            this.chatItUp("Hmm. Perhaps I need to attack it with a Pickaxe?");
            return false;
        }
        final boolean flag = super.damage(target, Math.max(0, amount));
        if (flag)
        {
            for (int j = 0; j < ((this.health <= 0) ? 16 : 48); ++j)
            {
                final double a = this.x + (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
                final double b = this.boundingBox.minY + 1.75 + (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
                final double c = this.z + (this.random.nextFloat() - this.random.nextFloat()) * 1.5;
                if (this.health <= 0)
                {
                    this.world.addParticle("explode", a, b, c, 0.0, 0.0, 0.0);
                }
            }
            if (this.health <= 0)
            {
                this.dead = true;
                this.openDoor();
                this.unlockBlock(this.dungeonX, this.dungeonY, this.dungeonZ);
                this.world.method_154(this.dungeonX + 7, this.dungeonY + 1, this.dungeonZ + 7, Block.TRAPDOOR.id, 3);
                this.world.method_154(this.dungeonX + 8, this.dungeonY + 1, this.dungeonZ + 7, Block.TRAPDOOR.id, 2);
                this.world.method_154(this.dungeonX + 7, this.dungeonY + 1, this.dungeonZ + 8, Block.TRAPDOOR.id, 3);
                this.world.method_154(this.dungeonX + 8, this.dungeonY + 1, this.dungeonZ + 8, Block.TRAPDOOR.id, 2);
                AetherMod.giveAchievement(AetherAchievements.defeatBronze);
                AetherMod.currentBoss = null;
            }
            else if (!this.awake)
            {
                this.world.playSound((Entity) this, "aether:aether.sound.bosses.slider.sliderawaken", 2.5f, 1.0f / (this.random.nextFloat() * 0.2f + 0.9f));
                this.awake = true;
                this.target = target;
                this.texture = "aether:textures/entity/sliderAwake.png";
                final int x = this.dungeonX + 15;
                for (int y = this.dungeonY + 1; y < this.dungeonY + 8; ++y)
                {
                    for (int z = this.dungeonZ + 5; z < this.dungeonZ + 11; ++z)
                    {
                        this.world.method_200(x, y, z, AetherBlocks.LOCKED_DUNGEON_STONE.id);
                    }
                }
                AetherMod.currentBoss = this;
            }
            else if (this.gotMovement)
            {
                this.speedy *= 0.75f;
            }
            final double a2 = Math.abs(this.x - target.x);
            final double c2 = Math.abs(this.z - target.z);
            if (a2 > c2)
            {
                this.dennis = 1;
                this.rennis = 0;
                if (this.x > target.x)
                {
                    this.dennis = -1;
                }
            }
            else
            {
                this.rennis = 1;
                this.dennis = 0;
                if (this.z > target.z)
                {
                    this.rennis = -1;
                }
            }
            this.harvey = 0.7f - this.health / 875.0f;
            if (this.criticalCondition())
            {
                this.texture = "aether:textures/entity/sliderAwake_red.png";
            }
            else
            {
                this.texture = "aether:textures/entity/sliderAwake.png";
            }
        }
        return flag;
    }

    private void unlockBlock(final int i, final int j, final int k)
    {
        final int id = this.world.getBlockId(i, j, k);
        if (id == AetherBlocks.LOCKED_DUNGEON_STONE.id)
        {
            this.world.method_154(i, j, k, AetherBlocks.DUNGEON_STONE.id, this.world.getBlockMeta(i, j, k));
            this.unlockBlock(i + 1, j, k);
            this.unlockBlock(i - 1, j, k);
            this.unlockBlock(i, j + 1, k);
            this.unlockBlock(i, j - 1, k);
            this.unlockBlock(i, j, k + 1);
            this.unlockBlock(i, j, k - 1);
        }
        if (id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
        {
            this.world.method_154(i, j, k, AetherBlocks.LIGHT_DUNGEON_STONE.id, this.world.getBlockMeta(i, j, k));
            this.unlockBlock(i + 1, j, k);
            this.unlockBlock(i - 1, j, k);
            this.unlockBlock(i, j + 1, k);
            this.unlockBlock(i, j - 1, k);
            this.unlockBlock(i, j, k + 1);
            this.unlockBlock(i, j, k - 1);
        }
    }

    public void method_1322(final double x, final double y, final double z)
    {
    }

    @Override
    public void method_925(final Entity entity, final int i, final double d, final double d1)
    {
    }

    public void blockCrush(final int x, final int y, final int z)
    {
        final int a = this.world.getBlockId(x, y, z);
        final int b = this.world.getBlockMeta(x, y, z);
        if (a == 0 || a == AetherBlocks.LOCKED_DUNGEON_STONE.id || a == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
        {
            return;
        }
        // todo: particleMinecraftClientAccessor.getMCinstance().particleManager.addTileBreakParticles(x, y, z, a, b);
        Block.BLOCKS[a].onBreak(this.world, x, y, z);
        Block.BLOCKS[a].dropStacks(this.world, x, y, z, b);
        this.world.setBlock(x, y, z, 0);
        this.crushed = true;
        this.addSquirrelButts(x, y, z);
    }

    public void addSquirrelButts(final int x, final int y, final int z)
    {
        final double a = x + 0.5 + (this.random.nextFloat() - this.random.nextFloat()) * 0.375;
        final double b = y + 0.5 + (this.random.nextFloat() - this.random.nextFloat()) * 0.375;
        final double c = z + 0.5 + (this.random.nextFloat() - this.random.nextFloat()) * 0.375;
        this.world.addParticle("explode", a, b, c, 0.0, 0.0, 0.0);
    }

    public void setDungeon(final int i, final int j, final int k)
    {
        this.dungeonX = i;
        this.dungeonY = j;
        this.dungeonZ = k;
    }

    @Override
    public int getBossHP()
    {
        return this.health;
    }

    @Override
    public int getBossMaxHP()
    {
        return 500;
    }

    @Override
    public boolean isCurrentBoss()
    {
        return AetherMod.currentBoss != null && this.equals(AetherMod.currentBoss);
    }

    @Override
    public int getBossEntityID()
    {
        return this.id;
    }

    @Override
    public String getBossTitle()
    {
        return this.bossName + ", the Slider";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Slider");
    }
}
