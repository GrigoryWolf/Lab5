package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Route;

import static java.lang.Double.parseDouble;

/**
 * Класс осуществляющий реализацию команды count_by_distance
 * @author grigoryvolkov
 */
public class CountByDistanceCommand extends Command{
    public CountByDistanceCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        try {
            argsСheck(1, args);
            Double distance = parseDouble(args[0].trim().replace(",","."));
            long count = 0;
            if (getInvoker().getModelManager().getCollection().isEmpty()) {
                return "Коллекция пуста";
            }
            for (Route route : getInvoker().getModelManager().getCollection().values()) {
                if (distance.equals(route.getDistance())){
                    count += 1;
                }
            }
            return String.format("Число объектов класса route со значением distance %s: %s", distance, count);
        }
        catch (NumberFormatException exception){
            return "Аргумент должен быть числом типа Double!";
        }
    }

    @Override
    public String getDescription() {
        return "count_by_distance distance : вывести количество элементов, значение поля distance которых равно заданному";
    }
}
