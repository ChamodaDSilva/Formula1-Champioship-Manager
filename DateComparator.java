import java.util.Comparator;

//this comparator to sort the races by date
public class DateComparator implements Comparator<Race> {
    @Override
    public int compare(Race race1, Race race2) {
        if(race1.getRaceDay().equals(race2.getRaceDay())){
            return 0;
        }else if(race1.getRaceDay().after(race2.getRaceDay())){
            return 1;
        }else{
            return -1;
        }
    }
}
