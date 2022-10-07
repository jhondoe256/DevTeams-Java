package DevTeams.UI;

import DevTeams.Data.Developer;
import DevTeams.Data.DeveloperTeam;
import DevTeams.Repository.DeveloperTeamRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static DevTeams.UI.ProgramUI.ClearConsole;

public class DevTeamUI {
    private Scanner scanner = new Scanner(System.in);
    private DeveloperTeamRepository _devTeamRepo;
    private boolean isRunning=true;
    public DevTeamUI(DeveloperTeamRepository _devTeamRepo) {
        this._devTeamRepo = _devTeamRepo;
    }
    public void Run(){
        RunApplication();
    }
    private void RunApplication() {

        while (isRunning){
            System.out.println("-- Welcome to DevTeams (Developer Team Page)--\n" +
                    "Please make a Selection: \n" +
                    "1.  Add Developer Team to Database\n" +
                    "2.  Add Developer To An Existing DevTeam\n" +
                    "3.  View all Existing Developer Teams\n" +
                    "4.  View Developer Team Details\n" +
                    "5.  Update Existing Developer Team Details\n" +
                    "6.  Delete Existing Developer Team\n" +
                    "7.  Load Main UI\n");

            var userInput = scanner.next();
            switch (userInput){
                case"1":
                    ClearConsole();
                    AddDeveloperTeamToDatabase();
                    break;
                case"2":
                    ClearConsole();
                    AddDeveloperToAnExistingDevTeam();
                    break;
                case"3":
                    ClearConsole();
                    ViewAllExistingDeveloperTeams();
                    break;
                case"4":
                    ClearConsole();
                    ViewDeveloperTeamDetails();
                    break;
                case"5":
                    ClearConsole();
                    UpdateExistingDeveloperTeamDetails();
                    break;
                case"6":
                    ClearConsole();
                    DeleteExistingDeveloperTeam();
                    break;
                case"7":
                    LoadMainUI();
                    break;
            }

        }
    }
    private void LoadMainUI() {
        isRunning=false;
    }
    private void DeleteExistingDeveloperTeam() {
        System.out.print("Please enter an existing Developer Team Id: ");
        var userInputDevId = scanner.nextInt();
        var developerTeam = _devTeamRepo.GetDeveloperTeam(userInputDevId);
        if(developerTeam == null) System.out.println("Sorry the Developer Team with the Id: "+ userInputDevId + " "+ "doesn't Exist.");
        else{
            if(_devTeamRepo.DeleteDeveloperTeam(userInputDevId))
                System.out.println("Developer Team was Successfully Deleted.");
            else
                System.out.println("Developer Team Failed to be Deleted.");
        }
        TypeContinueToProceed();
    }
    private void UpdateExistingDeveloperTeamDetails() {
        System.out.print("Please enter an existing Developer Team Id: ");
        var userInputDevId = scanner.nextInt();
        var developerTeam = _devTeamRepo.GetDeveloperTeam(userInputDevId);
        if(developerTeam == null) System.out.println("Sorry the Developer Team with the Id: "+ userInputDevId + " "+ "doesn't Exist.");
        else{
            DeveloperTeam team = new DeveloperTeam();

            System.out.println("Please enter a DevTeam Name: ");
            var devTeamName= scanner.next();
            team.setDevTeamName(devTeamName);

            boolean positionsFilled = false;
            List<Developer> auxDevelopers = _devTeamRepo._dRepo.getAllDevelopers();
            List<Developer>userSelections = new ArrayList<>();
            while (!positionsFilled){
                System.out.println("Do you want to add any Developers to this team? y/n");
                var answer = scanner.next();
                if(!answer.startsWith("y")){
                    team.setDevelopers(userSelections);
                    if(_devTeamRepo.UpdateDeveloperTeam(developerTeam.get().getId(),team))
                        System.out.println("Team Was Succesfully Added To the Database.");
                    else
                        System.out.println("Sorry The Team Could not be added to the Database.");

                    positionsFilled=true;
                    TypeContinueToProceed();
                }else{
                    ClearConsole();
                    Integer _count= 0;
                    System.out.println("Who Do you want on your team?");
                    auxDevelopers.stream().forEach(aD-> System.out.println(aD.getId() +
                            " "+
                            aD.getFirstName()+
                            " "+
                            aD.getLastName().toUpperCase().toCharArray()[0]));

                    var userInputUserId = scanner.nextInt();
                    var developer = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                    if(developer ==null){
                        System.out.println("Sorry, that Developer is not in the database.");
                    }
                    userSelections.add(developer.get());
                    var value = auxDevelopers.indexOf(userInputUserId);
                    var developerInDb = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                    auxDevelopers.remove(developerInDb.get());
                }
            }
        }
    }
    private void ViewDeveloperTeamDetails() {
        System.out.println("Please input an Existing DevTeam Id: ");
        var teamId = scanner.nextInt();
        var team = _devTeamRepo.GetDeveloperTeam(teamId);
        if(team == null){
            System.out.println("Sorry the team id you listed doesn't match any in the database.");
        }
        System.out.println(team);
        TypeContinueToProceed();
    }
    private void ViewAllExistingDeveloperTeams() {
        System.out.println("-----DevTeams-----");
        var teams = _devTeamRepo.GetAllDevTeams();
        System.out.println(teams);
        TypeContinueToProceed();
    }
    private void AddDeveloperToAnExistingDevTeam() {
        System.out.println("Please input an Existing DevTeam Id: ");
        var teamId = scanner.nextInt();
        var team = _devTeamRepo.GetDeveloperTeam(teamId);
        if(team == null){
            System.out.println("Sorry the team id you listed doesn't match any in the database.");
        }
        boolean positionsFilled = false;
        List<Developer> auxDevelopers = _devTeamRepo._dRepo.getAllDevelopers();
        List<Developer>userSelections = new ArrayList<>();
        List<Developer> previousGrp = team.get().getDevelopers();
        for (var member:previousGrp) {
            userSelections.add(member);
        }
        while (!positionsFilled){
            System.out.println(team.toString());
            System.out.println("Do you want to add any Developers to this team? y/n");
            var answer = scanner.next();
            if(!answer.startsWith("y")){
               if(userSelections.stream().count()>0){
                   team.get().setDevelopers(userSelections);
                   if(_devTeamRepo.AddDevToTeam_List(teamId,userSelections))
                       System.out.println("Team Was Successfully Added To the Database.");
                   else
                       System.out.println("Sorry The Team Could not be added to the Database.");
               }

                positionsFilled=true;
                TypeContinueToProceed();
            }else{
                ClearConsole();
                System.out.println("Who Do you want on your team?");
                auxDevelopers.stream().forEach(aD-> System.out.println(aD.getId() +
                        " "+
                        aD.getFirstName()+
                        " "+
                        aD.getLastName().toUpperCase().toCharArray()[0]));

                var userInputUserId = scanner.nextInt();
                var developer = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                if(developer ==null){
                    System.out.println("Sorry, that Developer is not in the database.");
                }
                userSelections.add(developer.get());
                var value = auxDevelopers.indexOf(userInputUserId);
                var developerInDb = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                auxDevelopers.remove(developerInDb.get());
            }
        }
    }
    private void AddDeveloperTeamToDatabase() {
        DeveloperTeam team = new DeveloperTeam();

        System.out.println("Please enter a DevTeam Name: ");
        var devTeamName= scanner.next();
        team.setDevTeamName(devTeamName);

        boolean positionsFilled = false;
        List<Developer> auxDevelopers = _devTeamRepo._dRepo.getAllDevelopers();
        List<Developer>userSelections = new ArrayList<>();
        while (!positionsFilled){
            System.out.println("Do you want to add any Developers to this team? y/n");
            var answer = scanner.next();
            if(!answer.startsWith("y")){
                team.setDevelopers(userSelections);
                if(_devTeamRepo.AddDevTeam(team))
                    System.out.println("Team Was Succesfully Added To the Database.");
                else
                    System.out.println("Sorry The Team Could not be added to the Database.");

                positionsFilled=true;
                TypeContinueToProceed();
            }else{
                ClearConsole();
                Integer _count= 0;
                System.out.println("Who Do you want on your team?");
                auxDevelopers.stream().forEach(aD-> System.out.println(aD.getId() +
                        " "+
                        aD.getFirstName()+
                        " "+
                        aD.getLastName().toUpperCase().toCharArray()[0]));

                var userInputUserId = scanner.nextInt();
                var developer = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                if(developer ==null){
                    System.out.println("Sorry, that Developer is not in the database.");
                }
                userSelections.add(developer.get());
                var value = auxDevelopers.indexOf(userInputUserId);
                var developerInDb = _devTeamRepo._dRepo.getDeveloperDetails(userInputUserId);
                auxDevelopers.remove(developerInDb.get());
            }
        }


    }
    private void TypeContinueToProceed() {
        System.out.println("Type: continue, and press ENTER to proceed.");
        if( scanner.next() == "continue")
            ClearConsole();
        RunApplication();
    }
}
