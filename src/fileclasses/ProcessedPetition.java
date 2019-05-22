package fileclasses;

import java.time.LocalDate;
import java.util.List;

public class ProcessedPetition extends Petition{
    //This class will contain the info of the petitions for a certain month
    private List<LocalDate> daysMoth;
    private List<Integer> hours;

    public ProcessedPetition(String activity, String room, LocalDate startDate, LocalDate endDate, String weekDays, String[] schedule, List<LocalDate> daysMoth, List<Integer> hours) {
        super(activity, room, startDate, endDate, weekDays, schedule);
        this.daysMoth = daysMoth;
        this.hours = hours;
    }

    public List<LocalDate> getDaysMonth() {
        return daysMoth;
    }

    public List<Integer> getHours() {
        return hours;
    }
}
