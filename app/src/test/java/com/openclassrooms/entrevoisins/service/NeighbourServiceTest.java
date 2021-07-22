package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;
import java.util.Objects;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedNeighbours.toArray())));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void createNeighbourWithSuccess() {
        Neighbour neighbourToCreate = new Neighbour(13,"fake_name", "fake_avatar_url", "fake_address", "fake_phone_number", "fake_about_me");
        service.createNeighbour(neighbourToCreate);
        assertTrue(service.getNeighbours().contains(neighbourToCreate));
    }

    @Test
    public void getFavoritesWithSuccess() {
        List<Neighbour> favorites = service.getFavorites();
        List<Neighbour> expectedFavorites = DummyNeighbourGenerator.DUMMY_FAVORITES;
        assertThat(favorites, IsIterableContainingInAnyOrder.containsInAnyOrder(Objects.requireNonNull(expectedFavorites.toArray())));
    }

    @Test
    public void addFavoriteWithSuccess() {
        Neighbour favoriteToAdd = service.getNeighbours().get(0);
        service.addFavorite(favoriteToAdd);
        assertTrue(service.getFavorites().contains(favoriteToAdd));
    }

    @Test
    public void deleteFavoriteWithSuccess() {
        Neighbour favoriteToDelete = service.getNeighbours().get(0);
        service.addFavorite(favoriteToDelete);
        assertTrue(service.getFavorites().contains(favoriteToDelete));
        service.deleteFavorite(favoriteToDelete);
        assertFalse(service.getFavorites().contains(favoriteToDelete));
    }
}