# Token Specification

At the core of our tokenization process is the `Token` class, which encapsulates the essential components of a token. The code for Token is [here](../src/main/java/penguinox/Token.java).

## Tokens

| Field   | Description                                                                 | Applies to                  |
|---------|-----------------------------------------------------------------------------|-----------------------------|
| TokenType | The type of the token, indicating its role in the language.                | All tokens                  |
| Lexeme  | The string representation of the token as it appears in the source code.    | All tokens                  |
| Literal | The literal value of the token, applicable to strings, numbers, and identifiers. | Strings, numbers, identifiers |
| Line    | The line number in the source code where the token appears.                 | All tokens                  |

## Token Types

Our language defines a variety of token types, each serving a specific purpose in the syntax and semantics of the language, and are defined [here](../src/main/java/penguinox/TokenType.java). These token types are grouped into several categories:

- **Single-character Tokens**:
  - `LEFT_PARENTHESIS`
  - `RIGHT_PARENTHESIS`
  - `LEFT_BRACE`
  - `RIGHT_BRACE`
  - `COMMA`
  - `DOT`
  - `SEMICOLON`

- **Math Tokens**:
  - `PLUS`
  - `MINUS`
  - `SLASH`
  - `STAR`
  - `MODULUS`
  - `BANG`

- **Comparison Tokens**:
  - `BANG_EQUAL`
  - `EQUAL`
  - `EQUAL_EQUAL`
  - `GREATER`
  - `GREATER_EQUAL`
  - `LESS`
  - `LESS_EQUAL`

- **Literals**:
  - `IDENTIFIER`
  - `NUMBER`
  - `STRING`

- **Keywords**:
| Keyword Lexeme | TokenType     |
|----------------|---------------|
| **OOP and Functional Programming Constructs** | |
| class          | CLASS         |
| func           | FUNCTION      |
| var            | VARIABLE      |
| this           | THIS          |
| super          | SUPER         |
| **Control Flow Keywords** | |
| if             | IF            |
| else           | ELSE          |
| for            | FOR           |
| while          | WHILE         |
| continue       | CONTINUE      |
| break          | BREAK         |
| return         | RETURN        |
| **Logical Programming Keywords** | |
| true           | TRUE          |
| false          | FALSE         |
| and            | AND           |
| or             | OR            |
| not            | NOT           |
| **Keywords for Ease of Use** | |
| print          | PRINT         |
| **Miscellaneous** | |
| nil            | NIL           |

- **Miscellaneous**:
  - `EOF`

This categorization helps in understanding the role each token type plays in the language, facilitating easier parsing and interpretation.



