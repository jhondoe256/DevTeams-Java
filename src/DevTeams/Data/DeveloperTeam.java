package DevTeams.Data;

import java.util.List;

public class DeveloperTeam {
    private Integer Id;
    private String DevTeamName;
    private List<Developer> Developers;
    public DeveloperTeam(Integer id, String devTeamName, List<Developer> developers) {
        Id = id;
        DevTeamName = devTeamName;
        Developers = developers;
    }
    public DeveloperTeam(Integer id, String devTeamName) {
        Id = id;
        DevTeamName = devTeamName;
    }
    public DeveloperTeam(){}
    public Integer getId() {
        return Id;
    }
    public List<Developer> getDevelopers() {
        return Developers;
    }
    public String getDevTeamName() {
        return DevTeamName;
    }
    public void setDevelopers(List<Developer> developers) {
        Developers = developers;
    }
    public void setDevTeamName(String devTeamName) {
        DevTeamName = devTeamName;
    }
    public void setId(Integer id) {
        Id = id;
    }
    @Override
    public String toString() {
        var string = "DevTeam Id: "+ getId()+"\n"+ "DevTeam Name: "+ getDevTeamName() + "\n" +
                     "-----------Developers----------------\n" + getDevelopers();
        return string;
    }
}
