package generator_task.logic_opearation.factory_method;

import generator_task.logic_opearation.LogicOperation;

import java.util.HashMap;

public class CreatorLogicOperation {

    private static HashMap<Integer, CLogicMethod> operations = generateOperations();

    private static HashMap<Integer, CLogicMethod> generateOperations(){
        return new HashMap<Integer, CLogicMethod>() {{
            put(0, new CConjunction());
            put(1, new CDisjunction());
            put(2, new CImplication());
            put(3, new CEquvalense());
        }};
    }

    public static LogicOperation createOperation(int value){
        return operations.get(value).create();
    }

}
