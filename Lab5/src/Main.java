import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;
import core.Invoker;
import core.Printer;
import core.manager.FileManager;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        try {
            Printer printer = new Printer();
            Scanner scanner = new Scanner(System.in);
            String stringEnv;
            do{
                printer.print("Введите имя переменной окружения: ");
                stringEnv = scanner.nextLine();
            }
            while (stringEnv.trim() == "");
            String path;
            if(System.getenv().containsKey(stringEnv)){path = System.getenv(stringEnv);}
            else {
                printer.print("Такой переменной окружения не существует. Будет использован файл data.json");
                path = "data.json";
            }
            FileManager fileManager = new FileManager(path, printer);
            Invoker invoker = new Invoker(printer, fileManager);
            fileManager.collectionLoad(invoker);
            printer.print("Коллекция загружена из файла");
            invoker.startReading();
        }
        catch (IOException | NullPointerException | JsonSyntaxException exception){
            Printer printer = new Printer();
            printer.print("Произошла ошибка при работе с файлом");
            Invoker invoker = new Invoker(printer);
            invoker.startReading();
        }
    }
}