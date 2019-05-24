package fileclasses;

public class International {
    private String title;
    private String[] weekDays;
    private String abbreviatedWeekDays;
    private String[] months;
    private String[] timeWords;
    private String generatedBy;
    private String closed;
    private String error;
    private String available;


    public International(String title, String[] weekDays, String abbreviatedWeekDays, String[] months, String[] timeWords, String generatedBy, String closed, String error, String available) {
        this.title = title;
        this.weekDays = weekDays;
        this.abbreviatedWeekDays = abbreviatedWeekDays;
        this.months = months;
        this.timeWords = timeWords;
        this.generatedBy = generatedBy;
        this.closed = closed;
        this.error = error;
        this.available = available;
    }

    public String getTitle() {
        return title;
    }

    public String[] getWeekDays() {
        return weekDays;
    }

    public String getAbbreviatedWeekDays() {
        return abbreviatedWeekDays;
    }

    public String[] getMonths() {
        return months;
    }

    public String[] getTimeWords() {
        return timeWords;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public String getClosed() {
        return closed;
    }

    public String getError() {
        return error;
    }

    public String getAvailable() { return available; }
}
