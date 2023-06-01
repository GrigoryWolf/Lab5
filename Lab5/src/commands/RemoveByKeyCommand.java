package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;

import static java.lang.Integer.parseInt;
/**
 * Класс осуществляющий реализацию команды remove_key
 * @author grigoryvolkov
 */
public class RemoveByKeyCommand extends Command{
    public RemoveByKeyCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        try{
            argsСheck(1, args);
            Integer id = parseInt(args[0].trim());
            getInvoker().getModelManager().deleteModel(id);
            return String.format("Маршрут по id: %s успешно удален", id);}
        catch(NumberFormatException exception){
            return "Аргумент должен быть числом типа Integer!";
        }
    }

    @Override
    public String getDescription() {
        return "remove_key id: удалить элемент из коллекции по его ключу";
    }
}
