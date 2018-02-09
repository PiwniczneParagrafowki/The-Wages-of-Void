package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface ItemService {

    List<Item> getAllHeroItems(Hero hero);

    List<Item> getAllHeroItems(long heroId);

    void giveItemToHero(Hero hero, Item item);

    void giveItemToHero(long heroId, Item item);

    void giveItemToHero(Hero hero, String itemName, int amount);

    void giveItemToHero(long heroId, String itemName, int amount);

    boolean checkIfHeroHasAnItem(Hero hero, String itemName, int amount);

    boolean checkIfHeroHasAnItem(long heroId, String itemName, int amount);

    void removeItemFromHeroInventory(Hero hero, String itemName, int amount);

    void removeItemFromHeroInventory(long heroId, String itemName, int amount);

}
