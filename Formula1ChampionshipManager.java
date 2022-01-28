import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Formula1ChampionshipManager implements ChampionshipManager{

    public static ArrayList<Formula1Driver> formula1DriversList= new ArrayList<>();// this contains the objects of drivers in the list
    public static ArrayList<Race> racesList= new ArrayList<>();//this contains the all races have completed

    public Formula1ChampionshipManager(){//default constructor for the formula1ChampionshipManager

    }

    public void addNewDriver(){
        // to add a new driver for the drivers list
        System.out.println("\n🏁----Add Driver----🏁");
        Scanner input =new Scanner(System.in);
        System.out.print("Enter the Name of the driver: ");
        String name = input.nextLine();//get the driver name to add

        if(driverNotIn(name)) {//checking driver is already in the race
            System.out.print("Enter the driver location: ");//if driver is not in, the user can see these input getting and should insert them to add a driver
            String location = input.nextLine();//get input for driver location
            System.out.print("Enter the Name of the Team: ");
            String team = input.nextLine();//gets input for driver team

            if (teamNotIn(team)) {//checking team is unique and add the driver to the list if the driver has a unique team
                formula1DriversList.add(new Formula1Driver(name, location, team));
                System.out.println("✅ Driver successfully added to the list.");
            } else {
                System.out.println("⚠️Team is already registered.");// this will inform if the team is already registered
            }
        }else{
            System.out.println("⚠️"+name+" Already in the race!");//this will print if driver already in
        }

    }
    private boolean teamNotIn(String team){
        // to check the team is unique or not, return true when it is unique
        for(Formula1Driver driver : formula1DriversList){
            if(driver.getTeam().equals(team)){
                return false;// if team already in the list
            }
        }
        return true;//if team not already in the list
    }
    public boolean driverNotIn(String name){
        // to check the team is unique or not, return true when it is unique
        for(Formula1Driver driver : formula1DriversList){
            if(driver.getName().equals(name)){
                return false;// if driver already in the list
            }
        }
        return true;//if driver not in the list yet
    }

    public void deleteDriver(){
        //method to delete the driver from the list
        System.out.println("\n🏁-----Delete Driver----🏁");
        Scanner input= new Scanner(System.in);
        Boolean notInTheList=true;//to check the entered driver in the list
        System.out.print("Enter the name: ");
        String name=input.nextLine();
        for(Formula1Driver driver : formula1DriversList){
            if(driver.getName().equals(name)){//if given name has a driver
                formula1DriversList.remove(driver);//remove the given driver from the list
                System.out.println("✅"+name+" removed successfully.");
                notInTheList=false;//if the driver is in the list make the variable false
                break;
            }
        }
        if(notInTheList){//this will run if the user input doesn't have a driver!
            System.out.println("⚠️The driver is not in the list");
        }
    }
    public void changeTeam(){
        //this method to change the team driver of a current team
        System.out.println("\n🏁----Change Team Driver ----🏁");
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the Team: ");//get input for team to change the player
        String team=input.nextLine();

        if(!teamNotIn(team)) {//check the team is already in
            for (Formula1Driver driver:formula1DriversList) {
                if(driver.getTeam().equals(team)){//if the input has a team get the inputs for the new driver
                    System.out.print("Enter the new driver name:");
                    String name=input.nextLine();
                    if(driverNotIn(name)) {// adding the driver if driver is unique
                        System.out.print("Enter the new location name:");//get input for the location
                        String location = input.nextLine();
                        driver.setName(name);//changing the name of the driver
                        driver.setLocation(location);//changing the location of driver
                        System.out.println("✅ Changed Successfully!");
                    }else{
                        System.out.println("⚠️Your driver already has a team!");
                    }
                    break;
                }
            }
        }else{//if team not in the list inform them
            System.out.println("⚠️The team is not in the list, you entered!");
        }
    }

    public void searchDriver(){
        //this method to search a driver from his name and display the statics and details about him
        System.out.println("\n🏁------Search Driver------🏁");
        Scanner input =new Scanner(System.in);
        System.out.print("Enter the Driver Name: ");
        String name=input.nextLine();//get the input for the driver name for search
        boolean notInTheList=true;//to check not in the list for a given input

        for (Formula1Driver driver:formula1DriversList) {
            if (driver.getName().equals(name)){//if the searching input has a driver show his details and stats
                notInTheList=false;//if this runs the driver has found, and he is in the list then make the variable false.
                System.out.println("Location            > "+driver.getLocation());//displays the location
                System.out.println("Team                > "+driver.getTeam());//displays the team
                System.out.println("Num of Races        > "+driver.getNumOfRaces());//displays the number of races
                System.out.println("Num of Points       > "+driver.getNumOfPoints());//displays the number of points
                System.out.println("Num of First Places > "+driver.getFirstPosition());//displays the number of first places
                System.out.println("Num of Second Places> "+driver.getSecondPosition());//displays the number of second places
                System.out.println("Num of Third Places > "+driver.getThirdPosition());//displays the number of third positions
                break;
            }
        }
        if(notInTheList){//inform them their input hasn't a driver
            System.out.println("⚠️"+name+" not in the list.");
        }
    }
    public void addRace() {
        //to add a race
        System.out.println("\n🏁--------Add Race--------🏁");
        Scanner input =new Scanner(System.in);
        Formula1Driver[] winnersSorted=new Formula1Driver[formula1DriversList.size()];//to sort the race placed drivers according tot the place
        String date;//for the date
        do {//validating and getting date
            System.out.print("Enter the race date(dd/MM/yyyy): ");//get input for race date
            date=input.nextLine();
        }while(!isValidDate(date));


        for(int i=0;i<formula1DriversList.size();i++){
            System.out.print("Enter the name of place "+(i+1)+" : ");
            String DriverName=input.nextLine();
            if(driverNotIn(DriverName)){//checking the user inputs a driver name not in the list
                System.out.println("⚠️Driver not in the list");
                i--;//count not increasing cursor keeps in the same index in next calculation

            }else if(alreadyHasPlace(winnersSorted,DriverName,i)){//checking driver is repeating in the race for positions-third parameter is filling index
                System.out.println("⚠️Already has a place");
                i--;//count not increasing cursor keeps in the same index in next calculation
            }else{
                winnersSorted[i]=driverForName(DriverName);//adding winners for the list respectively for the index
                driverForName(DriverName).setNumOfPointsByPlace(i+1);//adding points matches and places if it in first three
            }
        }
        try {
            racesList.add(new Race(date,winnersSorted));//adding race to the race list
            System.out.println("✅ Added Race Successfully!");
        } catch (ParseException e) {//runs when error got when adding the driver
            System.out.println("⚠️Problem found!");
            e.printStackTrace();
        }
        saveRaceData();
    }
    private boolean alreadyHasPlace(Formula1Driver[] winners,String driver,int AlreadyFilledPlaces){
        //this method for help add race method
        if(winners.length>0){
            for (int i=0;i<AlreadyFilledPlaces;i++) {
                if(winners[i].getName().equals(driver)){
                    return true;//return true when driver already has a race place
                }
            }
        }
        return false;
    }
    private boolean isValidDate(String dateString) {
        //this method to validate the date inputted in string
        DateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");//format should be date in
        simpleDateFormat.setLenient(false);// checking the date is 100% correct format
        try {
            simpleDateFormat.parse(dateString);//checking
        } catch (ParseException e) {
            System.out.println("⚠️Invalid Date");//if not correct passing the msg and return false
            return false;

        }
        return true;//return true when the date is correct format
    }
    private Formula1Driver driverForName(String name){
        //this method give the output driver object for driver name
        for (Formula1Driver driver:formula1DriversList) {
            if (driver.getName().equals(name)){
                return driver;//returning the driver found
            }
        }

        return new Formula1Driver(name,"Empty","Empty");//returning this if name hasn't a driver

    }

    public void displayPointsTable(){
        //method to display the points table of drivers
        System.out.println("🏁-----Points Table-----🏁");
        Collections.sort(formula1DriversList,new PointComparator().reversed());//sorting the drivers array list descending order by points
        System.out.println("---------------------------------------------------------------------------------------");
        System.out.println("|    Driver    |  Team Name  |   Location   | Races | Points | First | Second | Third |");
        System.out.println("---------------------------------------------------------------------------------------");
        for (Formula1Driver driver:formula1DriversList) {//runs for all drivers and made the table by string formatting
            System.out.printf("| %12s | %11s | %12s | %5d | %6d | %5d | %6d | %5d |%n",driver.getName(),driver.getTeam(),driver.getLocation(),driver.getNumOfRaces(),driver.getNumOfPoints(),driver.getFirstPosition(),
                    driver.getSecondPosition(),driver.getThirdPosition());
        }
        System.out.println("---------------------------------------------------------------------------------------");
    }
    public void saveData(){
        //this method save the drivers data
        File f1=new File("Drivers.txt");//this is the file got driver data
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(f1);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(formula1DriversList);//write data to the file
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {//inform if got an error when the saving process
            e.printStackTrace();
            System.out.println("⚠️Error occurred on saving.");
        }
    }
    public void saveRaceData(){
        //this method to save race data
        File f1=new File("Races.txt");//this file contains race data
        FileOutputStream fileOutputStream= null;
        try {
            fileOutputStream = new FileOutputStream(f1);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(racesList);//write data to the file
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {//inform if got an error when the saving process
            e.printStackTrace();
            System.out.println("⚠️Error occurred on saving.");
        }
    }

    public void loadRaceData(){
        //this method to load race data from the file
        try {
            FileInputStream fileInputStream=new FileInputStream("Races.txt");//file contains race data
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            racesList= (ArrayList<Race>) objectInputStream.readObject();//getting the file values to the raceList array list
            objectInputStream.close();
            fileInputStream.close();

        } catch (EOFException ex1) {//inform if got an error when the loading process
            System.out.println("⚠️Error!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️Error occurred on loading.");
            e.printStackTrace();
        }

    }
    public void loadData(){
        //this method to load driver data from the file
        try {
            FileInputStream fileInputStream=new FileInputStream("Drivers.txt");//file contains driver data
            ObjectInputStream objectInputStream=new ObjectInputStream(fileInputStream);
            formula1DriversList= (ArrayList<Formula1Driver>) objectInputStream.readObject();//getting values from the file and inserting them to the drivers list
            objectInputStream.close();
            fileInputStream.close();

        } catch (EOFException ex1) {//inform if got an error when the loading process
            System.out.println("⚠️Error");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️Error occurred on loading.");
            e.printStackTrace();
        }

    }

}
