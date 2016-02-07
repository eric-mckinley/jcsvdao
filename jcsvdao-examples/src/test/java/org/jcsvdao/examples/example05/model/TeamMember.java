package org.jcsvdao.examples.example05.model;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 * @RentBadger
 */
public class TeamMember {

    private Integer playerNumber;
    private String name;
    private Boolean injured;
    private SportsTeam currentTeam;

    public SportsTeam getCurrentTeam() {
        return currentTeam;
    }

    public void setCurrentTeam(SportsTeam currentTeam) {
        this.currentTeam = currentTeam;
    }

    public Integer getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(Integer playerNumber) {
        this.playerNumber = playerNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getInjured() {
        return injured;
    }

    public void setInjured(Boolean injured) {
        this.injured = injured;
    }
}
