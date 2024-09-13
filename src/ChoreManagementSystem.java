import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChoreManagementSystem {
    private List<User> users;
    private List<RoommateGroup> roommateGroups;
    private ChoreList choreList;
    private User currentUser; // Track current user for session management

    public ChoreManagementSystem() {
        users = new ArrayList<>();
        roommateGroups = new ArrayList<>();
        choreList = new ChoreList();

        // Initialize some users
        User user1 = new User("John", "Doe", "johndoe", "password1");
        User user2 = new User("Jane", "Smith", "janesmith", "password2");
        User user3 = new User("Emily", "Nguyen", "emilynguyen", "password3");
        User user4 = new User("Chloe", "Fuentes", "chloefuentes", "password4");
        User user5 = new User("Lucy", "Yeates", "lucyyeates", "password5");
        User user6 = new User("Brennan", "Hugo", "brennanhugo", "password6");

        // Add users to the Chore Management System
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);
        users.add(user6);

        // Initialize some roommate groups
        RoommateGroup group1 = new RoommateGroup("Group 1");
        group1.addUser(user1); // John
        group1.addUser(user2); // Jane

        RoommateGroup group2 = new RoommateGroup("Group 2");
        group2.addUser(user3); // Emily
        group2.addUser(user4); // Chloe
        group2.addUser(user5); // Lucy
        group2.addUser(user6); // Brennan

        // Add roommate groups to the Chore Management System
        roommateGroups.add(group1);
        roommateGroups.add(group2);
    }

    public boolean authenticateUser(String username, String password) {
        // Authenticates the user
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                System.out.println("Authentication successful.");
                currentUser = user; // Set current user
                return true;
            }
        }
        System.out.println("Authentication failed. Invalid username or password.");
        return false;
    }

    public void createUser(String firstName, String lastName, String username, String password) {
        // Creates a new user
        User newUser = new User(firstName, lastName, username, password);
        users.add(newUser);
        System.out.println("User created successfully.");
    }

    public RoommateGroup createRoommateGroup(String groupName) {
        // Creates a new roommate group with the current user as the creator
        RoommateGroup newGroup = new RoommateGroup(groupName);
        newGroup.addUser(currentUser);
        roommateGroups.add(newGroup);
        System.out.println("Roommate group created successfully.");
        return newGroup;
    }

    public void joinRoommateGroup(User user, RoommateGroup groupToJoin) {
        // Check if the group is not null
        if (groupToJoin != null) {
            groupToJoin.addUser(user);
            System.out.println("User joined the roommate group successfully.");
        } else {
            System.out.println("Roommate group not found.");
        }
    }

    public void addChoreToGroup(String choreName, RoommateGroup group) {
        // Attempt to get the chore from the chore list
        Chore chore = choreList.getChoreByName(choreName);

        // If the chore does not exist, create a new chore
        if (chore == null) {
            chore = new Chore(choreName); // Create a new chore object
            choreList.addChore(chore); // Add the new chore to the chore list
        }
        
        System.out.println("Chore added to the group's to-do list successfully.");
    }
    

    public void markChoreCompleted(String choreName, RoommateGroup group) {
        // Marks a chore as completed by the current user and reassigns if necessary
        if (currentUser == null) {
            System.out.println("No user logged in.");
            return;
        }

        for (User user : group.getUsers()) {
            if (user.getUsername().equals(currentUser.getUsername())) {
                Chore completedChore = user.markChoreCompleted(choreName);
                if (completedChore != null) {
                    System.out.println("Chore marked as completed successfully.");
                    User nextUser = group.getNextUserInRotation(user);
                    nextUser.addChore(completedChore);
                    System.out.println("Chore reassigned to " + nextUser.getUsername() + " successfully.");
                    return;
                } else {
                    System.out.println("Chore not found in the user's assigned chores.");
                    return;
                }
            }
        }
        System.out.println("User not found in the roommate group.");
    }

    public void listAvailableChores() {
        // Lists all available chores
        choreList.listChores();
    }

    public void viewAssignedChores() {
        // Views the current user's assigned chores
        if (currentUser == null) {
            System.out.println("No user logged in.");
            return;
        }
        currentUser.viewAssignedChores();
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public List<RoommateGroup> getAvailableRoommateGroups() {
        return roommateGroups;
    }

    public void assignChoresToGroup(RoommateGroup group) {
    List<User> users = group.getUsers();
    List<Chore> availableChores = choreList.getChores();

    if (users.isEmpty() || availableChores.isEmpty()) {
        System.out.println("Unable to assign chores. No users or chores available.");
        return;
    }

    // Shuffle the list of available chores to randomize assignment
    Collections.shuffle(availableChores);

    int numChores = availableChores.size();
    int choreIndex = 0;

    // Assign chores to each user in the group
    for (User user : users) {
        // Get the chore at the current index, wrapping around if necessary
        Chore chore = availableChores.get(choreIndex % numChores);
        user.addChore(chore); // Assign the chore to the user
        choreIndex++; // Move to the next chore for the next user
    }

    System.out.println("Chores assigned to the group successfully.");
}

}



