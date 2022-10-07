package DevTeams.Repository;

import DevTeams.Data.Developer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DeveloperRepository {
    private List<Developer> _developers = new ArrayList<>();
    private  Integer _Count = 0;
//c
    public boolean addDeveloper(Developer developer){
        return (developer == null)? false : addToRepository(developer);
    }
    private boolean addToRepository(Developer developer) {
        _Count++;
        developer.setId(_Count);
        _developers.add(developer);
        return true;
    }
//r
    public Optional<Developer> getDeveloperDetails(int id){
       var developer = _developers.stream().filter(d->d.getId()==id).findAny();
       return developer;
    }
//r
    public List<Developer>getAllDevelopers(){
        return _developers;
    }
//u
    public boolean updateDeveloperDetails(int id, Developer devDetails){
        var developer = getDeveloperDetails(id);

        developer.get().setSkillSets(devDetails.getSkillSets());
        developer.get().setHasPluralSight(devDetails.getHasPluralSight());
        developer.get().setFirstName(devDetails.getFirstName());
        developer.get().setLastName(devDetails.getLastName());
        return true;
    }
//d
    public  boolean deleteDeveloper(int id){
        var developer = getDeveloperDetails(id);
        if(developer==null)return false;
        else _developers.remove(developer.get());
        return true;
    }
}
