package lym.interprete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Lexer {

    private String source;
    private String character;
    private int readPosition;
    private int position;

    public Lexer(String source) {
        this.source = source.toUpperCase();
        this.character = "";
        this.readPosition = 0;
        this.position = 0;

        readCharacter();
    }

    public Token nextToken() {

        Token token;

        skipWhitespaces();

        switch(this.character) {

            // Characters
            case "[":
                token = new Token(TokenType.OPEN_BRACKET, this.character);
                break;
            case "]":
                token = new Token(TokenType.CLOSE_BRACKET, this.character);
                break;
            case ":":
                token = new Token(TokenType.COLON, this.character);
                break;
            case ";":
                token = new Token(TokenType.SEMICOLON, this.character);
                break;
            case ",":
                token = new Token(TokenType.COMMA, this.character);
                break;
            case "|":
                token = new Token(TokenType.PIPE, this.character);
                break;
            case "": 
                token = new Token(TokenType.EOF, this.character);
                break;

            default: 
                // Identifier (name, command, condition, keyword, etc.)
                if(isLetter(this.character)) {
                    String literal = readName();
                    TokenType tokenType = TokenType.getTokenType(literal);
                    token = new Token(tokenType, literal);
                }
                // Number
                else if(isNumber(this.character)) {
                    String literal = readNumber();
                    TokenType tokenType = TokenType.NUMBER;
                    token = new Token(tokenType, literal);
                }
                // Not recognized
                else {
                    token = new Token(TokenType.ILLEGAL, this.character); 
                }
                break;
        }

        readCharacter();

        return token;
    }

    // Read a single character
    private void readCharacter() {
        if(this.readPosition >= source.length()) {
            this.character = "";
        }
        else {
            this.character = this.source.substring(readPosition, readPosition+1);
        }
        this.position = this.readPosition;
        this.readPosition++;
    }

    // Read an alphanumeric_ string that starts with a letter
    private String readName() {

        int startPosition = this.position;

        while(
               isLetter(this.character) 
            || isNumber(this.character) 
            || this.character.equals("_")
        ) {
            readCharacter();
        }

        // Retroceder un carácter leído de más
        this.readPosition--;

        return this.source.substring(startPosition, this.position);
    }

    // Note: only reads positive integers that may start with char '0'
    private String readNumber() {

        int startPosition = this.position;

        while(isNumber(this.character)) {
            readCharacter();
        }

        // Retroceder un carácter leído de más
        this.readPosition--;

        return this.source.substring(startPosition, this.position);
    }

    private void skipWhitespaces() {

        while(
               this.character.equals(" ")
            || this.character.equals("\t")
            || this.character.equals("\n")
        ) {
            readCharacter();
        }
    }

    private boolean isLetter(String s) {
        if(s.length() == 0) return false;
        return Character.isAlphabetic(s.charAt(0));
    }

    private boolean isNumber(String s) {
        if(s.length() == 0) return false;
        return Character.isDigit(s.charAt(0));
    }

    // Read - Eval - Print - Loop
    private static void runREPL() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
            Token tokenEOF = new Token(TokenType.EOF, "");
    
            System.out.println("Welcome to the Robot Lexer");
            System.out.println("Type exit to stop the program");
            System.out.print(">> ");
            String source = input.readLine();
    
            while(!source.equals("exit")) {
    
                Lexer lexer = new Lexer(source);
                Token token = lexer.nextToken();
                while(!token.equals(tokenEOF)) {
                    System.out.println(token);
                    token = lexer.nextToken();
                }
    
                System.out.print("\n>> ");
                source = input.readLine();
            }
    
            input.close();

        }
        catch(IOException e) {
            System.out.println("IO Error:\n" + e);
        }
    }

    public static void main(String[] args) {
        if(args.length == 0) {
            runREPL();
        }
        else {
            System.out.println("Welcome to the Robot Lexer");
            System.out.println("Tokenizing file at path: " + args[0]);
            BufferedReader objReader;
            String strCurrentLine;
            try {
                objReader = new BufferedReader(new FileReader(args[0]));
                String source = "";
                while ((strCurrentLine = objReader.readLine()) != null) {
                    source += strCurrentLine + "\n";
                }
                Lexer lexer = new Lexer(source);
                Token tokenEOF = new Token(TokenType.EOF, "");
                Token token = lexer.nextToken();
                while(!token.equals(tokenEOF)) {
                    System.out.println(token);
                    token = lexer.nextToken();
                }
                objReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
