package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgramItemNode implements ASTNode{
    private List<ASTNode> children = null;

    @Override
    public int getType() {
        return -2;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        children = new ArrayList<>();

        final var r1 = List.of(CL.Function);
        final var r2 = Arrays.asList(CL.If, CL.Break, CL.Continue, CL.For, CL.While, CL.Return,
                CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral, CL.LBrace, CL.LBrack, CL.LParen, CL.Identifier,
                CL.Let);
        final var r3 = List.of(CL.Import);
        final var r4 = List.of(CL.Export);

        if (r1.contains(next.getType())) {
            children.add(new FunctionDefinitionNode());
        }
        else if (r2.contains(next.getType())) {
            children.add(new StatementNode());
        }
        else if (r3.contains(next.getType())) {
            children.add(new ImportStatementNode());
        }
        else if (r3.contains(next.getType())) {
            children.add(new ExportStatementNode());
        }
        else {
            dispatchError(next);
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
