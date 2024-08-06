package Task1;

public class SignInWorker {

    // Метод для проверки логина и пароля
    public static boolean checkCredentials(String login, String password, String confirmPassword) {
        boolean conf = password.equals(confirmPassword);
        int llen = login.length();
        int plen = password.length();

        if (login == null || llen >= 20) { // Добавлена проверка на null для логина
            throw new WrongLoginException(llen);
        } else if (password == null || plen < 20 || !conf) { // Добавлена проверка на null для пароля
            throw new WrongPasswordException(plen, conf);
        } else {
            return true;
        }
    }
}
