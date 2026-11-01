package com.interviewlog;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InterviewManager manager = new InterviewManager();

    public static void main(String[] args) {
        System.out.println("================================");
        System.out.println("       Welcome to InterviewLog");
        System.out.println("================================");

        boolean running = true;
        while (running) {
            showMenu();
            String choice = readLine("Choose an option: ");

            switch (choice) {
                case "1":
                    addInterview();
                    break;
                case "2":
                    displayInterviews(manager.getAll());
                    break;
                case "3":
                    searchInterviews();
                    break;
                case "4":
                    updateOutcome();
                    break;
                case "5":
                    deleteInterview();
                    break;
                case "0":
                    running = false;
                    System.out.println("Good luck with your interviews. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void showMenu() {
        System.out.println("1. Add interview");
        System.out.println("2. View all interviews");
        System.out.println("3. Search by company");
        System.out.println("4. Update interview outcome");
        System.out.println("5. Delete interview");
        System.out.println("0. Exit");
    }

    private static void addInterview() {
        String company = readRequired("Company name: ");
        String role = readRequired("Job role: ");
        String date = readRequired("Interview date (e.g. 2026-11-10): ");
        String outcome = readRequired("Outcome (Scheduled, Passed, Rejected, Pending): ");
        String notes = readLine("Notes (optional): ");
        manager.add(company, role, date, outcome, notes);
    }

    private static void searchInterviews() {
        String company = readRequired("Enter company name to search: ");
        displayInterviews(manager.searchByCompany(company));
    }

    private static void updateOutcome() {
        int id = readInt("Enter interview ID: ");
        String outcome = readRequired("New outcome: ");
        if (manager.updateOutcome(id, outcome)) {
            System.out.println("Interview outcome updated.");
        } else {
            System.out.println("No interview found with that ID.");
        }
    }

    private static void deleteInterview() {
        int id = readInt("Enter interview ID to delete: ");
        if (manager.delete(id)) {
            System.out.println("Interview deleted.");
        } else {
            System.out.println("No interview found with that ID.");
        }
    }

    private static void displayInterviews(ArrayList<Interview> interviews) {
        if (interviews.isEmpty()) {
            System.out.println("No interviews found.");
            return;
        }
        System.out.println("\n--------- Interview List ---------");
        for (Interview interview : interviews) {
            System.out.println(interview);
            System.out.println("----------------------------------");
        }
    }

    private static String readRequired(String prompt) {
        while (true) {
            String value = readLine(prompt).trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    private static String readLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int readInt(String prompt) {
        while (true) {
            String value = readRequired(prompt);
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
