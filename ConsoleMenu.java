import java.util.Locale;
import java.util.Scanner;

public class ConsoleMenu {//this class contains the menu of the application, runs in the main method
    public static void main(String[] args) {
        ChampionshipManager manager =new Formula1ChampionshipManager();//making an object from the formula1ChampionshipManger
        Scanner input = new Scanner(System.in);
        manager.loadData();//load drivers details from the file
        manager.loadRaceData();//load race details from the file
        String command;
        System.out.println("🏁 Welcome to Formula1 Championship Manager 🏁");
        while (true) {//while true for runs the program continues
            System.out.println("\n----------------------");
            System.out.println("|     Main Menu      |");
            System.out.println("----------------------");
            System.out.println("|A - Add a New Driver|");
            System.out.println("|D - Delete a Driver |");
            System.out.println("|C - Change the Team |");
            System.out.println("|S - Search a Driver |");
            System.out.println("|P - Points Table    |");
            System.out.println("|R - Add a Race      |");
            System.out.println("|G - Open GUI        |");
            System.out.println("|Q - Exit            |");
            System.out.println("----------------------");
            System.out.print(">> ");
            command = input.nextLine();//getting input for the command for feature.
            command=command.toLowerCase(Locale.ROOT);//setting command is lower case all time, then the lower case problem is handled

            switch (command) {
                case "a":
                    manager.addNewDriver();
                    manager.saveData();//save data after adding driver
                    break;
                case "d":
                    manager.deleteDriver();
                    manager.saveData();//saving data after deleting driver
                    break;
                case "c":
                    manager.changeTeam();//search team name and change driver
                    manager.saveData();//saving the data after changing team driver
                    break;
                case "s":
                    manager.searchDriver();//search driver by name
                    break;
                case "r":
                    manager.addRace();// add a race feature
                    manager.saveData();//save data for the file after a race
                    break;
                case "p":
                    manager.displayPointsTable();//display points table
                    break;
                case "g":
                    new TableGUI();//open the gui
                    manager.saveData();//save the data after gui operations
                    manager.saveRaceData();
                    break;
                case "q":
                    System.out.println("Shutting Down..❗");//shutting down the system inform
                    System.exit(0);//exits
                    break;
                default:// validated invalid inputs
                    System.out.println("⚠️Invalid Input❗");
                    break;
            }
        }
    }
}
