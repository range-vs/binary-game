package generator_task.factory_method;

import generator_task.Level1_3;
import generator_task.Task;

public class CLevel1_3 extends CTask {
    @Override
    public Task create() {
        return new Level1_3();
    }
}
