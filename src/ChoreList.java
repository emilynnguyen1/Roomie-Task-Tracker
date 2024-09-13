import java.util.ArrayList;
import java.util.List;

public class ChoreList {
    private List<Chore> chores;

    public ChoreList() {
        chores = new ArrayList<>();

        // default chores
        chores.add(new Chore("Take out the trash"));
        chores.add(new Chore("Take out the recycling"));
        chores.add(new Chore("Vacuum"));
        chores.add(new Chore("Mop the floors"));
        chores.add(new Chore("Wash the dishes"));
        chores.add(new Chore("Clean the bathroom"));
        chores.add(new Chore("Clean the kitchen"));

    }

    public List<Chore> getChores() {
        return chores;
    }

    public void addChore(Chore chore) {
        chores.add(chore);
        System.out.println("Chore added to the list successfully.");
    }

    public void removeChore(Chore chore) {
        chores.remove(chore);
        System.out.println("Chore removed from the list successfully.");
    }

    // Method to get a chore by its name
    public Chore getChoreByName(String choreName) {
        for (Chore chore : chores) {
            if (chore.getName().equals(choreName)) {
                return chore;
            }
        }
        return null; // Return null if chore not found
    }

    // Method to list all chores
    public void listChores() {
        if (chores.isEmpty()) {
            System.out.println("No chores available.");
        } else {
            for (Chore chore : chores) {
                System.out.println("- " + chore.getName());
            }
        }
    }
}

