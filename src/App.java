import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Create a Chore Management System instance
        ChoreManagementSystem cms = new ChoreManagementSystem();

        Scanner scanner = new Scanner(System.in);

        // User Selection/Authentication
        System.out.println("Welcome to the Chore Management System!");
        System.out.println("1. Login");
        System.out.println("2. Create a new user");
        System.out.print("Select an option: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (option == 1) {
            System.out.print("Enter your username: ");
            String username = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();

            if (!cms.authenticateUser(username, password)) {
                System.out.println("Login failed. Invalid username or password.");
                scanner.close();
                return;
            }
        } else if (option == 2) {
            System.out.print("Enter your first name: ");
            String firstName = scanner.nextLine();
            System.out.print("Enter your last name: ");
            String lastName = scanner.nextLine();
            System.out.print("Enter a username: ");
            String username = scanner.nextLine();
            System.out.print("Enter a password: ");
            String password = scanner.nextLine();

            // Create a new user
            cms.createUser(firstName, lastName, username, password);

            // Ask them to login
            System.out.println("Thank you for joining Chore Management System! Please log in.");
            System.out.print("Enter your username: ");
            username = scanner.nextLine();
            System.out.print("Enter your password: ");
            password = scanner.nextLine();

            if (!cms.authenticateUser(username, password)) {
                System.out.println("Login failed. Invalid username or password.");
                scanner.close();
                return;
            }

            // Creating or Joining a Roommate Group for new users only
            System.out.println("1. Create a new roommate group");
            System.out.println("2. Join an existing roommate group");
            System.out.print("Select an option: ");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (option == 1) {
                System.out.print("Enter the name for the new roommate group: ");
                String groupName = scanner.nextLine();
                RoommateGroup newGroup = cms.createRoommateGroup(groupName);
                newGroup.addUser(cms.getCurrentUser()); // Add the current user to the new group
            } else if (option == 2) {
                // Display available roommate groups to join
                List<RoommateGroup> availableGroups = cms.getAvailableRoommateGroups();
                System.out.println("Available roommate groups to join:");
                for (int i = 0; i < availableGroups.size(); i++) {
                    System.out.println((i+1) + ". " + availableGroups.get(i).getGroupName());
                }
                
                // Prompt user to choose a group to join
                System.out.print("Enter the number of the roommate group you want to join: ");
                int groupNumber = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (groupNumber >= 1 && groupNumber <= availableGroups.size()) {
                    RoommateGroup groupToJoin = availableGroups.get(groupNumber - 1);
                    cms.joinRoommateGroup(cms.getCurrentUser(), groupToJoin);
                } else {
                    System.out.println("Invalid group number.");
                }
            } else {
                System.out.println("Invalid option.");
                scanner.close();
                return;
            }
        } else {
            System.out.println("Invalid option.");
            scanner.close();
            return;
        }

        // Chore Selection and Assignment
        RoommateGroup currentUserGroup = cms.getCurrentUser().getRoommateGroup();
        cms.assignChoresToGroup(cms.getCurrentUser().getRoommateGroup());

        boolean exit = false;
        while (!exit) {
            System.out.println("Menu:");
            System.out.println("1. List available chores");
            System.out.println("2. Add chore to your group's to-do list");
            System.out.println("3. View your assigned chores");
            System.out.println("4. Mark chore as completed");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");

            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Available Chores:");
                    cms.listAvailableChores();
                    break;
                case 2:
                    System.out.print("Enter the name of the chore you want to add to your group's to-do list: ");
                    String choreName = scanner.nextLine();
                    cms.addChoreToGroup(choreName, currentUserGroup);
                    break;
                case 3:
                    System.out.println("Your assigned chores:");
                    cms.getCurrentUser().viewAssignedChores();
                    break;
                case 4:
                    System.out.print("Enter the name of the chore you've completed: ");
                    String completedChoreName = scanner.nextLine();
                    cms.markChoreCompleted(completedChoreName, currentUserGroup);
                    break;
                case 5:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option.");
            }

            // Asks to exit or return to main menu.
            if (!exit) {
                System.out.println("Do you want to return to the main menu or exit?");
                System.out.println("1. Return to main menu");
                System.out.println("2. Exit");
                System.out.print("Select an option: ");
                int exitOption = scanner.nextInt();
                if (exitOption == 1) {
                    // Continue to the next iteration of the loop to show the main menu again
                    continue;
                } else if (exitOption == 2) {
                    System.out.println("Exiting...");
                    exit = true; // Exit the loop and the program
                } else {
                    System.out.println("Invalid option. Returning to main menu.");
                }
            }
        }
        scanner.close();

        

    }
}


