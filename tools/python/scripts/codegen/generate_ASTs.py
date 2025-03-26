## Generate ASTs for the given source code
import os

# Base class name for the AST hierarchy
BASE_CLASS = 'Expr'

# Dictionary defining the structure of AST node types
# Each entry specifies the node type and its fields with their types
SUBCLASSES = {
    'Binary': ['Expr left', 'Token op', 'Expr right'],  # Binary operations (e.g., +, -, *, /)
    'Grouping': ['Expr expression'],                    # Parenthesized expressions
    'Literal': ['Object value'],                        # Literal values (numbers, strings, etc.)
    'Unary': ['Token op', 'Expr right']                # Unary operations (e.g., -, !)
}

def write_to_file(file_stream_object, statement, indent_level=0):
    """
    Write a statement to a file with proper indentation.
    
    Args:
        file_stream_object: File object to write to
        statement: String to write
        indent_level: Number of tab indentations to prepend
    """
    indent = '\t' * indent_level
    file_stream_object.write(f'{indent}{statement}\n')

def write_subclass(file_stream_object, subclass, indent_level=0):
    """
    Generate and write a subclass definition for an AST node type.
    
    Args:
        file_stream_object: File object to write to
        subclass: Name of the subclass to generate
        indent_level: Number of tab indentations for the class definition
    """
    subclass_features = SUBCLASSES[subclass]
    # Write class declaration
    write_to_file(file_stream_object, f'static class {subclass} extends {BASE_CLASS} {{ \n', indent_level)

    # Write field declarations
    for feature in subclass_features:
        write_to_file(file_stream_object, 
                       f'final {feature};',
                       indent_level + 1)
    write_to_file(file_stream_object, '', indent_level + 1)
    
    # Write constructor
    write_to_file(file_stream_object,
                  f'{subclass}({", ".join(subclass_features)}) {{',
                  indent_level + 1)
    
    # Initialize fields in constructor
    for feature in subclass_features:
        feature_name = feature.split(' ')[1]
        write_to_file(file_stream_object,
                      f'this.{feature_name} = {feature_name};',
                      indent_level + 2)
    write_to_file(file_stream_object, '}\n', indent_level + 1)

    write_to_file(file_stream_object, f'@Override', indent_level + 1)
    write_to_file(file_stream_object, f'<R> R acceptVisitor({BASE_CLASS}Visitor<R> visitor) {{', indent_level + 1)

    write_to_file(file_stream_object, f'return visitor.visit{subclass}{BASE_CLASS}(this);', indent_level + 2)
    write_to_file(file_stream_object, '}\n', indent_level + 1)
        
    write_to_file(file_stream_object, '}\n\n', indent_level)

def main(base_directory):
    """
    Generate the AST class hierarchy in a Java file.
    
    Args:
        base_directory: Directory where the generated file will be placed
    """
    path_to_file = os.path.join(base_directory, f'{BASE_CLASS}.java')
    # Create Java file and write class definitions
    with open(path_to_file, 'w') as f:
        INDENT = 0
        # Write package declaration and base class opening
        write_to_file(f, 'package penguinox;\n\n', INDENT)
        write_to_file(f, f'abstract class {BASE_CLASS} {{\n', INDENT)
        INDENT += 1


        write_to_file(f, f'interface {BASE_CLASS}Visitor<R> {{\n', INDENT)
        INDENT += 1
        for subclass in SUBCLASSES:
            write_to_file(f, f'R visit{subclass}{BASE_CLASS}({subclass} {subclass.lower()});\n', INDENT)
        INDENT -= 1
        write_to_file(f, '}\n\n', INDENT)
        

        write_to_file(f, f'abstract <R> R acceptVisitor({BASE_CLASS}Visitor<R> visitor);\n', INDENT)
        # Generate all subclasses
        for subclass in SUBCLASSES:
            write_subclass(f, subclass, INDENT)
        INDENT -= 1
        write_to_file(f, '}', INDENT)

if __name__ == "__main__":
    import argparse

    # Set up command line argument parsing
    parser = argparse.ArgumentParser(description='Generate ASTs for the given source code')
    parser.add_argument('base_directory', help='Base directory for AST generation')
    
    args = parser.parse_args()
    base_directory = args.base_directory
    abs_path = os.path.abspath(base_directory)
    print(f"Generating ASTs for {abs_path}")
    main(base_directory)

