package penguinox;

public enum TokenType {
    // Single-character Tokens
    LEFT_PARENTHESIS, RIGHT_PARENTHESIS, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, SEMICOLON,

    // Maths Tokens
    PLUS, MINUS, SLASH, STAR,
    MODULUS, 
    BANG, 

    // Comparison Tokens
    BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals
    IDENTIFIER, NUMBER, STRING, 

    // Keywords
    // Constructs for OOP and Functional Programming
    CLASS, FUNCTION, VARIABLE, THIS, SUPER, 

    // Control flow keywords
    IF, ELSE, FOR, WHILE, CONTINUE, BREAK, RETURN,

    // logical programming keywords
    TRUE, FALSE, AND, OR, NOT,

    // KEYWORDS FOR EASE OF USE
    PRINT,

    // MISC KEYWORDS
    NIL, EOF,

}
