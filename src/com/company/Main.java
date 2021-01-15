package com.company;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private static String[] sequencias = {"\\*<]:-DOo", ">:o\\)", "[^\\*]<]:-D"};
    private static String[] sequencias2 = {"*<]:-DOo", ">:o)", "<]:-D"};
    private static int[] objetos = new int[3];
    private static String[] nombres = {"Pare Noel", "Ren", "Follets"};
    private static File file = new File(System.getProperty("user.home") + "/Escritorio/santako");

    public static void main(String[] args) throws IOException {
        sinRegularExpression();
    }

    private static void regularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

        Pattern pattern;
        Matcher matcher;

        String linea = bufferedReader.readLine();
        while (linea != null) {
            int x = 0;
            resetArray();
            for (String sequencia : sequencias) {
                pattern = Pattern.compile(sequencia);
                matcher = pattern.matcher(linea);
                while (matcher.find()) {
                    objetos[x]++;
                }
                x++;
            }
            mostrarResultados(objetos);
            linea = bufferedReader.readLine();
        }
        bufferedReader.close();
    }

    private static void sinRegularExpression() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String linea = bufferedReader.readLine();
        while (linea != null) {
            resetArray();
            int x = 0;
            for (String sequencia : sequencias2) {
                for (int i = 0; i < linea.length(); i++) {
                    if (i < linea.length() - 1) {
                        for (int j = 0; j < sequencia.length(); j++) {
                            if (linea.charAt(i + j) != sequencia.charAt(j)) {
                                break;
                            }
                            if (j == sequencia.length() - 1) {
                                objetos[x]++;
                            }
                        }
                    }
                }
                x++;
            }
            objetos[2] -= objetos[0];
            mostrarResultados(objetos);
            linea = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    private static void resetArray() {
        for (int i = 0; i < objetos.length; i++) {
            objetos[i] = 0;
        }
    }

    private static void mostrarResultados(int[] objetos) {
        for (int i = 0; i < objetos.length; i++) {
            if (objetos[i] > 0) {
                System.out.print(nombres[i] + "(" + objetos[i] + ") ");
            }
        }
    }
}
