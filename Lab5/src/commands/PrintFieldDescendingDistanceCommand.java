package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Класс осуществляющий реализацию команды print_field_descending_distance
 * @author grigoryvolkov
 */
public class PrintFieldDescendingDistanceCommand extends Command{
    public PrintFieldDescendingDistanceCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        if (getInvoker().getModelManager().getCollection().isEmpty()){
            return "Коллекция пуста";
        }
        ArrayList<Double> distances = new ArrayList<>();
        for (Route route  : getInvoker().getModelManager().getCollection().values()){
            distances.add(route.getDistance());
        }
        Collections.sort(distances, Collections.reverseOrder());
        return "Дистанции маршрутов в порядке убывания: " + distances.toString();
    }

    @Override
    public String getDescription() {
        return "print_field_descending_distance : вывести значения поля distance всех элементов в порядке убывания";
    }
}
