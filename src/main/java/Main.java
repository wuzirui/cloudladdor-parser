import ast.ASTNode;
import org.antlr.v4.runtime.misc.Pair;
import parser.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static void dfs(ASTNode node, List<String> nodes, List<Pair<String, String>> edges) {
        String cur = Parser.renderNodeToDotString(node);
        if (!nodes.contains(cur)) {
            nodes.add(cur);
        }
        if (node.getChildren() == null) return;
        node.getChildren().forEach((e) -> {
            edges.add(new Pair<>(cur, Parser.renderNodeToDotString(e)));
            dfs(e, nodes, edges);
        });
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("usage: parser [filename]");
            return;
        }
        System.out.printf("Parsing filename: %s\n", args[0]);
        Parser parser;
        try {
            parser = Parser.fromFilename(args[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        var root = parser.parse();

        List<String> nodes = new ArrayList<>();
        List<Pair<String, String>> edges = new ArrayList<>();
        dfs(root, nodes, edges);

        String output = "digraph \"DirectedGraph\" {\n" +
                "graph [label = \"testNonEscaped\", labelloc=t, concentrate = true];\n";
        for (var e : nodes) {
            output += "\"" + e + "\"\n";
        }
        for (var  e : edges) {
            output += "\"" + e.a + "\" -> \"" + e.b + "\"\n";
        }
        output += "}\n";
        System.out.println(output);
    }
}
