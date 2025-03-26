package penguinox;

public class ASTPrinter implements Expr.ExprVisitor<StringBuilder> {

    public static void main(String[] args) {
        Expr expression = new Expr.Binary(
            new Expr.Unary(
                new Token(TokenType.MINUS, "-", null, 1),
                new Expr.Literal(123)),
            new Token(TokenType.STAR, "*", null, 1),
            new Expr.Grouping(
                new Expr.Literal(45.67)));
    
        System.out.println(new ASTPrinter().print(expression));
      }

    public String print(Expr expr) {
        return expr.acceptVisitor(this).toString();
    }
    
    @Override
    public StringBuilder visitBinaryExpr(Expr.Binary binary) {
        StringBuilder builder = new StringBuilder();

        builder.append("( ");

        builder.append(binary.op.lexeme).append(binary.left.acceptVisitor(this)).append(" ").append(binary.right.acceptVisitor(this));
        builder.append(")");

        return builder;
    }

    @Override
    public StringBuilder visitGroupingExpr(Expr.Grouping grouping) {
        StringBuilder builder = new StringBuilder();

        builder.append("( ");

        builder.append("group").append("  ").append(grouping.expression.acceptVisitor(this));
        builder.append(")");

        return builder;
    }

    @Override
    public StringBuilder visitLiteralExpr(Expr.Literal literal) {
        if (literal.value == null) return new StringBuilder("nil");
        return new StringBuilder(literal.value.toString());
    }

    @Override
    public StringBuilder visitUnaryExpr(Expr.Unary unary) {
        StringBuilder builder = new StringBuilder();

        builder.append("( ").append(unary.op.lexeme).append(unary.right.acceptVisitor(this)).append(")");

        return builder;
    }
}
