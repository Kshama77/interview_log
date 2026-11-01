package com.interviewlog;

import java.io.*;
import java.util.ArrayList;

public class InterviewManager {
    private final ArrayList<Interview> interviews = new ArrayList<Interview>();
    private final String fileName = "interviews.txt";
    private int nextId = 1;

    public InterviewManager() {
        loadFromFile();
    }

    public ArrayList<Interview> getAll() {
        return interviews;
    }

    public void add(String company, String role, String date, String outcome, String notes) {
        interviews.add(new Interview(nextId++, company, role, date, outcome, notes));
        saveToFile();
        System.out.println("Interview added successfully.");
    }

    public boolean updateOutcome(int id, String outcome) {
        for (Interview interview : interviews) {
            if (interview.getId() == id) {
                interview.setOutcome(outcome);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        for (int i = 0; i < interviews.size(); i++) {
            if (interviews.get(i).getId() == id) {
                interviews.remove(i);
                saveToFile();
                return true;
            }
        }
        return false;
    }

    public ArrayList<Interview> searchByCompany(String searchText) {
        ArrayList<Interview> results = new ArrayList<Interview>();
        for (Interview interview : interviews) {
            if (interview.getCompany().toLowerCase().contains(searchText.toLowerCase())) {
                results.add(interview);
            }
        }
        return results;
    }

    private void loadFromFile() {
        File file = new File(fileName);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Interview interview = Interview.fromFileLine(line);
                if (interview != null) {
                    interviews.add(interview);
                    if (interview.getId() >= nextId) {
                        nextId = interview.getId() + 1;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not load saved interviews: " + e.getMessage());
        }
    }

    private void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Interview interview : interviews) {
                writer.println(interview.toFileLine());
            }
        } catch (IOException e) {
            System.out.println("Could not save interviews: " + e.getMessage());
        }
    }
}
