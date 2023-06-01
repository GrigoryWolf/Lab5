package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;

/**
 * Класс осуществляющий реализацию команды help
 * @author grigoryvolkov
 */
public class CommandHelp extends Command {
    public CommandHelp(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String ... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        StringBuilder helpInformation = new StringBuilder("Список доступных команд: \n");
        for (Command command : getInvoker().getReader().getCommandManager().getCommands()){
            helpInformation.append(command.getDescription()).append("\n");
        }
        return helpInformation.toString();
    }
    @Override
    public String getDescription() {
        return "help : вывести справку по доступным командам";
    }
}
