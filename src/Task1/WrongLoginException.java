package Task1;

public class WrongLoginException extends RuntimeException {
    private int currentLength;

    public WrongLoginException(int currentLength) {
        super();
        this.currentLength = currentLength;
    }

    @Override
    public String getMessage() {
        return String.format("Ваш логин имеет некорректную длину, ожидается < 20, текущая длина %d.",
                currentLength);
    }
}
