import java.util.Comparator;

// this comparator for sort formula1drivers in ascending order by number of points
public class PointComparator implements Comparator<Formula1Driver>{


    @Override
    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
        if(driver1.getNumOfPoints()==driver2.getNumOfPoints()){// if the points are equal it is checking for number of fist positions and sort according to them
            if(driver1.getFirstPosition()>driver2.getFirstPosition()){
                return 1;
            }else if(driver1.getFirstPosition()==driver2.getFirstPosition()){
                return 0;
            }else{
                return -1;
            }
        }else if(driver1.getNumOfPoints()>driver2.getNumOfPoints()){
            return 1;
        }else{
            return -1;
        }
    }

}
