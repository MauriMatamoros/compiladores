import java.util.ArrayList;

public abstract class Tree {
    protected String description;
    public int line, column;
    protected Tree(int line, int column) {
        this.line = line;
        this.column = column;
    }
    @Override
    public String toString() {
        return description;
    }
    public Tree[] getChildren(){
        return null;
    }
}

class ListClass<T> extends Tree {
    ArrayList<T> elements;
    public ListClass(int line, int column, ArrayList<T> elements) {
        super(line, column);
        this.elements = elements;
        this.description = "List";
    }
    public ListClass(int line, int column, T element) {
        super(line, column);
        this.elements = new ArrayList<T>();
        this.elements.add(element);
        this.description = "List";
    }
    public void Add(T element) {
        this.elements.add(0, element);
        this.line = ((Tree) element).line;
        this.column = ((Tree) element).column;
    }
    public Tree[] getChildren(){
        Object[] a = elements.toArray();
        Tree[] c = new Tree[a.length];
        for(int i = 0; i < a.length; i++)
        {
            c[i] = (Tree) a[i];
        }
        return c;
    }
}

abstract class StatementClass extends Tree {
    protected StatementClass(int line, int column) {
        super(line, column);
    }
}

class DeclarationStatement extends StatementClass{
    String id;
    String type;
    Expression expression = null;
    public DeclarationStatement(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Declaration " + this.id + " " + this.type;
    }
    public DeclarationStatement(int line, int column, String id, String type, Expression expression) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.expression = expression;
        this.description = "Declaration " + this.id + " " + this.type;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expression};
    }
}
class ForStatement extends StatementClass{
    AssignmentStatement assignment;
    int integer;
    ListClass<StatementClass> statements;
    public ForStatement(int line, int column, AssignmentStatement assignment, int integer, ListClass<StatementClass> statements) {
        super(line, column);
        this.assignment =  assignment;
        this.integer = integer;
        this.statements = statements;
        this.description = "For " + integer;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {assignment, statements};
    }
}
class WhileStatement extends StatementClass{
    Expression expression;
    ListClass<StatementClass> statements;
    public WhileStatement(int line, int column, Expression expression, ListClass<StatementClass> statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
        this.description = "While";
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expression, statements};
    }
}
class AssignmentStatement extends StatementClass{
    String id;
    Expression expression;
    public AssignmentStatement(int line, int column, String id, Expression expression) {
        super(line, column);
        this.id = id;
        this.expression = expression;
        this.description = "Assignment " + this.id;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expression};
    }
}
class ReadStatement extends StatementClass{
    String id;
    public ReadStatement(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "Read " + this.id;
    }
}
class WriteStatement extends StatementClass{
    Expression expression;
    public WriteStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
        this.description = "Write";
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expression};
    }
}
class FunctionCallStatement extends StatementClass{
    String id;
    ListClass<Expression> expressions;
    public FunctionCallStatement(int line, int column, String id, ListClass<Expression> expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expressions};
    }
}
class ConditionalStatement extends StatementClass{
    Expression expression;
    ListClass<StatementClass> statementsIfTrue;
    ListClass<StatementClass> statementsIfFalse;
    public ConditionalStatement(int line, int column, Expression expression, ListClass<StatementClass> statementsIfTrue, ListClass<StatementClass> statementsIfFalse) {
        super(line, column);
        this.expression = expression;
        this.statementsIfTrue = statementsIfTrue;
        this.statementsIfFalse = statementsIfFalse;
        this.description = "If";
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expression, statementsIfTrue, statementsIfFalse};
    }
}

class ModuleListClass<T> extends Tree {
    T module;
    ModuleListClass tail;
    public ModuleListClass(int line, int column, ModuleListClass tail, T module) {
        super(line, column);
        this.module = module;
        this.tail = tail;
        this.description = "ModuleListClass";
    }
    @Override
    public Tree[] getChildren(){
        if (tail == null) {
            Tree[] children = { ((Tree) module) };
            return children;
        } else {
            Tree[] t = tail.getChildren();
            Tree[] children = new Tree[1 + t.length];
            children[0] = ((Tree) module);
            System.arraycopy(t, 0, children, 1, t.length);
            return children;
        }
    }
}

class ProcedureHelper extends Tree {
    String id;
    ListClass<ArgumentHelper> arguments;
    ListClass<StatementClass> statements;
    public ProcedureHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments, ListClass<StatementClass> statements) {
        super(line, column);
        this.id = id;
        this.arguments = arguments;
        this.statements = statements;
        this.description = "Procedure " + this.id; 
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {arguments, statements};
    }
}

class FunctionHelper extends Tree {
    String id;
    ListClass<ArgumentHelper> arguments;
    ListClass<StatementClass> statements;
    String type;
    public FunctionHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments, ListClass<StatementClass> statements, String type) {
        super(line, column);
        this.id = id;
        this.arguments = arguments;
        this.statements = statements;
        this.type = type;
        this.description = "Function " + this.id + " " + this.type;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {arguments, statements};
    }
}

class ArgumentHelper extends Tree {
    String id;
    String type;
    public ArgumentHelper(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Argument " + this.id + " " + this.type;
    }
}

abstract class Expression extends Tree {
    protected Expression(int line, int column) {
        super(line, column);
    }
}

class BinaryExpression extends Expression {
    Expression left;
    String operator;
    Expression right;
    public BinaryExpression(int line, int column, Expression left, String operator, Expression right) {
        super(line, column);
        this.left = left;
        this.right = right;
        this.operator = operator;
        this.description = "Binary Expression  " + operator;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {left, right};
    }
}

class UnaryExpression extends Expression {
    String operator;
    Expression right;
    public UnaryExpression(int line, int column, String operator, Expression right) {
        super(line, column);
        this.right = right;
        this.operator = operator;
        this.description = "Unary Expression  " + operator;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {right};
    }
}

class LiteralExpression<T> extends Expression {
    T value;
    public LiteralExpression(int line, int column, T value) {
        super(line,column);
        this.value = value;
        this.description = value.toString();
    }
}

class IdExpression extends Expression {
    String id;
    public IdExpression(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "ID " + this.id;
    }
}

class FunctionCallExpression extends Expression {
    String id;
    ListClass<Expression> expressions;
    public FunctionCallExpression(int line, int column, String id, ListClass<Expression> expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }
    @Override
    public Tree[] getChildren(){
        return new Tree[] {expressions};
    }
}


