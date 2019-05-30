package fileclasses;

public class International {
	private int id;
	private String value;
	private String result;

    public International(String title, String[] weekDays, String abbreviatedWeekDays, String[] months, String[] timeWords, String generatedBy, String closed, String error) {
        this.title = title;
        this.weekDays = weekDays;
        this.abbreviatedWeekDays = abbreviatedWeekDays;
        this.months = months;
        this.timeWords = timeWords;
        this.generatedBy = generatedBy;
        this.closed = closed;
        this.error = error;
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
}
