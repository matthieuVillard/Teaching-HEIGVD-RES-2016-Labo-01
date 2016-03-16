package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // Use String method to convert to uppercase and Writer method to write
    out.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // first convert to string and then to uppercase
    write(String.valueOf(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    // Use Character method to convert to uppercase and Writer method to write
    out.write(Character.toUpperCase(c));
  }

}
