package generator_task.logic_opearation;

public class Disjunction extends LogicOperation {

    public Disjunction() {
        operation = '|';
        name = "Дизъюнкция";
    }

    @Override
    public int calcuate(int a, int b) throws Exception {
        if ((a >= 0 && a <= 1) && (b >= 0 && b <= 1)) {
            return a | b;
        }
        throw new Exception("Неверные входные операнды");
    }

}
