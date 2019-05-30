package generator_task;

import javafx.scene.layout.FlowPane;
import utils.Pair;

public abstract class Task {

    public abstract Pair generateTask();
    public abstract int answer();

}
