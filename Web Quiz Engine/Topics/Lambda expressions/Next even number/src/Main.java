import java.util.function.LongUnaryOperator;

class Operator {

    public static LongUnaryOperator unaryOperator = (x) -> {
        if (x % 2 == 0){
            return x+2;
        }
        while (x % 2 != 0){
            x++;
        }
        return x;
    };// Write your code here
}