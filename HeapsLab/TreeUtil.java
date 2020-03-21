import java.util.*;
/**
 * TreeUtil contains the following methods for manipulating binary trees:
 * 
 * @author Shounak Ghosh
 * @version 11.12.2018
 *
 */
public class TreeUtil
{
    //used to prompt for command line input
    private static Scanner in = new Scanner(System.in);

    //private static final boolean debug = false;

    /**
     * Acesses the value stored in the leftmost node of a tree
     * @param t the tree 
     * @return the object stored in the leftmost Node
     */
    public static Object leftmost(TreeNode t)
    {
        // implement with a loop
        Object leftMostObject = null;
        while (t != null)
        {
            leftMostObject = t.getValue();
            t = t.getLeft();
        }
        return leftMostObject;
        //throw new RuntimeException("Write ME!");
    }

    /**
     * Acesses the value stored in the rightmost node of a tree
     * @param t the tree 
     * @return the object stored in the rightmost Node
     */
    public static Object rightmost(TreeNode t)
    {
        // implement this recursively
        if (t == null)
        {
            return null;
        }
        if (t.getRight() == null)
        {
            return t.getValue();
        }
        return rightmost(t.getRight());
        //throw new RuntimeException("Write ME!");
    }

    /**
     * Acesses the depth of a given tree
     * @param t the tree
     * @return the depth of the tree given
     */
    public static int maxDepth(TreeNode t)
    {
        if (t == null)
        {
            return 0; 
        }
        return 1 + Math.max(maxDepth(t.getRight()), maxDepth(t.getLeft()));
        //throw new RuntimeException("Write ME!"); 
    }

    /**
     * create a random tree of the specified depth.  
     * No attempt to balance the tree is provided.
     * @param depth depth of the tree
     * @return TreeNode object that points to the generated tree
     */
    public static TreeNode createRandom(int depth)
    {
        if (Math.random() * Math.pow(2, depth) < 1)
            return null;
        return new TreeNode(((int)(Math.random() * 10)),
            createRandom(depth - 1),
            createRandom(depth - 1));
    }

    /**
     * Counts the number of nodes in a given tree
     * @param t the tree
     * @return the number of nodes in the tree given
     */
    public static int countNodes(TreeNode t)
    {
        if (t == null)
        {
            return 0;
        }
        return 1 + countNodes(t.getLeft()) + countNodes(t.getRight());
        //throw new RuntimeException("Write ME!");
    }

    /**
     * The number of leaf nodes (nodes whose children are null) in a given tree
     * @param t the tree
     * @return the number of leaf nodes in a given tree
     */
    public static int countLeaves(TreeNode t)
    {
        //throw new RuntimeException("Write ME!");
        if (t == null)
        {
            return 0;
        }
        else if (t.getLeft() == null && t.getRight() == null)
        {
            //System.out.println("Leaf: " + t.getValue());
            return 1;
        }
        else
        {
            return countLeaves(t.getRight()) + countLeaves(t.getLeft());
        }
    }

    /**
     * Graphically moves through a tree using preorder traversal
     * @param t the tree
     * @param display the framework used to display the tree
     * 
     */
    public static void preOrder(TreeNode t, TreeDisplay display)
    {
        //throw new RuntimeException("Write ME!");
        if (t != null)
        {
            display.visit(t);
            preOrder(t.getLeft(), display);
            preOrder(t.getRight(), display);
        }

    }

    /**
     * Graphically moves through a tree using inorder traversal
     * @param t the tree
     * @param display the framework used to display the tree
     */
    public static void inOrder(TreeNode t, TreeDisplay display)
    {
        // throw new RuntimeException("Write ME!");
        if (t != null)
        {
            inOrder(t.getLeft(), display);
            display.visit(t);
            inOrder(t.getRight(), display);
        }
    }

    /**
     * Graphically moves through a tree using postorder traversal
     * @param t the tree
     * @param display the framework used to display the tree>
     */
    public static void postOrder(TreeNode t, TreeDisplay display)
    {
        //throw new RuntimeException("Write ME!");
        if (t != null)
        {
            postOrder(t.getLeft(), display);
            postOrder(t.getRight(), display);
            display.visit(t);
        }
    }

    /**
     * Fills a list of Strings with the values stored in a tree, 
     * using "$" as a marker for null values
     * @param t the tree
     * @param list the list to be filled with the tree's values
     */
    public static void fillList(TreeNode t, List<String> list)
    {
        //throw new RuntimeException("Write ME!");
        if (t != null)
        {
            list.add("" + t.getValue());
            fillList(t.getLeft(), list);
            fillList(t.getRight(), list);
        }
        else
        {
            list.add("$");
        }
        System.out.println(list);
    }

    /**
     * saveTree uses the FileUtil utility class to save the tree rooted at t
     * as a file with the given file name
     * @param fileName is the name of the file to create
     *        which will hold the data values in the tree 
     * @param t is the root of the tree to save
     */
    public static void saveTree(String fileName, TreeNode t)
    {
        //throw new RuntimeException("Write ME!");
        List l = new ArrayList<String>();
        fillList(t, l);
        FileUtil.saveFile(fileName, l.iterator());
    }

    /**
     * buildTree takes in an iterator which will iterate through a valid 
     * description of a binary tree with String values.  
     * Null nodes are indicated by "$" markers
     * @param it the iterator which will iterate over the tree description
     * @return a pointer to the root of the tree built by the iteration
     */
    public static TreeNode buildTree(Iterator<String> it)
    {
        //throw new RuntimeException("Write ME!");
        String currVal = it.next();
        if (currVal.equals("$"))
        {
            return null;
        }
        else
        {
            return new TreeNode(currVal, buildTree(it), buildTree(it));
        }
    }

    /**
     * read a file description of a tree and then build the tree
     * @param fileName is a valid file name for a file 
     *        that describes a binary tree
     * @return a pointer to the root of the tree
     */
    public static TreeNode loadTree(String fileName)
    {
        //throw new RuntimeException("Write ME!");
        return buildTree(FileUtil.loadFile(fileName));
    }

    /**
     * copy a binary tree
     * @param t the root of the tree to copy
     * @return a new tree, which is a complete copy
     *         of t with all new TreeNode objects
     *         pointing to the same values as t (in the same order, shape, etc)
     */
    public static TreeNode copy(TreeNode t)
    {
        //throw new RuntimeException("Write ME!");    
        if (t == null)
        {
            return null;
        }
        TreeNode newT = new TreeNode(t.getValue());
        newT.setLeft(t.getLeft());
        newT.setRight(t.getRight());
        return newT;
    }

    /**
     * tests to see if two trees have the same shape, but not necessarily the
     * same values.  Two trees have the same shape if they have TreeNode objects
     * in the same locations relative to the root
     * @param t1 pointer to the root of the first tree
     * @param t2 pointer to the root of the second tree
     * @return true if t1 and t2 describe trees having the same shape, 
     *         false otherwise
     */
    public static boolean sameShape(TreeNode t1, TreeNode t2)
    {
        //throw new RuntimeException("Write ME!");
        if (t1 == null && t2 == null)
        {
            return true;
        }
        if (t1 != null && t2 != null)
        {
            return sameShape(t1.getLeft(), t2.getLeft()) 
                   && sameShape(t1.getRight(), t2.getRight());
        }
        return false;
    }

    /**
     * Generate a tree for decoding Morse code
     * @param display the display that will show the decoding tree
     * @return the decoding tree
     */
    public static TreeNode createDecodingTree(TreeDisplay display)
    {
        TreeNode tree = new TreeNode("Morse Tree");
        display.displayTree(tree);
        insertMorse(tree, "a", ".-", display);
        insertMorse(tree, "b", "-...", display);
        insertMorse(tree, "c", "-.-.", display);
        insertMorse(tree, "d", "-..", display);
        insertMorse(tree, "e", ".", display);
        insertMorse(tree, "f", "..-.", display);
        insertMorse(tree, "g", "--.", display);
        insertMorse(tree, "h", "....", display);
        insertMorse(tree, "i", "..", display);
        insertMorse(tree, "j", ".---", display);
        insertMorse(tree, "k", "-.-", display);
        insertMorse(tree, "l", ".-..", display);
        insertMorse(tree, "m", "--", display);
        insertMorse(tree, "n", "-.", display);
        insertMorse(tree, "o", "---", display);
        insertMorse(tree, "p", ".--.", display);
        insertMorse(tree, "q", "--.-", display);
        insertMorse(tree, "r", ".-.", display);
        insertMorse(tree, "s", "...", display);
        insertMorse(tree, "t", "-", display);
        insertMorse(tree, "u", "..-", display);
        insertMorse(tree, "v", "...-", display);
        insertMorse(tree, "w", ".--", display);
        insertMorse(tree, "x", "-..-", display);
        insertMorse(tree, "y", "-.--", display);
        insertMorse(tree, "z", "--..", display);
        /*
        insertMorse(tree,"0","-----", display);
        insertMorse(tree,"1",".----", display);
        insertMorse(tree,"2","..---", display);
        insertMorse(tree,"3","...---", display);
        insertMorse(tree,"4","....--", display);
        insertMorse(tree,"5",".....", display);
        insertMorse(tree,"6","-....", display);
        insertMorse(tree,"7","--...", display);
        insertMorse(tree,"8","---..", display);
        insertMorse(tree,"9","----.", display);
         */
        return tree;
    }

    /**
     * helper method for building a Morse code decoding tree.
     * postcondition:  inserts the given letter into the decodingTree,
     *                 in the appropriate position, as determined by
     *                 the given Morse code sequence; lights up the display
     *                 as it walks down the tree
     * @param decodingTree is the partial decoding tree
     * @param letter is the letter to add
     * @param code is the Morse code for letter
     * @param display is the display that will show progress 
     *        as the method walks down the tree
     */
    private static void insertMorse(TreeNode decodingTree, String letter,
    String code, TreeDisplay display)
    {
        String temp = "";
        TreeNode ref = decodingTree;
        for (int i = 0; i < code.length(); i++)
        {
            temp = code.substring(i, i + 1);
            if (temp.equals("."))
            {
                if (ref.getLeft() != null)
                {
                    ref = ref.getLeft();
                }
                else
                {
                    ref.setLeft(new TreeNode("?", null, null));
                    ref = ref.getLeft();
                }
            }
            else if (temp.equals("-"))
            {
                if (ref.getRight() != null)
                {
                    ref = ref.getRight();
                }
                else
                {
                    ref.setRight(new TreeNode("?", null, null));
                    ref = ref.getRight();
                }
            }
            display.visit(ref);
        }
        ref.setValue(letter);                    
    }

    /**
     * decodes Morse code by walking the decoding tree 
     * according to the input code
     * @param decodingTree is the Morse code decoding tree
     * @param cipherText is Morse code consisting of dots, dashes, and spaces
     * @param display is the display object that will show the decoding progress
     * @return the string represented by cipherText
     */
    public static String decodeMorse(TreeNode decodingTree, 
    String cipherText, TreeDisplay display)
    {
        //throw new RuntimeException("Write ME!");
        String decoded = "";
        Tokenizer t = new Tokenizer(cipherText);
        String temp = "";
        String s = "";
        TreeNode tN = decodingTree;
        while (t.hasMoreTokens())
        {
            tN = decodingTree;
            temp = t.nextToken();
            for (int i = 0; i < temp.length(); i++)
            {
                s = temp.substring(i, i + 1);
                if (s.equals("."))
                {
                    //display.visit(tN); // this calls a print statement
                    tN = tN.getLeft();
                }
                else if (s.equals("-"))
                {
                    //display.visit(tN); // this calls a print statement
                    tN = tN.getRight();
                }
            }
            s = "" + tN.getValue();
            //System.out.println("Value: " + s);
            decoded = decoded + s;
        } 
        System.out.println(decoded);
        return decoded;
    }

    /**
     * Evaluates an expression tree
     * @param expTree an expression tree
     * @return the value of the expression stored in the tree 
     *
     */
    public static int eval(TreeNode expTree)
    {
        //throw new RuntimeException("Write ME!");
        int left = 0;
        int right = 0;
        if (expTree != null)
        {
            if (!isOperator((String)expTree.getValue()))
            {
                return Integer.parseInt(((String) expTree.getValue()));
            }
            left = eval(expTree.getLeft());
            right = eval(expTree.getRight());
            return computeVal(right, left, (String) expTree.getValue());
        }
        return -1;
    }

    /**
     * Computes the value of a given operation
     * @precondition op1 and op2 are numbers, and operator is an operator
     * @param op1 the second operand (comes off the stack first)
     * @param op2 the first operand  (comes off the stack second)
     * @param operator the operator for the operation
     * @return String value of the operation
     */
    public static int computeVal(int op1, int op2, String operator)
    {
        if (operator.equals("+"))
        {
            return (op1 + op2);
        }
        else if (operator.equals("-"))
        {
            return (op2 - op1);
        }
        else if (operator.equals("*"))
        {
            return (op1 * op2);
        }
        else if (operator.equals("/"))
        {
            return (op2 / op1);
        }
        else if (operator.equals("%"))
        {
            return  (op2 % op1);
        }
        return Integer.MAX_VALUE;
    }

    /**
     * creates an expression tree given an infix expression
     * @param exp an infix expression seperated by spaces
     * @return the expression tree
     */
    public static TreeNode createExpressionTree(String exp) 
    {
        //throw new RuntimeException("Write ME!");
        String postExp = infixToPostfix(exp);
        //System.out.println("Postfix: "  + postExp);
        Stack<TreeNode> stack = new Stack<TreeNode>();
        Tokenizer t = new Tokenizer(postExp);

        while (t.hasMoreTokens())
        {
            String tok = t.nextToken();
            if (isOperator(tok))
            {
                TreeNode right = stack.pop();
                TreeNode left = stack.pop();
                stack.push(new TreeNode(tok, left, right));
            }
            else
            {
                stack.push(new TreeNode(tok));
            }
        }
        return stack.pop();
    }

    /**
     * converts an infix expression into postfix notation
     * @param exp valid expression in infix form
     * @return equivalent expression in postfix form
     */
    static String infixToPostfix(String exp) 
    { 
        String result = "";  
        Stack<String> stack = new Stack<String>(); 
        Tokenizer t = new Tokenizer(exp);
        while (t.hasMoreTokens())
        { 
            String tok = t.nextToken(); 
            if (!isOperator(tok) && !(tok.equals("(")  || tok.equals(")")))
            {
                result += tok + " ";
                //System.out.println("Res: " + result);
            }   
            // If the scanned character is an '(', push it to the stack. 
            else if (tok.equals("(")) 
                stack.push(tok); 

            // If the scanned character is an ')', 
            //pop and output from the stack 
            // until an '(' is encountered. 
            else if (tok.equals(")")) 
            { 
                while (!stack.isEmpty() && !stack.peek().equals("("))
                {
                    result += stack.pop() + " "; 
                    //System.out.println("Res: " + result);
                } 

                if (!stack.isEmpty() && !stack.peek().equals("(")) 
                    return "Invalid Expression";                 
                else
                    stack.pop(); 
            } 
            else // an operator is encountered 
            { 
                while (!stack.isEmpty()
                       && !(stack.peek().equals("(") 
                       || stack.peek().equals(")"))
                       && getPrec(tok) <= getPrec(stack.peek()))
                {
                    result += stack.pop() + " "; 
                    //System.out.println("Res: " + result);
                } 
                stack.push(tok); 
            } 
        } 
        while (!stack.isEmpty())
        {
            result += stack.pop() + " "; 
            //System.out.println("Res: " + result);

        } 
        return result; 
    } 

    /**
     * Checks if a one-character string is an operator
     * @param s 
     * @return true if string is an operator;
     *         false otherwise
     */
    public static boolean isOperator(String s)
    {
        return (s.equals("+") 
            || s.equals("-") 
            || s.equals("*") 
            || s.equals("/") 
            || s.equals("%"));
    }

    /**
     * calculates the precedence of a certain operation
     * @param operator the operator of the operation
     * @return value of the operation, 
     *         with higher precedence having higher values
     */
    public static int getPrec(String operator)
    {
        if (operator.equals("+") || operator.equals("-"))
        {
            return 0;
        }
        return 1;
    }
}
