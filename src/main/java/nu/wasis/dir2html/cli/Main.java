package nu.wasis.dir2html.cli;

import java.io.File;

import nu.wasis.dir2html.DirToHtml;

public class Main {

    public static void main(final String[] args) {
        System.out.println(DirToHtml.toHtml(new File(args[0]), args[1], args[2]));
    }

}
