package org.jcsvdao.examples.example05.model;

import java.util.List;

/**
 * Created by ericmckinley on 07/02/2016.
 *
 * @RentBadger
 */
public class SportsTeam {

    private Integer teamId;
    private String teamName;
    private String sport;
    private List<TeamMember> members;

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }
}
