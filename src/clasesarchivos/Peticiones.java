package clasesarchivos;

import java.time.LocalDate;

public class Peticiones {
    private String activity;
    private String room;
    private LocalDate startDate;
    private LocalDate endDate;
    private String weekDays;
    private String schedule;


    public Peticiones(String activity, String room, LocalDate startDate, LocalDate endDate, String weekDays, String schedule) {
        this.activity = activity;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekDays = weekDays;
        this.schedule = schedule;
    }

    public String getActivity() {
        return activity;
    }
    public String getRoom() {
        return room;
    }
    public LocalDate getStartDate() {
        return startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }

    public String getWeekDays() {
        return weekDays;
    }

    public String getSchedule() {
        return schedule;
    }
}
