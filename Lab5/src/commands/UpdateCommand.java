package commands;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import static java.lang.Integer.parseInt;

/**
 * Класс осуществляющий реализацию команды update
 * @author grigoryvolkov
 */
public class UpdateCommand extends Command{
    public UpdateCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        try {
            argsСheck(1, args);
            Integer id = parseInt(args[0]);
            getInvoker().getModelManager().update(id);
            return "Команда успешно выполнена";
        }
        catch (NumberFormatException exception){return "Аргумент должен быть числом типа Integer!";}
    }

    @Override
    public String getDescription() {
        return "update id: обновить значение элемента коллекции, id которого равен заданному";
    }
}
