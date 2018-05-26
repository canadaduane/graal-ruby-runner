import java.io.File;
import java.io.IOException;

import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;

public class RubyRunner {
  public static void main(String[] args) {
    String scriptFilename = "script.rb";
    File file = new File(".", scriptFilename);
    try {
      String language = Source.findLanguage(file);
      Source source = Source.newBuilder(language, file).build();

      // Need to at least allow Native Access for ruby; see
      // https://stackoverflow.com/questions/50027675/graalvm-access-to-native-code-is-not-allowed-by-the-host-environment
      Context context = Context.newBuilder().allowAllAccess(true).build();

      System.out.println("Evaluating " + scriptFilename + " as " + language);
      Value result = context.eval(source);
    } catch (IOException ex) {
      System.out.println(ex.toString());
      System.out.println("Can't open file " + scriptFilename);
      System.exit(-1);
    }
  }
}