package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.List;

public class ExpressionTailNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -27;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        if (next.getType() == CL.LParen) {
            children.add(new TokenNode(CL.LParen));
            children.add(new ExpListNode());
            children.add(new TokenNode(CL.RParen));
        }
        else {
            children.add(new LeftPostfixNode());
            children.add(new AssignOperatorNode());
            children.add(new ExpressionNode());
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
