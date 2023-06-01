package core.model;

import core.Exceptions.NotValidArgumentsException;

/**
 * Шаблон класса Coordinates. Содержит геттеры и сеттеры для всех полей.
 * Содержит ограничения прописанные для каждого поля
 * @author grigoryvolkov
 */
public class Coordinates {
    private long x; //Значение поля должно быть больше -866
    private Long y; //Максимальное значение поля: 107, Поле не может быть null

    public Coordinates(long x, Long y) throws NotValidArgumentsException {
        setX(x);
        setY(y);
    }
    public void setX(long x){
        this.x=x;
    }
    public void setY(Long y) throws NotValidArgumentsException {
        if (y.equals(null)){
            throw new NotValidArgumentsException("Поле не может быть null");
        }
        this.y=y;
    }
    public long getX(){return x;}
    public Long getY(){return y;}

    @Override
    public String toString() {
        return "{" + x + ", " + y + '}';
    }
}