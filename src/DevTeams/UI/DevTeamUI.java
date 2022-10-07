package DevTeams.UI;

import DevTeams.Data.DeveloperTeam;

import java.util.Scanner;

import static DevTeams.UI.ProgramUI.ClearConsole;

public class DevTeamUI {
    private Scanner scanner = new Scanner(System.in);


    public void Run(){
        RunApplication();
    }

    private void RunApplication() {
        boolean isRunning=true;
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

    }

    private void DeleteExistingDeveloperTeam() {

    }

    private void UpdateExistingDeveloperTeamDetails() {

    }

    private void ViewDeveloperTeamDetails() {

    }

    private void ViewAllExistingDeveloperTeams() {

    }

    private void AddDeveloperToAnExistingDevTeam() {

    }

    private void AddDeveloperTeamToDatabase() {

    }
}
