package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;

/**
 * Класс осуществляющий реализацию команды clear
 * @author grigoryvolkov
 */
public class ClearCommand extends Command{
    public ClearCommand(Invoker invoker){super(invoker);}

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        getInvoker().getModelManager().clearCollection();
        return "Коллекция успешно очищена";
    }

    @Override
    public String getDescription() {
        return "clear : очистить коллекцию";
    }
}
