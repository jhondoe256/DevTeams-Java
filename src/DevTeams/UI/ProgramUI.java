package DevTeams.UI;

import DevTeams.Data.Developer;
import DevTeams.Data.DeveloperTeam;
import DevTeams.Data.Enums.DeveloperType;
import DevTeams.Repository.DeveloperRepository;
import DevTeams.Repository.DeveloperTeamRepository;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProgramUI {
    private Scanner scanner = new Scanner(System.in);
    private DeveloperRepository _devRepo = new DeveloperRepository();
    private DeveloperTeamRepository _devTeamRepo = new DeveloperTeamRepository(_devRepo);
    public void Run(){
        SeedDevelopers();
        RunApplication();
    }
    private void SeedDevelopers() {
        ArrayList<DeveloperType> devTypeA = new ArrayList<>();
        devTypeA.add(DeveloperType.FRONTEND);
      var devA=  new Developer(1,"Bill","Burr",true,devTypeA);

        ArrayList<DeveloperType> devtypeB = new ArrayList<>();
        devtypeB.add(DeveloperType.FRONTEND);
        devtypeB.add(DeveloperType.BACKEND);
      var devB=  new Developer(2,"Mr.","Magoo",false,devtypeB);


      var devC=  new Developer(3,"Mike","Tyson",true);

        _devRepo.addDeveloper(devA);
        _devRepo.addDeveloper(devB);
        _devRepo.addDeveloper(devC);

        var TeamA = new DeveloperTeam();
        TeamA.setDevelopers(List.of(devA,devB));
        _devTeamRepo.AddDevTeam(TeamA);
    }
    private void RunApplication() {
        boolean isRunning = true;
        while(isRunning){
            System.out.println("-- Welcome to DevTeams --\n" +
                    "Please make a Selection: \n" +
                    "1.  Add Developer to Database\n" +
                    "2.  View all Existing Developers\n" +
                    "3.  View Developer Details By Id\n" +
                    "4.  Update Existing Developer Details\n" +
                    "5.  Delete Existing Developer\n" +
                    "6.  Load Developer Team UI\n" +
                    "50. Close Application\n");

            var userInput = scanner.next();
            switch (userInput){
                case "1":
                case "one":
                   ClearConsole();
                   AddDeveloperToDataBase();
                    break;
                case "2":
                case "two":
                    ClearConsole();
                    ViewAllExistingDevelopers();
                    break;
                case "3":
                case "three":
                    ClearConsole();
                    ViewDeveloperDetailsById();
                    break;
                case "4":
                case "four":
                    ClearConsole();
                    UpdateExistingDeveloperDetails();
                    break;
                case "5":
                case "five":
                    ClearConsole();
                    DeleteExistingDeveloper();
                    break;
                case "6":
                case "six":
                    ClearConsole();
                    LoadDeveloperTeamUI();
                    break;
                case "50":
                case "quit":
                    isRunning = CloseApplication();
            }
        }
    }
    private static boolean CloseApplication() {
        boolean isRunning;
        isRunning=false;
        System.out.println("Thank you for using DevTeams!");
        return isRunning;
    }
    private void DeleteExistingDeveloper() {
        System.out.print("Please enter an existing Developer Id: ");
        var userInputDevId = scanner.nextInt();
        var developer = _devRepo.getDeveloperDetails(userInputDevId);
        if(developer == null) System.out.println("Sorry the developer with the Id: "+ userInputDevId + " "+ "doesn't Exist.");
        else{
            if(_devRepo.deleteDeveloper(userInputDevId))
                System.out.println("Developer was Successfully Deleted.");
            else
                System.out.println("Developer Failed to be Deleted.");
        }
        TypeContinueToProceed();
    }
    private void UpdateExistingDeveloperDetails() {
        System.out.print("Please enter an existing Developer Id: ");
        var userInputDevId = scanner.nextInt();
        var developer = _devRepo.getDeveloperDetails(userInputDevId);
        if(developer == null) System.out.println("Sorry the developer with the Id: "+ userInputDevId + " "+ "doesn't Exist.");
        else{
            ArrayList<DeveloperType> devTypeList = new ArrayList<>();
            Developer developerForDb= new Developer();

            System.out.print("Please Input Developer First Name: ");
            var firstName = scanner.next();
            developerForDb.setFirstName(firstName);

            System.out.print("Please Input Developer Last Name: ");
            var lastName = scanner.next();
            developerForDb.setLastName(lastName);

            System.out.print("Does this Developer have Pluralsight? Y/N: ");
            var hasPlural = scanner.next();
            if(hasPlural.startsWith("y")){
                developerForDb.setHasPluralSight(true);
            }
            else{
                developerForDb.setHasPluralSight(false);
            }

            boolean addedSkillSets = false;
            while (!addedSkillSets){
                System.out.println("What kind of Developer are you?\n" +
                        "1. Front End\n" +
                        "2. Back End\n");
                var userInput = scanner.next();
                switch (userInput){
                    case "1":
                        devTypeList.add(DeveloperType.FRONTEND);
                        break;
                    case "2":
                        devTypeList.add(DeveloperType.BACKEND);
                        break;
                }
                System.out.println("Do you want to add another Skill Set? y/n");
                var answer = scanner.next();
                if(!answer.startsWith("y")) {
                    developerForDb.setSkillSets(devTypeList);
                    addedSkillSets=true;
                }
            }
            if(_devRepo.updateDeveloperDetails(developer.get().getId(),developerForDb))
                System.out.println("Developer"+ developerForDb.getFirstName() +" "+ developerForDb.getLastName() + "was Successfully Updated!");
                else
                System.out.println("Sorry, the Update of the Developer Could Not Be Processed.");
        }
        TypeContinueToProceed();
    }
    private void ViewDeveloperDetailsById() {
        System.out.print("Please enter an existing Developer Id: ");
        var userInputDevId = scanner.nextInt();
        var developer = _devRepo.getDeveloperDetails(userInputDevId);
        if(developer == null) System.out.println("Sorry the developer with the Id: "+ userInputDevId + " "+ "doesn't Exist.");
        else
            System.out.println(developer.toString());

        TypeContinueToProceed();
    }
    private void TypeContinueToProceed() {
        System.out.println("Type: continue, and press ENTER to proceed.");
        if( scanner.next() == "continue")
            ClearConsole();
        RunApplication();
    }
    private void ViewAllExistingDevelopers() {
          var developers = _devRepo.getAllDevelopers();
          if(developers.stream().count()>0){
              developers.stream().forEach(d-> System.out.println(d.toString()));
          }
          else{
              System.out.println("Sorry There Are No Available Developers.");
          }
        TypeContinueToProceed();

    }
    private void AddDeveloperToDataBase() {

        ArrayList<DeveloperType> devTypeList = new ArrayList<>();
        Developer developerForDb= new Developer();

        System.out.print("Please Input Developer First Name: ");
        var firstName = scanner.next();
        developerForDb.setFirstName(firstName);

        System.out.print("Please Input Developer Last Name: ");
        var lastName = scanner.next();
        developerForDb.setLastName(lastName);

        System.out.print("Does this Developer have Pluralsight? Y/N: ");
        var hasPlural = scanner.next();
        if(hasPlural.startsWith("y")){
            developerForDb.setHasPluralSight(true);
        }
        else{
            developerForDb.setHasPluralSight(false);
        }

        boolean addedSkillSets = false;
        while (!addedSkillSets){
            System.out.println("What kind of Developer are you?\n" +
                    "1. Front End\n" +
                    "2. Back End\n");
            var userInput = scanner.next();
            switch (userInput){
                case "1":
                    devTypeList.add(DeveloperType.FRONTEND);
                    break;
                case "2":
                    devTypeList.add(DeveloperType.BACKEND);
                    break;
            }
            System.out.println("Do you want to add another Skill Set? y/n");
            var answer = scanner.next();
            if(!answer.startsWith("y")) {
                developerForDb.setSkillSets(devTypeList);
                addedSkillSets=true;
            }
        }

        if(_devRepo.addDeveloper(developerForDb)){
            System.out.println("Developer"+ developerForDb.getFirstName() +" "+ developerForDb.getLastName() + "was Successfully Added!");
            ClearConsole();
        }
        else{
            System.out.println("Sorry Invalid Data, Developer could not be added to the database.");
            ClearConsole();
        }


    }
    public static void ClearConsole(){
        for(int i = 0; i<100;i++){
            System.out.println();
        }
    }
    private void LoadDeveloperTeamUI(){
        DevTeamUI UI = new DevTeamUI(_devTeamRepo);
        UI.Run();
    }
}
