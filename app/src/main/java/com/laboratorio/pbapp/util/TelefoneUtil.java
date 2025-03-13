import androidx.annotation.NonNull;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class TelefoneUtil {

    private static final String TELEFONE_REGEX = "^(\\(\\d{2}\\))\\d{4,5}-\\d{4}$";

    public static boolean isTelefoneValido(String telefone) {
        if (telefone == null || telefone.isEmpty()) return false;

        Pattern pattern = Pattern.compile(TELEFONE_REGEX);
        Matcher matcher = pattern.matcher(telefone);
        return matcher.matches();
    }

    public static String formatarTelefone(String telefone) {
        if (telefone == null) return null;

        // Remove caracteres que não sejam números
        telefone = telefone.replaceAll("[^\\d]", "");

        // Verifica se há um zero no início e remove
        if (telefone.startsWith("0")) {
            telefone = telefone.substring(1);
        }

        if (telefone.length() == 10) { // Telefone fixo
            return String.format("(%s)%s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 6),
                    telefone.substring(6, 10));
        } else if (telefone.length() == 11) { // Celular
            return String.format("(%s)%s-%s",
                    telefone.substring(0, 2),
                    telefone.substring(2, 7),
                    telefone.substring(7, 11));
        } else {
            return null;
        }
    }

}
