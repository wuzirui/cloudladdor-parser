package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StatsNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -25;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        final var r1 = Arrays.asList(CL.If, CL.Function, CL.Break, CL.Continue, CL.For, CL.While, CL.Return,
                CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral, CL.LBrace, CL.LBrack, CL.LParen, CL.Identifier,
                CL.Import, CL.Export, CL.Let);
        if (r1.contains(next.getType())) {
            children.add(new StatementNode());
            children.add(new StatsPNode());
        }
        else if (next.getType() == CL.RBrace) {
            return;
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
