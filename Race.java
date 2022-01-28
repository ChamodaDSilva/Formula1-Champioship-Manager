import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Race implements Serializable {
    private static final long serialVersionUID=2L;//the serial number for serializable

    private Date raceDay;//to store the race day of a race
    private Formula1Driver [] places; // first place is in 0 index and others i++

    public Race(String raceDay, Formula1Driver[] places) throws ParseException {
        //constructor for the race getting parameter as race day and the place with an array list
        this.raceDay = new SimpleDateFormat("dd/MM/yyyy").parse(raceDay);//converting string format into date format
        this.places = places;
    }

    public Date getRaceDay() {
        // getter for race date
        return raceDay;
    }


    public Boolean isInTheRace(String name){
        //check the driver by a name that in the race or not, if in return true
        for (Driver driver: places) {
            if(driver.getName().equals(name)){
                return true;//if in the race
            }
        }
        return false;//if not in the race
    }
    public int getPlaceForName(String name){
        //this method for return the place of the formula1 driver in the race by the name
        for(int i=0;i<places.length;i++){
            if(places[i].getName().equals(name)){
                return i+1;//return the index +1 ,because first place in zero index and respectively others
            }
        }
        return -1;//if not in the list
    }

    public Formula1Driver getFirst(){
        //this method for give the first place of the race
        return places[0];//first winner in  0 th position
    }
    public Formula1Driver getSecond(){
        //this method to give the second place of the race
        return places[1];//second position in 1 st position
    }
    public Formula1Driver getThird(){
        //this method to give the third place of the race
        return places[2];//third position in 2nd index
    }

}
