package generator_task.factory_method;

import generator_task.Level1_2;
import generator_task.Task;

public class CLevel1_2 extends CTask {
    @Override
    public Task create() {
        return new Level1_2();
    }
}
