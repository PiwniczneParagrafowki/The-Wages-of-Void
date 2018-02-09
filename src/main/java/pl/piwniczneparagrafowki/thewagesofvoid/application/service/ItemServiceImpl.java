package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.HeroRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ItemRepository;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    ItemRepository itemRepository;

    @Resource
    HeroRepository heroRepository;

    @Override
    public List<Item> getAllHeroItems(long heroId) {
        return itemRepository.findByHeroId(heroId);
    }

    @Override
    public Item giveItemToHero(long heroId, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if(itemInInventory!=null) {
            itemInInventory.setAmount(itemInInventory.getAmount()+amount);
            return itemRepository.save(itemInInventory);
        } else {
            Item item = new Item(itemName);
            item.setAmount(amount);
            item.setHero(heroRepository.findById(heroId));
            return itemRepository.save(item);
        }
    }

    @Override
    public boolean checkIfHeroHasAnItem(long heroId, String itemName, int expectedAmount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if((itemInInventory!=null) && (itemInInventory.getAmount() >= expectedAmount)) {
            return true;
        }
        return false;
    }

    @Override
    public void removeItemFromHeroInventory(long heroId, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if(itemInInventory!=null) {
            deleteTheDeclaredAmountOfItems(itemInInventory, amount);
        } else {
            throw new EmptyResultDataAccessException("DELETE FAILED: Item with name=" + itemName + " for hero with id=" + heroId + " not found in the database.", 1);
        }
    }

    private void deleteTheDeclaredAmountOfItems(Item itemInInventory, int amountToRemove) {
        if(itemInInventory.getAmount()>amountToRemove) {
            itemInInventory.setAmount(itemInInventory.getAmount()-amountToRemove);
            itemRepository.save(itemInInventory);
        } else {
            itemRepository.delete(itemInInventory);
        }
    }

}
