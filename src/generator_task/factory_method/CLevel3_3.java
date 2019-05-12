package generator_task.factory_method;

import generator_task.Level3_3;
import generator_task.Task;

public class CLevel3_3 extends CTask {
    @Override
    public Task create() {
        return new Level3_3();
    }
}
