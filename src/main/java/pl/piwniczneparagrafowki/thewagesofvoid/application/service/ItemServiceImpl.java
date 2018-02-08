package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.springframework.stereotype.Service;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.CharacterRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ItemRepository;

import javax.annotation.Resource;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    CharacterRepository characterRepository;

    @Resource
    ItemRepository itemRepository;

    @Override
    public void giveItemToCharacter(Character character, Item item) {
        Item itemInInventory = itemRepository.findByNameAndCharacter(item.getName(), character);
        if(itemInInventory!=null) {
            item.setAmount(itemInInventory.getAmount() + item.getAmount());
        }
        item.setCharacter(character);
        itemRepository.save(item);
    }
}
