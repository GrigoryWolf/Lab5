package commands;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.manager.FileManager;

import java.io.IOException;
/**
 * Класс осуществляющий реализацию команды save
 * @author grigoryvolkov
 */
public class SaveCommand extends Command{
    FileManager fileManager;
    public SaveCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException, IOException {
        try {
            argsСheck(1, args);
            fileManager = new FileManager(args[0], getInvoker().getPrinter());
            fileManager.collectionSave(getInvoker().getModelManager().getCollection(), args[0]);
            return "Файл успешно сохранен";
        }
        catch (IOException exception){
            return "Не удалось сохранить данные в файл";
        }
    }

    @Override
    public String getDescription() {
        return "save : сохранить коллекцию в файл.";
    }
}
