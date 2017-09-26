package com.dungeonstory.backend.rules;

import java.util.Optional;

import com.dungeonstory.backend.data.Character;
import com.dungeonstory.backend.data.CharacterEquipment;
import com.dungeonstory.backend.data.Shop;
import com.dungeonstory.backend.data.ShopEquipment;
import com.dungeonstory.backend.service.Services;

public class ShopRules {

    private ShopRules() {

    }

    public static void buyItem(Character character, Shop shop, ShopEquipment item, int quantity) {

        ShopEquipment shopEquipment = shop.getShopEquipments()
                                          .stream()
                                          .filter(equip -> equip.getEquipment().equals(item.getEquipment()))
                                          .findFirst()
                                          .orElse(null);

        if (shopEquipment != null) {

            //remove item from shop
            if (shopEquipment.getQuantity() > quantity) {
                shopEquipment.substractQuantity(quantity);
            } else if (shopEquipment.getQuantity() == quantity) {
                shop.getShopEquipments().remove(shopEquipment);
            } else {
                return;
            }
            Services.getShopService().saveOrUpdate(shop);

            //add item to character
            Optional<CharacterEquipment> equipOpt = character.getEquipment()
                                                             .stream()
                                                             .filter(equip -> equip.getEquipment().equals(item.getEquipment()))
                                                             .findFirst();
            if (equipOpt.isPresent()) {
                equipOpt.get().setQuantity(equipOpt.get().getQuantity() + quantity);
            } else {
                CharacterEquipment characterEquipment = new CharacterEquipment(character, item.getEquipment(), quantity,
                        (int) (item.getUnitPrice() * 0.8));
                character.getEquipment().add(characterEquipment);
            }
            long price = item.getUnitPrice() * quantity;
            character.setGold(character.getGold() - price);
            Services.getCharacterService().saveOrUpdate(character);
        }

        
    }

    public static void sellItem(Character character, Shop shop, CharacterEquipment item, int quantity) {

        CharacterEquipment characterEquipment = character.getEquipment()
                                                         .stream()
                                                         .filter(equip -> equip.getEquipment().equals(item.getEquipment()))
                                                         .findFirst()
                                                         .orElse(null);
        if (characterEquipment != null) {

            //remove item from character
            if (characterEquipment.getQuantity() > quantity) {
                characterEquipment.substractQuantity(quantity);
            } else if (characterEquipment.getQuantity() == quantity) {
                character.getEquipment().remove(characterEquipment);
            } else {
                return;
            }

            long value = item.getSellableValue() * quantity;
            character.setGold(character.getGold() + value);
            Services.getCharacterService().saveOrUpdate(character);

            //add item to shop
            Optional<ShopEquipment> equipOpt = shop.getShopEquipments()
                                                   .stream()
                                                   .filter(equip -> equip.getEquipment().equals(item.getEquipment()))
                                                   .findFirst();
            if (equipOpt.isPresent()) {
                equipOpt.get().addQuantity(quantity);
            } else {
                ShopEquipment shopEquipment = new ShopEquipment(shop, item.getEquipment(), quantity, (int) (item.getSellableValue() * 1.2));
                shop.getShopEquipments().add(shopEquipment);
            }
            Services.getShopService().saveOrUpdate(shop);
        }

    }

}
