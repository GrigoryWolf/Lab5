package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
/**
 * Класс осуществляющий реализацию команды history
 * @author grigoryvolkov
 */
public class CommandHistory extends Command{
    public CommandHistory(Invoker invoker){
        super(invoker);
    }
    public String execute(String ... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        return getInvoker().getReader().getCommandManager().getHistory();
    }

    @Override
    public String getDescription() {
        return "history : вывести последние 13 команд (без их аргументов)";
    }
}

