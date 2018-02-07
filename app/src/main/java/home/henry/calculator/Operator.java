package home.henry.calculator;

public class Operator{
    int Position;
    char operation;

    public Operator(int position, char operation) {
        Position = position;
        this.operation = operation;
    }

    public int getPosition() {
        return Position;
    }

    public char getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "Operator{" +
                "Position=" + Position +
                ", operation=" + operation +
                '}';
    }
}
