package work;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class IDList<T extends Comparable<T> & WithID> {

    private final ArrayList<T> entries;

    public IDList() {
        this.entries = new ArrayList<>();
    }

    public IDList(T[] entries) {
        this.entries = new ArrayList<>(List.of(entries));
    }

    public boolean isRegistered(T entry) {
        for (T e : entries) {
            if (e.isSame(entry)) {
                return true;
            }
        }
        return false;
    }

    public boolean isRegistered(int ID) {
        for (T e : entries) {
            if (e.getID() == ID) {
                return true;
            }
        }
        return false;
    }

    public void register(T entry) {
        if (!isRegistered(entry)) {
            entries.add(entry);
        }
    }

    public int getSize() {
        return entries.size();
    }

    public Optional<T> getByID(int ID) {
        for (T e : entries) {
            if (e.getID() == ID) {
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

    public void add(T entry) {
        entries.add(entry);
    }

    public void remove(int ID) {
        getByID(ID).ifPresent(entries::remove);
    }

}
