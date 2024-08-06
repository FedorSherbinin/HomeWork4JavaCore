package Task1;

public class WrongPasswordException extends RuntimeException {
    private int currentLength;
    private boolean matchConfirm;

    public WrongPasswordException(int currentLength, boolean matchConfirm) {
        super();
        this.currentLength = currentLength;
        this.matchConfirm = matchConfirm;
    }

    @Override
    public String getMessage() {
        boolean badlen = currentLength < 20; // Исправлено условие на < 20
        return String.format("Ваш пароль имеет %sдопустимую длину%s %d. Пароль %s совпадает с подтверждением.",
                ((badlen) ? "не" : ""),
                ((badlen) ? ", ожидается >= 20, текущая длина" : ","),
                currentLength,
                (matchConfirm) ? "" : "не ");
    }
}
