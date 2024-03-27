package com.gildedgames.aether.item.accessory;

import com.gildedgames.aether.entity.base.EntityProjectileBase;
import com.gildedgames.aether.mixin.access.EntityRenderAccessor;
import com.gildedgames.aether.mixin.access.LivingAccessor;
import com.matthewperiut.accessoryapi.api.Accessory;
import com.matthewperiut.accessoryapi.api.render.AccessoryRenderer;
import com.matthewperiut.accessoryapi.api.render.HasCustomRenderer;
import com.matthewperiut.accessoryapi.impl.mixin.client.LivingEntityRendererAccessor;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.class_564;
import net.minecraft.client.Minecraft;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.modificationstation.stationapi.api.template.item.TemplateItem;
import net.modificationstation.stationapi.api.util.Identifier;
import org.lwjgl.opengl.GL11;

import java.util.List;
import java.util.Optional;

public class EnergyShield extends TemplateItem implements Accessory, HasCustomRenderer
{
    public EnergyShield(Identifier identifier)
    {
        super(identifier);
        this.setMaxCount(1);
        this.setMaxDamage(500);
    }

    @Override
    public String[] getAccessoryTypes(ItemStack item)
    {
        return new String[]{"shield"};
    }

    @Override
    public ItemStack tickWhileWorn(PlayerEntity playerBase, ItemStack itemInstance)
    {
        final World world = playerBase.world;
        if (world != null && !world.isRemote)
        {
            final List list1 = world.field_200;
            if (list1 != null && list1.size() > 0)
            {
                for (int i = 0; i < list1.size(); ++i)
                {
                    final PlayerEntity player = (PlayerEntity) list1.get(i);
                    boolean flag = false;
                    final net.minecraft.entity.player.PlayerInventory inv = player.inventory;
                    ItemStack shieldItem = null;
                    if (inv.armor.length > 4)
                    {
                        //flag = (inv != null && inv.armour[6] != null && inv.armour[6].itemId == AetherItems.RepShield.id);
                        shieldItem = inv.armor[6];
                    }
                    if (flag && (player.field_1623 || (player.field_1595 != null && player.field_1595.field_1623)) && ((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f)
                    {
                        /*if (!game.options.thirdPerson && player == game.player) {
                            this.renderShieldEffect(game);
                        }*/
                        final List list2 = world.getEntities(player, player.boundingBox.expand(4.0, 4.0, 4.0));
                        for (int j = 0; j < list2.size() && shieldItem != null && shieldItem.getDamage() < 500; ++j)
                        {
                            final Entity entity = (Entity) list2.get(j);
                            boolean flag2 = false;
                            if (entity instanceof EntityProjectileBase && entity.method_1351(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z))
                            {
                                final EntityProjectileBase proj = (EntityProjectileBase) entity;
                                if (proj.shooter == null || proj.shooter != player)
                                {
                                    final Entity dick = proj.shooter;
                                    proj.shooter = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null)
                                    {
                                        a = proj.x - dick.x;
                                        b = proj.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj.z - dick.z;
                                    }
                                    else
                                    {
                                        a = player.x - proj.x;
                                        b = player.y - proj.y;
                                        c = player.z - proj.z;
                                    }
                                    final double d = Math.sqrt(a * a + b * b + c * c);
                                    a /= -d;
                                    b /= -d;
                                    c /= -d;
                                    proj.velocityX = a * 0.75;
                                    proj.velocityY = b * 0.75 + 0.05;
                                    proj.velocityZ = c * 0.75;
                                    proj.setArrowHeading(proj.velocityX, proj.velocityY, proj.velocityZ, 0.8f, 0.5f);
                                    world.playSound((Entity) proj, "note.snare", 1.0f, (random.nextFloat() - random.nextFloat() * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k)
                                    {
                                        double d2 = -proj.velocityX * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj.velocityY * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj.velocityZ * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj.x, proj.y, proj.z, d2, e1, f1);
                                    }
                                }
                            }
                            else if (entity instanceof ArrowEntity && entity.method_1351(player) < 2.5f && (entity.prevX != entity.x || entity.prevY != entity.y || entity.prevZ != entity.z))
                            {
                                final ArrowEntity proj2 = (ArrowEntity) entity;
                                if (proj2.field_1576 == null || proj2.field_1576 != player)
                                {
                                    final Entity dick = proj2.field_1576;
                                    proj2.field_1576 = player;
                                    flag2 = true;
                                    double a;
                                    double b;
                                    double c;
                                    if (dick != null)
                                    {
                                        a = proj2.x - dick.x;
                                        b = proj2.boundingBox.minY - dick.boundingBox.minY;
                                        c = proj2.z - dick.z;
                                    }
                                    else
                                    {
                                        a = player.x - proj2.x;
                                        b = player.y - proj2.y;
                                        c = player.z - proj2.z;
                                    }
                                    final double d = Math.sqrt(a * a + b * b + c * c);
                                    a /= -d;
                                    b /= -d;
                                    c /= -d;
                                    proj2.velocityX = a * 0.75;
                                    proj2.velocityY = b * 0.75 + 0.15;
                                    proj2.velocityZ = c * 0.75;
                                    proj2.method_1291(proj2.velocityX, proj2.velocityY, proj2.velocityZ, 0.8f, 0.5f);
                                    world.playSound((Entity) proj2, "note.snare", 1.0f, (random.nextFloat() - random.nextFloat() * 0.4f + 0.8f) * 1.1f);
                                    for (int k = 0; k < 12; ++k)
                                    {
                                        double d2 = -proj2.velocityX * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        double e1 = -proj2.velocityY * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        double f1 = -proj2.velocityZ * 0.15000000596046448 + (random.nextFloat() - 0.5f) * 0.05f;
                                        d2 *= 0.625;
                                        e1 *= 0.625;
                                        f1 *= 0.625;
                                        world.addParticle("flame", proj2.x, proj2.y, proj2.z, d2, e1, f1);
                                    }
                                }
                            }
                            if (flag2 && shieldItem != null)
                            {
                                shieldItem.damage(1, null);
                                if (shieldItem.getDamage() >= 500)
                                {
                                    player.inventory.armor[6] = null;
                                }
                            }
                        }
                    }
                }
            }
        }
        return itemInstance;
    }

    AccessoryRenderer renderer;

    @Override
    public Optional<AccessoryRenderer> getRenderer()
    {
        return Optional.ofNullable(renderer);
    }

    @Override
    public void constructRenderer()
    {
        renderer = new EnergyShieldRenderer();
    }

    private static class EnergyShieldRenderer implements AccessoryRenderer
    {
        BipedEntityModel modelEnergyShield = new BipedEntityModel(1.25F);

        public void renderThirdPerson(PlayerEntity player, PlayerEntityRenderer renderer, ItemStack itemInstance, double x, double y, double z, float h, float v)
        {
            final ItemStack itemstack = player.inventory.getSelectedItem();
            modelEnergyShield.rightArmPose = (itemstack != null);
            modelEnergyShield.sneaking = player.method_1373();
            double d3 = y - player.eyeHeight;
            if (player.method_1373() && !(player instanceof PlayerEntity))
            {
                d3 -= 0.125;
            }
            doRenderEnergyShield(player, renderer, modelEnergyShield, x, d3, z, h, v);
            modelEnergyShield.sneaking = false;
            modelEnergyShield.rightArmPose = false;
        }

        public void renderFirstPerson(PlayerEntity player, PlayerEntityRenderer renderer, ItemStack itemInstance)
        {
            // todo: make this work

            Minecraft minecraft = (Minecraft) FabricLoader.getInstance().getGameInstance();

            if (!(player.field_1623 || (player.field_1595 != null && player.field_1595.field_1623)))
                return;
            if (!(((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f))
                return;
            if (minecraft.options.thirdPerson)
                return;

            final class_564 scaledresolution = new class_564(minecraft.options, minecraft.displayWidth, minecraft.displayHeight);
            final int i = scaledresolution.method_1857();
            final int j = scaledresolution.method_1858();
            GL11.glDisable(2929);
            GL11.glDepthMask(false);
            GL11.glBlendFunc(770, 771);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glDisable(3008);
            GL11.glEnable(2977);
            GL11.glEnable(3042);
            System.out.println("step 3");
            GL11.glBindTexture(3553, minecraft.textureManager.getTextureId("aether:textures/capes/shieldEffect.png"));
            final Tessellator tessellator = Tessellator.INSTANCE;
            tessellator.startQuads();
            tessellator.vertex(0.0, j, -90.0, 0.0, 1.0);
            tessellator.vertex(i, j, -90.0, 1.0, 1.0);
            tessellator.vertex(i, 0.0, -90.0, 1.0, 0.0);
            tessellator.vertex(0.0, 0.0, -90.0, 0.0, 0.0);
            tessellator.draw();
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
            GL11.glEnable(3008);
            GL11.glDisable(2977);
            GL11.glDisable(3042);
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        }

        private void doRenderEnergyShield(final LivingEntity entityliving, PlayerEntityRenderer playerRenderer, BipedEntityModel modelEnergyShield, final double d, final double d1, final double d2, final float f, final float f1)
        {
            GL11.glPushMatrix();
            GL11.glEnable(2884);
            modelEnergyShield.handWingProgress = ((LivingEntityRendererAccessor) playerRenderer).invoke820(entityliving, f1);
            modelEnergyShield.riding = entityliving.method_1360();
            try
            {
                final float f2 = entityliving.field_1013 + (entityliving.field_1012 - entityliving.field_1013) * f1;
                final float f3 = entityliving.prevYaw + (entityliving.yaw - entityliving.prevYaw) * f1;
                final float f4 = entityliving.prevPitch + (entityliving.pitch - entityliving.prevPitch) * f1;
                ((LivingEntityRendererAccessor) playerRenderer).invoke826(entityliving, d, d1, d2);
                final float f5 = ((LivingEntityRendererAccessor) playerRenderer).invoke828(entityliving, f1);
                ((LivingEntityRendererAccessor) playerRenderer).invoke824(entityliving, f5, f2, f1);
                final float f6 = 0.0625f;
                GL11.glEnable(32826);
                GL11.glScalef(-1.0f, -1.0f, 1.0f);
                ((LivingEntityRendererAccessor) playerRenderer).invoke823(entityliving, f1);
                GL11.glTranslatef(0.0f, -24.0f * f6 - 0.0078125f, 0.0f);
                float f7 = entityliving.field_1048 + (entityliving.field_1049 - entityliving.field_1048) * f1;
                final float f8 = entityliving.field_1050 - entityliving.field_1049 * (1.0f - f1);
                if (f7 > 1.0f)
                {
                    f7 = 1.0f;
                }
                GL11.glEnable(3008);
                if (setEnergyShieldBrightness((PlayerEntity) entityliving, playerRenderer, 0, f1))
                {
                    modelEnergyShield.render(f8, f7, f5, f3 - f2, f4, f6);
                    GL11.glDisable(3042);
                    GL11.glEnable(3008);
                }
                GL11.glDisable(32826);
            }
            catch (Exception exception)
            {
                exception.printStackTrace();
            }
            GL11.glEnable(2884);
            GL11.glPopMatrix();
        }

        protected boolean setEnergyShieldBrightness(final PlayerEntity player, PlayerEntityRenderer playerRenderer, final int i, final float f)
        {
            if (i != 0) return false;
            if ((player.field_1623 || (player.field_1595 != null && player.field_1595.field_1623)) && ((LivingAccessor) player).get1029() == 0.0f && ((LivingAccessor) player).get1060() == 0.0f)
            {
                ((EntityRenderAccessor) playerRenderer).invokeBindTexture("aether:textures/entity/energyGlow.png");
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            }
            else
            {
                GL11.glEnable(2977);
                GL11.glEnable(3042);
                GL11.glBlendFunc(770, 771);
                ((EntityRenderAccessor) playerRenderer).invokeBindTexture("aether:textures/entity/energyNotGlow.png");
            }
            return true;
        }
    }
}
