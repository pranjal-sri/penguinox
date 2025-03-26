package penguinox;


abstract class Expr {

	interface ExprVisitor<R> {

		R visitBinaryExpr(Binary binary);

		R visitGroupingExpr(Grouping grouping);

		R visitLiteralExpr(Literal literal);

		R visitUnaryExpr(Unary unary);

	}


	abstract <R> R acceptVisitor(ExprVisitor<R> visitor);

	static class Binary extends Expr { 

		final Expr left;
		final Token op;
		final Expr right;
		
		Binary(Expr left, Token op, Expr right) {
			this.left = left;
			this.op = op;
			this.right = right;
		}

		@Override
		<R> R acceptVisitor(ExprVisitor<R> visitor) {
			return visitor.visitBinaryExpr(this);
		}

	}


	static class Grouping extends Expr { 

		final Expr expression;
		
		Grouping(Expr expression) {
			this.expression = expression;
		}

		@Override
		<R> R acceptVisitor(ExprVisitor<R> visitor) {
			return visitor.visitGroupingExpr(this);
		}

	}


	static class Literal extends Expr { 

		final Object value;
		
		Literal(Object value) {
			this.value = value;
		}

		@Override
		<R> R acceptVisitor(ExprVisitor<R> visitor) {
			return visitor.visitLiteralExpr(this);
		}

	}


	static class Unary extends Expr { 

		final Token op;
		final Expr right;
		
		Unary(Token op, Expr right) {
			this.op = op;
			this.right = right;
		}

		@Override
		<R> R acceptVisitor(ExprVisitor<R> visitor) {
			return visitor.visitUnaryExpr(this);
		}

	}


}
