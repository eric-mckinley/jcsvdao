package org.jcsvdao.examples.example01;

import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.criteria.Check;
import org.jcsvdao.criteria.NormalCriteria;
import org.jcsvdao.examples.example01.model.Gender;
import org.jcsvdao.examples.example01.model.UserDetail;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

/**
 * Demonstrates a CSV file that each row maps to a UserDetail object
 * A boolean converter is supplied to handle Yes, No as boolean values
 * A date converter with specific format is supplied for registration date
 * A Custom Enum converter is created and mapped to the gender property.
 */
public class Example01Test {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
        CSVDaoFactory factory = new CSVDaoFactory("/example01/csv-config.xml");
        dao = new CSVDao(factory);
    }

    @Test
    public void find_allEntries_noErrors() {
        List<UserDetail> users = dao.find(UserDetail.class);
        assertEquals(users.size(), 6L);
    }

    @Test
    public void find_byEmail_oneEntry(){
        UserDetail user = dao.findUnique(UserDetail.class, new NormalCriteria("email", Check.EQ, "helen@test.com"));
        assertNotNull(user);
        assertEquals("helen@test.com",user.getEmail() );
    }

    @Test
    public void find_byGenderWoman_twoEntries(){
        List<UserDetail> users = dao.find(UserDetail.class, new NormalCriteria("gender", Check.EQ, Gender.FEMALE));
        assertEquals(users.size(), 2L);
    }

    @Test
    public void find_byMalePremium_twoEntries(){
        NormalCriteria maleSearch =  new NormalCriteria("gender", Check.EQ, Gender.MALE);
        NormalCriteria premiumSearch =  new NormalCriteria("premiumUser", Check.EQ, Boolean.TRUE);
        List<UserDetail> users = dao.find(UserDetail.class, maleSearch, premiumSearch );
        assertEquals(users.size(), 2L);
    }

    @Test
    public void find_byAgeOver21_threeEntries(){
        List<UserDetail> users = dao.find(UserDetail.class, new NormalCriteria("age", Check.GT, 21));
        assertEquals(users.size(), 4L);
    }

}
