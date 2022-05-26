import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then read link upto next )
        int currentIndex = 0;
        while(currentIndex < markdown.length()) {
            // test 8 error, thinking open bracket is on the second line

            int openBracket = markdown.indexOf("[", currentIndex);
            if (openBracket == -1) {System.out.println("here1");return new ArrayList<>();}
            // check if openBracket on first line
            if (markdown.charAt(0) == '[') {
                openBracket=0;
            }
            int closeBracket = markdown.indexOf("]", openBracket);
            if (closeBracket == -1) {System.out.println("here2");return new ArrayList<>();}
            int openParen = markdown.indexOf("(", closeBracket);
            if (openParen == -1) {System.out.println("here3");return new ArrayList<>();}
            int closeParen = markdown.indexOf(")", openParen);
            if (closeParen == -1) {System.out.println("here4");return new ArrayList<>();}

            // if the open paren does not touch a closing bracket, then it is not a link
            if (openParen-closeBracket != 1) {
                currentIndex = closeParen+1;
                System.out.println(currentIndex);
                continue;
            }
            try {
                toReturn.add(markdown.substring(openParen + 1, closeParen));
                if (openBracket == 0) {
                    return toReturn;
                }
                
            }
            catch(Exception StringIndexOutOfBoundsException) {
                toReturn = null;
                return new ArrayList<>();
            }
            currentIndex = closeParen + 1;
        }

        return toReturn;
    }


    public static void main(String[] args) throws IOException {
        Path fileName = Path.of(args[0]);
        String content = Files.readString(fileName);
        ArrayList<String> links = getLinks(content);
	    System.out.println(links);
    }
}
