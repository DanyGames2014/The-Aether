package com.gildedgames.aether.entity.boss;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.entity.mobs.EntityFireMinion;
import com.gildedgames.aether.entity.projectile.EntityFiroBall;
import com.gildedgames.aether.mixin.access.EntityBaseAccessor;
import com.gildedgames.aether.registry.AetherAchievements;
import com.gildedgames.aether.registry.AetherBlocks;
import com.gildedgames.aether.registry.AetherItems;
import com.gildedgames.aether.utils.NameGen;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.FlyingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;
import net.modificationstation.stationapi.api.server.entity.MobSpawnDataProvider;

import java.util.List;

public class EntityFireMonster extends FlyingEntity implements IAetherBoss, MobSpawnDataProvider
{
    public int wideness;
    public int orgX;
    public int orgY;
    public int orgZ;
    public int motionTimer;
    public int entCount;
    public int flameCount;
    public int ballCount;
    public int chatLog;
    public int chatCount;
    public int hurtness;
    public int direction;
    public double rotary;
    public double speedness;
    public Entity target;
    public boolean gotTarget;
    public String bossName;
    public static final float jimz = 57.295773f;

    public EntityFireMonster(final World level)
    {
        super(level);
        this.texture = "aether:textures/entity/firemonster.png";
        this.setBoundingBoxSpacing(2.25f, 2.5f);
        this.field_1642 = true;
        this.orgX = MathHelper.floor(this.x);
        this.orgY = MathHelper.floor(this.boundingBox.minY) + 1;
        this.orgZ = MathHelper.floor(this.z);
        this.wideness = 10;
        this.health = 50;
        this.speedness = 0.5 - this.health / 70.0 * 0.2;
        this.direction = 0;
        this.entCount = this.random.nextInt(6);
        this.bossName = NameGen.gen();
    }

    public EntityFireMonster(final World world, final int x, final int y, final int z, final int rad, final int dir)
    {
        super(world);
        this.texture = "aether:textures/entity/firemonster.png";
        this.setBoundingBoxSpacing(2.25f, 2.5f);
        this.method_1340(x + 0.5, y, z + 0.5);
        this.wideness = rad - 2;
        this.orgX = x;
        this.orgY = y;
        this.orgZ = z;
        this.field_1642 = true;
        this.rotary = this.random.nextFloat() * 360.0;
        this.health = 50;
        this.speedness = 0.5 - this.health / 70.0 * 0.2;
        this.direction = dir;
        this.entCount = this.random.nextInt(6);
        this.bossName = NameGen.gen();
    }

    public boolean method_940()
    {
        return false;
    }

    @Override
    public void tick()
    {
        super.tick();
        if (this.health > 0)
        {
            final double a = this.random.nextFloat() - 0.5f;
            final double b = this.random.nextFloat();
            final double c = this.random.nextFloat() - 0.5f;
            final double d = this.x + a * b;
            final double e = this.boundingBox.minY + b - 0.5;
            final double f = this.z + c * b;
            this.world.addParticle("flame", d, e, f, 0.0, -0.07500000298023224, 0.0);
            ++this.entCount;
            if (this.entCount >= 3)
            {
                this.burnEntities();
                this.evapWater();
                this.entCount = 0;
            }
            if (this.hurtness > 0)
            {
                --this.hurtness;
                if (this.hurtness == 0)
                {
                    this.texture = "aether:textures/entity/firemonster.png";
                }
            }
        }
        if (this.chatCount > 0)
        {
            --this.chatCount;
        }
    }

    protected Entity findPlayerToAttack()
    {
        final PlayerEntity entityplayer = this.world.method_186(this, 32.0);
        if (entityplayer != null && this.method_928(entityplayer))
        {
            return entityplayer;
        }
        return null;
    }

    public void method_910()
    {
        super.method_910();
        if (this.gotTarget && this.target == null)
        {
            this.target = this.findPlayerToAttack();
            this.gotTarget = false;
        }
        if (this.target == null)
        {
            this.method_1340(this.orgX + 0.5, this.orgY, this.orgZ + 0.5);
            this.setDoor(0);
            return;
        }
        this.field_1012 = this.yaw;
        this.method_1340(this.x, this.orgY, this.z);
        this.velocityY = 0.0;
        boolean pool = false;
        if (this.velocityX > 0.0 && (int) Math.floor(this.x) > this.orgX + this.wideness)
        {
            this.rotary = 360.0 - this.rotary;
            pool = true;
        }
        else if (this.velocityX < 0.0 && (int) Math.floor(this.x) < this.orgX - this.wideness)
        {
            this.rotary = 360.0 - this.rotary;
            pool = true;
        }
        if (this.velocityZ > 0.0 && (int) Math.floor(this.z) > this.orgZ + this.wideness)
        {
            this.rotary = 180.0 - this.rotary;
            pool = true;
        }
        else if (this.velocityZ < 0.0 && (int) Math.floor(this.z) < this.orgZ - this.wideness)
        {
            this.rotary = 180.0 - this.rotary;
            pool = true;
        }
        if (this.rotary > 360.0)
        {
            this.rotary -= 360.0;
        }
        else if (this.rotary < 0.0)
        {
            this.rotary += 360.0;
        }
        if (this.target != null)
        {
            this.method_924(this.target, 20.0f, 20.0f);
        }
        final double crazy = this.rotary / 57.295772552490234;
        this.velocityX = Math.sin(crazy) * this.speedness;
        this.velocityZ = Math.cos(crazy) * this.speedness;
        ++this.motionTimer;
        if (this.motionTimer >= 20 || pool)
        {
            this.motionTimer = 0;
            if (this.random.nextInt(3) == 0)
            {
                this.rotary += (this.random.nextFloat() - this.random.nextFloat()) * 60.0;
            }
        }
        ++this.flameCount;
        if (this.flameCount == 40 && this.random.nextInt(2) == 0)
        {
            this.poopFire();
        }
        else if (this.flameCount >= 55 + this.health / 2 && this.target != null && this.target instanceof LivingEntity)
        {
            this.makeFireBall(1);
            this.flameCount = 0;
        }
        if (this.target != null && this.target.dead)
        {
            this.method_1340(this.orgX + 0.5, this.orgY, this.orgZ + 0.5);
            this.velocityX = 0.0;
            this.velocityY = 0.0;
            this.velocityZ = 0.0;
            this.target = null;
            this.chatLine("§cSuch is the fate of a being who opposes the might of the sun.");
            this.setDoor(0);
            AetherMod.currentBoss = null;
            this.gotTarget = false;
        }
    }

    public void burnEntities()
    {
        final List list = this.world.getEntities(this, this.boundingBox.expand(0.0, 4.0, 0.0));
        for (int j = 0; j < list.size(); ++j)
        {
            final Entity entity1 = (Entity) list.get(j);
            if (entity1 instanceof LivingEntity && !((EntityBaseAccessor) entity1).getImmunityToFire())
            {
                entity1.damage(this, 10);
                entity1.fire = 300;
            }
        }
    }

    public void evapWater()
    {
        final int x = MathHelper.floor(this.x);
        final int z = MathHelper.floor(this.z);
        for (int i = 0; i < 8; ++i)
        {
            final int b = this.orgY - 2 + i;
            if (this.world.method_1779(x, b, z) == Material.WATER)
            {
                this.world.setBlock(x, b, z, 0);
                this.world.playSound((double) (x + 0.5f), (double) (b + 0.5f), (double) (z + 0.5f), "random.fizz", 0.5f, 2.6f + (this.random.nextFloat() - this.random.nextFloat()) * 0.8f);
                for (int l = 0; l < 8; ++l)
                {
                    this.world.addParticle("largesmoke", x + Math.random(), b + 0.75, z + Math.random(), 0.0, 0.0, 0.0);
                }
            }
        }
    }

    public void makeFireBall(final int shots)
    {
        this.world.playSound((Entity) this, "mob.ghast.fireball", 5.0f, (this.random.nextFloat() - this.random.nextFloat()) * 0.2f + 1.0f);
        boolean flag = false;
        ++this.ballCount;
        if (this.ballCount >= 3 + this.random.nextInt(3))
        {
            flag = true;
            this.ballCount = 0;
        }
        for (int i = 0; i < shots; ++i)
        {
            final EntityFiroBall e1 = new EntityFiroBall(this.world, this.x - this.velocityX / 2.0, this.y, this.z - this.velocityZ / 2.0, flag);
            this.world.method_210(e1);
        }
    }

    public void poopFire()
    {
        final int x = MathHelper.floor(this.x);
        final int z = MathHelper.floor(this.z);
        final int b = this.orgY - 2;
        if (AetherBlocks.isGood(this.world.getBlockId(x, b, z), this.world.getBlockMeta(x, b, z)))
        {
            this.world.setBlock(x, b, z, Block.FIRE.id);
        }
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("OriginX", (short) this.orgX);
        tag.putShort("OriginY", (short) this.orgY);
        tag.putShort("OriginZ", (short) this.orgZ);
        tag.putShort("Wideness", (short) this.wideness);
        tag.putShort("FlameCount", (short) this.flameCount);
        tag.putShort("BallCount", (short) this.ballCount);
        tag.putShort("ChatLog", (short) this.chatLog);
        tag.putFloat("Rotary", (float) this.rotary);
        tag.putBoolean("GotTarget", this.gotTarget = (this.target != null));
        tag.putBoolean("IsCurrentBoss", this.isCurrentBoss());
        tag.putString("BossName", this.bossName);
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.orgX = tag.getShort("OriginX");
        this.orgY = tag.getShort("OriginY");
        this.orgZ = tag.getShort("OriginZ");
        this.wideness = tag.getShort("Wideness");
        this.flameCount = tag.getShort("FlameCount");
        this.ballCount = tag.getShort("BallCount");
        this.chatLog = tag.getShort("ChatLog");
        this.rotary = tag.getFloat("Rotary");
        this.gotTarget = tag.getBoolean("GotTarget");
        this.speedness = 0.5 - this.health / 70.0 * 0.2;
        if (tag.getBoolean("IsCurrentBoss"))
        {
            AetherMod.currentBoss = this;
        }
        this.bossName = tag.getString("BossName");
    }

    private void chatLine(final String s)
    {
        if (FabricLoader.getInstance().getEnvironmentType() == EnvType.SERVER)
            return;

        ((Minecraft) FabricLoader.getInstance().getGameInstance()).inGameHud.addChatMessage(s);
    }

    public boolean chatWithMe()
    {
        if (this.chatCount <= 0)
        {
            if (this.chatLog == 0)
            {
                this.chatLine("§cYou are certainly a brave soul to have entered this chamber.");
                this.chatLog = 1;
                this.chatCount = 100;
            }
            else if (this.chatLog == 1)
            {
                this.chatLine("§cBegone human, you serve no purpose here.");
                this.chatLog = 2;
                this.chatCount = 100;
            }
            else if (this.chatLog == 2)
            {
                this.chatLine("§cYour presence annoys me. Do you not fear my burning aura?");
                this.chatLog = 3;
                this.chatCount = 100;
            }
            else if (this.chatLog == 3)
            {
                this.chatLine("§cI have nothing to offer you, fool. Leave me at peace.");
                this.chatLog = 4;
                this.chatCount = 100;
            }
            else if (this.chatLog == 4)
            {
                this.chatLine("§cPerhaps you are ignorant. Do you wish to know who I am?");
                this.chatLog = 5;
                this.chatCount = 100;
            }
            else if (this.chatLog == 5)
            {
                this.chatLine("§cI am a sun spirit, embodiment of Aether's eternal daylight.");
                this.chatLine("§cAs long as I am alive, the sun will never set on this world.");
                this.chatLog = 6;
                this.chatCount = 100;
            }
            else if (this.chatLog == 6)
            {
                this.chatLine("§cMy body burns with the anger of a thousand beasts.");
                this.chatLine("§cNo man, hero, or villain can harm me. You are no exception.");
                this.chatLog = 7;
                this.chatCount = 100;
            }
            else if (this.chatLog == 7)
            {
                this.chatLine("§cYou wish to challenge the might of the sun? You are mad.");
                this.chatLine("§cDo not further insult me or you will feel my wrath.");
                this.chatLog = 8;
                this.chatCount = 100;
            }
            else if (this.chatLog == 8)
            {
                this.chatLine("§cThis is your final warning. Leave now, or prepare to burn.");
                this.chatLog = 9;
                this.chatCount = 100;
            }
            else
            {
                if (this.chatLog == 9)
                {
                    this.chatLine("§6As you wish, your death will be slow and agonizing.");
                    this.chatLog = 10;
                    AetherMod.currentBoss = this;
                    return true;
                }
                if (this.chatLog == 10 && this.target == null)
                {
                    this.chatLine("§cDid your previous death not satisfy your curiosity, human?");
                    this.chatLog = 9;
                    this.chatCount = 100;
                }
            }
        }
        return false;
    }

    public boolean method_1323(final PlayerEntity ep)
    {
        if (this.chatWithMe())
        {
            this.rotary = 57.295772552490234 * Math.atan2(this.x - ep.x, this.z - ep.z);
            this.target = ep;
            this.setDoor(AetherBlocks.LOCKED_DUNGEON_STONE.id);
            return true;
        }
        return false;
    }

    public void method_1322(final double x, final double y, final double z)
    {
    }

    @Override
    public void method_925(final Entity entity, final int i, final double d, final double d1)
    {
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (target == null || !(target instanceof EntityFiroBall))
        {
            return false;
        }
        this.speedness = 0.5 - this.health / 70.0 * 0.2;
        final boolean flag = super.damage(target, amount);
        if (flag)
        {
            this.hurtness = 15;
            this.texture = "aether:textures/entity/firemonsterHurt.png";
            final EntityFireMinion minion = new EntityFireMinion(this.world);
            minion.method_1341(this.x, this.y, this.z, this.yaw, 0.0f);
            this.world.method_210(minion);
            this.world.method_210(minion);
            this.world.method_210(minion);
            if (this.health <= 0)
            {
                AetherMod.currentBoss = null;
                this.chatLine("§bSuch bitter cold... is this the feeling... of pain?");
                this.setDoor(0);
                this.unlockTreasure();
            }
        }
        return flag;
    }

    @Override
    protected void method_933()
    {
        this.method_1327(new ItemStack(AetherItems.Key, 1, 2), 0.0f);
    }

    private void setDoor(final int ID)
    {
        if (this.direction / 2 == 0)
        {
            for (int y = this.orgY - 1; y < this.orgY + 2; ++y)
            {
                for (int z = this.orgZ - 1; z < this.orgZ + 2; ++z)
                {
                    this.world.method_154(this.orgX + ((this.direction == 0) ? -11 : 11), y, z, ID, 2);
                }
            }
        }
        else
        {
            for (int y = this.orgY - 1; y < this.orgY + 2; ++y)
            {
                for (int x = this.orgX - 1; x < this.orgX + 2; ++x)
                {
                    this.world.method_154(x, y, this.orgZ + ((this.direction == 3) ? 11 : -11), ID, 2);
                }
            }
        }
    }

    private void unlockTreasure()
    {
        if (this.direction / 2 == 0)
        {
            for (int y = this.orgY - 1; y < this.orgY + 2; ++y)
            {
                for (int z = this.orgZ - 1; z < this.orgZ + 2; ++z)
                {
                    this.world.method_200(this.orgX + ((this.direction == 0) ? 11 : -11), y, z, 0);
                }
            }
        }
        else
        {
            for (int y = this.orgY - 1; y < this.orgY + 2; ++y)
            {
                for (int x = this.orgX - 1; x < this.orgX + 2; ++x)
                {
                    this.world.method_200(x, y, this.orgZ + ((this.direction == 3) ? -11 : 11), 0);
                }
            }
        }
        AetherMod.giveAchievement(AetherAchievements.defeatGold);
        for (int x2 = this.orgX - 20; x2 < this.orgX + 20; ++x2)
        {
            for (int y2 = this.orgY - 3; y2 < this.orgY + 6; ++y2)
            {
                for (int z2 = this.orgZ - 20; z2 < this.orgZ + 20; ++z2)
                {
                    final int id = this.world.getBlockId(x2, y2, z2);
                    if (id == AetherBlocks.LOCKED_DUNGEON_STONE.id)
                    {
                        this.world.method_154(x2, y2, z2, AetherBlocks.DUNGEON_STONE.id, this.world.getBlockMeta(x2, y2, z2));
                    }
                    if (id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
                    {
                        this.world.method_154(x2, y2, z2, AetherBlocks.LIGHT_DUNGEON_STONE.id, this.world.getBlockMeta(x2, y2, z2));
                    }
                }
            }
        }
    }

    @Override
    public int getBossHP()
    {
        return this.health;
    }

    @Override
    public int getBossMaxHP()
    {
        return 50;
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
        return this.bossName + ", the Sun Spirit";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("SunSpirit");
    }
}
