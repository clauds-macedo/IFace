public class AccountException extends Exception {
    @Override
    public String getMessage() {
        return "O login não pode conter espaço em branco.";
    }

    public String accountAlreadyExists() {
        return "A conta já existe.";
    }

    public String accountDontExists() {
        return "A conta não existe no banco de dados.";
    }
}
