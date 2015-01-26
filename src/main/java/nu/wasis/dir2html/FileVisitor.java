package nu.wasis.dir2html;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

import org.apache.commons.io.comparator.NameFileComparator;

public class FileVisitor {

    private static final DirectoryFileFilter DIRECTORY_FILTER = new DirectoryFileFilter();
    private static final FileFileFilter FILE_FILTER = new FileFileFilter();

    private final File baseFile;

    public FileVisitor(final File baseFile) {
        this.baseFile = baseFile;
    }

    public void run(final FileHandler handler) {
        handler.start();
        runRec(handler, baseFile);
        handler.stop();
    }

    private void runRec(final FileHandler consumer, final File baseFile) {
        if (baseFile.isDirectory()) {
            consumer.beforeDirectory(baseFile);
        }
        if (baseFile.isDirectory()) {
            consumer.handleDirectory(baseFile);
        } else if (baseFile.isFile()) {
            consumer.handleFile(baseFile);
        }
        if (baseFile.isDirectory()) {
            final File[] directories = baseFile.listFiles(DIRECTORY_FILTER);
            Arrays.sort(directories, NameFileComparator.NAME_COMPARATOR);
            for (final File file : directories) {
                runRec(consumer, file);
            }
            final File[] files = baseFile.listFiles(FILE_FILTER);
            Arrays.sort(files, NameFileComparator.NAME_COMPARATOR);
            for (final File file : files) {
                runRec(consumer, file);
            }
        }
        if (baseFile.isDirectory()) {
            consumer.afterDirectory(baseFile);
        }
    }

    private static final class DirectoryFileFilter implements FileFilter {
        @Override
        public boolean accept(final File file) {
            return file.isDirectory();
        }
    }

    private static final class FileFileFilter implements FileFilter {
        @Override
        public boolean accept(final File file) {
            return file.isFile();
        }
    }

}
