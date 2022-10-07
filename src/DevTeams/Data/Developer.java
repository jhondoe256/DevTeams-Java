package DevTeams.Data;

import DevTeams.Data.Enums.DeveloperType;

import java.util.List;

public class Developer {
    private Integer Id;
    private String FirstName;
    private String LastName;
    private Boolean HasPluralSight;
    private List<DeveloperType> SkillSets;

    public Developer(Integer id, String firstName, String lastName, Boolean hasPluralSight, List<DeveloperType> skillSets) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        HasPluralSight = hasPluralSight;
        SkillSets = skillSets;
    }
    public Developer(Integer id, String firstName, String lastName, Boolean hasPluralSight) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        HasPluralSight = hasPluralSight;
    }
    public Developer(){}
    public Integer getId() {
        return Id;
    }
    public String getFirstName() {
        return FirstName;
    }
    public String getLastName() {
        return LastName;
    }
    public List<DeveloperType> getSkillSets() {
        return SkillSets;
    }
    public Boolean getHasPluralSight() {
        return HasPluralSight;
    }
    public void setFirstName(String firstName) {
        FirstName = firstName;
    }
    public void setLastName(String lastName) {
        LastName = lastName;
    }
    public void setSkillSets(List<DeveloperType> skillSets) {
        SkillSets = skillSets;
    }
    public void setHasPluralSight(Boolean hasPluralSight) {
        HasPluralSight = hasPluralSight;
    }
    public void setId(Integer id) {
        Id = id;
    }
    @Override
    public String toString(){
        var developerDetails = "Id: "+getId() + "\n" + "Name: " + getFirstName() +" "+ getLastName() +"\n"+"------- Skill Sets ----------\n"+
               getSkillSets()+ "\n" + "Has Pluralsight: " + getHasPluralSight()+"\n"+"-------------------------\n" ;
        return developerDetails;
    }
}
