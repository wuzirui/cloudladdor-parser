package ast;

import lexer.CL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AssignOperatorNode implements ASTNode {
    private List<ASTNode> children = new ArrayList<>();

    @Override
    public int getType() {
        return -22;
    }

    @Override
    public List<ASTNode> getChildren() {
        return this.children;
    }

    @Override
    public void parse(CL lexer) {
        var next = lexer.getToken();
        var acc = Arrays.asList(CL.Assign, CL.AddAssign, CL.SubAssign, CL.MulAssign, CL.DivAssign,
                CL.ModAssign);
        if (acc.contains(next.getType())) {
            children.add(new TokenNode(next.getType()));
        }
        else {
            dispatchError(next);
        }
        printChildren(next);
        extendChildren(lexer);
    }
}
