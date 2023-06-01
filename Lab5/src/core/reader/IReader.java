package core.reader;
import core.manager.CommandManager;
/**
 * Интерфейс, содержит методы для чтения
 * @author grigoryvolkov
 */
public interface IReader {
    void start();
    void stop();

    String nextLine();
    Boolean getWorking();
    CommandManager getCommandManager();
}
