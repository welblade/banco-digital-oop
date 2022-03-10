package bancodigital.ui;

import bancodigital.core.exception.BancoDigitalException;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class Comando {
    public abstract List<String> execute(List<String> args) throws BancoDigitalException;
    public abstract String help();
    public static List<String> separaArgumentos(String arg) {
        return Arrays.stream(arg.split("\\s+")).collect(Collectors.toList());
    }
}
