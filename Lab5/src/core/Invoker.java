package core;
import core.manager.FileManager;
import core.manager.ModelManager;
import core.reader.CommandLineInterfaceReader;
import core.reader.IReader;

import java.io.File;
import java.io.IOException;

/**
 * Класс вызывающий команды, организует работу всех manager-классов
 * @author grigoryvolkov
 */
public class Invoker {
    private final IReader reader;
    private final ModelManager modelManager;
    private FileManager fileManager;
    private final Printer printer;
    public Invoker(Printer printer, FileManager fileManager){
        this.printer = printer;
        reader = new CommandLineInterfaceReader(this);
        modelManager = new ModelManager(this);
        this.fileManager = fileManager;
    }
    public Invoker(Printer printer){
        this.printer = printer;
        reader = new CommandLineInterfaceReader(this);
        modelManager = new ModelManager(this);
    }
    public ModelManager getModelManager() {
        return modelManager;
    }
    public IReader getReader(){
        return reader;
    }
    public void startReading(){
        printer.print("Введите команду help для получения списка доступных команд");
        reader.start();
    }

    public Printer getPrinter() {
        return printer;
    }

    public FileManager getFileManager(){
        return fileManager;
    }
}

