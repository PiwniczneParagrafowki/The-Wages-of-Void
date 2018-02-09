package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;

import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface ItemService {

    List<Item> getAllHeroItems(long heroId);

    Item giveItemToHero(long heroId, String itemName, int amount);

    boolean checkIfHeroHasAnItem(long heroId, String itemName, int expectedAmount);

    void removeItemFromHeroInventory(long heroId, String itemName, int amount);

}
