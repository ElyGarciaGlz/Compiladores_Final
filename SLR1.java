import java.io.*;

public class SLR1 {
    private static String token;
    private static String lexema;
    private static String renglon;
    private static int filePointer = 0;
    private static final String[] pila = new String[75];
    private static final String[] t = new String[25];
    private static final String[] nt = new String[15];
    private static final int[][] M = new int[57][40];
    private static String estado;
    private static String sPDel;
    private static int tope = -1;
    private static final String[] pi = new String[30];
    private static final int[] lpd = new int[30];
    private static String entradaFile, salida;

    private static int labelCount = 0;
    private static String temp, Var_izq, DecV="";
    private static int vars;
    private static String varsTemp = "";
    private static String L1, L2;
    private static String L3, L4;

    private static String PosA = "";
    private static String PosB = "";
    private static String X = "";

    private static final String[] PROG_c = new String[100];
    private static int topePROG_c = -1;
    private static final String[] PROGP_c = new String[100];
    private static int topePROGP_c = -1;
    private static final String[] PPAL_c = new String[100];
    private static int topePPAL_c = -1;
    private static final String[] BLOQ_c = new String[100];
    private static int topeBLOQ_c = -1;
    private static final String[] INS_c = new String[100];
    private static int topeINS_c = -1;
    private static final String[] IF_c = new String[100];
    private static int topeIF_c = -1;
    private static final String[] ASIG_c = new String[100];
    private static int topeASIG_c = -1;
    private static final String[] REP_c = new String[100];
    private static int topeREP_c = -1;
    private static final String[] EXPL_c = new String[100];
    private static int topeEXPL_c = -1;
    private static final String[] A_c = new String[100];
    private static int topeA_c = -1;
    private static final String[] A_v = new String[100];
    private static int topeA_v = -1;
    private static final String[] B_c = new String[100];
    private static int topeB_c = -1;
    private static final String[] B_v = new String[100];
    private static int topeB_v = -1;
    private static final String[] C_c = new String[100];
    private static int topeC_c = -1;
    private static final String[] C_v = new String[100];
    private static int topeC_v = -1;
    private static final String[] OP_c = new String[100];
    private static int topeOP_c = -1;
    private static final String[] OP_v = new String[100];
    private static int topeOP_v = -1;

    public static void llenarTablas() {
        t[0] = "variables";
        t[1] = "fin_variables";
        t[2] = "id";
        t[3] = "comienza";
        t[4] = "termina";
        t[5] = "si";
        t[6] = "(";
        t[7] = ")";
        t[8] = "entonces";
        t[9] = "finsi";
        t[10] = "asig";
        t[11] = "repite";
        t[12] = "hasta";
        t[13] = "+";
        t[14] = "-";
        t[15] = "*";
        t[16] = "/";
        t[17] = "num";
        t[18] = "may";
        t[19] = "men";
        t[20] = "mai";
        t[21] = "mei";
        t[22] = "ig";
        t[23] = "dif";
        t[24] = "fin";

        nt[0] = "PROGP";
        nt[1] = "PROG";
        nt[2] = "VARS";
        nt[3] = "DEC";
        nt[4] = "PPAL";
        nt[5] = "BLQ";
        nt[6] = "INS";
        nt[7] = "IF";
        nt[8] = "ASIG";
        nt[9] = "REP";
        nt[10] = "A";
        nt[11] = "B";
        nt[12] = "C";
        nt[13] = "EXPL";
        nt[14] = "OP";

        pi[0] = "PROGP";
        pi[1] = "PROG";
        pi[2] = "VARS";
        pi[3] = "DEC";
        pi[4] = "DEC";
        pi[5] = "PPAL";
        pi[6] = "BLQ";
        pi[7] = "BLQ";
        pi[8] = "INS";
        pi[9] = "INS";
        pi[10] = "INS";
        pi[11] = "IF";
        pi[12] = "ASIG";
        pi[13] = "REP";
        pi[14] = "A";
        pi[15] = "A";
        pi[16] = "A";
        pi[17] = "B";
        pi[18] = "B";
        pi[19] = "B";
        pi[20] = "C";
        pi[21] = "C";
        pi[22] = "C";
        pi[23] = "EXPL";
        pi[24] = "OP";
        pi[25] = "OP";
        pi[26] = "OP";
        pi[27] = "OP";
        pi[28] = "OP";
        pi[29] = "OP";

        lpd[0] = 1;
        lpd[1] = 2;
        lpd[2] = 3;
        lpd[3] = 2;
        lpd[4] = 1;
        lpd[5] = 3;
        lpd[6] = 1;
        lpd[7] = 2;
        lpd[8] = 1;
        lpd[9] = 1;
        lpd[10] = 1;
        lpd[11] = 7;
        lpd[12] = 3;
        lpd[13] = 6;
        lpd[14] = 3;
        lpd[15] = 3;
        lpd[16] = 1;
        lpd[17] = 3;
        lpd[18] = 3;
        lpd[19] = 1;
        lpd[20] = 1;
        lpd[21] = 1;
        lpd[22] = 3;
        lpd[23] = 3;
        lpd[24] = 1;
        lpd[25] = 1;
        lpd[26] = 1;
        lpd[27] = 1;
        lpd[28] = 1;
        lpd[29] = 1;

        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                M[i][j] = 0;
            }
        }

        M[0][0] = 3;
        M[2][3] = 5;
        M[3][2] = 7;
        M[5][5] = 13;
        M[5][2] = 14;
        M[5][11] = 15;
        M[6][1] = 16;
        M[7][2] = 7;
        M[8][4] = 18;
        M[9][5] = 13;
        M[9][2] = 14;
        M[9][11] = 15;
        M[13][6] = 20;
        M[14][10] = 21;
        M[15][5] = 13;
        M[15][2] = 14;
        M[15][11] = 15;
        M[20][17] = 27;
        M[20][2] = 28;
        M[20][6] = 29;
        M[21][17] = 27;
        M[21][2] = 28;
        M[21][6] = 29;
        M[22][12] = 31;
        M[23][7] = 32;
        M[24][18] = 34;
        M[24][19] = 35;
        M[24][20] = 36;
        M[24][21] = 37;
        M[24][22] = 38;
        M[24][23] = 39;
        M[24][13] = 40;
        M[24][14] = 41;
        M[25][15] = 42;
        M[25][16] = 43;
        M[29][17] = 27;
        M[29][2] = 28;
        M[29][6] = 29;
        M[30][13] = 40;
        M[30][14] = 41;
        M[31][6] = 45;
        M[32][8] = 46;
        M[33][17] = 27;
        M[33][2] = 28;
        M[33][6] = 29;
        M[40][17] = 27;
        M[40][2] = 28;
        M[40][6] = 29;
        M[41][17] = 27;
        M[41][2] = 28;
        M[41][6] = 29;
        M[42][17] = 27;
        M[42][2] = 28;
        M[42][6] = 29;
        M[43][17] = 27;
        M[43][2] = 28;
        M[43][6] = 29;
        M[44][7] = 52;
        M[44][13] = 40;
        M[44][14] = 41;
        M[45][17] = 27;
        M[45][2] = 28;
        M[45][6] = 29;
        M[46][5] = 13;
        M[46][2] = 14;
        M[46][11] = 15;
        M[47][13] = 40;
        M[47][14] = 41;
        M[48][15] = 42;
        M[48][16] = 43;
        M[49][15] = 42;
        M[49][16] = 43;
        M[53][7] = 55;
        M[54][9] = 56;
        M[0][26] = 1;
        M[0][27] = 2;
        M[2][29] = 4;
        M[3][28] = 6;
        M[5][30] = 8;
        M[5][31] = 9;
        M[5][32] = 10;
        M[5][33] = 11;
        M[5][34] = 12;
        M[7][28] = 17;
        M[9][30] = 19;
        M[9][31] = 9;
        M[9][32] = 10;
        M[9][33] = 11;
        M[9][34] = 12;
        M[15][30] = 22;
        M[15][31] = 9;
        M[15][32] = 10;
        M[15][33] = 11;
        M[15][34] = 12;
        M[20][38] = 23;
        M[20][35] = 24;
        M[20][36] = 25;
        M[20][37] = 26;
        M[21][35] = 30;
        M[21][36] = 25;
        M[21][37] = 26;
        M[24][39] = 33;
        M[29][35] = 44;
        M[29][36] = 25;
        M[29][37] = 26;
        M[33][35] = 47;
        M[33][36] = 25;
        M[33][37] = 26;
        M[40][36] = 48;
        M[40][37] = 26;
        M[41][36] = 49;
        M[41][37] = 26;
        M[42][37] = 50;
        M[43][37] = 51;
        M[45][38] = 53;
        M[45][35] = 24;
        M[45][36] = 25;
        M[45][37] = 26;
        M[46][30] = 54;
        M[46][31] = 9;
        M[46][32] = 10;
        M[46][33] = 11;
        M[46][34] = 12;
        M[1][24] = 666;
        M[4][24] = -1;
        M[7][1] = -4;
        M[9][4] = -6;
        M[9][9] = -6;
        M[9][12] = -6;
        M[10][5] = -8;
        M[10][2] = -8;
        M[10][11] = -8;
        M[10][4] = -8;
        M[10][9] = -8;
        M[10][12] = -8;
        M[11][5] = -9;
        M[11][2] = -9;
        M[11][11] = -9;
        M[11][4] = -9;
        M[11][9] = -9;
        M[11][12] = -9;
        M[12][5] = -10;
        M[12][2] = -10;
        M[12][11] = -10;
        M[12][4] = -10;
        M[12][9] = -10;
        M[12][12] = -10;
        M[16][3] = -2;
        M[17][1] = -3;
        M[18][24] = -5;
        M[19][4] = -7;
        M[19][9] = -7;
        M[19][12] = -7;
        M[25][13] = -16;
        M[25][14] = -16;
        M[25][7] = -16;
        M[25][18] = -16;
        M[25][19] = -16;
        M[25][20] = -16;
        M[25][21] = -16;
        M[25][22] = -16;
        M[25][23] = -16;
        M[25][5] = -16;
        M[25][2] = -16;
        M[25][11] = -16;
        M[25][4] = -16;
        M[25][9] = -16;
        M[25][12] = -16;
        M[26][15] = -19;
        M[26][16] = -19;
        M[26][13] = -19;
        M[26][14] = -19;
        M[26][7] = -19;
        M[26][18] = -19;
        M[26][19] = -19;
        M[26][20] = -19;
        M[26][21] = -19;
        M[26][22] = -19;
        M[26][23] = -19;
        M[26][5] = -19;
        M[26][2] = -19;
        M[26][11] = -19;
        M[26][4] = -19;
        M[26][9] = -19;
        M[26][12] = -19;
        M[27][15] = -20;
        M[27][16] = -20;
        M[27][13] = -20;
        M[27][14] = -20;
        M[27][7] = -20;
        M[27][18] = -20;
        M[27][19] = -20;
        M[27][20] = -20;
        M[27][21] = -20;
        M[27][22] = -20;
        M[27][23] = -20;
        M[27][5] = -20;
        M[27][2] = -20;
        M[27][11] = -20;
        M[27][4] = -20;
        M[27][9] = -20;
        M[27][12] = -20;
        M[28][15] = -21;
        M[28][16] = -21;
        M[28][13] = -21;
        M[28][14] = -21;
        M[28][7] = -21;
        M[28][18] = -21;
        M[28][19] = -21;
        M[28][20] = -21;
        M[28][21] = -21;
        M[28][22] = -21;
        M[28][23] = -21;
        M[28][5] = -21;
        M[28][2] = -21;
        M[28][11] = -21;
        M[28][4] = -21;
        M[28][9] = -21;
        M[28][12] = -21;
        M[30][5] = -12;
        M[30][2] = -12;
        M[30][11] = -12;
        M[30][4] = -12;
        M[30][9] = -12;
        M[30][12] = -12;
        M[34][17] = -24;
        M[34][2] = -24;
        M[34][6] = -24;
        M[35][17] = -25;
        M[35][2] = -25;
        M[35][6] = -25;
        M[36][17] = -26;
        M[36][2] = -26;
        M[36][6] = -26;
        M[37][17] = -27;
        M[37][2] = -27;
        M[37][6] = -27;
        M[38][17] = -28;
        M[38][2] = -28;
        M[38][6] = -28;
        M[39][17] = -29;
        M[39][2] = -29;
        M[39][6] = -29;
        M[47][7] = -23;
        M[48][13] = -14;
        M[48][14] = -14;
        M[48][7] = -14;
        M[48][18] = -14;
        M[48][19] = -14;
        M[48][20] = -14;
        M[48][21] = -14;
        M[48][22] = -14;
        M[48][23] = -14;
        M[48][5] = -14;
        M[48][2] = -14;
        M[48][11] = -14;
        M[48][4] = -14;
        M[48][9] = -14;
        M[48][12] = -14;
        M[49][13] = -15;
        M[49][14] = -15;
        M[49][7] = -15;
        M[49][18] = -15;
        M[49][19] = -15;
        M[49][20] = -15;
        M[49][21] = -15;
        M[49][22] = -15;
        M[49][23] = -15;
        M[49][5] = -15;
        M[49][2] = -15;
        M[49][11] = -15;
        M[49][4] = -15;
        M[49][9] = -15;
        M[49][12] = -15;
        M[50][15] = -17;
        M[50][16] = -17;
        M[50][13] = -17;
        M[50][14] = -17;
        M[50][7] = -17;
        M[50][18] = -17;
        M[50][19] = -17;
        M[50][20] = -17;
        M[50][21] = -17;
        M[50][22] = -17;
        M[50][23] = -17;
        M[50][5] = -17;
        M[50][2] = -17;
        M[50][11] = -17;
        M[50][4] = -17;
        M[50][9] = -17;
        M[50][12] = -17;
        M[51][15] = -18;
        M[51][16] = -18;
        M[51][13] = -18;
        M[51][14] = -18;
        M[51][7] = -18;
        M[51][18] = -18;
        M[51][19] = -18;
        M[51][20] = -18;
        M[51][21] = -18;
        M[51][22] = -18;
        M[51][23] = -18;
        M[51][5] = -18;
        M[51][2] = -18;
        M[51][11] = -18;
        M[51][4] = -18;
        M[51][9] = -18;
        M[51][12] = -18;
        M[52][15] = -22;
        M[52][16] = -22;
        M[52][13] = -22;
        M[52][14] = -22;
        M[52][7] = -22;
        M[52][18] = -22;
        M[52][19] = -22;
        M[52][20] = -22;
        M[52][21] = -22;
        M[52][22] = -22;
        M[52][23] = -22;
        M[52][5] = -22;
        M[52][2] = -22;
        M[52][11] = -22;
        M[52][4] = -22;
        M[52][9] = -22;
        M[52][12] = -22;
        M[55][5] = -13;
        M[55][2] = -13;
        M[55][11] = -13;
        M[55][4] = -13;
        M[55][9] = -13;
        M[55][12] = -13;
        M[56][5] = -11;
        M[56][2] = -11;
        M[56][11] = -11;
        M[56][4] = -11;
        M[56][9] = -11;
        M[56][12] = -11;
    }

    public static void main(String[] args) {
        llenarTablas();

        entradaFile = args[0] + ".als";
        salida = args[0] + ".asm";

        if (!creaArchivo(entradaFile).exists()) {
            System.out.println("Error: el archivo con nombre " + entradaFile + " no existe");
            System.exit(4);
        }

        push("0");
        leeToken(creaArchivo(entradaFile));

        do {
            estado = pila[tope];
            int interseccion = M[Integer.parseInt(estado)][es_terminal(token)];
            if (interseccion == 666) {
                PROGP_c[0] = PROG_c[0];

                System.out.println("\nCodigo: ");
                System.out.println(PROGP_c[0]);
                creaEscribeArchivo(xArchivo(salida), PROGP_c[0]);
                System.exit(0);

            } else if (interseccion > 0) {
                
                GC_shift(interseccion);

                push(token);
                push(String.valueOf(interseccion));

                leeToken(creaArchivo(entradaFile));
            } else if (interseccion < 0) {
                int deleted = lpd[interseccion * -1] * 2;
                GC_reduce(interseccion * -1);

                for (int i = 0; i < deleted; i++) {
                    pop();
                }

                sPDel = pila[tope];

                String nuevo = pi[interseccion * -1];
                push(nuevo);

                if (M[Integer.parseInt(sPDel)][es_noTerminal(nuevo)] == 0) {
                    error();
                } else {
                    push(String.valueOf(M[Integer.parseInt(sPDel)][es_noTerminal(nuevo)]));
                }

            } else
                error();
        } while (true);

    }

    private static String GenVar() {
        return "T" + (vars++);
    }

    private static String GenEtq() {
        return "E" + (labelCount++);
    }

    private static void leeToken(File archivo) {
        try (BufferedReader locFile = new BufferedReader(new FileReader(archivo))) {
            locFile.skip(filePointer);

            token = locFile.readLine();
            filePointer += token.length() + 2;

            lexema = locFile.readLine();
            filePointer += lexema.length() + 2;

            renglon = locFile.readLine();
            filePointer += renglon.length() + 2;
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    private static void push(String x) {
        if (tope >= 9999) {
            System.out.println("Error: pila llena");
            System.exit(4);
        }
        pila[++tope] = x;
    }

    private static String pop() {
        if (tope < 0) {
            System.out.println("Error: pila vacía");
            System.exit(4);
        }
        return pila[tope--];
    }

    private static int es_terminal(String x) {
        for (int i = 0; i < t.length; i++) {
            if (t[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }

    private static int es_noTerminal(String x) {
        for (int i = 0; i < nt.length; i++) {
            if (nt[i].equals(x)) {
                return i + 25;
            }
        }
        return -1;
    }

    private static void GC_shift(int interseccion) {
        switch (interseccion) {
            case 7:
                DecV = DecV + "\n\tDOBLEP\t" + lexema;
                System.out.println("DecV: "+DecV);
                pausa();
                break;
            case 14:
                Var_izq = lexema;
                break;
            case 27:
                temp = lexema + "e";
                break;
            case 28:
                temp = lexema;
                break;
        }
    }

    private static void GC_reduce(int reduce) {
        switch (reduce) {
            case 1:
                varsTemp = DecVarsTemp();
                PROG_c[++topePROG_c] = varsTemp + DecV + PPAL_c[topePPAL_c--] + "\nVUEL 0 \nFIN";
                break;
            case 5:
                PPAL_c[++topePPAL_c] = BLOQ_c[topeBLOQ_c--];
                break;
            case 6:
                BLOQ_c[++topeBLOQ_c] = INS_c[topeINS_c--];
                break;
            case 7:
                L1 = INS_c[topeINS_c--] + BLOQ_c[topeBLOQ_c--];
                BLOQ_c[++topeBLOQ_c] = L1;
                break;
            case 8:
                INS_c[++topeINS_c] = IF_c[topeIF_c--];
                break;
            case 9:
                INS_c[++topeINS_c] = ASIG_c[topeASIG_c--];
                break;
            case 10:
                INS_c[++topeINS_c] = REP_c[topeREP_c--];
                break;
            case 11:
                PosA = GenEtq();
                PosB = GenEtq();
                IF_c[++topeIF_c] = EXPL_c[topeEXPL_c--] + PosA + "\n\tSAL\t" + PosB + "\n(" + PosA + ")" + "\n\tMUE\tRC, RC\n"
                        + BLOQ_c[topeBLOQ_c--] + "\n(" + PosB + ")" + "\n\tMUE\tRC, RC\n";
                break;
            case 12:
                ASIG_c[++topeASIG_c] = A_c[topeA_c--] + "\n\tMUE\t" + A_v[topeA_v--] + ", " + Var_izq;
                break;
            case 13:
                PosA = GenEtq();
                PosB = GenEtq();
                REP_c[++topeREP_c] = "\n\n(" + PosA + ")" + "\tMUE\tRC, RC\n" + BLOQ_c[topeBLOQ_c--] + EXPL_c[topeEXPL_c--]
                        + PosB + "\n\tSAL\t" + PosA + "\n(" + PosB + ")" + "\n\tMUE\tRC, RC\n";
                break;
            case 14:
                X = GenVar();
                L1 = A_c[topeA_c--] + B_c[topeB_c--] + "\n\tMUE\t" + A_v[topeA_v--] + ", RA" + "\n\tSUME\t" + B_v[topeB_v--]
                        + "\n\tMUE\tRA, " + X;
                A_c[++topeA_c] = L1;
                A_v[++topeA_v] = X;
                break;
            case 15:
                X = GenVar();
                L1 = A_c[topeA_c--] + B_c[topeB_c--] + "\n\tMUE\t" + A_v[topeA_v--] + ", RA" + "\n\tSUBE\t" + B_v[topeB_v--]
                        + "\n\tMUE\tRA, " + X;
                A_c[++topeA_c] = L1;
                A_v[++topeA_v] = X;
                break;
            case 16:
                A_c[++topeA_c] = B_c[topeB_c--];
                A_v[++topeA_v] = B_v[topeB_v--];
                break;
            case 17:
                X = GenVar();
                L1 = B_c[topeB_c--] + C_c[topeC_c--] + "\n\tMUE " + B_v[topeB_v--] + ", RA" + "\n\tMULE " + C_v[topeC_v--]
                        + "\n\tMUE RA, " + X;
                B_c[++topeB_c] = L1;
                B_v[++topeB_v] = X;
                break;
            case 18:
                X = GenVar();
                L1 = B_c[topeB_c--] + C_c[topeC_c--] + "\n\tMUE " + B_v[topeB_v--] + ", RA" + "\n\tDIVE " + C_v[topeC_v--]
                        + "\n\tMUE RA, " + X;
                B_c[++topeB_c] = L1;
                B_v[++topeB_v] = X;
                break;
            case 19:
                B_c[++topeB_c] = C_c[topeC_c--];
                B_v[++topeB_v] = C_v[topeC_v--];
                break;
            case 20:
                C_c[++topeC_c] = "";
                C_v[++topeC_v] = temp;
                break;
            case 21:
                C_c[++topeC_c] = "";
                C_v[++topeC_v] = temp;
                break;
            case 22:
                C_c[++topeC_c] = A_c[topeA_c--];
                C_v[++topeC_v] = A_v[topeA_v--];
                break;
            case 23:
                L1 = A_c[topeA_c--];
                L2 = A_c[topeA_c--];
                L3 = A_v[topeA_v--];
                L4 = A_v[topeA_v--];
                EXPL_c[++topeEXPL_c] = L1 + L2 + "\n\tMUE\t" + L3 + ", RA" + "\n\tMUE\t" +  L4 + ", RB" + "\n\tCMPE\tRA, RB" + OP_c[topeOP_c--];  
                break;
            case 24:
                OP_c[++topeOP_c] = "\n\tSMAY\t";
                break;
            case 25:
                OP_c[++topeOP_c] = "\n\tSMEN\t";
                break;
            case 26:
                OP_c[++topeOP_c] = "\n\tSMAI\t";
                break;
            case 27:
                OP_c[++topeOP_c] = "\n\tSMEI\t";
                break;
            case 28:
                OP_c[++topeOP_c] = "\n\tSIG\t";
                break;
            case 29:
                OP_c[++topeOP_c] = "\n\tSDIF\t";
                break;

            default:
                break;
        }
    }

    private static String DecVarsTemp() {
        String varsTemp = "";
        for (int i = 0; i < vars; i++) {
            varsTemp = varsTemp + "\n\tDOBLEP\tT" + i;
        }
        return varsTemp;
    }

    private static void mostrarPila() {
        System.out.print("[");
        for (String string : pila) {
            if (string != null) {
                System.out.print(string + " ");
            }
        }
        System.out.println("]");
    }

    private static void showStackTree(String[] pila, String nombre) {
        System.out.println("Pila " + nombre);
        System.out.print("[");
        for (String string : pila) {
            if (string != null) {
                System.out.print(string + ", ");
            }
        }
        System.out.print("]\n");
    }

    public static File creaArchivo(String archivo) {
        return new File(archivo);
    }

    private static boolean creaEscribeArchivo(File xFile, String mensaje) {
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

    private static void error() {
        System.out.println("Error sintáctico en renglón [" + renglon + "] en lexema:" + lexema);
        System.exit(4);
    }


    public static File xArchivo(String xName) {
        File xFile = new File(xName);
        return xFile;
    }
}
