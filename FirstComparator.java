import java.util.Comparator;
//this comparator to sort formula1 drivers in ascending order of first positions
public class FirstComparator implements Comparator<Formula1Driver> {
    @Override
    public int compare(Formula1Driver driver1, Formula1Driver driver2) {
        if(driver1.getFirstPosition()==driver2.getFirstPosition()){
            //if the first positions are equal the comparator sort them in point vise accenting
            if(driver1.getNumOfPoints()>driver2.getNumOfPoints()){
                return 1;
            }else if(driver1.getNumOfPoints()==driver2.getNumOfPoints()){
                return 0;
            }else{
                return -1;
            }
        }else if(driver1.getFirstPosition()>driver2.getFirstPosition()){
            return 1;
        }else{
            return -1;
        }
    }
}
