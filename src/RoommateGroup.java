import java.util.ArrayList;
import java.util.List;

public class RoommateGroup {
    private String groupName;
    private List<User> users;
    private List<Chore> chores;
    private int currentUserIndex; // Track the index of the current user for rotation

    public RoommateGroup(String groupName) {
        this.groupName = groupName;
        this.users = new ArrayList<>();
        this.currentUserIndex = 0; // Initialize index to the first user
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
        user.setRoommateGroup(this);
        System.out.println("User added to the roommate group successfully.");
    }

    public void addChore(Chore chore) {
        chores.add(chore);
        System.out.println("Chore added to the group's chore list successfully.");
    }

    public User getNextUserInRotation(User currentUser) {
        // Find the index of the current user
        int index = users.indexOf(currentUser);

        // If the current user is not found or is the last user in the list,
        // wrap around to the beginning of the list
        if (index == -1 || index == users.size() - 1) {
            currentUserIndex = 0; // Reset index to the beginning
        } else {
            currentUserIndex = index + 1; // Move to the next user
        }

        // Return the next user in rotation
        return users.get(currentUserIndex);
    }
}


