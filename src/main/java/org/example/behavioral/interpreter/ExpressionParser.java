package org.example.behavioral.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class ExpressionParser {
    public Expression parse(String expression) {
        Deque<Expression> stack = new ArrayDeque<>();

        for (String token : expression.split(" ")) {
            switch (token) {
                case "+" -> {
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new Plus(left, right));
                }
                case "-" -> {
                    Expression right = stack.pop();
                    Expression left = stack.pop();
                    stack.push(new Minus(left, right));
                }
                default -> stack.push(new Number(Integer.parseInt(token)));
            }
        }

        return stack.pop();
    }
}
