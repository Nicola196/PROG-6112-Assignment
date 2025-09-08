package part1;

import java.util.ArrayList;
import java.util.Scanner;

public class Series {
    private ArrayList<SeriesModel> seriesList;
    private Scanner scanner;

    public Series() {
        this.seriesList = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Series app = new Series();
        app.startApplication();
    }

    public void startApplication() {
        displayWelcome();
        String startChoice = getInput();

        if (!startChoice.equals("1")) {
            System.out.println("Exiting application...");
            return;
        }

        boolean running = true;
        while (running) {
            displayMainMenu();
            String choice = getInput();

            switch (choice) {
                case "1":
                    CaptureSeries();
                    break;
                case "2":
                    SearchSeries();
                    break;
                case "3":
                    UpdateSeries();
                    break;
                case "4":
                    DeleteSeries();
                    break;
                case "5":
                    SeriesReport();
                    break;
                case "6":
                    ExitSeriesApplication();
                    running = false;
                    break;
                default:
                    showError("Invalid choice! Please select 1-6.");
                    break;
            }

            if (running && !choice.equals("6")) {
                System.out.println();
                pauseForInput();
            }
        }
    }

    void CaptureSeries() {
        System.out.println("\n=== CAPTURE NEW SERIES ===");

        String seriesId = getSeriesId();

        if (findSeriesById(seriesId) != null) {
            showError("Series with ID '" + seriesId + "' already exists!");
            return;
        }

        String seriesName = getSeriesName();
        String seriesAge = getValidAge();
        String numberOfEpisodes = getNumberOfEpisodes();

        SeriesModel newSeries = new SeriesModel(seriesId, seriesName, seriesAge, numberOfEpisodes);
        seriesList.add(newSeries);

        showSuccess("Series '" + seriesName + "' has been successfully added!");
    }

    void SearchSeries() {
        System.out.println("\n=== SEARCH SERIES ===");

        String seriesId = getSeriesId();
        SeriesModel series = findSeriesById(seriesId);

        if (series != null) {
            displaySeriesDetails(series);
        } else {
            showError("Series with ID '" + seriesId + "' not found!");
        }
    }

    void UpdateSeries() {
        System.out.println("\n=== UPDATE SERIES ===");

        String seriesId = getSeriesId();
        SeriesModel series = findSeriesById(seriesId);

        if (series == null) {
            showError("Series with ID '" + seriesId + "' not found!");
            return;
        }

        System.out.println("Current details:");
        displaySeriesDetails(series);

        System.out.println("Enter new details (press Enter to keep current value):");

        System.out.print("New Series Name [" + series.SeriesName + "]: ");
        String newName = getInput();
        if (!newName.isEmpty()) {
            series.SeriesName = newName;
        }

        System.out.print("New Age Restriction [" + series.SeriesAge + "]: ");
        String newAge = getInput();
        if (!newAge.isEmpty()) {
            if (isValidAge(newAge)) {
                series.SeriesAge = newAge;
            } else {
                showError("Invalid age restriction. Keeping current value.");
            }
        }

        System.out.print("New Number of Episodes [" + series.SeriesNumberOfEpisodes + "]: ");
        String newEpisodes = getInput();
        if (!newEpisodes.isEmpty()) {
            series.SeriesNumberOfEpisodes = newEpisodes;
        }

        showSuccess("Series updated successfully!");
    }

    void DeleteSeries() {
        System.out.println("\n=== DELETE SERIES ===");

        String seriesId = getSeriesId();
        SeriesModel series = findSeriesById(seriesId);

        if (series == null) {
            showError("Series with ID '" + seriesId + "' not found!");
            return;
        }

        displaySeriesDetails(series);
        String confirmation = getConfirmation("Are you sure you want to delete this series?");

        if (confirmation.equals("y")) {
            seriesList.remove(series);
            showSuccess("Series deleted successfully!");
        } else {
            System.out.println("Delete operation cancelled.");
        }
    }

    void SeriesReport() {
        System.out.println("\n=== SERIES REPORT - 2025 ===\n");

        if (seriesList.isEmpty()) {
            System.out.println("No series found in the system.");
            return;
        }

        for (int i = 0; i < seriesList.size(); i++) {
            System.out.println(seriesList.get(i).toString());
            if (i < seriesList.size() - 1) {
                System.out.println("------------------------");
            }
        }

        System.out.println("Total series in system: " + seriesList.size());
    }

    void ExitSeriesApplication() {
        System.out.println("\nThank you for using TV Series Manager!");
        System.out.println("Goodbye!");
        scanner.close();
    }

    // Utility / Helper Methods

    private void displayWelcome() {
        System.out.println("LATEST SERIES - 2025");
        System.out.println();
        System.out.print("Enter (1) to launch menu or any other key to exit: ");
    }

    private void displayMainMenu() {
        System.out.println();
        System.out.println("Please select one of the following menu items:");
        System.out.println("(1) Capture a new series");
        System.out.println("(2) Search for a series");
        System.out.println("(3) Update series age restriction");
        System.out.println("(4) Delete a series");
        System.out.println("(5) Print series report - 2025");
        System.out.println("(6) Exit Application");
        System.out.print("Enter your choice: ");
    }

    private String getInput() {
        return scanner.nextLine().trim();
    }

    private String getSeriesId() {
        System.out.print("Enter Series ID: ");
        return scanner.nextLine().trim();
    }

    private String getSeriesName() {
        System.out.print("Enter Series Name: ");
        return scanner.nextLine().trim();
    }

    private String getNumberOfEpisodes() {
        System.out.print("Enter Number of Episodes: ");
        return scanner.nextLine().trim();
    }

    private String getConfirmation(String message) {
        System.out.print(message + " (y/n): ");
        return scanner.nextLine().trim().toLowerCase();
    }

    private void showError(String error) {
        System.out.println("ERROR: " + error);
    }

    private void showSuccess(String success) {
        System.out.println("SUCCESS: " + success);
    }

    private void displaySeriesDetails(SeriesModel series) {
        System.out.println("\n=== SERIES DETAILS ===");
        System.out.println(series.toString());
    }

    private void pauseForInput() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
    }

    private SeriesModel findSeriesById(String seriesId) {
        for (SeriesModel series : seriesList) {
            if (series.SeriesId.equalsIgnoreCase(seriesId)) {
                return series;
            }
        }
        return null;
    }

    private String getValidAge() {
        String age;
        while (true) {
            System.out.print("Enter Series Age Restriction (2-18): ");
            age = scanner.nextLine().trim();
            if (isValidAge(age)) {
                return age;
            }
            showError("Invalid age restriction. Please enter a number between 2 and 18.");
        }
    }

    private boolean isValidAge(String age) {
        try {
            int ageValue = Integer.parseInt(age);
            return ageValue >= 2 && ageValue <= 18;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
