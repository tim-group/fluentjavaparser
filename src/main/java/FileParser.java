import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;

public class FileParser {
    public static void main(String[] args) throws Exception {
        FileParser parser = new FileParser();
        InputStream in = new FileInputStream(new File(args[0]));
        Tree tree = parser.parse(in);
        print(tree, 0);
    }

    public static void print(Tree tree, int level) {
        for (int i=0; i<level; i++) { System.out.print(" "); }
        System.out.println(tree.getText());

        int childCount = tree.getChildCount();
        for (int i=0; i<childCount; i++) {
            print(tree.getChild(i), level+1);
        }
    }

    public Tree parse(InputStream in) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(in);
        JavaLexer lexer = new JavaLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        JavaParser parser = new JavaParser(tokens);
        return (Tree) parser.compilationUnit().getTree();
    }
}

