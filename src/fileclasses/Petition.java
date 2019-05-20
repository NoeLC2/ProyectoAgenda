package fileclasses;

import java.time.LocalDate;

public class Petition {
    private String activity;
    private String room;
    private LocalDate startDate;
    private LocalDate endDate;
    private String weekDays;
    private String[] schedule;

    //This toString should be deleted in the final version since it's not meant to be used.
    @Override
    public String toString() {
        return "Petition{" +
                "activity='" + activity + '\'' +
                ", room='" + room + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", weekDays='" + weekDays + '\'' +
                ", schedule='" + schedule + '\'' +
                '}';
    }
    public Petition(){}
    public Petition(String activity, String room, LocalDate startDate, LocalDate endDate, String weekDays, String[] schedule) {
        this.activity = activity;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weekDays = weekDays;
        this.schedule = schedule;
    }

    public Petition(String activity, String room) {
        this.activity = activity;
        this.room = room;
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

    public String[] getSchedule() {
        return schedule;
    }
}
