package penguinox;


abstract class Expr {

	static class Binary extends Expr { 

		final Expr left;
		final Token op;
		final Expr right;
		
		Binary(Expr left, Token op, Expr right) {
			this.left = left;
			this.op = op;
			this.right = right;
		}
	}


	static class Grouping extends Expr { 

		final Expr expression;
		
		Grouping(Expr expression) {
			this.expression = expression;
		}
	}


	static class Literal extends Expr { 

		final Object value;
		
		Literal(Object value) {
			this.value = value;
		}
	}


	static class Unary extends Expr { 

		final Token op;
		final Expr right;
		
		Unary(Token op, Expr right) {
			this.op = op;
			this.right = right;
		}
	}


}
