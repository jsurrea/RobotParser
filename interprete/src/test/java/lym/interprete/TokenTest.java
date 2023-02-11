package lym.interprete;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TokenTest {

    @Test
    public void testToString() {
        Token token = new Token(TokenType.COMMA, ",");
        equals(token.toString(), "(<COMMA>, \",\")");
    }

    @Test
    public void testEquals() {
        Token token1 = new Token(TokenType.EOF, "");
        Token token2 = new Token(TokenType.EOF, "");
        equals(token1, token2);
    }

    private void equals(Object obj1, Object obj2) {
        assertTrue(
            "Result: " + obj1 + " - Expected: " + obj2,
            obj1.equals(obj2)
        );
    }

}
