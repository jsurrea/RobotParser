package lym.interprete;

public class Token {
    
    private TokenType type;
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    public TokenType getType() {
        return this.type;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return "(<" + type + ">, \"" + value + "\")";
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj.getClass() != this.getClass()) return false;
        final Token other = (Token) obj;
        if(!this.type.equals(other.type)) return false;
        if(!this.value.equals(other.value)) return false;
        return true;
    }
}