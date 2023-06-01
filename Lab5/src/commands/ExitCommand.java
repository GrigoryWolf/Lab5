package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
/**
 * Класс осуществляющий реализацию команды exit
 * @author grigoryvolkov
 */
public class ExitCommand extends Command {
    public ExitCommand(Invoker invoker){
        super(invoker);
    }
    public String execute(String ... args) throws NotValidArgumentsException {
        argsСheck(0, args);
        getInvoker().getPrinter().print("Вы уверены что хотите выйти? Все несохраненные данные будут удалены! Введите \"да\" или \"нет\"");
        while(true) {
            String answer = getInvoker().getReader().nextLine().toLowerCase();
            switch (answer) {
                case "да" -> {
                    getInvoker().getReader().stop();
                    return "До встречи!";
                }
                case "нет" -> {
                    return "Хорошо! Тогда продолжим работу!";
                }
                default -> getInvoker().getPrinter().print(" Введите \"да\" или \"нет\"");
            }
        }

    }

    @Override
    public String getDescription() {
        return "exit : завершить программу (без сохранения в файл)";
    }
}
