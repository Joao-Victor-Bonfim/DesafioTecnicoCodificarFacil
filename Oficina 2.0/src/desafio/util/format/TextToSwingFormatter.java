package desafio.util.format;

/**
 *
 * @author João Victor Bonfim
 */
public class TextToSwingFormatter implements GenericFormatter<String, String> {

    @Override
    public String format(String original) {
        return ("<html> " + original.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll(System.lineSeparator(), "<br/>") + " </html>");
    }

}
