import java.util.ArrayList;

public abstract class Tree {
    protected String description;
    public int line, column;

    protected Tree(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public abstract boolean semanticTest(VariableTable variableTable);

    public abstract void generateIntermediateCode(IntermediateCode code, VariableTable variableTable);

    public void semanticError(String message) {
        System.out.println(
                "Semantic Error \"" + message + "\" in line: " + (line + 1) + " and column: " + (column + 1) + " ");
    }

    @Override
    public String toString() {
        return description;
    }

    public Tree[] getChildren() {
        return null;
    }
}

class ListClass<T> extends Tree {
    ArrayList<T> elements;

    public ListClass() {
        super(0, 0);
        this.elements = new ArrayList<T>();
        this.description = "List";
    }

    public ListClass(int line, int column, T element) {
        super(line, column);
        this.elements = new ArrayList<T>();
        this.elements.add(element);
        this.description = element instanceof StatementClass ? "StatementList" : "ExpressionList";
    }

    public void Add(T element) {
        if (element != null) {
            this.elements.add(0, element);
            this.line = ((Tree) element).line;
            this.column = ((Tree) element).column;
        }
    }

    public ArrayList<T> getList() {
        if (this.elements == null) {
            return new ArrayList<T>();
        }
        return this.elements;
    }

    public Tree[] getChildren() {
        Object[] a = elements.toArray();
        Tree[] c = new Tree[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = (Tree) a[i];
        }
        return c;
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
        for (int i = 0; i < this.elements.size(); i++) {
            ((Tree) this.elements.get(i)).generateIntermediateCode(code, variableTable);
        }
    }
}

abstract class StatementClass extends Tree {
    protected StatementClass(int line, int column) {
        super(line, column);
    }
}

class DeclarationStatement extends StatementClass {
    String id;
    String type;

    public DeclarationStatement(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Declaration " + this.id + " " + this.type;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (variableTable.variableExists(id)) {
            semanticError("This variable id: " + id + " already exists");
            return false;
        }
        variableTable.addToTable(id, type);
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

    @Override
    public Tree[] getChildren() {
        return null;
    }
}

class ForStatement extends StatementClass {
    AssignmentStatement assignment;
    int integer;
    ListClass<StatementClass> statements;

    public ForStatement(int line, int column, AssignmentStatement assignment, int integer,
            ListClass<StatementClass> statements) {
        super(line, column);
        this.assignment = assignment;
        this.integer = integer;
        this.statements = statements;
        this.description = "For " + integer;
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {

    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { assignment, statements };
    }
}

class WhileStatement extends StatementClass {
    Expression expression;
    ListClass<StatementClass> statements;

    public WhileStatement(int line, int column, Expression expression, ListClass<StatementClass> statements) {
        super(line, column);
        this.expression = expression;
        this.statements = statements;
        this.description = "While";
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!this.expression.getType(variableTable).equals("boolean")) {
            semanticError("Expression in while statement is not of type boolean");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        String trueLabel = code.newLabel();
        code.pushStack("label", "", "", trueLabel);
        this.statements.generateIntermediateCode(code, variableTable);
        this.expression.generateIntermediateCode(code, variableTable);
        String result = code.popHeap();
        code.pushStack("if>", result, "0", trueLabel);
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression, statements };
    }
}

class AssignmentStatement extends StatementClass {
    String id;
    Expression expression;

    public AssignmentStatement(int line, int column, String id, Expression expression) {
        super(line, column);
        this.id = id;
        this.expression = expression;
        this.description = "Assignment " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("Variable of id: " + id + " was not declared");
            return false;
        }
        String typeLeft = variableTable.getIdType(id);
        String typeRight = this.expression.getType(variableTable);
        if (!typeLeft.equals(typeRight)) {
            semanticError("Cannot assign expression of type: " + typeRight + " to expression of type " + typeLeft);
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        this.expression.generateIntermediateCode(code, variableTable);
        String result = code.popHeap();
        code.pushStack("assign", "", result, "~" + this.id);
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression };
    }
}

class ReadStatement extends StatementClass {
    String id;

    public ReadStatement(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "Read " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("Variable of id: " + id + " does not exist.");
            return false;
        }
        if (!variableTable.getIdType(id).equals("string")) {
            semanticError("Variable of id: " + id + " is not a String.");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }
}

class WriteStatement extends StatementClass {
    Expression expression;

    public WriteStatement(int line, int column, Expression expression) {
        super(line, column);
        this.expression = expression;
        this.description = "Write";
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression };
    }
}

class FunctionCallStatement extends StatementClass {
    String id;
    ListClass<Expression> expressions;

    public FunctionCallStatement(int line, int column, String id, ListClass<Expression> expressions) {
        super(line, column);
        this.id = id;
        this.expressions = expressions;
        this.description = "FunctionCall " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        if (!variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " does not exist.");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expressions };
    }
}

class ConditionalStatement extends StatementClass {
    Expression expression;
    ListClass<StatementClass> statementsIfTrue;
    ListClass<StatementClass> statementsIfFalse;

    public ConditionalStatement(int line, int column, Expression expression, ListClass<StatementClass> statementsIfTrue,
            ListClass<StatementClass> statementsIfFalse) {
        super(line, column);
        this.expression = expression;
        this.statementsIfTrue = statementsIfTrue;
        this.statementsIfFalse = statementsIfFalse;
        this.description = "If";
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!this.expression.getType(variableTable).equals("boolean")) {
            semanticError("The expression in the if statement is not a boolean");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        this.expression.generateIntermediateCode(code, variableTable);
        String result = code.popHeap();
        String trueLabel = code.newLabel();
        String falseLabel = code.newLabel();
        code.pushStack("if>", result, "0", trueLabel);
        code.pushStack("goto", "", "", falseLabel);
        code.pushStack("label", "", "", trueLabel);
        this.statementsIfTrue.generateIntermediateCode(code, variableTable);
        code.pushStack("label", "", "", falseLabel);
        if (this.statementsIfFalse != null) {
            this.statementsIfFalse.generateIntermediateCode(code, variableTable);
        }
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expression, statementsIfTrue, statementsIfFalse };
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

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
        ((Tree) module).generateIntermediateCode(code, variableTable);
        if (this.tail != null) {
            tail.generateIntermediateCode(code, variableTable);
        }
    }

    @Override
    public Tree[] getChildren() {
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

    public ProcedureHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments,
            ListClass<StatementClass> statements) {
        super(line, column);
        this.id = id;
        if (arguments == null) {
            this.arguments = new ListClass<ArgumentHelper>();
        } else {
            this.arguments = arguments;
        }
        this.statements = statements;
        this.description = "Procedure " + this.id;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<ArgumentHelper> temporaryArguments = this.arguments.getList();
        for (int i = 0; i < temporaryArguments.size(); i++) {
            types.add(temporaryArguments.get(i).type);
            names.add(temporaryArguments.get(i).id);
        }
        if (variableTable.functionExists(this.id, types)) {
            semanticError("The procedure with id: " + this.id + " and these arguments: " + types.toString()
                    + " already exists.");
            return false;
        }
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    semanticError(
                            "There was a duplicate argument: " + names.get(i) + " in this: " + this.id + " procedure");
                    return false;
                }
            }
        }
        variableTable.addToTable(this.id, "", temporaryArguments);
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
        this.statements.generateIntermediateCode(code, variableTable);
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { arguments, statements };
    }
}

class FunctionHelper extends Tree {
    String id;
    ListClass<ArgumentHelper> arguments;
    ListClass<StatementClass> statements;
    String type;

    public FunctionHelper(int line, int column, String id, ListClass<ArgumentHelper> arguments,
            ListClass<StatementClass> statements, String type) {
        super(line, column);
        this.id = id;
        this.arguments = arguments == null ? new ListClass<ArgumentHelper>() : arguments;
        this.statements = statements;
        this.type = type;
        this.description = "Function " + this.id + " " + this.type;
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<ArgumentHelper> temporaryArguments = this.arguments.getList();
        for (int i = 0; i < temporaryArguments.size(); i++) {
            types.add(temporaryArguments.get(i).type);
            names.add(temporaryArguments.get(i).id);
        }
        if (variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " already exists.");
            return false;
        }
        for (int i = 0; i < names.size(); i++) {
            for (int j = i + 1; j < names.size(); j++) {
                if (names.get(i).equals(names.get(j))) {
                    semanticError(
                            "There was a duplicate argument: " + names.get(i) + " in this: " + this.id + " function");
                    return false;
                }
            }
        }
        variableTable.addToTable(this.id, this.type, temporaryArguments);
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { arguments, statements };
    }
}

class ArgumentHelper extends Tree {
    String id;
    String type;
    boolean byValue;

    public ArgumentHelper(int line, int column, String id, String type, boolean byValue) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Argument " + this.id + " " + this.type;
        this.byValue = byValue;
    }

    public ArgumentHelper(int line, int column, String id, String type) {
        super(line, column);
        this.id = id;
        this.type = type;
        this.description = "Argument " + this.id + " " + this.type;
        this.byValue = true;
    }

    public boolean equals(ArgumentHelper argumentHelper) {
        return this.type.equals(argumentHelper.type);
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

}

abstract class Expression extends Tree {
    protected Expression(int line, int column) {
        super(line, column);
    }

    public abstract String getType(VariableTable variableTable);
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

    public String getType(VariableTable variableTable) {
        return resultingType(this.left.getType(variableTable), this.right.getType(variableTable), this.operator);
    }

    public boolean semanticTest(VariableTable variableTable) {
        String leftType = this.left.getType(variableTable);
        String rightType = this.right.getType(variableTable);
        if (!leftType.equals(rightType)) {
            semanticError("Type: " + leftType + " is not comapatible with: " + rightType);
            return false;
        }
        // TODO check if types are compatible with operator
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        String leftResult;
        String rightResult;
        this.left.generateIntermediateCode(code, variableTable);
        leftResult = code.popHeap();
        this.right.generateIntermediateCode(code, variableTable);
        rightResult = code.popHeap();
        TemporaryVariable temporaryVariable = code.newTemporary(
                resultingType(this.left.getType(variableTable), this.right.getType(variableTable), this.operator));
        code.pushStack(this.operator.toLowerCase(), leftResult, rightResult, temporaryVariable.toString());
        code.pushHeap(temporaryVariable.toString());
    }

    public String resultingType(String leftType, String rightType, String operator) {
        ArrayList<String> relationalOperators = new ArrayList<String>();
        relationalOperators.add(">");
        relationalOperators.add("<");
        relationalOperators.add(">=");
        relationalOperators.add("<=");
        ArrayList<String> comparativeOperators = new ArrayList<String>();
        comparativeOperators.add("<>");
        comparativeOperators.add("=");
        ArrayList<String> logicalOperators = new ArrayList<String>();
        logicalOperators.add("AND");
        logicalOperators.add("OR");
        logicalOperators.add("NOT");
        ArrayList<String> arithmeticOperators = new ArrayList<String>();
        arithmeticOperators.add("*");
        arithmeticOperators.add("/");
        arithmeticOperators.add("-");
        arithmeticOperators.add("+");
        arithmeticOperators.add("MOD");
        ArrayList<String> numericTypes = new ArrayList<String>();
        numericTypes.add("integer");
        numericTypes.add("double");
        if (relationalOperators.contains(operator)) {
            return numericTypes.contains(leftType) && leftType.equals(rightType) ? "boolean" : "error";
        } else if (logicalOperators.contains(operator)) {
            return leftType.equals("boolean") && rightType.equals("boolean") ? "boolean" : "error";
        } else if (arithmeticOperators.contains(operator)) {
            return numericTypes.contains(leftType) && leftType.equals(rightType) ? leftType : "error";
        } else if (comparativeOperators.contains(operator)) {
            return leftType.equals(rightType) ? leftType : "error";
        } else {
            System.out.println("I do not deal with this operator: " + operator);
        }
        return "error";
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { left, right };
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

    public String getType(VariableTable variableTable) {
        return right.getType(variableTable);
    }

    public boolean semanticTest(VariableTable variableTable) {
        String rightType = this.right.getType(variableTable);
        if (this.operator.equals("-")) {
            if (!(rightType.equals("integer") || rightType.equals("double"))) {
                semanticError("Operator - cannot be used with non numerical type: " + rightType);
                return false;
            }
        } else {
            if (!(rightType.equals("boolean"))) {
                semanticError("Operator NOT cannot be used with non boolean type: " + rightType);
                return false;
            }
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        String rightResult;
        this.right.generateIntermediateCode(code, variableTable);
        rightResult = code.popHeap();
        TemporaryVariable temporaryVariable = code.newTemporary(this.right.getType(variableTable));
        code.pushStack(this.operator.toLowerCase(), "", rightResult, temporaryVariable.toString());
        code.pushHeap(temporaryVariable.toString());
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { right };
    }
}

class LiteralExpression<T> extends Expression {
    T value;

    public LiteralExpression(int line, int column, T value) {
        super(line, column);
        this.value = value;
        this.description = value.toString();
    }

    public String getType(VariableTable variableTable) {
        if (Integer.class.isInstance(value)) {
            return "integer";
        } else if (Character.class.isInstance(value)) {
            return "char";
        } else if (String.class.isInstance(value)) {
            return "string";
        } else if (Boolean.class.isInstance(value)) {
            return "boolean";
        }
        return "error";
    }

    public boolean semanticTest(VariableTable variableTable) {
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        TemporaryVariable temporaryVariable = code.newTemporary(this.getType(variableTable));
        code.pushStack("assign", "", this.value.toString(), temporaryVariable.toString());
        code.pushHeap(temporaryVariable.toString());
    }
}

class IdExpression extends Expression {
    String id;

    public IdExpression(int line, int column, String id) {
        super(line, column);
        this.id = id;
        this.description = "ID " + this.id;
    }

    public String getType(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            return "error";
        }
        return variableTable.getIdType(id);
    }

    public boolean semanticTest(VariableTable variableTable) {
        if (!variableTable.variableExists(id)) {
            semanticError("This variable with id: " + id + " does not exist");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        TemporaryVariable temporaryVariable = code.newTemporary(this.getType(variableTable));
        code.pushStack("assign", "", "~" + this.id, temporaryVariable.toString());
        code.pushHeap(temporaryVariable.toString());
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

    public String getType(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        return variableTable.getFunctionType(this.id, types);
    }

    public boolean semanticTest(VariableTable variableTable) {
        ArrayList<String> types = new ArrayList<String>();
        ArrayList<Expression> temporaryExpressions = this.expressions.getList();
        for (int i = 0; i < temporaryExpressions.size(); i++) {
            types.add(temporaryExpressions.get(i).getType(variableTable));
        }
        if (!variableTable.functionExists(this.id, types)) {
            semanticError("The function with id: " + this.id + " and these arguments: " + types.toString()
                    + " does not exist.");
            return false;
        }
        return true;
    }

    public void generateIntermediateCode(IntermediateCode code, VariableTable variableTable) {
        // TODO
    }

    @Override
    public Tree[] getChildren() {
        return new Tree[] { expressions };
    }
}

class VariableTable {
    ArrayList<IdType> idTable;
    ArrayList<FunctionType> functionTable;
    VariableTable parentTable = null;
    ArrayList<VariableTable> subTables;
    int currentPosition = 0;

    public VariableTable() {
        this.idTable = new ArrayList();
        this.functionTable = new ArrayList();
        this.subTables = new ArrayList();
    }

    public VariableTable(VariableTable parentTable) {
        this.parentTable = parentTable;
        this.idTable = new ArrayList();
        this.functionTable = new ArrayList();
        this.subTables = new ArrayList();
        this.currentPosition = parentTable.currentPosition;
    }

    public VariableTable increaseScope() {
        this.subTables.add(new VariableTable(this));
        return this.subTables.get(this.subTables.size() - 1);
    }

    public VariableTable decreaseScope() {
        if (this.parentTable == null) {
            System.out.println("Trying to return from orfan scope");
            return this;
        }
        return this.parentTable;
    }

    public boolean hasParent() {
        return this.parentTable != null;
    }

    public int getSize(String type) {
        type = type.toLowerCase();
        if (type.equals("string")) {
            // TODO Maybe fix this?
            return 128;
        } else if (type.equals("integer")) {
            return 4;
        } else if (type.equals("boolean")) {
            return 1;
        } else if (type.equals("char")) {
            return 1;
        } else {
            return 0;
        }
    }

    // TODO throw error if already exists ****optional*****
    public void addToTable(IdType variable) {
        this.currentPosition += getSize(variable.getType());
        this.idTable.add(variable);
    }

    public void addToTable(FunctionType function) {
        this.functionTable.add(function);
    }

    public void addToTable(String id, String type) {
        this.idTable.add(new IdType(id, type.toLowerCase(), this.currentPosition));
        this.currentPosition += getSize(type);
        // System.out.println(this.idTable.toString());
    }

    public void addToTable(String id, String type, ArrayList<ArgumentHelper> arguments) {
        this.functionTable.add(new FunctionType(id, type.toLowerCase(), arguments));
        // System.out.println(this.functionTable.toString());
    }

    public boolean variableExists(String id) {
        for (int i = 0; i < this.idTable.size(); i++) {
            if (this.idTable.get(i).getId().equals(id)) {
                return true;
            }
        }
        if (this.hasParent()) {
            return this.parentTable.variableExists(id);
        }
        return false;
    }

    public boolean functionExists(String id, ArrayList<String> argumentTypes) {
        for (int i = 0; i < this.functionTable.size(); i++) {
            FunctionType currentFunction = this.functionTable.get(i);
            if (currentFunction.getId().equals(id)) {
                ArrayList<String> currentArguments = currentFunction.getTypes();
                if (currentArguments.size() == argumentTypes.size()) {
                    boolean argumentsAreEqual = true;
                    for (int j = 0; j < currentArguments.size(); j++) {
                        if (!currentArguments.get(j).equals(argumentTypes.get(j))) {
                            argumentsAreEqual = false;
                            break;
                        }
                    }
                    if (argumentsAreEqual) {
                        return true;
                    }
                }
            }
        }
        if (this.hasParent()) {
            return this.parentTable.functionExists(id, argumentTypes);
        }
        return false;
    }

    public String getIdType(String id) {
        for (int i = 0; i < this.idTable.size(); i++) {
            if (this.idTable.get(i).getId().equals(id)) {
                return this.idTable.get(i).getType();
            }
        }
        if (this.hasParent()) {
            return this.parentTable.getIdType(id);
        }
        return "error";
    }

    public String getFunctionType(String id, ArrayList<String> argumentTypes) {
        for (int i = 0; i < this.functionTable.size(); i++) {
            FunctionType currentFunction = this.functionTable.get(i);
            if (currentFunction.getId().equals(id)) {
                ArrayList<String> currentArguments = currentFunction.getTypes();
                if (currentArguments.size() == argumentTypes.size()) {
                    boolean argumentsAreEqual = true;
                    for (int j = 0; j < currentArguments.size(); j++) {
                        if (!currentArguments.get(j).equals(argumentTypes.get(j))) {
                            argumentsAreEqual = false;
                            break;
                        }
                    }
                    if (argumentsAreEqual) {
                        return currentFunction.getType();
                    }
                }
            }
        }
        if (this.hasParent()) {
            return this.parentTable.getFunctionType(id, argumentTypes);
        }
        return "error";
    }

    public boolean isEmpty() {
        if (this.idTable.size() > 0) {
            return false;
        } else if (this.functionTable.size() > 0) {
            return false;
        } else {
            for (int i = 0; i < this.subTables.size(); i++) {
                if (!this.subTables.get(i).isEmpty()) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public String toString() {
        String returnValue = "";
        for (int i = 0; i < this.subTables.size(); i++) {
            if (!this.subTables.get(i).isEmpty()) {
                returnValue += this.subTables.get(i).toString() + ",";
            }
        }
        return "{\n" + (this.idTable.isEmpty() ? "" : ("\"Variable-Table\": \n" + this.idTable.toString() + "\n"))
                + (this.functionTable.isEmpty() ? ""
                        : ("\"Function-Table\": \n" + this.functionTable.toString() + "\n"))
                + (returnValue.equals("") ? "" : ("\"Sub-Tables\": \n [" + returnValue + "]")) + "\n}";
    }
}

class IdType {
    String id;
    String type;
    int position;

    public IdType(String id, String type, int position) {
        this.id = id;
        this.type = type.toLowerCase();
        this.position = position;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return this.type + " " + this.id + " " + this.position;
    }
}

class FunctionType {
    String id, type;
    ArrayList<ArgumentHelper> arguments;

    public FunctionType(String id, String type, ArrayList<ArgumentHelper> arguments) {
        this.id = id;
        this.type = type.toLowerCase();
        for (int i = 0; i < arguments.size(); i++) {
            ArgumentHelper temporary = arguments.get(i);
            temporary.type = temporary.type.toLowerCase();
        }
        this.arguments = arguments;
    }

    public String getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public ArrayList<ArgumentHelper> getArguments() {
        return this.arguments;
    }

    public ArrayList<String> getTypes() {
        // TODO check by reference or by value
        ArrayList<String> types = new ArrayList<String>();
        for (int i = 0; i < this.arguments.size(); i++) {
            types.add(this.arguments.get(i).type);
        }
        return types;
    }

    @Override
    public String toString() {
        return this.id + " " + this.type + " " + this.arguments.toString();
    }
}

class IntermediateCode {
    ArrayList<FourAddressCode> stack;
    ArrayList<String> heap;
    int currentTemporary = 0;
    int currentLabel = 0;

    public IntermediateCode() {
        this.stack = new ArrayList<FourAddressCode>();
        this.heap = new ArrayList<String>();
    }

    public FourAddressCode popStack() {
        FourAddressCode deleted = this.stack.get(this.stack.size() - 1);
        this.stack.remove(this.stack.size() - 1);
        return deleted;
    }

    public String popHeap() {
        String deleted = this.heap.get(this.heap.size() - 1);
        this.heap.remove(this.heap.size() - 1);
        return deleted;
    }

    public void pushHeap(String T) {
        this.heap.add(T);
    }

    public void pushStack(FourAddressCode code) {
        this.stack.add(code);
    }

    public void pushStack(String operator, String left, String right, String direction) {
        this.stack.add(new FourAddressCode(operator, left, right, direction));
    }

    public TemporaryVariable newTemporary(String type) {
        this.currentTemporary++;
        return new TemporaryVariable(type, this.currentTemporary);
    }

    public String newLabel() {
        this.currentLabel++;
        return "@Label" + this.currentLabel;
    }

    @Override
    public String toString() {
        String returnValue = "";
        for (int i = 0; i < this.stack.size(); i++) {
            returnValue += this.stack.get(i).toString() + "\n";
        }
        return returnValue;
    }
}

class FourAddressCode {
    String operator;
    String left;
    String right;
    String direction;

    public FourAddressCode(String operator, String left, String right, String position) {
        this.operator = operator;
        this.left = left;
        this.right = right;
        this.direction = position;
    }

    public FourAddressCode(String operator, String left, String right) {
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return this.operator + "\t " + this.left + "\t " + this.right + "\t " + this.direction;
    }
}

class TemporaryVariable {
    int number;
    String type;

    public TemporaryVariable(String type, int number) {
        this.type = type;
        this.number = number;
    }

    @Override
    public String toString() {
        return "t" + this.number;
    }
}
