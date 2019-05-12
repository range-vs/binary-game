package generator_task.factory_method;

import generator_task.Level2_2;
import generator_task.Task;

public class CLevel2_2 extends CTask {
    @Override
    public Task create() {
        return new Level2_2();
    }
}
