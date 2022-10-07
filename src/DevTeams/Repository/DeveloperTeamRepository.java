package DevTeams.Repository;

import DevTeams.Data.Developer;
import DevTeams.Data.DeveloperTeam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperTeamRepository {
    private List<DeveloperTeam> _devTeamRepo = new ArrayList<>() {};
    public DeveloperRepository _dRepo;
    private Integer _Count = 0;
    public DeveloperTeamRepository(DeveloperRepository devRepo) {
        _dRepo=devRepo;
    }
    public boolean AddDevTeam(DeveloperTeam devTeam) {
        return devTeam == null ? false : AddToDatabase(devTeam);
    }
    private boolean AddToDatabase(DeveloperTeam devTeam) {
        _Count++;
        devTeam.setId(_Count);
        _devTeamRepo.add(devTeam);
        return true;
    }
    public List<DeveloperTeam>GetAllDevTeams(){
        var developers = _devTeamRepo.stream().toList();
        return developers;
    }
    public Optional<DeveloperTeam> GetDeveloperTeam(int id){
        var team = _devTeamRepo.stream().filter(t->t.getId()==id).findFirst();
        return (team == null)?   null: team;
    }
    public boolean UpdateDeveloperTeam(int id, DeveloperTeam devTeamData){
        var team = GetDeveloperTeam(id);
        return team == null ? false :  UpdateTeamCredentials(devTeamData, team);
    }
    private static boolean UpdateTeamCredentials(DeveloperTeam devTeamData, Optional<DeveloperTeam> team) {
        team.get().setDevelopers(devTeamData.getDevelopers());
        team.get().setDevTeamName(devTeamData.getDevTeamName());
        return true;
    }
    public boolean DeleteDeveloperTeam(int id){
        var devTeam = GetDeveloperTeam(id);
        return (devTeam==null)? false : RemoveFromDb(devTeam);
    }
    private boolean RemoveFromDb(Optional<DeveloperTeam> devTeam) {
        _devTeamRepo.remove(devTeam.get());
        return true;
    }
    public boolean AddDevToTeam_List(int teamId, List<Developer> devs){

        var team = GetDeveloperTeam(teamId);
        if(team == null) return false;

        if(devs == null) return false;

        team.get().setDevelopers(devs);
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
