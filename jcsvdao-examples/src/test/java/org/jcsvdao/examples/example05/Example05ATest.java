package org.jcsvdao.examples.example05;

import org.jcsvdao.CSVDao;
import org.jcsvdao.CSVDaoFactory;
import org.jcsvdao.criteria.Check;
import org.jcsvdao.criteria.NormalCriteria;
import org.jcsvdao.examples.example04.model.Chef;
import org.jcsvdao.examples.example04.model.Restaurant;
import org.jcsvdao.examples.example05.model.SportsTeam;
import org.jcsvdao.examples.example05.model.TeamMember;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 */
public class Example05ATest {

    private static CSVDao dao;

    @BeforeClass
    public static void setup() {
        CSVDaoFactory factory = new CSVDaoFactory("/example05A/csv-config.xml");
        dao = new CSVDao(factory);
    }

    @Test
    public void find_allTeams_noErrors() {
        List<SportsTeam> teams = dao.find(SportsTeam.class);
        assertEquals(teams.size(), 3L);
    }

    @Test
    public void find_allPlayers_noErrors() {
        List<TeamMember> players = dao.find(TeamMember.class);
        assertEquals(players.size(), 15L);
    }

    @Test
    public void find_teamHasMembers_noErrors() {
        SportsTeam dartsTeam = dao.findUnique(SportsTeam.class, new NormalCriteria("sport", Check.EQ, "Darts"));
        SportsTeam quizTeam = dao.findUnique(SportsTeam.class, new NormalCriteria("sport", Check.EQ, "Pub Quiz"));
        SportsTeam bowlsTeam = dao.findUnique(SportsTeam.class, new NormalCriteria("sport", Check.EQ, "Bowling"));

        assertNotNull(dartsTeam);
        assertNotNull(dartsTeam.getMembers());
        assertEquals(dartsTeam.getMembers().size(), 5L);

        assertNotNull(quizTeam);
        assertNotNull(quizTeam.getMembers());
        assertEquals(quizTeam.getMembers().size(), 2L);

        assertNotNull(bowlsTeam);
        assertNotNull(bowlsTeam.getMembers());
        assertEquals(bowlsTeam.getMembers().size(), 8L);
    }

    @Test
    public void find_playerHasTeam_noErrors() {
        TeamMember member = dao.get(TeamMember.class, 1004);

        assertNotNull(member);
        assertNotNull(member.getCurrentTeam());
        assertEquals("The Last Throw", member.getCurrentTeam().getTeamName());
    }

}
