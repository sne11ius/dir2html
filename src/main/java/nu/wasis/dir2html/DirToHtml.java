package nu.wasis.dir2html;

import java.io.File;

public class DirToHtml {

    public static String toHtml(final File file) {
        return toHtml(file, "");
    }

    public static String toHtml(final File file, final String active) {
        final HtmlGeneratingFileHandler handler = new HtmlGeneratingFileHandler(file, active, null);
        new FileVisitor(file).run(handler);
        return handler.print();
    }

    public static String toHtml(final File file, final String active, final String hrefTemplate) {
        System.err.println("Template: " + hrefTemplate);
        final HtmlGeneratingFileHandler handler = new HtmlGeneratingFileHandler(file, active, hrefTemplate);
        new FileVisitor(file).run(handler);
        return handler.print();
    }

}
