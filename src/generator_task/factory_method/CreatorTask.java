package generator_task.factory_method;

import generator_task.Task;

import java.util.HashMap;

public class CreatorTask {

    private static HashMap<Integer, CTask> tasks = generateTasks();

    private static HashMap<Integer, CTask> generateTasks(){
        return new HashMap<Integer, CTask>() {{
            put(0, new CLevel1_1());
            put(1, new CLevel1_2());
            put(2, new CLevel1_3());
            put(3, new CLevel2_1());
            put(4, new CLevel2_2());
            put(5, new CLevel2_3());
            put(6, new CLevel3_1());
            put(7, new CLevel3_2());
            put(8, new CLevel3_3());
        }};
    }

    public static Task createTask(int value){
        return tasks.get(value).create();
    }

}
