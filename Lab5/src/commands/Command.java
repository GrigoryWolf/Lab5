package commands;

import core.Exceptions.NotValidArgumentsException;
import core.Invoker;

import java.io.IOException;
/**
 * Абстрактный класс содержит базовые методы для реализации команд
 * @author grigoryvolkov
 */
public abstract class Command {
    private Invoker invoker;
    public Command(Invoker invoker){
        this.invoker = invoker;
    }
    public abstract String execute(String ... args) throws NotValidArgumentsException, IOException;
    public abstract String getDescription();
    public Invoker getInvoker(){
        return invoker;
    }
    public void argsСheck(int numberOfArgs, String ... args) throws NotValidArgumentsException {
        if (numberOfArgs !=args.length){
            throw new NotValidArgumentsException(String.format("Число аргументов должно быть равным %s", numberOfArgs));
        }
    }
}
