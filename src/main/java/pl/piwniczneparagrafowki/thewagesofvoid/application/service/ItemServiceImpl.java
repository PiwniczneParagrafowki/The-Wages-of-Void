package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
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
    public List<Item> getAllHeroItems(Hero hero) {
        return itemRepository.findByHero(hero);
    }

    @Override
    public List<Item> getAllHeroItems(long heroId) {
        return itemRepository.findByHeroId(heroId);
    }

    @Override
    public void giveItemToHero(Hero hero, Item item) {
        Item itemInInventory = itemRepository.findByNameAndHero(item.getName(), hero);
        if(itemInInventory!=null) {
            item.setAmount(itemInInventory.getAmount() + item.getAmount());
            itemRepository.save(itemInInventory);
        } else {
            item.setHero(hero);
            itemRepository.save(item);
        }
    }

    @Override
    public void giveItemToHero(long heroId, Item item) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(item.getName(), heroId);
        if(itemInInventory!=null) {
            itemInInventory.setAmount(itemInInventory.getAmount() + item.getAmount());
            itemRepository.save(itemInInventory);
        } else {
            item.setHero(heroRepository.findById(heroId));
            itemRepository.save(item);
        }
    }

    @Override
    public void giveItemToHero(Hero hero, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, hero.getId());
        if(itemInInventory!=null) {
            itemInInventory.setAmount(itemInInventory.getAmount() + amount);
            itemRepository.save(itemInInventory);
        } else {
            Item item = new Item(itemName);
            item.setHero(hero);
            item.setAmount(amount);
            itemRepository.save(item);
        }
    }

    @Override
    public void giveItemToHero(long heroId, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if(itemInInventory!=null) {
            itemInInventory.setAmount(itemInInventory.getAmount()+amount);
            itemRepository.save(itemInInventory);
        } else {
            Item item = new Item(itemName);
            item.setAmount(amount);
            item.setHero(heroRepository.findById(heroId));
            itemRepository.save(item);
        }
    }

    @Override
    public boolean checkIfHeroHasAnItem(Hero hero, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHero(itemName, hero);
        if((itemInInventory!=null) && (itemInInventory.getAmount() >= amount)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean checkIfHeroHasAnItem(long heroId, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if((itemInInventory!=null) && (itemInInventory.getAmount() >= amount)) {
            return true;
        }
        return false;
    }

    @Override
    public void removeItemFromHeroInventory(Hero hero, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHero(itemName, hero);
        if(itemInInventory!=null) {
            if(itemInInventory.getAmount()>amount) {
                itemInInventory.setAmount(itemInInventory.getAmount()-amount);
                itemRepository.save(itemInInventory);
            } else {
                itemRepository.delete(itemInInventory);
            }
        } else {
            throw new EmptyResultDataAccessException("DELETE FAILED: Item with name=" + itemName + " for hero with id=" + hero.getId() + " not found in the database.", 1);
        }
    }

    @Override
    public void removeItemFromHeroInventory(long heroId, String itemName, int amount) {
        Item itemInInventory = itemRepository.findByNameAndHeroId(itemName, heroId);
        if(itemInInventory!=null) {
            if(itemInInventory.getAmount()>amount) {
                itemInInventory.setAmount(itemInInventory.getAmount()-amount);
                itemRepository.save(itemInInventory);
            } else {
                itemRepository.delete(itemInInventory);
            }
        } else {
            throw new EmptyResultDataAccessException("DELETE FAILED: Item with name=" + itemName + " for hero with id=" + heroId + " not found in the database.", 1);
        }
    }


}
