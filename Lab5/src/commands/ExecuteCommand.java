package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Exceptions.RecursionException;
import core.Invoker;
import core.reader.ReaderFiles;
import core.reader.IReader;
import java.util.LinkedList;
/**
 * Класс осуществляющий реализацию команды execute_script
 * @author grigoryvolkov
 */
public class ExecuteCommand extends Command{
    private String filePath;
    private IReader reader;
    private static LinkedList<IReader> readersQueue = new LinkedList<>();
    private static boolean recursionFlag = false;
    private static LinkedList<String> pathChain = new LinkedList<>();
    public ExecuteCommand(Invoker invoker){
        super(invoker);
    }

    @Override
    public String execute(String... args) throws NotValidArgumentsException {
        argsСheck(1, args);
        String file = args[0].trim();
        try {
            if (recursionCheck(file)){
                reader = new ReaderFiles(file, getInvoker());
                readersQueue.add(reader);
                reader.start();
                pathChain.remove(file);
            }
            else{
                for(IReader reader : readersQueue){
                    reader.stop();
                }
                throw new RecursionException("Рекурсия! Execute вызывает тот же файл внутри себя");
            }
            return "Команды из файла успешно выполнены";
        }
        catch (RecursionException ex){
            return ex.getMessage();
        }
    }

    @Override
    public String getDescription() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
    }

    private boolean recursionCheck(String filePath){
        if (pathChain.contains(filePath)){
            return false;
        }
        pathChain.add(filePath);
        return true;
    }
}
