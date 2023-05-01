package org.example.behavioral.interpreter;

import java.util.Stack;

public class ExpressionParser {
    public Expression parse(String expression) {
        Stack<Expression> stack = new Stack<>();

        for (String token : expression.split(" ")) {
            if (token.equals("+")) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                stack.push(new Plus(left, right));
            } else if (token.equals("-")) {
                Expression right = stack.pop();
                Expression left = stack.pop();
                stack.push(new Minus(left, right));
            } else {
                stack.push(new Number(Integer.parseInt(token)));
            }
        }

        return stack.pop();
    }
}
