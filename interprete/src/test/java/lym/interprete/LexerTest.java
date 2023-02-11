package lym.interprete;

import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class LexerTest {

    @Test
    public void testEOF() {
        String source = "";
        Lexer lexer = new Lexer(source);
        Token result = lexer.nextToken();
        Token expected = new Token(TokenType.EOF, "");
        equals(expected, result);
    }

    @Test
    public void testIllegal() {
        String source = "¿?!¡$%";
        Lexer lexer = new Lexer(source);
        for(int i=0; i<6; i++) {
            Token expected = new Token(TokenType.ILLEGAL, source.substring(i, i+1));
            Token result = lexer.nextToken();
            equals(expected, result);
        }
    }

    @Test
    public void testSingleCharacters() {
        String source = "[]:;,|";
        Lexer lexer = new Lexer(source);
        Token token;

        token = new Token(TokenType.OPEN_BRACKET, "[");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CLOSE_BRACKET, "]");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.SEMICOLON, ";");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.PIPE, "|");
        equals(lexer.nextToken(), token);

        // Test EOF
        token = new Token(TokenType.EOF, "");
        equals(lexer.nextToken(), token);
    }

    @Test
    public void testWhitespaces() {
        String source = " \t\n";
        Lexer lexer = new Lexer(source);
        Token result = lexer.nextToken();
        Token expected = new Token(TokenType.EOF, "");
        equals(expected, result);
    }

    @Test
    public void testNumber() {
        String source = " 1492 ";
        Lexer lexer = new Lexer(source);
        Token result = lexer.nextToken();
        Token expected = new Token(TokenType.NUMBER, "1492");
        equals(expected, result);
    }

    @Test
    public void testName() {
        String source = " hola ";
        Lexer lexer = new Lexer(source);
        Token result = lexer.nextToken();
        Token expected = new Token(TokenType.NAME, "HOLA");
        equals(expected, result);
    }
    
    @Test
    public void testRobotHeader() {
        String source = "Robot_R ";
        Lexer lexer = new Lexer(source);
        Token result = lexer.nextToken();
        Token expected = new Token(TokenType.ROBOT_R, "ROBOT_R");
        equals(expected, result);
    }

    @Test
    public void testVariableDeclaration() {
        String source = "Vars nom,x , y, one ;";
        Lexer lexer = new Lexer(source);
        Token token;

        token = new Token(TokenType.VARS, "VARS");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "NOM");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "X");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "Y");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "ONE");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.SEMICOLON, ";");
        equals(lexer.nextToken(), token);
    }

    @Test
    public void testProcedureDeclaration() {
        String source = "PROCS putCB [ |c,b |] assignTo: 1, one; put : c , chips;put: b ,ballons]";
        Lexer lexer = new Lexer(source);
        Token token;

        token = new Token(TokenType.PROCS, "PROCS");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "PUTCB");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.OPEN_BRACKET, "[");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.PIPE, "|");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "C");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "B");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.PIPE, "|");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CLOSE_BRACKET, "]");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.ASSIGN_TO, "ASSIGNTO");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NUMBER, "1");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "ONE");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.SEMICOLON, ";");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.PUT, "PUT");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "C");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CHIPS, "CHIPS");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.SEMICOLON, ";");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.PUT, "PUT");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "B");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NAME, "BALLONS");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CLOSE_BRACKET, "]");
        equals(lexer.nextToken(), token);
    }

    @Test
    public void testConditional() {
        String source = "if : canMoveInDir : 1 , west then: [ MoveInDir : 1 , west ] else : [nop : ]";
        Lexer lexer = new Lexer(source);
        Token token;

        token = new Token(TokenType.IF, "IF");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CAN_MOVE_IN_DIR, "CANMOVEINDIR");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NUMBER, "1");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.WEST, "WEST");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.THEN, "THEN");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.OPEN_BRACKET, "[");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.MOVE_IN_DIR, "MOVEINDIR");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NUMBER, "1");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COMMA, ",");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.WEST, "WEST");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CLOSE_BRACKET, "]");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.ELSE, "ELSE");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.OPEN_BRACKET, "[");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.NOP, "NOP");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.COLON, ":");
        equals(lexer.nextToken(), token);
        token = new Token(TokenType.CLOSE_BRACKET, "]");
        equals(lexer.nextToken(), token);
    }

    private void equals(Object obj1, Object obj2) {
        assertTrue(
            "Result: " + obj1 + " - Expected: " + obj2,
            obj1.equals(obj2)
        );
    }

}
