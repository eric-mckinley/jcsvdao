package org.jcsvdao.examples.example04;

import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.examples.example03.model.PostgradStudent;
import org.jcsvdao.examples.example03.model.Student;
import org.jcsvdao.examples.example04.model.Chef;
import org.jcsvdao.examples.example04.model.Restaurant;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class Example04Test {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
        CSVDaoFactory factory = new CSVDaoFactory("/example04/csv-config.xml");
        dao = new CSVDao(factory);
    }

    @Test
    public void find_allChefs_noErrors() {
        List<Chef> chefs = dao.find(Chef.class);
        assertEquals(chefs.size(), 4L);
    }

    @Test
    public void find_allRestaurants_noErrors() {
        List<Restaurant> foodPlaces = dao.find(Restaurant.class);
        assertEquals(foodPlaces.size(), 4L);
    }

    @Test
    public void find_chefHasRestaurant_noErrors() {
        Chef chef = dao.get(Chef.class, 202);

        assertNotNull(chef);
        assertNotNull(chef.getEmployer());

        assertEquals("La Buffet", chef.getEmployer().getName());
        assertEquals("Paris", chef.getEmployer().getCity());
    }

    @Test
    public void find_restaurantHasChef_noErrors() {
        Restaurant restaurant = dao.get(Restaurant.class, "Grilltown");

        assertNotNull(restaurant);
        assertNotNull(restaurant.getHeadChef());
        assertEquals("Naomi Boils", restaurant.getHeadChef().getFullName());
    }

}
