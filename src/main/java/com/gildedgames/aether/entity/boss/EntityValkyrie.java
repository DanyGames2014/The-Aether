package com.gildedgames.aether.entity.boss;

import com.gildedgames.aether.AetherMod;
import com.gildedgames.aether.entity.base.EntityDungeonMob;
import com.gildedgames.aether.entity.base.IAetherBoss;
import com.gildedgames.aether.entity.mobs.EntityHomeShot;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.util.Identifier;

public class EntityValkyrie extends EntityDungeonMob implements IAetherBoss
{
    public boolean isSwinging;
    public boolean boss;
    public boolean duel;
    public boolean hasDungeon;
    public int teleTimer;
    public int angerLevel;
    public int timeLeft;
    public int chatTime;
    public int dungeonX;
    public int dungeonY;
    public int dungeonZ;
    public int dungeonEntranceZ;
    public double safeX;
    public double safeY;
    public double safeZ;
    public float sinage;
    public double lastMotionY;
    public String bossName;

    public EntityValkyrie(final World level)
    {
        super(level);
        this.setBoundingBoxSpacing(0.8f, 1.6f);
        this.texture = "aether:textures/entity/valkyrie.png";
        this.teleTimer = this.random.nextInt(250);
        this.health = 50;
        this.movementSpeed = 0.5f;
        this.timeLeft = 1200;
        this.attackStrength = 7;
        this.safeX = this.x;
        this.safeY = this.y;
        this.safeZ = this.z;
    }

    public EntityValkyrie(final World world, final double x, final double y, final double z, final boolean flag)
    {
        super(world);
        this.setBoundingBoxSpacing(0.8f, 1.6f);
        this.bossName = NameGen.gen();
        this.texture = "aether:textures/entity/valkyrie.png";
        if (flag)
        {
            this.texture = "aether:textures/entity/valkyrie2.png";
            this.health = 500;
            this.boss = true;
        }
        else
        {
            this.health = 50;
        }
        this.teleTimer = this.random.nextInt(250);
        this.movementSpeed = 0.5f;
        this.timeLeft = 1200;
        this.attackStrength = 7;
        this.x = x;
        this.safeX = x;
        this.y = y;
        this.safeY = y;
        this.z = z;
        this.safeZ = z;
        this.hasDungeon = false;
    }

    public void method_1389(final float height)
    {
    }

    public void tick()
    {
        this.lastMotionY = this.velocityY;
        super.tick();
        if (!this.field_1623 && this.target != null && this.lastMotionY >= 0.0 && this.velocityY < 0.0 && this.method_1351(this.target) <= 16.0f && this.method_928(this.target))
        {
            final double a = this.target.x - this.x;
            final double b = this.target.z - this.z;
            final double angle = Math.atan2(a, b);
            this.velocityX = Math.sin(angle) * 0.25;
            this.velocityZ = Math.cos(angle) * 0.25;
        }
        if (!this.field_1623 && !this.method_932() && Math.abs(this.velocityY - this.lastMotionY) > 0.07 && Math.abs(this.velocityY - this.lastMotionY) < 0.09)
        {
            this.velocityY += 0.054999999701976776;
            if (this.velocityY < -0.2750000059604645)
            {
                this.velocityY = -0.2750000059604645;
            }
        }
        this.movementSpeed = ((this.target == null) ? 0.5f : 1.0f);
        if (this.world.field_213 <= 0 && (this.target != null || this.angerLevel > 0))
        {
            this.angerLevel = 0;
            this.target = null;
        }
        if (this.isSwinging)
        {
            this.field_1027 += 0.15f;
            this.field_1035 += 0.15f;
            if (this.field_1027 > 1.0f || this.field_1035 > 1.0f)
            {
                this.isSwinging = false;
                this.field_1027 = 0.0f;
                this.field_1035 = 0.0f;
            }
        }
        if (!this.field_1623)
        {
            this.sinage += 0.75f;
        }
        else
        {
            this.sinage += 0.15f;
        }
        if (this.sinage > 6.283186f)
        {
            this.sinage -= 6.283186f;
        }
        if (!this.otherDimension())
        {
            --this.timeLeft;
            if (this.timeLeft <= 0)
            {
                this.dead = true;
                this.method_919();
            }
        }
    }

    public boolean otherDimension()
    {
        return true;
    }

    public void teleport(double x, double y, double z, final int rad)
    {
        int a = this.random.nextInt(rad + 1);
        int b = this.random.nextInt(rad / 2);
        int c = rad - a;
        a *= this.random.nextInt(2) * 2 - 1;
        b *= this.random.nextInt(2) * 2 - 1;
        c *= this.random.nextInt(2) * 2 - 1;
        x += a;
        y += b;
        z += c;
        int newX = (int) Math.floor(x - 0.5);
        int newY = (int) Math.floor(y - 0.5);
        int newZ = (int) Math.floor(z - 0.5);
        boolean flag = false;
        for (int q = 0; q < 32 && !flag; ++q)
        {
            final int i = newX + (this.random.nextInt(rad / 2) - this.random.nextInt(rad / 2));
            final int j = newY + (this.random.nextInt(rad / 2) - this.random.nextInt(rad / 2));
            final int k = newZ + (this.random.nextInt(rad / 2) - this.random.nextInt(rad / 2));
            if (j <= 124)
            {
                if (j >= 5)
                {
                    if (this.isAirySpace(i, j, k) && this.isAirySpace(i, j + 1, k) && !this.isAirySpace(i, j - 1, k) && (!this.hasDungeon || (i > this.dungeonX && i < this.dungeonX + 20 && j > this.dungeonY && j < this.dungeonY + 12 && k > this.dungeonZ && k < this.dungeonZ + 20)))
                    {
                        newX = i;
                        newY = j;
                        newZ = k;
                        flag = true;
                    }
                }
            }
        }
        if (!flag)
        {
            this.teleFail();
        }
        else
        {
            this.method_919();
            this.method_1340(newX + 0.5, newY + 0.5, newZ + 0.5);
            this.velocityX = 0.0;
            this.velocityY = 0.0;
            this.velocityZ = 0.0;
            this.field_1029 = 0.0f;
            this.field_1060 = 0.0f;
            this.jumping = false;
            this.pitch = 0.0f;
            this.yaw = 0.0f;
            this.method_635(null);
            this.field_1012 = this.random.nextFloat() * 360.0f;
            this.method_919();
            this.teleTimer = this.random.nextInt(40);
        }
    }

    public boolean isAirySpace(final int x, final int y, final int z)
    {
        final int p = this.world.getBlockId(x, y, z);
        return p == 0 || Block.BLOCKS[p].getCollisionShape(this.world, x, y, z) == null;
    }

    public boolean method_940()
    {
        return !this.boss;
    }

    public boolean method_1323(final PlayerEntity entityplayer)
    {
        this.method_924(entityplayer, 180.0f, 180.0f);
        if (!this.boss)
        {
            if (this.timeLeft >= 1200)
            {
                final ItemStack itemstack = entityplayer.getHand();
                if (itemstack != null && itemstack.itemId == AetherItems.VictoryMedal.id && itemstack.count >= 0)
                {
                    if (itemstack.count >= 10)
                    {
                        this.chatItUp("Umm... that's a nice pile of medallions you have there...");
                    }
                    else if (itemstack.count >= 5)
                    {
                        this.chatItUp("That's pretty impressive, but you won't defeat me.");
                    }
                    else
                    {
                        this.chatItUp("You think you're a tough guy, eh? Well, bring it on!");
                    }
                }
                else
                {
                    final int pokey = this.random.nextInt(3);
                    if (pokey == 2)
                    {
                        this.chatItUp("What's that? You want to fight? Aww, what a cute little human.");
                    }
                    else if (pokey == 1)
                    {
                        this.chatItUp("You're not thinking of fighting a big, strong Valkyrie are you?");
                    }
                    else
                    {
                        this.chatItUp("I don't think you should bother me, you could get really hurt.");
                    }
                }
            }
        }
        else if (this.duel)
        {
            this.chatItUp("If you wish to challenge me, strike at any time.");
        }
        else if (!this.duel)
        {
            final ItemStack itemstack = entityplayer.getHand();
            if (itemstack != null && itemstack.itemId == AetherItems.VictoryMedal.id && itemstack.count >= 10)
            {
                final ItemStack itemInstance = itemstack;
                itemInstance.count -= 10;
                if (itemstack.count <= 0)
                {
                    itemstack.method_700(entityplayer);
                    entityplayer.method_503();
                    this.chatTime = 0;
                    this.chatItUp("Very well, attack me when you wish to begin.");
                    this.duel = true;
                }
            }
            else
            {
                this.chatItUp("Show me 10 victory medals, and I will fight you.");
            }
        }
        return true;
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

    public void makeHomeShot(final int shots, final LivingEntity ep)
    {
        for (int i = 0; i < shots; ++i)
        {
            final EntityHomeShot e1 = new EntityHomeShot(this.world, this.x - this.velocityX / 2.0, this.y, this.z - this.velocityZ / 2.0, ep);
            this.world.method_210(e1);
        }
    }

    protected void method_933()
    {
        if (this.boss)
        {
            this.method_1327(new ItemStack(AetherItems.Key, 1, 1), 0.0f);
            this.method_1339(Item.GOLDEN_SWORD.id, 1);
        }
        else
        {
            this.method_1339(AetherItems.VictoryMedal.id, 1);
        }
    }

    public void method_910()
    {
        super.method_910();
        ++this.teleTimer;
        if (this.teleTimer >= 450)
        {
            if (this.target != null)
            {
                if (this.boss && this.field_1623 && this.random.nextInt(2) == 0 && this.target != null && this.target instanceof LivingEntity)
                {
                    this.makeHomeShot(1, (LivingEntity) this.target);
                    this.teleTimer = -100;
                }
                else
                {
                    this.teleport(this.target.x, this.target.y, this.target.z, 7);
                }
            }
            else if (!this.field_1623 || this.boss)
            {
                this.teleport(this.safeX, this.safeY, this.safeZ, 6);
            }
            else
            {
                this.teleport(this.x, this.y, this.z, 12 + this.random.nextInt(12));
            }
        }
        else if (this.teleTimer < 446 && (this.y <= 0.0 || this.y <= this.safeY - 16.0))
        {
            this.teleTimer = 446;
        }
        else if (this.teleTimer % 5 == 0 && this.target != null && !this.method_928(this.target))
        {
            this.teleTimer += 100;
        }
        if (this.field_1623 && this.teleTimer % 10 == 0 && !this.boss)
        {
            this.safeX = this.x;
            this.safeY = this.y;
            this.safeZ = this.z;
        }
        if (this.target != null && this.target.dead)
        {
            this.target = null;
            if (this.boss)
            {
                this.unlockDoor();
                AetherMod.currentBoss = null;
            }
            this.angerLevel = 0;
        }
        if (this.chatTime > 0)
        {
            --this.chatTime;
        }
    }

    public void swingArm()
    {
        if (!this.isSwinging)
        {
            this.isSwinging = true;
            this.field_1027 = 0.0f;
            this.field_1035 = 0.0f;
        }
    }

    public void teleFail()
    {
        this.teleTimer -= this.random.nextInt(40) + 40;
        if (this.y <= 0.0)
        {
            this.teleTimer = 446;
        }
    }

    @Override
    public boolean canSpawn()
    {
        final int i = MathHelper.floor(this.x);
        final int j = MathHelper.floor(this.boundingBox.minY);
        final int k = MathHelper.floor(this.z);
        return this.world.method_252(i, j, k) > 8 && this.world.canSpawnEntity(this.boundingBox) && this.world.method_190(this, this.boundingBox).size() == 0 && !this.world.method_218(this.boundingBox);
    }

    @Override
    public void writeNbt(final NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putShort("Anger", (short) this.angerLevel);
        tag.putShort("TeleTimer", (short) this.teleTimer);
        tag.putShort("TimeLeft", (short) this.timeLeft);
        tag.putBoolean("Duel", this.duel);
        tag.putInt("DungeonX", this.dungeonX);
        tag.putInt("DungeonY", this.dungeonY);
        tag.putInt("DungeonZ", this.dungeonZ);
        tag.putInt("DungeonEntranceZ", this.dungeonEntranceZ);
        tag.put("SafePos", (NbtElement) this.method_1329(this.safeX, this.safeY, this.safeZ));

        tag.putBoolean("Boss", this.boss);
        if (this.boss)
        {
            tag.putBoolean("IsCurrentBoss", this.isCurrentBoss());
            tag.putString("BossName", this.bossName);
        }
    }

    @Override
    public void readNbt(final NbtCompound tag)
    {
        super.readNbt(tag);
        this.angerLevel = tag.getShort("Anger");
        this.teleTimer = tag.getShort("TeleTimer");
        this.timeLeft = tag.getShort("TimeLeft");
        this.duel = tag.getBoolean("Duel");
        this.boss = tag.getBoolean("Boss");
        this.dungeonX = tag.getInt("DungeonX");
        this.dungeonY = tag.getInt("DungeonY");
        this.dungeonZ = tag.getInt("DungeonZ");
        this.dungeonEntranceZ = tag.getInt("DungeonEntranceZ");
        if (this.boss)
        {
            this.texture = "aether:textures/entity/valkyrie2.png";
            this.bossName = tag.getString("BossName");
            if (tag.getBoolean("IsCurrentBoss"))
            {
                //todo: boss refactor
                AetherMod.currentBoss = this;
            }

        }
        final NbtList nbttaglist = tag.getList("SafePos");
        this.safeX = ((NbtDouble) nbttaglist.get(0)).value;
        this.safeY = ((NbtDouble) nbttaglist.get(1)).value;
        this.safeZ = ((NbtDouble) nbttaglist.get(2)).value;
    }

    @Override
    protected Entity method_638()
    {
        if (this.otherDimension() && (this.world.field_213 <= 0 || (this.boss && !this.duel) || this.angerLevel <= 0))
        {
            return null;
        }
        return super.method_638();
    }

    @Override
    public boolean damage(final Entity target, final int amount)
    {
        if (!(target instanceof PlayerEntity) || this.world.field_213 <= 0)
        {
            this.teleport(this.x, this.y, this.z, 8);
            this.fire = 0;
            return false;
        }
        if (this.boss && (!this.duel || this.world.field_213 <= 0))
        {
            this.method_919();
            final int pokey = this.random.nextInt(2);
            if (pokey == 2)
            {
                this.chatItUp("Sorry, I don't fight with weaklings.");
            }
            else
            {
                this.chatItUp("Try defeating some weaker valkyries first.");
            }
            return false;
        }
        if (this.boss)
        {
            if (this.target == null)
            {
                AetherMod.currentBoss = this;
                this.chatTime = 0;
                this.chatItUp("This will be your final battle!");
            }
            else
            {
                this.teleTimer += 60;
            }
        }
        else if (this.target == null)
        {
            this.chatTime = 0;
            final int pokey = this.random.nextInt(3);
            if (pokey == 2)
            {
                this.chatItUp("I'm not going easy on you!");
            }
            else if (pokey == 1)
            {
                this.chatItUp("You're gonna regret that!");
            }
            else
            {
                this.chatItUp("Now you're in for it!");
            }
        }
        else
        {
            this.teleTimer -= 10;
        }
        this.becomeAngryAt(target);
        final boolean flag = super.damage(target, amount);
        if (flag && this.health <= 0)
        {
            final int pokey2 = this.random.nextInt(3);
            this.dead = true;
            if (this.boss)
            {
                this.dead = false;
                this.unlockDoor();
                this.unlockTreasure();
                this.chatItUp("You are truly... a mighty warrior...");
                AetherMod.currentBoss = null;
            }
            else if (pokey2 == 2)
            {
                this.chatItUp("Alright, alright! You win!");
            }
            else if (pokey2 == 1)
            {
                this.chatItUp("Okay, I give up! Geez!");
            }
            else
            {
                this.chatItUp("Oww! Fine, here's your medal...");
            }
            this.method_919();
        }
        return flag;
    }

    @Override
    protected void method_637(final Entity target, final float f)
    {
        if (this.field_1042 <= 0 && f < 2.75f && target.boundingBox.maxY > this.boundingBox.minY && target.boundingBox.minY < this.boundingBox.maxY)
        {
            this.field_1042 = 20;
            this.swingArm();
            target.damage(this, this.attackStrength);
            if (target != null && this.target != null && target == this.target && target instanceof LivingEntity)
            {
                final LivingEntity e1 = (LivingEntity) target;
                if (e1.health <= 0)
                {
                    this.target = null;
                    this.angerLevel = 0;
                    final int pokey = this.random.nextInt(3);
                    this.chatTime = 0;
                    if (this.boss)
                    {
                        this.chatItUp("As expected of a human.");
                        this.unlockDoor();
                        AetherMod.currentBoss = null;
                    }
                    else if (pokey == 2)
                    {
                        this.chatItUp("You want a medallion? Try being less pathetic.");
                    }
                    else if (pokey == 1 && e1 instanceof PlayerEntity)
                    {
                        final PlayerEntity ep = (PlayerEntity) e1;
                        final String s = ep.name;
                        this.chatItUp("Maybe some day, " + s + "... maybe some day.");
                    }
                    else
                    {
                        this.chatItUp("Humans aren't nearly as cute when they're dead.");
                    }
                }
            }
        }
    }

    private void becomeAngryAt(final Entity entity)
    {
        this.target = entity;
        this.angerLevel = 200 + this.random.nextInt(200);
        if (this.boss)
        {
            for (int k = this.dungeonZ + 2; k < this.dungeonZ + 23; k += 7)
            {
                if (this.world.getBlockId(this.dungeonX - 1, this.dungeonY, k) == 0)
                {
                    this.dungeonEntranceZ = k;
                    this.world.method_154(this.dungeonX - 1, this.dungeonY, k, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.world.method_154(this.dungeonX - 1, this.dungeonY, k + 1, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.world.method_154(this.dungeonX - 1, this.dungeonY + 1, k + 1, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    this.world.method_154(this.dungeonX - 1, this.dungeonY + 1, k, AetherBlocks.LOCKED_DUNGEON_STONE.id, 1);
                    return;
                }
            }
        }
    }

    private void unlockDoor()
    {
        this.world.method_200(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ, 0);
        this.world.method_200(this.dungeonX - 1, this.dungeonY, this.dungeonEntranceZ + 1, 0);
        this.world.method_200(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ + 1, 0);
        this.world.method_200(this.dungeonX - 1, this.dungeonY + 1, this.dungeonEntranceZ, 0);
    }

    private void unlockTreasure()
    {
        this.world.method_154(this.dungeonX + 16, this.dungeonY + 1, this.dungeonZ + 9, Block.TRAPDOOR.id, 3);
        this.world.method_154(this.dungeonX + 17, this.dungeonY + 1, this.dungeonZ + 9, Block.TRAPDOOR.id, 2);
        this.world.method_154(this.dungeonX + 16, this.dungeonY + 1, this.dungeonZ + 10, Block.TRAPDOOR.id, 3);
        this.world.method_154(this.dungeonX + 17, this.dungeonY + 1, this.dungeonZ + 10, Block.TRAPDOOR.id, 2);
        AetherMod.giveAchievement(AetherAchievements.defeatSilver);
        for (int x = this.dungeonX - 26; x < this.dungeonX + 29; ++x)
        {
            for (int y = this.dungeonY - 1; y < this.dungeonY + 22; ++y)
            {
                for (int z = this.dungeonZ - 5; z < this.dungeonZ + 25; ++z)
                {
                    final int id = this.world.getBlockId(x, y, z);
                    if (id == AetherBlocks.LOCKED_DUNGEON_STONE.id)
                    {
                        this.world.method_154(x, y, z, AetherBlocks.DUNGEON_STONE.id, this.world.getBlockMeta(x, y, z));
                    }
                    if (id == AetherBlocks.TRAPPED_DUNGEON_STONE.id)
                    {
                        this.world.method_154(x, y, z, AetherBlocks.DUNGEON_STONE.id, this.world.getBlockMeta(x, y, z));
                    }
                    if (id == AetherBlocks.LOCKED_LIGHT_DUNGEON_STONE.id)
                    {
                        this.world.method_154(x, y, z, AetherBlocks.LIGHT_DUNGEON_STONE.id, this.world.getBlockMeta(x, y, z));
                    }
                }
            }
        }
    }

    public void setDungeon(final int i, final int j, final int k)
    {
        this.hasDungeon = true;
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
        return this.bossName + ", the Valkyrie Queen";
    }

    @Override
    public Identifier getHandlerIdentifier()
    {
        return AetherMod.MODID.id("Valkyrie");
    }
}
