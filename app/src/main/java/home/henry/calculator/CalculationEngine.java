package home.henry.calculator;

import android.graphics.Path;
import android.util.Log;

import java.util.LinkedList;
import java.util.List;


public class CalculationEngine {
    List<Double> numberList = new LinkedList();
    List<Operator> operatorList = new LinkedList();

    boolean resolveHighPiorityOperator() {
        for (int i = 0; i < operatorList.size(); ++i) {
            Operator operator = operatorList.get(i);
            if (operator.getOperation() == 'x') {
                double result = numberList.get(i) * numberList.get(i + 1);
                numberList.set(i, result);
                numberList.remove(i + 1);
                operatorList.remove(i);
                return true;
            } else if (operator.getOperation() == '/') {
                double result = numberList.get(i) / numberList.get(i + 1);
                numberList.set(i, result);
                numberList.remove(i + 1);
                operatorList.remove(i);
                return true;
            }

        }
        return false;
    }

    boolean resolveLowPiorityOperator() {
        for (int i = 0; i < operatorList.size(); ++i) {
            Operator operator = operatorList.get(i);
            if (operator.getOperation() == '+') {
                double result = numberList.get(i) + numberList.get(i + 1);
                numberList.set(i, result);
                numberList.remove(i + 1);
                operatorList.remove(i);
                return true;
            } else if (operator.getOperation() == '-') {
                double result = numberList.get(i) - numberList.get(i + 1);
                numberList.set(i, result);
                numberList.remove(i + 1);
                operatorList.remove(i);
                return true;
            }
        }
        return false;
    }

    void resolveHighPiorityOperators() {
        boolean flag = true;
        while (resolveHighPiorityOperator()) {
            Log.i("resolve high", operatorList.toString());
            Log.i("resolve high", numberList.toString());
        }
    }

    void resolveLowPiorityOperators() {
        boolean flag = true;
        while (resolveLowPiorityOperator()) {
            Log.i("resolve low", operatorList.toString());
            Log.i("resolve low", numberList.toString());

        }
    }

    double calculate() {
        resolveHighPiorityOperators();
        resolveLowPiorityOperators();
        Log.i("result", numberList.toString());
        return numberList.get(0);
    }

//    resolveOperatorWithPiority(String queryString) {
//        if (queryString.indexOf('x') > 0) {
//            return new Operator(queryString.indexOf('x'), 'x');
//        }
//
//    }

    void analyseQueryString(String queryString) {
        numberList.clear();
        operatorList.clear();
        Log.i("Test", queryString);
        for (int i = 0; i < queryString.length(); i++) {
            char character = queryString.charAt(i);
            if (isOperator(character)) {
                operatorList.add(new Operator(i, character));
            }
        }
        System.out.println(operatorList);
        int startIndex = 0;
        for (Operator operator : operatorList) {
            double number = Double.parseDouble(queryString.substring(startIndex, operator.getPosition()));
            numberList.add(number);
            startIndex = operator.getPosition() + 1;
        }
        //the las case
        Double number = Double.parseDouble(queryString.substring(startIndex, queryString.length()));
        numberList.add(number);
        System.out.println(numberList);
        System.out.println(operatorList);
    }


    boolean isOperator(char character) {
        if (character == '+' ||
                character == '-' ||
                character == 'x' ||
                character == '/') {
            return true;
        } else {
            return false;
        }
    }

    boolean isNumeric(char character) {
        if (character >= 0 || character <= 9) {
            return true;
        } else {
            return false;
        }
    }

}

