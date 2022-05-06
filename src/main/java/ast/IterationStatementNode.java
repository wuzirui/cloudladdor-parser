package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class IterationStatementNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -19;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var r1 = List.of(CL.While);
        var r2 = List.of(CL.For);
        if (r1.contains(next.getType())) {
            children.add(new TokenNode(CL.While));
            children.add(new TokenNode(CL.LParen));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.RParen));
            children.add(new StatementNode());
        }
        else if (r2.contains(next.getType())) {
            children.add(new TokenNode(CL.For));
            children.add(new TokenNode(CL.LParen));
            children.add(new ExpressionNode());
            children.add(new ForInitNode());
            children.add(new TokenNode(CL.Semi));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.Semi));
            children.add(new ExpressionStatementNode());
            children.add(new TokenNode(CL.RParen));
            children.add(new StatementNode());
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
