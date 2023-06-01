package core.reader;
import core.Invoker;
import core.manager.CommandManager;
import java.util.Scanner;
/**
 * Класс, который считывает и обрабатывает ввод командной строки
 * @author grigoryvolkov
 */

public class CommandLineInterfaceReader implements IReader {
    private Boolean isWorking = false;
    private CommandManager commandManager;
    private Scanner scanner;
    private Invoker invoker;
    public CommandLineInterfaceReader(Invoker invoker){
        this.invoker = invoker;
    }
    @Override
    public void start() {
        scanner = new Scanner(System.in);
        isWorking = true;
        commandManager = new CommandManager(invoker);
        while(isWorking){
            String line = nextLine();
            commandManager.parseLine(line);
        }
    }
    public void stop(){
        isWorking = false;
    }
    @Override
    public String nextLine() {
        System.out.printf(">");
        if (scanner.hasNextLine()){return scanner.nextLine();}
        stop();
        return "";

    }
    public Boolean getWorking(){
        return isWorking;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }
}
