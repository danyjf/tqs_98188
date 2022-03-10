package stack;

import java.util.Stack;

public class TqsStack {
    private Stack<String> stack;
    private int limit;

    public TqsStack() {
        stack = new Stack<String>();
        this.limit = Integer.MAX_VALUE;
    }

    public TqsStack(int limit) {
        stack = new Stack<String>();
        this.limit = limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void push(String element) {
        if(stack.size() >= limit) {
            throw new IllegalStateException();
        }
        stack.push(element);
    }

    public String pop() {
        return stack.pop();
    }

    public String peek() {
        return stack.peek();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public int size() {
        return stack.size();
    }
}
