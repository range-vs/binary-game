package generator_task.factory_method;

import generator_task.Level1_1;
import generator_task.Task;

public class CLevel1_1 extends CTask {
    @Override
    public Task create() {
        return new Level1_1();
    }
}
