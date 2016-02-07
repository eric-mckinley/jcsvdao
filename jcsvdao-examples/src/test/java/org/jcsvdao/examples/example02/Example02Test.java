package org.jcsvdao.examples.example02;

import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.examples.example02.model.Address;
import org.jcsvdao.examples.example02.model.CompanyDetail;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class Example02Test {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
        CSVDaoFactory factory = new CSVDaoFactory("/example02/csv-config.xml");
        dao = new CSVDao(factory);
    }

    @Test
    public void find_allCompanies_noErrors() {
        List<CompanyDetail> companies = dao.find(CompanyDetail.class);
        assertEquals(companies.size(), 3L);
    }

    @Test
    public void find_allAddresses_noErrors() {
        List<Address> addresses = dao.find(Address.class);
        assertEquals(addresses.size(), 2L);
    }

    @Test
    public void find_companyByPK_noErrors() {
        CompanyDetail apple = dao.get(CompanyDetail.class, "Apple");
        assertNotNull(apple);
        assertEquals("Apple", apple.getName());
        assertEquals("Steve Jobs", apple.getFounder());
    }

}
