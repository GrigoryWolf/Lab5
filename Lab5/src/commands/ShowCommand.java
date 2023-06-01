package commands;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

/**
 * Класс осуществляющий реализацию команды show
 * @author grigoryvolkov
 */
public class ShowCommand extends Command{
    public ShowCommand(Invoker invoker){
        super(invoker);
    }
    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        if (getInvoker().getModelManager().getCollection().isEmpty()){return "Коллекция пуста";}
        for (Route route : getInvoker().getModelManager().getCollection().values()){
            getInvoker().getPrinter().print(route.toString());
        }
        return "Команда успешно выполнена";
    }

    @Override
    public String getDescription() {
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
}
