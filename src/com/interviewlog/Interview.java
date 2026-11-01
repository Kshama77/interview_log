package com.interviewlog;

public class Interview {
    private int id;
    private String company;
    private String role;
    private String date;
    private String outcome;
    private String notes;

    public Interview(int id, String company, String role, String date, String outcome, String notes) {
        this.id = id;
        this.company = company;
        this.role = role;
        this.date = date;
        this.outcome = outcome;
        this.notes = notes;
    }

    public int getId() { return id; }
    public String getCompany() { return company; }
    public String getRole() { return role; }
    public String getDate() { return date; }
    public String getOutcome() { return outcome; }
    public String getNotes() { return notes; }

    public void setOutcome(String outcome) { this.outcome = outcome; }

    public String toFileLine() {
        return id + "|" + clean(company) + "|" + clean(role) + "|" + clean(date)
                + "|" + clean(outcome) + "|" + clean(notes);
    }

    private String clean(String value) {
        return value == null ? "" : value.replace("|", "/").replace("\n", " ");
    }

    public static Interview fromFileLine(String line) {
        String[] parts = line.split("\\|", -1);
        if (parts.length != 6) {
            return null;
        }
        try {
            return new Interview(Integer.parseInt(parts[0]), parts[1], parts[2],
                    parts[3], parts[4], parts[5]);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nCompany: " + company + "\nRole: " + role
                + "\nDate: " + date + "\nOutcome: " + outcome + "\nNotes: " + notes;
    }
}
