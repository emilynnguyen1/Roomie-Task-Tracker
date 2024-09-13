import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private RoommateGroup roommateGroup;
    private List<Chore> assignedChores;

    public User(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        assignedChores = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public RoommateGroup getRoommateGroup() {
        return roommateGroup;
    }

    public void setRoommateGroup(RoommateGroup roommateGroup) {
        this.roommateGroup = roommateGroup;
    }

    public List<Chore> getAssignedChores() {
        return assignedChores;
    }

    public void addChore(Chore chore) {
        assignedChores.add(chore);
        chore.setAssignedTo(this);
    }

    public void viewAssignedChores() {
        if (assignedChores.isEmpty()) {
            System.out.println("No chores assigned to the user.");
        } else {
            for (Chore chore : assignedChores) {
                System.out.println(chore.getName());
            }
        }
    }

    public Chore markChoreCompleted(String choreName) {
        for (Chore chore : assignedChores) {
            if (chore.getName().equals(choreName)) {
                assignedChores.remove(chore);
                System.out.println("Chore '" + choreName + "' marked as completed.");
                return chore;
            }
        }

        return null;
    }
}


