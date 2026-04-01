package xlorenz.tutorialmod.items.custom;

import com.google.common.collect.ImmutableMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import org.jspecify.annotations.Nullable;
import xlorenz.tutorialmod.items.ModArmorMaterials;

import java.util.List;
import java.util.Map;

public class ModArmorItem extends Item {
    public RegistryKey<EquipmentAsset> materialKey;

    private static final Map<RegistryKey<EquipmentAsset>, List<StatusEffectInstance>> MATERIAL_TO_EFFECT_MAP =
            (new ImmutableMap.Builder<RegistryKey<EquipmentAsset>, List<StatusEffectInstance>>())
                    .put(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL.assetId(),
                            List.of(new StatusEffectInstance(StatusEffects.HASTE,400,1,false,false),
                                    new StatusEffectInstance(StatusEffects.JUMP_BOOST,400,1,false,false)))
                    .build();


    public ModArmorItem(ArmorMaterial material, EquipmentType type, Settings settings) {
        super(settings.armor(material, type));
        this.materialKey = material.assetId();
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {

        if(!world.isClient()) {
            if(entity instanceof PlayerEntity player) {
                if(hasFullSuitOfArmorOn(player)) {
                    evaluateArmorEffects(player);
                }
            }
        }

        super.inventoryTick(stack, world, entity, slot);
    }

    private boolean hasFullSuitOfArmorOn(PlayerEntity player) {
        ItemStack head = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chest = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legs = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feet = player.getEquippedStack(EquipmentSlot.FEET);

        return !head.isEmpty() && !chest.isEmpty() && !legs.isEmpty() && !feet.isEmpty();
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for (Map.Entry<RegistryKey<EquipmentAsset>, List<StatusEffectInstance>> entry : MATERIAL_TO_EFFECT_MAP.entrySet()) {
            RegistryKey<EquipmentAsset> mapArmorMaterial = entry.getKey();
            List<StatusEffectInstance> mapStatusEffects = entry.getValue();

            if(hasCorrectArmorOn(mapArmorMaterial, player)) {
                addStatusEffectForMaterial(player, mapArmorMaterial, mapStatusEffects);
            }
        }
    }

    private void addStatusEffectForMaterial(PlayerEntity player, RegistryKey<EquipmentAsset> mapArmorMaterial, List<StatusEffectInstance> mapStatusEffects) {
        boolean hasPlayerEffect = mapStatusEffects.stream().allMatch(statusEffectInstance -> player.hasStatusEffect(statusEffectInstance.getEffectType()));

        if(!hasPlayerEffect) {
            for(StatusEffectInstance instance : mapStatusEffects) {
                player.addStatusEffect(new StatusEffectInstance(instance));
            }
        }
    }

    private boolean hasCorrectArmorOn(RegistryKey<EquipmentAsset> mapArmorMaterial, PlayerEntity player) {
        Item head = player.getEquippedStack(EquipmentSlot.HEAD).getItem();
        Item chest = player.getEquippedStack(EquipmentSlot.CHEST).getItem();
        Item legs = player.getEquippedStack(EquipmentSlot.LEGS).getItem();
        Item feet = player.getEquippedStack(EquipmentSlot.FEET).getItem();

        return head instanceof ModArmorItem && ((ModArmorItem) head).materialKey == mapArmorMaterial &&
                chest instanceof ModArmorItem &&((ModArmorItem) chest).materialKey == mapArmorMaterial &&
                legs instanceof ModArmorItem &&((ModArmorItem) legs).materialKey == mapArmorMaterial &&
                feet instanceof ModArmorItem && ((ModArmorItem) feet).materialKey == mapArmorMaterial;
    }

}
