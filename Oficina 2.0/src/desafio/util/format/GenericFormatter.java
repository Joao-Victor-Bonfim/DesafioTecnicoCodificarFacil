package desafio.util.format;

/**
 *
 * @author João Victor Bonfim
 * @param <O>
 * @param <F>
 */
public interface GenericFormatter<O, F> {

    public F format(O original);
}
