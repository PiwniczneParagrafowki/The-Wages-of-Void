package pl.piwniczneparagrafowki.thewagesofvoid.application.service;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.EmptyResultDataAccessException;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Hero;
import pl.piwniczneparagrafowki.thewagesofvoid.application.model.Item;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.HeroRepository;
import pl.piwniczneparagrafowki.thewagesofvoid.application.repository.ItemRepository;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @Author Arkadiusz Parafiniuk
 * arkadiusz.parafiniuk@gmail.com
 */
public class ItemServiceTests {

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Mock
    ItemRepository itemRepository;

    @Mock
    HeroRepository heroRepository;

    @Mock
    Item item;

    @Mock
    List<Item> items;

    @Mock
    Hero hero;

    @InjectMocks
    ItemServiceImpl itemService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllHeroItemsByHeroId() {
        when(itemRepository.findByHeroId(anyLong())).thenReturn(items);

        assertEquals(items, itemService.getAllHeroItems(anyLong()));
    }

    @Test
    public void testGiveItemToHeroIfItemNotExistsInTheInventory() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(null);
        when(heroRepository.findById(anyLong())).thenReturn(hero);
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        assertEquals(item, itemService.giveItemToHero(1, "testItem", 3));
    }

    @Test
    public void testGiveItemToHeroIfItemExistsInTheInventory() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(item);
        when(heroRepository.findById(anyLong())).thenReturn(hero);
        when(itemRepository.save(any(Item.class))).thenReturn(item);

        assertEquals(item, itemService.giveItemToHero(1, "testItem", 3));
    }

    @Test
    public void testCheckIfHeroHasAnItemIfHas() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(new Item("testItem", 1, hero));

        assertTrue(itemService.checkIfHeroHasAnItem(1, "testItem", 1));
        assertFalse(itemService.checkIfHeroHasAnItem(1, "testItem", 2));
    }

    @Test
    public void testCheckIfHeroHasAnItemIfHasNot() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(null);

        assertFalse(itemService.checkIfHeroHasAnItem(1, "testItem", 1));
    }

    @Test
    public void testRemoveItemFromHeroIfItemExistsInInventoryInLargerAmount() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(new Item("testItem", 2, hero));

        itemService.removeItemFromHeroInventory(1, "testItem", 1);
        verify(itemRepository, times(1)).save(any(Item.class));
        verify(itemRepository, times(0)).delete(any(Item.class));
    }

    @Test
    public void testRemoveItemFromHeroIfLastItemExistsInInventory() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(new Item("testItem", 1, hero));

        itemService.removeItemFromHeroInventory(1, "testItem", 1);
        verify(itemRepository, times(1)).delete(any(Item.class));
        verify(itemRepository, times(0)).save(any(Item.class));
    }

    @Test
    public void testRemoveItemFromHeroAndThrowEmptyResultDataAccessException() {
        when(itemRepository.findByNameAndHeroId(anyString(), anyLong())).thenReturn(null);

        exception.expect(EmptyResultDataAccessException.class);
        itemService.removeItemFromHeroInventory(1, "testItem", 1);
    }


}
