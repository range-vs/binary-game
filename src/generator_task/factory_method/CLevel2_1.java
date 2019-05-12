package generator_task.factory_method;

import generator_task.Level2_1;
import generator_task.Task;

public class CLevel2_1 extends CTask {
    @Override
    public Task create() {
        return new Level2_1();
    }
}
