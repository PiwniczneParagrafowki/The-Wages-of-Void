package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Character;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public interface ItemService {

    void giveItemToCharacter(Character character, Item item);

}
