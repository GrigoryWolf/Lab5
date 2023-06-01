package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
/**
 * Класс осуществляющий реализацию команды info
 * @author grigoryvolkov
 */
public class InfoCommand extends Command{
    public InfoCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        getInvoker().getModelManager().getInfoAboutCollection();
        return "Команда успешно выполнена";
    }

    @Override
    public String getDescription() {
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
    }
}
