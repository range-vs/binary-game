package generator_task.logic_opearation;

public abstract class LogicOperation {

    protected char operation;
    protected String name;

    public abstract int calcuate(int a, int b) throws Exception;

    public char getOperation() {
        return operation;
    }

    public String getName() {
        return name;
    }
}
