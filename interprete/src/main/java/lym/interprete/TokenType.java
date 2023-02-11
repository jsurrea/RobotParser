package lym.interprete;

public enum TokenType {

    // Characters
    OPEN_BRACKET,
    CLOSE_BRACKET,
    COLON,
    SEMICOLON,
    COMMA,
    PIPE,

    // Keywords
    ROBOT_R,
    VARS,
    PROCS,
    IF,
    THEN,
    ELSE,
    WHILE,
    DO,
    REPEAT,

    // Commands
    ASSIGN_TO,
    GOTO,
    MOVE,
    TURN,
    FACE,
    PUT,
    PICK,
    MOVE_TO_THE,
    MOVE_IN_DIR,
    JUMP_TO_THE,
    JUMP_IN_DIR,
    NOP,

    // Conditions
    FACING,
    CAN_PUT,
    CAN_PICK,
    CAN_MOVE_IN_DIR,
    CAN_JUMP_IN_DIR,
    CAN_MOVE_TO_THE,
    CAN_JUMP_TO_THE,
    NOT,

    // Special Words
    NORTH,
    SOUTH,
    EAST,
    WEST,
    FRONT,
    RIGHT,
    LEFT,
    BACK,
    AROUND,
    CHIPS,
    BALLOONS,
    
    // Others 
    NAME, 
    NUMBER, 
    ILLEGAL, 
    EOF;

    public static TokenType getTokenType(String literal) {

        TokenType tokenType;

        switch (literal) {
            // Keywords
            case "ROBOT_R":
                tokenType = ROBOT_R;
                break;
            case "VARS":
                tokenType = VARS;
                break;
            case "PROCS":
                tokenType = PROCS;
                break;
            case "IF":
                tokenType = IF;
                break;
            case "THEN":
                tokenType = THEN;
                break;
            case "ELSE":
                tokenType = ELSE;
                break;
            case "WHILE":
                tokenType = WHILE;
                break;
            case "DO":
                tokenType = DO;
                break;
            case "REPEAT":
                tokenType = REPEAT;
                break;

            // Commands
            case "ASSIGNTO":
                tokenType = ASSIGN_TO;
                break;
            case "GOTO":
                tokenType = GOTO;
                break;
            case "MOVE":
                tokenType = MOVE;
                break;
            case "TURN":
                tokenType = TURN;
                break;
            case "FACE":
                tokenType = FACE;
                break;
            case "PUT":
                tokenType = PUT;
                break;
            case "PICK":
                tokenType = PICK;
                break;
            case "MOVETOTHE":
                tokenType = MOVE_TO_THE;
                break;
            case "MOVEINDIR":
                tokenType = MOVE_IN_DIR;
                break;
            case "JUMPTOTHE":
                tokenType = JUMP_TO_THE;
                break;
            case "JUMPINDIR":
                tokenType = JUMP_IN_DIR;
                break;
            case "NOP":
                tokenType = NOP;
                break;

            // Conditions
            case "FACING":
                tokenType = FACING;
                break;
            case "CANPUT":
                tokenType = CAN_PUT;
                break;
            case "CANPICK":
                tokenType = CAN_PICK;
                break;
            case "CANMOVEINDIR":
                tokenType = CAN_MOVE_IN_DIR;
                break;
            case "CANJUMPINDIR":
                tokenType = CAN_JUMP_IN_DIR;
                break;
            case "CANMOVETOTHE":
                tokenType = CAN_MOVE_TO_THE;
                break;
            case "CANJUMPTOTHE":
                tokenType = CAN_JUMP_TO_THE;
                break;
            case "NOT":
                tokenType = NOT;
                break;

            // Special Words
            case "NORTH":
                tokenType = NORTH;
                break;
            case "SOUTH":
                tokenType = SOUTH;
                break;
            case "EAST":
                tokenType = EAST;
                break;
            case "WEST":
                tokenType = WEST;
                break;
            case "FRONT":
                tokenType = FRONT;
                break;
            case "RIGHT":
                tokenType = RIGHT;
                break;
            case "LEFT":
                tokenType = LEFT;
                break;
            case "BACK":
                tokenType = BACK;
                break;
            case "AROUND":
                tokenType = AROUND;
                break;
            case "CHIPS":
                tokenType = CHIPS;
                break;
            case "BALLOONS":
                tokenType = BALLOONS;
                break;
        
            // An identifier for a variable or a procedure
            default:
                tokenType = NAME;
                break;
        }

        return tokenType;
    }
}
