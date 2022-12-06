import java.io.*;
import java.util.ArrayList;

public class Analex {
    static int renglon = 0; // Almacena el renglon en el que se encuentra cada token
    static int fileSize = 0; // Almacena el tamaño del archivo
    static boolean finArchivo = false; // Indica si el archivo ya ha terminado de leerse (eof)
    static char[] linea; // Almacena linea a linea el archivo txt
    static int aA, aI; // Son los dos apuntadores aA es el de Adelante aI es el de atras o inicial
    static int nodoInicial; // Es el nodo o elemento inicial de cada changeDiagram
    static int estado; // Es un nodo intermedio o estado en el cual se encuentran los apuntadores
                       // dependiendo del token que se este leyendo
    static int c; // Es el caracter en el cual leemos
    static String entrada = "";
    static String salida = "";
    static String lexema = "";
    static String miToken = "";

    private static final ArrayList<String> palRes = new ArrayList<>();

    public static void main(String[] argumento) {
        palRes.add("variables");
        palRes.add("fin_variables");
        palRes.add("comienza");
        palRes.add("termina");
        palRes.add("si");
        palRes.add("entonces");
        palRes.add("finsi");
        palRes.add("repite");
        palRes.add("hasta");

        entrada = argumento[0] + ".prg";
        salida = argumento[0] + ".als";
        if (!xArchivo(entrada).exists()) {
            System.out.printf("\7ERROR: El archivo [%s] no existe", entrada);
        }
        linea = abreLeeCierra(entrada);
        aI = 0;
        aA = 0;

        do {
            aI = aA;
            estado = 0;
            nodoInicial = 0;
            lexema = "";
            miToken = getToken();
            if (!miToken.equals("nosirve")) {
                if (palRes.contains(lexema)) {
                    creaEscribeArchivo(xArchivo(salida), lexema);
                    creaEscribeArchivo(xArchivo(salida), lexema);
                    creaEscribeArchivo(xArchivo(salida), renglon + "");
                } else {
                    creaEscribeArchivo(xArchivo(salida), miToken);
                    creaEscribeArchivo(xArchivo(salida), lexema);
                    creaEscribeArchivo(xArchivo(salida), renglon + "");
                }
            }

        } while (!finArchivo);
        creaEscribeArchivo(xArchivo(salida), "fin");
        creaEscribeArchivo(xArchivo(salida), "fin");
        creaEscribeArchivo(xArchivo(salida), "666");
        System.out.println("Analisis Lexico terminado");
    }

    public static int changeDiagram() {
        aA = aI;
        switch (nodoInicial) {
            case 0:
                nodoInicial = 3;
                break;
            case 3:
                nodoInicial = 7;
                break;
            case 7:
                nodoInicial = 10;
                break;
            case 10:
                nodoInicial = 14;
                break;
            case 14:
                nodoInicial = 21;
                break;
            case 21:
                nodoInicial = 38;
                break;
            case 38:
                rut_error();
                break;
        }
        return nodoInicial;
    }

    public static String getToken() {
        do {
            switch (estado) {
                case 0:
                    c = readChar();
                    if (es_delim(c))
                        estado = 1;
                    else
                        estado = changeDiagram();
                    break;
                case 1:
                    c = readChar();
                    if (es_delim(c))
                        estado = 1;
                    else
                        estado = 2;
                    break;
                case 2:
                    aA--;
                    lexema = getLexema();
                    return "nosirve";
                case 3:
                    c = readChar();
                    if (isLetter(c))
                        estado = 4;
                    else
                        estado = changeDiagram();
                    break;
                case 4:
                    c = readChar();
                    if (isLetter(c) || isDigit(c)) {
                        estado = 4;
                    } else if (c == '_') {
                        estado = 5;
                    } else {
                        estado = 6;
                    }
                    break;
                case 5:
                    c = readChar();
                    if (isLetter(c) || isDigit(c))
                        estado = 4;
                    else
                        estado = changeDiagram();
                    break;
                case 6:
                    aA--;
                    lexema = getLexema();
                    System.out.println("Lexema: %s" + lexema);
                    return "id";
                case 7:
                    c = readChar();
                    if (isDigit(c))
                        estado = 8;
                    else
                        estado = changeDiagram();
                    break;
                case 8:
                    c = readChar();
                    if (isDigit(c))
                        estado = 8;
                    else
                        estado = 9;
                    break;
                case 9:
                    aA--;
                    lexema = getLexema();
                    return "num";
                case 10:
                    c = readChar();
                    if (c == '/')
                        estado = 11;
                    else
                        estado = changeDiagram();
                    break;
                case 11:
                    c = readChar();
                    if (c == '/')
                        estado = 12;
                    else
                        estado = changeDiagram();
                    break;
                case 12:
                    c = readChar();
                    if (c != 13 && c != 10 && c != 255)
                        estado = 12;
                    else
                        estado = 13;
                    break;
                case 13:
                    aA--;
                    lexema = getLexema();
                    return "nosirve";
                case 14:
                    c = readChar();
                    if (c == '+')
                        estado = 15;
                    else if (c == '-')
                        estado = 16;
                    else if (c == '*')
                        estado = 17;
                    else if (c == '/')
                        estado = 18;
                    else if (c == '(')
                        estado = 19;
                    else if (c == ')')
                        estado = 20;
                    else
                        estado = changeDiagram();
                    break;
                case 15:
                    lexema = getLexema();
                    return "+";
                case 16:
                    lexema = getLexema();
                    return "-";
                case 17:
                    lexema = getLexema();
                    return "*";
                case 18:
                    lexema = getLexema();
                    return "/";
                case 19:
                    lexema = getLexema();
                    return "(";
                case 20:
                    lexema = getLexema();
                    return ")";
                case 21:
                    c = readChar();
                    if (c == '=')
                        estado = 22;
                    else if (c == '<')
                        estado = 28;
                    else if (c == '>')
                        estado = 33;
                    else if (c == '!')
                        estado = 36;
                    else
                        estado = changeDiagram();
                    break;
                case 22:
                    c = readChar();
                    if (c == '/')
                        estado = 23;
                    else if (c == '>')
                        estado = 25;
                    else if (c == '<')
                        estado = 26;
                    else
                        estado = 27;
                    break;
                case 23:
                    c = readChar();
                    if (c == '=')
                        estado = 24;
                    else
                        estado = changeDiagram();
                    break;
                case 24:
                    lexema = getLexema();
                    return "dif";
                case 25:
                    lexema = getLexema();
                    return "mai";
                case 26:
                    lexema = getLexema();
                    return "mei";
                case 27:
                    aA--;
                    lexema = getLexema();
                    return "ig";
                case 28:
                    c = readChar();
                    if (c == '>')
                        estado = 29;
                    else if (c == '=')
                        estado = 30;
                    else if (c == '<')
                        estado = 31;
                    else
                        estado = 32;
                    break;
                case 29:
                    lexema = getLexema();
                    return "dif";
                case 30:
                    lexema = getLexema();
                    return "mei";
                case 31:
                    lexema = getLexema();
                    return "asig";
                case 32:
                    aA--;
                    lexema = getLexema();
                    return "men";
                case 33:
                    c = readChar();
                    if (c == '=')
                        estado = 34;
                    else
                        estado = 35;
                    break;
                case 34:
                    lexema = getLexema();
                    return "mai";
                case 35:
                    aA--;
                    lexema = getLexema();
                    return "may";
                case 36:
                    c = readChar();
                    if (c == '=')
                        estado = 37;
                    else
                        estado = changeDiagram();
                    break;
                case 37:
                    lexema = getLexema();
                    return "dif";
                case 38:
                    c = readChar();
                    if (c == 255)
                        estado = 39;
                    else
                        estado = changeDiagram();
                    break;
                case 39:
                    lexema = getLexema();
                    return "nosirve";
            }
        } while (true);
    }

    public static void rut_error() {
        System.out
                .println("\n\nERROR lexemaicografico(" + renglon + "):  nodoInicialpilacion terminada, en el caracter["
                        + Character.toString(c) + "] !!!!\n");
        System.exit(4);
    }

    public static int readChar() {
        if (aA <= fileSize - 1) {
            if (linea[aA] == 10) {
                renglon++;
                // System.out.println(linea[aA-1]*1+" "+linea[aA]*1+" "+linea[aA+1]*1);
            }
            return (linea[aA++]);
        } else {
            finArchivo = true;
            return 255;
        }
    }

    public static boolean isLetter(int x) {
        if ((x >= 65 && x <= 90) || (x >= 97 && x <= 122)) {
            return (true);
        }
        return (false);
    }

    public static boolean isDigit(int x) {
        if ((x >= 48 && x <= 57))
            return (true);
        return (false);
    }

    public static String getLexema() {
        String x = "";
        for (int i = aI; i < aA; i++)
            x = x + linea[i];
        return (x);
    }

    public static boolean creaEscribeArchivo(File xFile, String mensaje) {
        try {
            PrintWriter fileOut = new PrintWriter(
                    new FileWriter(xFile, true));
            fileOut.println(mensaje);
            fileOut.close();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public static File xArchivo(String xName) {
        File xFile = new File(xName);
        return xFile;
    }

    public static boolean es_delim(int x) {
        if (x == 32 || x == 9 || x == 10 || x == 13)
            return (true);
        return (false);
    }

    public static String pausa() {
        BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
        String nada = null;
        try {
            nada = entrada.readLine();
            return (nada);
        } catch (Exception e) {
            System.err.println(e);
        }
        return ("");
    }

    public static char[] abreLeeCierra(String xName) {
        File xFile = new File(xName);
        char[] data;
        try {
            FileReader fin = new FileReader(xFile);
            fileSize = (int) xFile.length();
            data = new char[fileSize + 1];
            fin.read(data, 0, fileSize);
            data[fileSize] = ' ';
            fileSize++;
            fin.close();
            return (data);
        } catch (FileNotFoundException exc) {
        } catch (IOException exc) {
        }
        return null;
    }

}