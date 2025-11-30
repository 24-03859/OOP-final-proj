import java.io.*;
import java.util.Scanner;

public class Main {

    static Dilemma[] dilemmas = new Dilemma[0];
    static int size = 0;
    static Scanner scan = new Scanner(System.in);

    private static String repeat(char c, int n) {
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++)
            sb.append(c);
        return sb.toString();
    }

    private static void clear() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    static String toUpper(String text) {
        if (text == null) {
            return null;
        } else {
            return text.toUpperCase();
        }
    }

    static void quit() {
        clear();
        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 52) + "||");
        System.out.println("||" + repeat(' ', 52) + "||");
        System.out.println("||        YOU'VE SUCCESSFULLY LEFT THE PROGRAM        ||");
        System.out.println("||" + repeat(' ', 52) + "||");
        System.out.println("||" + repeat(' ', 52) + "||");
        System.out.println(' ' + repeat('=', 54));
    }

    static void display() {
        clear();

        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 15) + "ETHICAL DILEMMA DATA/S" + repeat(' ', 15) + "||");
        System.out.println(' ' + repeat('-', 54));

        if (size == 0) {
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
            System.out.println("             EMPTY! NO DILEMMAS TO DISPLAY!           ");
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
        } else {
            System.out.print("  BY CATEGORY or STATUS (c/s): ");
            char first_choice;

            String token = scan.nextLine();
            first_choice = token.length() > 0 ? token.charAt(0) : '\0';

            System.out.println(' ' + "-".repeat(54) + "\n");

            first_choice = Character.toUpperCase(first_choice);

            if (first_choice == 'C') {
                String[] categories = { "Least Severe", "Severe", "Most Severe" };

                for (int i = 0; i < 3; i++) {
                    boolean checker = false;
                    int counter = 1;

                    System.out.println(" " + categories[i] + ":");

                    for (int j = 0; j < size; j++) {
                        if (dilemmas[j].getCategory().equals(categories[i])) {
                            System.out.println("   " + counter + ". " + dilemmas[j].getDilemma());
                            System.out.println("   SOLUTION: " + dilemmas[j].getSolution());
                            System.out.println("   STATUS: " + dilemmas[j].getStatus());
                            System.out.println("   AUTHOR: " + dilemmas[j].getAuthor());
                            checker = true;
                            counter++;
                        }
                    }

                    if (!checker) {
                        System.out.println("   - None.");
                    }

                    if (i == 0 || i == 1) {
                        System.out.println(" " + repeat('-', 15));
                    }
                    System.out.println();
                }
            } else if (first_choice == 'S') {
                String[] categories = { "SOLVABLE", "PENDING" };

                for (int i = 0; i < 2; i++) {
                    boolean checker = false;
                    int counter = 1;

                    System.out.println(" " + categories[i] + ':');

                    for (int j = 0; j < size; j++) {
                        if (dilemmas[j].getStatus().equals(categories[i])) {
                            System.out.println("   " + counter + ". " + dilemmas[j].getDilemma());
                            System.out.println("   CATEGORY: " + dilemmas[j].getCategory());
                            System.out.println("   SOLUTION: " + dilemmas[j].getSolution());
                            System.out.println("   AUTHOR: " + dilemmas[j].getAuthor());
                            checker = true;
                            counter++;
                        }
                    }

                    if (!checker) {
                        System.out.print("   - None.");
                    }
                    if (i == 0) {
                        System.out.println(" " + repeat('-', 15));
                    }
                    System.out.println();
                }
            } else {
                scan.nextLine();
                System.out.println("Invalid Input!");
            }
        }

        int display_op;

        do {
            System.out.println(' ' + repeat('-', 54));
            System.out.println("||                1. Back to Main Menu                ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print("    >>: ");
            display_op = scan.nextInt();

            if (display_op == 1) {
                scan.nextLine();
                return;
            }
        } while (display_op != 1);
    }

    static void insertion() {
        int ins_choice;

        clear();
        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 21) + "INSERT DATA" + repeat(' ', 20) + "||");
        System.out.println(' ' + repeat('-', 54));

        Dilemma[] temp = new Dilemma[size + 1];

        for (int i = 0; i < size; i++) {
            temp[i] = dilemmas[i];
        }

        // UPDATED
        temp[size] = new ConcreteDilemma();

        do {
            System.out.println("  CATEGORIES: ");
            System.out.println("  1. Least Severe.");
            System.out.println("  2. Severe.");
            System.out.println("  3. Most Severe.");
            System.out.println(" " + repeat('-', 54));
            System.out.print("  CATEGORY: ");
            ins_choice = scan.nextInt();

            switch (ins_choice) {
                case 1:
                    temp[size].setCategory("Least Severe");
                    break;
                case 2:
                    temp[size].setCategory("Severe");
                    break;
                case 3:
                    temp[size].setCategory("Most Severe");
                    break;
                default:
                    System.out.println("Invalid Input!");
                    continue;
            }
            break;
        } while (true);

        System.out.print("  DILEMMA: ");
        scan.nextLine();

        temp[size].setDilemma(scan.nextLine());

        char sol;
        boolean checker = false;

        System.out.print("  IS THERE A SOLUTION (y/n): ");
        String solChecker = scan.next();

        sol = solChecker.length() > 0 ? solChecker.charAt(0) : '\0';

        sol = Character.toUpperCase(sol);

        if (sol == 'Y') {
            char sol_checker;

            do {
                System.out.print("  SOLUTION: ");
                scan.nextLine();
                temp[size].setSolution(scan.nextLine());

                sol_checker = temp[size].getSolution().length() > 0 ? temp[size].getSolution().charAt(0) : '\0';

                if (!Character.isLetter(sol_checker)) {
                    System.out.println("  Must Enter a possible solution!");
                    continue;
                }
            } while (!Character.isLetter(sol_checker));
            checker = true;
        } else {
            temp[size].setSolution("N/A");
            scan.nextLine();
        }

        if (!checker) {
            temp[size].setStatus("PENDING");
        } else {
            temp[size].setStatus("SOLVABLE");
        }

        System.out.println("  STATUS: " + temp[size].getStatus());

        System.out.print("  AUTHOR: ");
        temp[size].setAuthor(scan.nextLine());
        System.out.println();

        dilemmas = temp;
        size++;

        System.out.println(' ' + repeat('-', 54));
        System.out.println("                   NEW DILEMMA ADDED!                 ");

        int ins_op;
        do {
            System.out.println(' ' + repeat('-', 54));
            System.out.println("||  1. Add New                  2. Back to Main Menu  ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print("  >>: ");
            ins_op = scan.nextInt();

            if (ins_op == 1) {
                scan.nextLine();
                insertion();
            } else if (ins_op == 2) {
                scan.nextLine();
                return;
            }
        } while (ins_op != 1 && ins_op != 2);
    }

    static void deletion() {
        clear();
        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 21) + "DELETE DATA" + repeat(' ', 20) + "||");
        System.out.println(' ' + repeat('-', 54));

        if (size == 0) {
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
            System.out.println("             EMPTY! NO DILEMMAS TO DELETE!           ");
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));

            int del_choice;
            do {
                System.out.println(' ' + repeat('-', 54));
                System.out.println("||                1. Back to Main Menu                ||");
                System.out.println(' ' + repeat('=', 54));
                System.out.print("   >>: ");
                del_choice = scan.nextInt();
            } while (del_choice != 1);
            scan.nextLine();
            return;
        } else {
            String[] categories = { "Least Severe", "Severe", "Most Severe" };

            for (int i = 0; i < 3; i++) {
                boolean checker = false;

                System.out.println(" " + categories[i] + ':');

                for (int j = 0; j < size; j++) {
                    if (dilemmas[j].getCategory().equals(categories[i])) {
                        System.out.println("   " + (j + 1) + ". " + dilemmas[j].getDilemma());
                        checker = true;
                    }
                }

                if (!checker) {
                    System.out.println("   - None.");
                }
                System.out.println();
            }

            int del_pos;

            do {
                System.out.println(repeat('-', 54));
                System.out.print("  Position to Delete: ");
                del_pos = scan.nextInt();

                if (del_pos < 1 || del_pos > size) {
                    System.out.println("No Dilemma Yet.");
                    continue;
                }
                break;
            } while (true);

            String deleted = dilemmas[del_pos - 1].getDilemma();
            Dilemma[] temp = new Dilemma[size - 1];

            for (int i = 0; i < del_pos - 1; i++) {
                temp[i] = dilemmas[i];
            }

            for (int i = del_pos; i < size; i++) {
                temp[i - 1] = dilemmas[i];
            }

            dilemmas = temp;
            size--;

            if (deleted.length() <= 30) {
                System.out.println(repeat('-', 54));
                System.out.println("  DILEMMA DELETED: " + deleted);

            } else {
                System.out.println(repeat('-', 54));
                System.out.println("                  DILEMMA DELETED!                 ");
            }

            int del_choice;
            do {
                System.out.println(repeat('-', 54));
                System.out.println("  1. Delete Another             2. Back to Main Menu");
                System.out.println(repeat('=', 54));
                System.out.print("  >>: ");
                del_choice = scan.nextInt();

                if (del_choice == 1) {
                    scan.nextLine();
                    deletion();
                } else if (del_choice == 2) {
                    scan.nextLine();
                    return;
                }
            } while (del_choice != 1 && del_choice != 2);
        }
    }

    static void load() {
        clear();

        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 21) + "LOAD DATA" + repeat(' ', 22) + "||");
        System.out.println(' ' + repeat('-', 54));

        System.out.print("  File Name: ");
        String filename = scan.nextLine();

        System.out.println(' ' + repeat('-', 54));

        String directory_ng_file = "C:\\Users\\MyPC\\Desktop\\OOP final proj - Copy\\DATAS\\" + filename + ".txt";

        File f = new File(directory_ng_file);

        if (!f.exists()) {
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
            System.out.println("                  FILE DOESN'T EXIST!             ");
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
        } else {
            try (BufferedReader file_checker = new BufferedReader(new FileReader(f))) {
                String line;

                // skip any header lines until we reach a category header or data line
                // (old code skipped fixed 10 lines which could miss or skip content)
                while ((line = file_checker.readLine()) != null) {
                    String t = line.trim().toUpperCase();
                    if (t.contains("SEVERE") || (t.length() > 0 && t.charAt(0) == 'D')) {
                        // we've found the first real content line, break so the outer loop handles it
                        break;
                    }
                    // otherwise keep skipping header / blank lines
                }

                Dilemma[] temp = new Dilemma[100];
                int loaded = 0;

                String category = "";
                // 'line' may already contain the first content line found by the header-skip
                while (line != null) {
                    String tline = line.trim();
                    if (tline.toUpperCase().contains("SEVERE")) {
                        if (line.toUpperCase().contains("MOST")) {
                            category = "Most Severe";   
                        } else if (line.toUpperCase().contains("LEAST")) {
                            category = "Least Severe";
                        } else {
                            category = "Severe";
                        }
                    } else if (tline.length() > 0 && tline.charAt(0) == 'D') {

                        // UPDATED
                        temp[loaded] = new ConcreteDilemma();


                        temp[loaded].setCategory(category);
                        int index = tline.indexOf(": ");
                        temp[loaded].setDilemma(index >= 0 ? tline.substring(index + 2) : "");

                        // read solution line
                        line = file_checker.readLine();
                        if (line == null) break;
                        tline = line.trim();
                        index = tline.indexOf(": ");
                        temp[loaded].setSolution(index >= 0 ? tline.substring(index + 2) : "");

                        // read status line
                        line = file_checker.readLine();
                        if (line == null) break;
                        tline = line.trim();
                        index = tline.indexOf(": ");
                        temp[loaded].setStatus(index >= 0 ? tline.substring(index + 2) : "");

                        // read author line
                        line = file_checker.readLine();
                        if (line == null) break;
                        tline = line.trim();
                        index = tline.indexOf(": ");
                        temp[loaded].setAuthor(index >= 0 ? tline.substring(index + 2) : "");

                        loaded++;

                        // skip blank lines separating entries (if any)
                        file_checker.readLine();
                        file_checker.readLine();

                        // read next line to continue (we already read until author)
                        line = file_checker.readLine();
                        // continue to avoid the final read at bottom of loop
                        continue;
                    }

                    // read next line normally
                    line = file_checker.readLine();
                }
                if (loaded > 0) {
                    dilemmas = new Dilemma[loaded];

                    for (int i = 0; i < loaded; i++) {
                        dilemmas[i] = temp[i];
                    }
                    size = loaded;
                }

                if (loaded > 0) {
                    System.out.println("                      DATA LOADED!                   ");
                } else {
                    System.out.println("                  NO DILEMMAS FOUND IN FILE!        ");
                }

            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }
        }

        int load_op;
        do {
            System.out.println(' ' + repeat('-', 54));
            System.out.println("||                1. Back to Main Menu                ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print("    >>: ");
            load_op = scan.nextInt();
            scan.nextLine();
        } while (load_op != 1);

        return;
    }

    static void save() {
        clear();

        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 21) + "SAVE DATA" + repeat(' ', 22) + "||");
        System.out.println(' ' + repeat('-', 54));

        if (size == 0) {
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
            System.out.println("               EMPTY! NO DILEMMAS TO SAVE!             ");
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
        } else {
            System.out.print("  File Name: ");

            String filename = scan.nextLine();

            String directory_ng_file = "C:\\Users\\MyPC\\Desktop\\OOP final proj - Copy\\DATAS\\" + filename + ".txt";

            try (BufferedWriter W = new BufferedWriter(new FileWriter(directory_ng_file))) {
                W.write("DILEMMA RECORDS" + size + "\n");
                W.write("• DATA/S" + "\n");
                W.write("• ATTRIBUTES" + "\n");
                W.write("  - Category (header)\n  - Dilemma\n  - Solution" + "\n\n\n");

                String[] category = { "Least Severe", "Severe", "Most Severe" };

                for (int j = 0; j < 3; j++) {
                    boolean checker = false;
                    String CATEGORY = toUpper(category[j]);
                    W.write(CATEGORY + ": " + "\n");

                    for (int i = 0; i < size; i++) {
                        if (dilemmas[i].getCategory().equals(category[j])) {
                            W.write('D' + String.valueOf(i + 1) + ": " + dilemmas[i].getDilemma() + "\n");
                            W.write('S' + String.valueOf(i + 1) + ": " + dilemmas[i].getSolution() + "\n");
                            W.write('s' + String.valueOf(i + 1) + ": " + dilemmas[i].getStatus() + "\n");
                            W.write('A' + String.valueOf(i + 1) + ": " + dilemmas[i].getAuthor() + "\n\n");
                            W.write("\n\n");
                            checker = true;
                        }
                    }
                    if (!checker) {
                        W.write("  - None.\n\n");
                    }
                }
                System.out.println();
            } catch (IOException e) {
                System.out.println("Error writing file: " + e.getMessage());
            }

            System.out.println(' ' + repeat('-', 54));
            System.out.println(repeat(' ', 22) + "DATA SAVED!" + repeat(' ', 22));
        }

        int save_choice;
        do {
            System.out.println(' ' + repeat('-', 54));
            System.out.println("||                1. Back to Main Menu                ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print("  >>: ");
            save_choice = scan.nextInt();

            if (save_choice == 1) {
                scan.nextLine();
                return;
            }
        } while (save_choice != 1);
    }
    
    static void search() {
        clear();

        System.out.println(' ' + repeat('=', 54));
        System.out.println("||" + repeat(' ', 21) + "SEARCH DATA" + repeat(' ', 21) + "||");
        System.out.println(' ' + repeat('-', 54));

        if (size == 0) {
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
            System.out.println("             EMPTY! NO DILEMMAS TO SEARCH!           ");
            System.out.println(repeat(' ', 54));
            System.out.println(repeat(' ', 54));
        } else {
            System.out.print("  SEARCH: ");
            String search = scan.nextLine();

            System.out.println(' ' + repeat('-', 54));

            System.out.println("  RELATED DILEMMAS/S: \n");
            boolean found = false;
            int counter = 1;

            search = toUpper(search);

            for (int i = 0; i < size; i++) {
                String upperdilemma = toUpper(dilemmas[i].getDilemma());

                if (upperdilemma.contains(search)) {
                    System.out.println(" " + counter + ". " + dilemmas[i].getDilemma());
                    System.out.println("  CATEGORY: " + dilemmas[i].getCategory());
                    System.out.println("  SOLUTION: " + dilemmas[i].getSolution() + "\n");
                    found = true;
                    counter++;
                }
            }

            if (!found) {
                System.out.println(repeat(' ', 54));
                System.out.println(repeat(' ', 54));
                System.out.println("             NO MATCHING DILEMMAS FOUND!           ");
                System.out.println(repeat(' ', 54));
                System.out.println(repeat(' ', 54));
            }
        }

        int search_op;

        do {
            System.out.println(' ' + repeat('-', 54));
            System.out.println("|| 1. Search Another              2. Back to Main Menu ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print(" >>: ");
            search_op = scan.nextInt();

            if (search_op == 1) {
                scan.nextLine();
                search();
            } else if (search_op == 2) {
                scan.nextLine();
                return;
            }
        } while (search_op != 1 && search_op != 2);
    }

    static void about() {
        int about_op;

        clear();

        do {
            System.out.println(' ' + repeat('=', 54));
            System.out.println("||" + repeat(' ', 23) + "ABOUT" + repeat(' ', 24) + "||");
            System.out.println(' ' + repeat('-', 54));
            System.out.println("   Project Name: Ethical Dilemma Sorting System");
            System.out.println("   Developed by:\n");
            System.out.println("       John Andrei Chan\n");
            System.out.println("       John Reinner Claveria\n");
            System.out.println("       Rence Catipon\n");
            System.out.println("                 Lance Catipon\n");

            System.out.println("   Subject/s:");
            System.out.println("    - Data Structure and Algorithm");
            System.out.println("    - Computer Programming 1");
            System.out.println("    - Science, Technology, & Society\n");

            System.out.println("   Professor/s:");
            System.out.println("    - Mr. Arjonel Mendoza");
            System.out.println("    - Mrs. Ligaya Lutero");
            System.out.println("    - Ms. Hannah Carmela Marquez\n");

            System.out.println("   Tools:");
            System.out.println("   - Visual Studio Code (IDE)");
            System.out.println("   - C++ (Programming Language)");
            System.out.println("   - Claude 3.5 Sonnet (LLM)\n");

            System.out.println("   Description:");
            System.out.println("     DISCLAIMER: This Console-Based Project was");
            System.out.println("     developed with AI assistance, using Claude");
            System.out.println("     3.5 Sonnet.\n");

            System.out.println("   DATE CREATED: March 2025\n");

            System.out.println(' ' + repeat('-', 54));
            System.out.println("||                1. Back to Main Menu                ||");
            System.out.println(' ' + repeat('=', 54));
            System.out.print("   >>: ");
            about_op = scan.nextInt();

            if (about_op != 1) {
                continue;
            } else {
                scan.nextLine();
                return;
            }

        } while (about_op != 1);
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int choice;

        do {
            System.out.print("\033[H\033[2J");
            System.out.flush();

            System.out.println(" " + "=".repeat(54));
            System.out.println("||                      WELCOME TO                    ||");
            System.out.println("||           INTEGRITY CHECK: DATA MANAGEMENT         ||");
            System.out.println(" " + "-".repeat(54));

            System.out.println("   1. Display (Sorted) Current Dilemmas.");
            System.out.println("   2. Search Dilemma.");
            System.out.println("   3. Insert New Dilemma.");
            System.out.println("   4. Delete Dilemma.");
            System.out.println("   5. Load a File.");
            System.out.println("   6. Save a File.");
            System.out.println("   7. About System.");
            System.out.println("   8. Quit.");
            System.out.println(" " + "-".repeat(54));
            System.out.print("   >>: ");
            choice = scan.nextInt();

            switch (choice) {
                case 1:
                    display();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    insertion();
                    break;
                case 4:
                    deletion();
                    break;
                case 5:
                    load();
                    break;
                case 6:
                    save();
                    break;
                case 7:
                    about();
                    break;
                case 8:
                    quit();
                    break;
                default:
                    System.out.println("Invalid Input!");
            }
        } while (choice != 8);
        scan.close();
    }
}
