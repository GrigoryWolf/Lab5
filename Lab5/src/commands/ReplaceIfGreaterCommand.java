package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

import static java.lang.Integer.parseInt;
/**
 * Класс осуществляющий реализацию команды replace_if_greater
 * @author grigoryvolkov
 */
public class ReplaceIfGreaterCommand extends Command{
    public ReplaceIfGreaterCommand(Invoker invoker){
        super(invoker);
    }
    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        try {
            argsСheck(1, args);
            Integer id = parseInt(args[0]);
            if (!getInvoker().getModelManager().getCollection().containsKey(id)) {
                return "Модели с таким id не существует";
            }
            Route newRoute = new Route();
            getInvoker().getModelManager().setRoute(newRoute);
            if (newRoute.getDistance() > getInvoker().getModelManager().getCollection().get(id).getDistance()) {
                getInvoker().getModelManager().update(newRoute);
                return "Модель успешно заменена на модель с большим значением distance";
            }
            return "У новой модели значение distance меньше";
        }
        catch (NumberFormatException exception){return "Аргумент должен быть числом типа Integer!";}
    }

    @Override
    public String getDescription() {
        return "replace_if_greater id : заменить значение по ключу, если новое значение больше старого";
    }
}
