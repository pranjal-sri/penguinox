# Tokenizer Design Document

## Overview
The `Tokenizer` class is responsible for breaking down the source code into a series of tokens, which are the smallest units of meaning in the language. This document outlines the design and implementation details of the tokenizer.

## Visual Representation
Below is a visual representation of the key functions and their groupings within the `Tokenizer` class:

```
+-------------------+
|   Tokenizer       |
+-------------------+
| - scanTokens()    |
|   + scanToken()   |
|     + consumeString()  |
|     + consumeNumber()  |
|     + consumeIdentifier() |
| - addToken()      |
| - advance()       |
| - peek()          |
| - match()         |
+-------------------+
```

## Cursor
The tokenizer maintains a cursor that scans through the source code line by line. This cursor helps in identifying and creating tokens by moving through the characters of the source code.

## Functions
The tokenizer uses several key functions to process the source code:

- **advance**: This function moves the cursor forward by one character and returns the character. It is used to consume characters one by one.

- **peek**: This function returns the current character without advancing the cursor. It is useful for lookahead operations, such as checking for the end of a string or number.

- **match**: This function checks if the current character matches an expected character. If it does, the cursor is advanced; otherwise, it remains in place. This is particularly useful for handling multi-character operators like `!=` or `==`.

## Additional Functions
The tokenizer also includes other functions that contribute to its operation:

- **Token Scanning**:
  - **scanTokens**: This function initiates the tokenization process, repeatedly calling `scanToken` until the end of the source is reached.
  - **scanToken**: This function identifies the type of token based on the current character and delegates to specific functions for handling strings, numbers, identifiers, etc.

- **Token Consumption**:
  - **consumeString**: Handles the consumption of string literals.
  - **consumeNumber**: Handles the consumption of numeric literals.
  - **consumeIdentifier**: Handles the consumption of identifiers and keywords.

- **Token Management**:
  - **addToken**: This function creates a new token and adds it to the list of tokens.

## Single-Lookahead Tokenizer
The tokenizer is designed as a single-lookahead tokenizer, meaning it only looks one character ahead in the source code to make decisions about tokenization. 
That is why we do not support multiline comments.

## Conclusion
The `Tokenizer` class efficiently breaks down the source code into tokens using a combination of cursor management and specialized functions. Its single-lookahead design ensures that it can process the source code with minimal complexity while maintaining accuracy.
