package Task1;

public class Main {

    public static void main(String[] args) {
        // Примеры данных для проверки
        String[][] credentials = {
                {"ivan", "1i2v3a4n5i6v7a8n91011", "1i2v3a4n5i6v7a8n91011"}, // корректные данные
                {"1i2v3a4n5i6v7a8n91011", "password", "password"}, // некорректная длина логина
                {"ivan", "1i2v3a4n5i6v7a8n91011", "1i2v3a4n5"}, // подтверждение пароля не совпадает
                {"ivan", "1i2v3a4n5", "1i2v3a4n5"}, // некорректная длина пароля
                {"ivan", "1i2v3a4n5", "1i"} // некорректная длина пароля и подтверждение не совпадает
        };

        // Проверка каждой пары логина и пароля
        for (int i = 0; i < credentials.length; i++) {
            try {
                System.out.println(SignInWorker.checkCredentials(credentials[i][0], credentials[i][1], credentials[i][2]));
            } catch (WrongLoginException e) {
                System.out.println(e.getMessage()); // Вывод сообщения об ошибке логина
            } catch (WrongPasswordException e) {
                System.out.println(e.getMessage()); // Вывод сообщения об ошибке пароля
            }
        }
    }
}
