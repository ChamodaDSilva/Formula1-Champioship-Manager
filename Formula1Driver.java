import java.io.Serializable;

public class Formula1Driver extends Driver  implements Serializable{
    private static final long serialVersionUID=1L;//serial version for serializable

    private int firstPosition;//number of first positions of the driver
    private int secondPosition;//number of second positions of the driver
    private int thirdPosition;//number of third positions of the driver

    private int numOfPoints;//number of points of the driver
    private int numOfRaces;//number of races of the driver

    public Formula1Driver(String name, String location, String team) {
        super(name, location, team);//passing required values for the super class constructor
        firstPosition=0;//set number of first positions for 0 when making a driver
        secondPosition=0;//set number of second positions for 0 when making a driver
        thirdPosition=0;//set number of third positions for 0 when making a driver
        numOfPoints=0;//set number pf points when making a driver
        numOfRaces=0;//set number of faced races for 0 at the start
    }

    public Formula1Driver(String name, String location, String team, int firstPosition, int secondPosition, int thirdPosition, int numOfPoints, int numOfRaces) {
        //another constructor can pass the statics to, made for future use
        super(name, location, team);//passing required values for the super class constructor
        this.firstPosition = firstPosition;
        this.secondPosition = secondPosition;
        this.thirdPosition = thirdPosition;
        this.numOfPoints = numOfPoints;
        this.numOfRaces = numOfRaces;
    }

    public void setNumOfPointsByPlace(int place){
        //this method to update the all formula1driver stats from the place automatically
        numOfRaces++;// increase the number of matches played for every player.
        //giving points according to the place
        if(place==1){
            numOfPoints+=25;
            firstPosition+=1;// increase the number of first positions for the driver by 1
        }else if(place==2){
            numOfPoints+=18;
            secondPosition+=1;//increase the number of second positions by 1
        }else if(place==3){
            numOfPoints+=15;
            thirdPosition+=1;//increase the number of third positions by 1
        }else if(place==4){
            numOfPoints+=12;
        }else if(place==5){
            numOfPoints+=10;
        }else  if(place==6){
            numOfPoints+=8;
        }else if(place==7){
            numOfPoints+=6;
        }else if(place==8){
            numOfPoints+=4;
        }else if(place==9){
            numOfPoints+=2;
        }else if(place==10){
            numOfPoints+=1;
        }
    }

    public int getFirstPosition() {
        //getter for number of first positions
        return firstPosition;
    }

    public int getSecondPosition() {
        //getter for number of second positions
        return secondPosition;
    }

    public int getThirdPosition() {
        //getter for number of third positions
        return thirdPosition;
    }

    public int getNumOfPoints() {
        //getter for number of points
        return numOfPoints;
    }

    public int getNumOfRaces() {
        //getter for number of races
        return numOfRaces;
    }
}
