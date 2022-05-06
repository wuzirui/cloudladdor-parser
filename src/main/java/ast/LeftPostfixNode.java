package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LeftPostfixNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -23;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var eps = Arrays.asList(CL.Assign, CL.AddAssign, CL.SubAssign, CL.MulAssign, CL.DivAssign,
                CL.ModAssign);
        if (next.getType() == CL.Dot) {
            children.add(new TokenNode(CL.Dot));
            children.add(new TokenNode(CL.Identifier));
            children.add(new LeftPostfixNode());
        }
        else if (next.getType() == CL.LBrack) {
            children.add(new TokenNode(CL.LBrack));
            children.add(new ExpressionNode());
            children.add(new TokenNode(CL.RBrack));
            children.add(new LeftPostfixNode());
        }
        else if (eps.contains(next.getType())) {
            return;
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
