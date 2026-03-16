import java.util.Stack;

public class InfixCalculator {

    public static void main(String[] args) {
        String[] testExpressions = {
                "6+7",
                "16+8*4",
                "(9+2)*5",
                "30%4",
                "80/5+3",
                "9+",
                "100/(25-5)",
                "5-5"
        };

        for (String expression : testExpressions) {
            try {
                int result = evaluate(expression);
                System.out.println("Expression: " + expression);
                System.out.println("Result: " + result);
            } catch (Exception e) {
                System.out.println("Expression: " + expression);
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    public static int evaluate(String expression) {
        Stack<Integer> values = new Stack<>();
        Stack<Character> operators = new Stack<>();

        int i = 0;
        while (i < expression.length()) {
            char ch = expression.charAt(i);

            if (Character.isWhitespace(ch)) {
                i++;
                continue;
            }

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                    num = num * 10 + (expression.charAt(i) - '0');
                    i++;
                }
                values.push(num);
                continue;
            }

            if (ch == '(') {
                operators.push(ch);
            } else if (ch == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperator(values, operators.pop());
                }
                if (operators.isEmpty() || operators.pop() != '(') {
                    throw new IllegalArgumentException("Mismatched parentheses");
                }
            } else if (isOperator(ch)) {
                while (!operators.isEmpty() && precedence(operators.peek()) >= precedence(ch)) {
                    if (operators.peek() == '(') {
                        break;
                    }
                    applyOperator(values, operators.pop());
                }
                operators.push(ch);
            } else {
                throw new IllegalArgumentException("Invalid character: " + ch);
            }

            i++;
        }

        while (!operators.isEmpty()) {
            if (operators.peek() == '(') {
                throw new IllegalArgumentException("Mismatched parentheses");
            }
            applyOperator(values, operators.pop());
        }

        if (values.size() != 1) {
            throw new IllegalArgumentException("Invalid expression");
        }

        return values.pop();
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/' || ch == '%';
    }

    public static int precedence(char op) {
        if (op == '+' || op == '-') {
            return 1;
        }
        if (op == '*' || op == '/' || op == '%') {
            return 2;
        }
        return 0;
    }

    public static void applyOperator(Stack<Integer> values, char op) {
        if (values.size() < 2) {
            throw new IllegalArgumentException("Invalid expression");
        }

        int b = values.pop();
        int a = values.pop();
        int result;

        switch (op) {
            case '+':
                result = a + b;
                break;
            case '-':
                result = a - b;
                break;
            case '*':
                result = a * b;
                break;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero");
                }
                result = a / b;
                break;
            case '%':
                if (b == 0) {
                    throw new ArithmeticException("Modulo by zero");
                }
                result = a % b;
                break;
            default:
                throw new IllegalArgumentException("Unknown operator: " + op);
        }

        values.push(result);
    }
}