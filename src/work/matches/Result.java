package work.matches;

import java.util.Optional;

public interface Result <T> {

    Optional<T> getResult();

    boolean isTie();

}
