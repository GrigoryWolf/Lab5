package commands;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

import static java.lang.Integer.parseInt;
/**
 * Класс осуществляющий реализацию команды remove_lower_key
 * @author grigoryvolkov
 */
public class RemoveLowerKeyCommand extends Command{
    public RemoveLowerKeyCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        try {
            argsСheck(1, args);
            Integer id = parseInt(args[0].trim());
            for (Route route : getInvoker().getModelManager().getCollection().values()) {
                if (id > route.getId()) {
                    getInvoker().getModelManager().deleteModel(route.getId());
                }
            }
            return String.format("Все элементы, id которых меньше %s были удалены", id);
        }
        catch (NumberFormatException exception){
            return "Аргумент должен быть числом типа Integer!";
        }

    }

    @Override
    public String getDescription() {
        return "remove_lower_key id : удалить из коллекции все элементы, ключ которых меньше, чем заданный.";
    }
}
