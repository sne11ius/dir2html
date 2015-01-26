package nu.wasis.dir2html;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Stack;

import org.apache.commons.io.FilenameUtils;

public class HtmlGeneratingFileHandler implements FileHandler {

    Stack<URI> baseUris = new Stack<>();

    private URI baseUri;
    private final URI rootUri;

    final StringBuilder s = new StringBuilder();
    int tabs = 1;

    private final String active;

    private final String hrefTemplate;

    public HtmlGeneratingFileHandler(final File baseDir, final String active, final String hrefTemplate) {
        this.baseUri = baseDir.toURI();
        this.rootUri = baseUri;
        baseUris.push(baseUri);
        this.active = active;
        this.hrefTemplate = hrefTemplate;
    }

    @Override
    public void start() {
        s.append("<ul class=\"directory-tree\">\n");
    }

    @Override
    public void stop() {
        s.append("</ul>\n");
    }

    @Override
    public void handleFile(final File file) {
        addTabs();
        final String extension = FilenameUtils.getExtension(file.getName());
        final String activeClass = isActive(file) ? " active" : "";
        final String relativeFilename = relative(file);
        final String rootRelativeFilename = "/" + rootRelative(file);
        final String linkStart = createLinkStart(rootRelativeFilename);
        final String linkEnd = createLinkEnd();
        s.append("<li class=\"file " + extension + activeClass + "\" data-path=\"" + rootRelativeFilename + "\">" + linkStart + relativeFilename + linkEnd + "</li>\n");
    }

    private String createLinkStart(final String rootRelativeFilename) {
        if (null == hrefTemplate) {
            return "";
        }
        try {
            return "<a href=\"" + hrefTemplate.replace("{file}", URLEncoder.encode(rootRelativeFilename, StandardCharsets.UTF_8.name())) + "\">";
        } catch (final UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createLinkEnd() {
        return null != hrefTemplate ? "</a>" : "";
    }

    private boolean isActive(final File file) {
        return ("/" + rootRelative(file)).equals(active);
    }

    @Override
    public void handleDirectory(final File directory) {
        addTabs();
        final String dir = relative(directory);
        s.append("<li class=\"directory-name\">" + (dir.length() > 1 ? dir.substring(0, dir.length() - 1) : dir) + "</li>\n");
        final URI newBaseUri = directory.toURI();
        baseUris.push(baseUri);
        baseUri = newBaseUri;
    }

    @Override
    public void beforeDirectory(final File directory) {
        addTabs();
        s.append("<li class=\"directory\">\n");
        ++tabs;
        addTabs();
        s.append("<ul class=\"directory\">\n");
        ++tabs;
    }

    @Override
    public void afterDirectory(final File directory) {
        --tabs;
        addTabs();
        s.append("</ul>\n");
        --tabs;
        addTabs();
        s.append("</li>\n");
        baseUri = baseUris.pop();
    }

    public String print() {
        return s.toString();
    }

    private void addTabs() {
        for (int i = 0; i < tabs; ++i) {
            s.append("  ");
        }
    }

    private String relative(final File file) {
        final String path = baseUri.relativize(file.toURI()).getPath();
        return path.isEmpty() ? "/" : path;
    }

    private String rootRelative(final File file) {
        final String path = rootUri.relativize(file.toURI()).getPath();
        return path.isEmpty() ? "/" : path;
    }

}
