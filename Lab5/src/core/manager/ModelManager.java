package core.manager;
import core.Exceptions.NotValidArgumentsException;
import core.Invoker;
import core.model.Coordinates;
import core.model.Location;
import core.model.Route;
import java.util.*;
/**
 * Класс, который управляет коллекцией
 * @author grigoryvolkov
 */
public class ModelManager {
    private final ArrayList<Integer> usedIDs = new ArrayList<>();
    private LinkedHashMap models;
    private Date creationDate;
    private final Invoker invoker;
    public ModelManager(Invoker invoker){
        models = new LinkedHashMap<Integer, Route>();
        creationDate = new Date();
        this.invoker = invoker;
    }
    public void putModel(Route route){
        models.put(route.getId(), route);
    }
    public void update(Integer id){
        if (!usedIDs.contains(id)){
            invoker.getPrinter().print("Элемента с таким id не существует!");
            return;
        }
        setRoute((Route) models.get(id));
    }
    public void update(Route route){
        models.remove(route.getId());
        models.put(route.getId(), route);
    }
    public void deleteModel(Integer id){
        if (usedIDs.contains(id)) {
            models.remove(id);
            usedIDs.remove(id);
        }
        else {invoker.getPrinter().print("Элемента с таким id не существует!");}
    }
    public void clearCollection(){
        models.clear();
        usedIDs.clear();
    }
    public Integer generateId(){
        Random rnd = new Random();
        Integer id = rnd.nextInt(Integer.MAX_VALUE);
        while(usedIDs.contains(id)){
            id = rnd.nextInt();
        }
        usedIDs.add(id);
        return id;
    }
    public Date generateDate(){
        return new Date();
    }
    public String requestName(String message){
        while(invoker.getReader().getWorking()){
            try{
                invoker.getPrinter().print(message);
                String name = invoker.getReader().nextLine().trim();
                nameCheck(name);
                return name;
            }
            catch (NotValidArgumentsException ex){
                invoker.getPrinter().print(ex.getMessage());
            }
        }
        return null;
    }
    public Long reqestCoordinate(String message){
        while(invoker.getReader().getWorking()){
            try{
                invoker.getPrinter().print(message);
                String stringCoordinate = invoker.getReader().nextLine().trim();
                nullCheck(stringCoordinate);
                return Long.parseLong(stringCoordinate);
            }
            catch (NotValidArgumentsException | NumberFormatException ex){
                invoker.getPrinter().print("Координата должна быть Long и не null");
            }
        }
        return null;
    }
    public Double reqestDistance(){
        while(invoker.getReader().getWorking()){
            try{
                invoker.getPrinter().print("Введите дистанцию маршрута в формате Double больше 1: ");
                String stringDistance = invoker.getReader().nextLine().trim().replace(",",".");
                nullCheck(stringDistance);
                if (Double.parseDouble(stringDistance)>1){
                    return Double.parseDouble(stringDistance);
                }
            }
            catch (NotValidArgumentsException | NumberFormatException ex){
                invoker.getPrinter().print("distance должен быть double и не null");
            }
        }
        return null;
    }
    public Coordinates reqestCoordinates(String message) throws NotValidArgumentsException {
        long x;
        Long y;
        invoker.getPrinter().print(message);
        do {
            x = reqestCoordinate("Введите координату x в формате long." +
                    " Значение поля должно быть больше -866: ");
        } while (invoker.getReader().getWorking() && x <= -866);
        do {
            y = reqestCoordinate("Введите координату y в формате Long." +
                    " Максимальное значение поля 107, Поле не может быть null: ");
        } while (invoker.getReader().getWorking() && y > 107);
        return new Coordinates(x, y);
    }
    public Location reqestLocation(String message) throws NotValidArgumentsException {
        invoker.getPrinter().print(message);
        long x = reqestCoordinate("Введите координату x в формате long: ");
        long y = reqestCoordinate("Введите координату y в формате long: ");
        Long z = reqestCoordinate("Введите координату z в формате Long." +
                " Поле не может быть null: ");
        String name = requestName("Введите название Локации." +
                " Название Локации не может быть null: ");
        return new Location(x, y, z, name);
    }
    public boolean setRoute(Route route){
        try{
            route.setId(generateId());
            route.setName(requestName("Введите название маршрута. " +
                    "Название не может быть пустым или null: "));
            route.setCoordinates(reqestCoordinates("Введите координаты маршрута: "));
            route.setCreationDate(generateDate());
            route.setFrom(reqestLocation("Введите данные для локации " +
                    "из которой идет маршрут: "));
            route.setTo(reqestLocation("Введите данные для локации " +
                    "в которую идет маршрут: "));
            route.setDistance(reqestDistance());
            return true;
        }
        catch(Exception e){
            return false;
        }
    }
    public void getInfoAboutCollection() {
        invoker.getPrinter().print("Тип коллекции: " + models.getClass().getSimpleName());
        invoker.getPrinter().print("\nКоличество элементов в коллекции: " + models.size());
        invoker.getPrinter().print("\nДата инициализации: " + creationDate);
    }

    public void modelCheck(LinkedHashMap<Integer,Route> checkedCollection){
        Collection<Route> checkedRoutes = checkedCollection.values();
        for (Route route : checkedRoutes){
            try {
                nullCheck(route);
                Coordinates coordinates = route.getCoordinates();
                Location from = route.getFrom();
                Location to = route.getTo();
                nullCheck(route.getId(), coordinates, to, from, route.getDistance(),
                        route.getCreationDate(), coordinates.getX(), coordinates.getY(), to.getX(),
                        to.getY(), to.getZ(), from.getX(), from.getY(), from.getZ());
                nameCheck(route.getName(), from.getName(), to.getName());
                if (!checkedCollection.get(route.getId()).equals(route)){
                    throw new NotValidArgumentsException();
                }
                if (coordinates.getX()<=-866){
                    throw new NotValidArgumentsException();
                }
                if (coordinates.getY()>107){
                    throw new NotValidArgumentsException();
                }
                putModel(route);
                usedIDs.add(route.getId());
            }
            catch (NotValidArgumentsException exception){
                invoker.getPrinter().print("Один из объектов Route имеет не валидные значения," +
                        " он не будет добавлен в коллекцию");
            }
        }
    }

    public ArrayList<Integer> getUsedIDs(){
        return usedIDs;
    }
    public LinkedHashMap<Integer, Route> getCollection(){
        return models;
    }
    public void nameCheck(String ... names) throws NotValidArgumentsException {
        for(String name : names){
            if(name == null || name.equals("")){
            throw new NotValidArgumentsException("Аргумент" +
                    " не может быть null");}
        }
    }
    public void nullCheck(Object ... objects) throws NotValidArgumentsException {
        for(Object object : objects){if(object == null){
            throw new NotValidArgumentsException("Аргумент" +
                    " не может быть null");}
        }
    }

}
