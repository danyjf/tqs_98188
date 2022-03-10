package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class TqsStackTest {
    private TqsStack stack;

    @BeforeEach
    public void init() {
        stack = new TqsStack();
    }

    @Test
    void push() {
        stack.push("one");
        stack.push("two");
        stack.push("three");
        assertEquals(3, stack.size());
        assertEquals("three", stack.peek());
    }

    @Test
    void pushBounded() {
        stack.setLimit(3);
        stack.push("first");
        stack.push("second");
        stack.push("third");
        assertThrows(IllegalStateException.class, () -> stack.push("last"));
    }

    @Test
    void pop() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
        stack.push("programming");
        stack.push("language");
        assertEquals("language", stack.pop());
        assertEquals(1, stack.size());
        assertEquals("programming", stack.pop());
        assertEquals(0, stack.size());
        assertTrue(stack.isEmpty());
    }

    @Test
    void peek() {
        assertThrows(EmptyStackException.class, () -> stack.pop());
        stack.push("laptop");
        assertEquals(1, stack.size());
        assertEquals("laptop", stack.peek());
        assertEquals(1, stack.size());
    }

    @Test
    void isEmpty() {
        assertTrue(stack.isEmpty());
        stack.push("element");
        assertFalse(stack.isEmpty());
    }

    @Test
    void size() {
        assertEquals(0, stack.size());
        stack.push("java");
        stack.push("code");
        assertEquals(2, stack.size());
    }
}
