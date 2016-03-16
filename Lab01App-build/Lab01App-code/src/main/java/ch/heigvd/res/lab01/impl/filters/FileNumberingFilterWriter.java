package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  // Remark: we write char by char and check the char value to detect line break and increment the line number
  // For string, maybe it could be more efficient to use a regex on the string and then write it as a string, but i
  // could not figure out how to do that

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineCounter = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // use existing method override below, with a char[] as argument
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    len = Math.min(cbuf.length, len); // update len value
    for(int i = 0; i < len; i++){
      write(cbuf[off + i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(lineCounter == 0){
      // write index of the first line
      out.write(String.valueOf(++lineCounter) + '\t');
    }

    out.write(c); // Write single char

    // Look for line break
    if(c == '\n'){
      // write index of the new line (used to write index of empty line)
      out.write(String.valueOf(++lineCounter) + '\t');
    }
  }

}
