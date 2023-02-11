package lym.interprete;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;

import static lym.interprete.TokenType.*;

public class Parser {

    private Lexer lexer;
    private Token token;
    private Environment globalEnv;

    public Parser(String source) {
        this.lexer = new Lexer(source);
        this.token = lexer.nextToken();
        this.globalEnv = new Environment();
    }

    public boolean parse() {
        try {
            acceptProgram();
            return true;
        }
        catch(ParseException e) {
            return false;
        }
    }

    private void acceptProgram() throws ParseException {
        acceptToken(ROBOT_R);
        if(this.token.getType() == VARS) {
            acceptVars();
        }
        if(this.token.getType() == PROCS) {
            acceptProcs();
        }
        acceptBlock();
        acceptToken(EOF);
    } 

    private void acceptVars() throws ParseException {
        acceptToken(VARS);
        acceptNameList();
    }

    private void acceptProcs() throws ParseException {
        acceptToken(PROCS);
        acceptProc();
        while(this.token.getType() == NAME) {
            acceptProc();
        }
    }

    private void acceptProc() throws ParseException {
        globalEnv.define(this.token.getValue());
        acceptToken(NAME);
        acceptToken(OPEN_BRACKET);
        acceptToken(PIPE);
        this.globalEnv = new Environment(globalEnv); // Start local scope
        if(this.token.getType() == NAME) {
            acceptNameList();
        }
        acceptToken(PIPE);
        acceptInstructions();
        acceptToken(CLOSE_BRACKET);
        this.globalEnv = this.globalEnv.getEnclosing(); // End local scope
    }

    private void acceptBlock() throws ParseException {
        acceptToken(OPEN_BRACKET);
        acceptInstructions();
        acceptToken(CLOSE_BRACKET);
    }

    private void acceptInstructions() throws ParseException {
        acceptInstruction();
        while(this.token.getType() == SEMICOLON) {
            acceptToken(SEMICOLON);
            acceptInstruction();
        }
    }

    private void acceptInstruction() throws ParseException {
        switch (this.token.getType()) {
            case ASSIGN_TO:
            case GOTO:
            case MOVE:
            case TURN:
            case FACE:
            case PUT:
            case PICK:
            case MOVE_TO_THE:
            case MOVE_IN_DIR:
            case JUMP_TO_THE:
            case JUMP_IN_DIR:
            case NOP:
                acceptCmd();
                break;
            case IF:
            case WHILE:
            case REPEAT:
                acceptControl();
                break;
            case NAME:
                acceptCall();
                break;
            default:
                throw new ParseException("Expected Cmd, Control or Call but got " + this.token.getType(), 0);
        }
    }

    private void acceptControl() throws ParseException {
        switch (this.token.getType()) {
            case IF:
                acceptCond();
                break;
            case WHILE:
                acceptLoop();
                break;
            case REPEAT:
                acceptRepeat();
                break;
            default:
                throw new ParseException("Expected IF, WHILE or REPEAT but got " + this.token.getType(), 0);
        }
    }

    private void acceptCond() throws ParseException {
        acceptToken(IF);
        acceptToken(COLON);
        acceptCnd();
        acceptToken(THEN);
        acceptToken(COLON);
        acceptBlock();
        acceptToken(ELSE);
        acceptToken(COLON);
        acceptBlock();
    }

    private void acceptLoop() throws ParseException {
        acceptToken(WHILE);
        acceptToken(COLON);
        acceptCnd();
        acceptToken(DO);
        acceptToken(COLON);
        acceptBlock();
    }

    private void acceptRepeat() throws ParseException {
        acceptToken(REPEAT);
        acceptToken(COLON);
        acceptArg();
        acceptBlock();
    }

    private void acceptCall() throws ParseException {
        globalEnv.check(this.token.getValue());
        acceptToken(NAME);
        acceptToken(COLON);
        if(
               this.token.getType() == NAME
            || this.token.getType() == NUMBER
        ) {
            acceptArgsList();
        }
    }

    private void acceptNameList() throws ParseException {
        globalEnv.define(this.token.getValue());
        acceptToken(NAME);
        while(this.token.getType() == COMMA) {
            acceptToken(COMMA);
            globalEnv.define(this.token.getValue());
            acceptToken(NAME);
        }
    }

    private void acceptArgsList() throws ParseException {
        acceptArg();
        while(this.token.getType() == COMMA) {
            acceptToken(COMMA);
            acceptArg();
        }
    }

    private void acceptArg() throws ParseException {
        switch (this.token.getType()) {
            case NAME:
                globalEnv.check(this.token.getValue());
                acceptToken(NAME);
                break;
            case NUMBER:
                acceptToken(NUMBER);
                break;
            default:
                throw new ParseException("Expected NAME or NUMBER but got " + this.token.getType(), 0);
        }
    }

    private void acceptCmd() throws ParseException {
        switch (this.token.getType()) {
            case ASSIGN_TO:
                acceptToken(ASSIGN_TO);
                acceptToken(COLON);
                acceptToken(NUMBER);
                acceptToken(COMMA);
                globalEnv.check(this.token.getValue());
                acceptToken(NAME);
                break;
            case GOTO:
                acceptToken(GOTO);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                acceptArg();
                break;
            case MOVE:
                acceptToken(MOVE);
                acceptToken(COLON);
                acceptArg();
                break;
            case TURN:
                acceptToken(TURN);
                acceptToken(COLON);
                switch (this.token.getType()) {
                    case LEFT:
                        acceptToken(LEFT);
                        break;
                    case RIGHT:
                        acceptToken(RIGHT);
                        break;
                    case AROUND:
                        acceptToken(AROUND);
                        break;
                    default:
                        throw new ParseException("Expected LEFT, RIGHT or AROUND but got " + this.token.getType(), 0);
                }
                break;
            case FACE:
                acceptToken(FACE);
                acceptToken(COLON);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case PUT:
                acceptToken(PUT);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case BALLOONS:
                        acceptToken(BALLOONS);
                        break;
                    case CHIPS:
                        acceptToken(CHIPS);
                        break;
                    default:
                        throw new ParseException("Expected BALLOONS or CHIPS but got " + this.token.getType(), 0);
                }
                break;
            case PICK:
                acceptToken(PICK);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case BALLOONS:
                        acceptToken(BALLOONS);
                        break;
                    case CHIPS:
                        acceptToken(CHIPS);
                        break;
                    default:
                        throw new ParseException("Expected BALLOONS or CHIPS but got " + this.token.getType(), 0);
                }
                break;
            case MOVE_TO_THE:
                acceptToken(MOVE_TO_THE);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case FRONT:
                        acceptToken(FRONT);
                        break;
                    case RIGHT:
                        acceptToken(RIGHT);
                        break;
                    case LEFT:
                        acceptToken(LEFT);
                        break;
                    case BACK:
                        acceptToken(BACK);
                        break;
                    default:
                        throw new ParseException("Expected FRONT, RIGHT, LEFT or BACK but got " + this.token.getType(), 0);
                }
                break;
            case MOVE_IN_DIR:
                acceptToken(MOVE_IN_DIR);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case JUMP_TO_THE:
                acceptToken(JUMP_TO_THE);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case FRONT:
                        acceptToken(FRONT);
                        break;
                    case RIGHT:
                        acceptToken(RIGHT);
                        break;
                    case LEFT:
                        acceptToken(LEFT);
                        break;
                    case BACK:
                        acceptToken(BACK);
                        break;
                    default:
                        throw new ParseException("Expected FRONT, RIGHT, LEFT or BACK but got " + this.token.getType(), 0);
                }
                break;
            case JUMP_IN_DIR:
                acceptToken(JUMP_IN_DIR);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case NOP:
                acceptToken(NOP);
                acceptToken(COLON);
                break;
            default:
                throw new ParseException("Expected CMD but got " + this.token.getType(), 0);
        }
    }

    private void acceptCnd() throws ParseException {
        switch (this.token.getType()) {
            case FACING:
                acceptToken(FACING);
                acceptToken(COLON);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case CAN_PUT:
                acceptToken(CAN_PUT);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case BALLOONS:
                        acceptToken(BALLOONS);
                        break;
                    case CHIPS:
                        acceptToken(CHIPS);
                        break;
                    default:
                        throw new ParseException("Expected BALLOONS or CHIPS but got " + this.token.getType(), 0);
                }
                break;
            case CAN_PICK:
                acceptToken(CAN_PICK);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case BALLOONS:
                        acceptToken(BALLOONS);
                        break;
                    case CHIPS:
                        acceptToken(CHIPS);
                        break;
                    default:
                        throw new ParseException("Expected BALLOONS or CHIPS but got " + this.token.getType(), 0);
                }
                break;
            case CAN_MOVE_IN_DIR:
                acceptToken(CAN_MOVE_IN_DIR);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case CAN_JUMP_IN_DIR:
                acceptToken(CAN_JUMP_IN_DIR);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case NORTH:
                        acceptToken(NORTH);
                        break;
                    case SOUTH:
                        acceptToken(SOUTH);
                        break;
                    case EAST:
                        acceptToken(EAST);
                        break;
                    case WEST:
                        acceptToken(WEST);
                        break;
                    default:
                        throw new ParseException("Expected NORTH, SOUTH, EAST or WEST but got " + this.token.getType(), 0);
                }
                break;
            case CAN_MOVE_TO_THE:
                acceptToken(CAN_MOVE_TO_THE);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case FRONT:
                        acceptToken(FRONT);
                        break;
                    case RIGHT:
                        acceptToken(RIGHT);
                        break;
                    case LEFT:
                        acceptToken(LEFT);
                        break;
                    case BACK:
                        acceptToken(BACK);
                        break;
                    default:
                        throw new ParseException("Expected FRONT, RIGHT, LEFT or BACK but got " + this.token.getType(), 0);
                }
                break;
            case CAN_JUMP_TO_THE:
                acceptToken(CAN_JUMP_TO_THE);
                acceptToken(COLON);
                acceptArg();
                acceptToken(COMMA);
                switch (this.token.getType()) {
                    case FRONT:
                        acceptToken(FRONT);
                        break;
                    case RIGHT:
                        acceptToken(RIGHT);
                        break;
                    case LEFT:
                        acceptToken(LEFT);
                        break;
                    case BACK:
                        acceptToken(BACK);
                        break;
                    default:
                        throw new ParseException("Expected FRONT, RIGHT, LEFT or BACK but got " + this.token.getType(), 0);
                }
                break;
            case NOT:
                acceptToken(NOT);
                acceptToken(COLON);
                acceptCnd();
                break;
            default:
                throw new ParseException("Expected CND but got " + this.token.getType(), 0);
        }
    }

    private void acceptToken(TokenType type) throws ParseException {
        if(this.token.getType() == type) {
            this.token = lexer.nextToken();
        }
        else {
            throw new ParseException("Expected " + type + " but got " + this.token.getType(), 0);
        }
    }

    // Read - Eval - Print - Loop
    private static void runREPL() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in)); 
    
            System.out.println("Welcome to the Robot Parser");
            System.out.println("Type exit to stop the program");
            System.out.print(">> ");
            String source = input.readLine();
    
            while(!source.equals("exit")) {

                Parser parser = new Parser(source);
                System.out.println(parser.parse());
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
            System.out.println("Welcome to the Robot Parser");
            System.out.println("Parsing file at path: " + args[0]);
            BufferedReader objReader;
            String strCurrentLine;
            try {
                objReader = new BufferedReader(new FileReader(args[0]));
                String source = "";
                while ((strCurrentLine = objReader.readLine()) != null) {
                    source += strCurrentLine + "\n";
                }
                Parser parser = new Parser(source);
                System.out.println(parser.parse());
                objReader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
