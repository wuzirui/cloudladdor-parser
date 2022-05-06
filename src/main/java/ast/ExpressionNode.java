package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExpressionNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();
    @Override
    public int getType() {
        return -8;
    }

    @Override
    public List<ASTNode> getChildren() {
        return children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        final var r1 = Arrays.asList(CL.NumberLiteral, CL.BoolLiteral, CL.StringLiteral,
                CL.LBrack, CL.LBrace, CL.LParen, CL.Identifier);
        final var r2 = List.of(CL.Function);
        final var r3 = Arrays.asList(CL.Sub, CL.Exclamation);

        if (r1.contains(next.getType())) {
            children.add(new PrimaryExpressionNode());
            children.add(new EpNode());
        }
        else if (r2.contains(next.getType())) {
            children.add(new TokenNode(CL.Function));
            children.add(new TokenNode(CL.LParen));
            children.add(new FpNode());
            children.add(new EpNode());
        }
        else if (r3.contains(next.getType())) {
            children.add(new TokenNode(next.getType()));
            children.add(new ExpressionNode());
            children.add(new EpNode());
        }

        printChildren(next);
        extendChildren(lexer);
    }
}
