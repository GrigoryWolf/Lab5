package commands;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;
/**
 * Класс осуществляющий реализацию команды insert
 * @author grigoryvolkov
 */
public class InsertCommand extends Command{
    public InsertCommand(Invoker invoker){
        super(invoker);
    }
    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        Route route = new Route();
        if (getInvoker().getModelManager().setRoute(route)){
            getInvoker().getModelManager().putModel(route);
            return("Новый маршрут успешно добавлен в коллекцию");
        }
        return "Произошла ошибка во время создания маршрута";
    }

    @Override
    public String getDescription() {
        return "insert: добавить новый элемент с заданным ключом";
    }
}
