import java.io.Serializable;

public abstract class Driver implements Serializable {

    private String name;//name of the driver
    private String location;//location of the driver
    private String team;//team of the driver

    public Driver(String name, String location, String team) {
        //parameterized constructor for the driver for initialization
        this.name = name;
        this.location = location;
        this.team = team;
    }

    public String getName() {
        // simple getter for name
        return name;
    }

    public void setName(String name) {
        //simple setter for name
        this.name = name;
    }

    public String getLocation() {
        //simple getter for location
        return location;
    }

    public void setLocation(String location) {
        //simple setter for location
        this.location = location;
    }

    public String getTeam() {
        //simple getter for team
        return team;
    }

    public void setTeam(String team) {
        //simple setter for team
        this.team = team;
    }
}
