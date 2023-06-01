package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

import java.util.HashMap;
import java.util.Map;
/**
 * Класс осуществляющий реализацию команды group_counting_by_name
 * @author grigoryvolkov
 */

public class GroupContingByNameCommand extends Command{
    public GroupContingByNameCommand(Invoker invoker){
        super(invoker);
    }
    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        HashMap<String, Integer> counter = new HashMap<>();
        if (getInvoker().getModelManager().getCollection().isEmpty()){
            return "Коллекция пуста";
        }
        for (Route route  : getInvoker().getModelManager().getCollection().values()){
            String name = route.getName();
            if (counter.containsKey(name)){
                counter.put(name, counter.get(name) + Integer.valueOf(1));
            }
            else {
                counter.put(name, Integer.valueOf(1));
            }
        }
        for (Map.Entry<String, Integer> entry : counter.entrySet()){
            getInvoker().getPrinter().print(String.format("Имя \"%s\" находится в коллекции в количестве %s экземпляров", entry.getKey(), counter.get(entry.getKey())));
        }
        return "Команда успешно выполнена";
    }

    @Override
    public String getDescription() {
        return "group_counting_by_name : сгруппировать элементы коллекции по значению поля name, вывести количество элементов в каждой группе";
    }
}
