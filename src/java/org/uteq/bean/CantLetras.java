/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.uteq.bean;

/**
 *
 * @Modifico René Gómez
 */
public abstract class CantLetras {

    private static final String[] DECIMAS = {"", "UNO", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
        "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE", "VEINTIUNO", "VEINTIDOS",
        "VEINTRES", "VEINTICUATRO", "VEINTECINCO", "VEINTESEIS", "VEINTISIETE",
        "VEINTIOCHO", "VEINTINUEVE", "TREINTA", "TREINTA Y UNO", "TREINTA Y DOS",
        "TREINTA Y TRES", "TREINTA Y CUATRO", "TREINTA Y CINCO", "TREINTA Y SEIS",
        "TREINTA Y SIETE", "TREINTA Y OCHO", "TREINTA Y NUEVE", "CUARENTA", "CUARENTA Y UNO",
        "CUARENTA Y DOS", "CUARENTA Y TRES", "CUARENTA Y CUATRO", "CUARENTA Y CINCO", "CUARENTA Y SEIS",
        "CUARENTA Y SIETE", "CUARENTA Y OCHO", "CUARENTA Y NUEVE", "CINCUENTA", "CINCUENTA Y UNO", "CINCUENTA Y DOS",
        "CINCUENTA Y TRES", "CINCUENTA Y CUATRO", "CINCUENTA Y CINCO", "CINCUENTA Y SEIS", "CINCUENTA Y SIETE",
        "CINCUENTA Y OCHO", "CINCUENTA Y NUEVE", "SESENTA", "SESENTA Y UNO", "SESENTA Y DOS", "SESENTA Y TRES",
        "SESENTA Y CUATRO", "SESENTA Y CINCO", "SESENTA Y SEIS", "SESENTA Y SIETE", "SESENTA Y OCHO", "SESENTA Y NUEVE",
        "SETENTA", "SETENTA Y UNO", "SETENTA Y DOS", "SETENTA Y TRES", "SETENTA Y CUATRO", "SETENTA Y CINCO", "SETENTA Y SEIS",
        "SETENTA Y SIETE", "SETENTA Y OCHO", "SETENTA Y NUEVE", "OCHENTA", "OCHENTA Y UNO", "OCHENTA Y DOS", "OCHENTA Y TRES",
        "OCHENTA Y CUATRO", "OCHENTA Y CINCO", "OCHENTA Y SEIS", "OCHENTA Y SIETE", "OCHENTA Y OCHO", "OCHENTA Y NUEVE", "NOVENTA",
        "NOVENTA Y UNO", "NOVENTA Y DOS", "NOVENTA Y TRES", "NOVENTA Y CUATRO", "NOVENTA Y CINCO", "NOVENTA Y SEIS",
        "NOVENTA Y SIETE", "NOVENTA Y OCHO", "NOVENTA Y NUEVE"};

    private static final String[] UNIDADES = {"", "UNO", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
        "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE"};

    private static final String[] UNIDADESF = {"", "UN", "DOS ", "TRES ",
        "CUATRO ", "CINCO ", "SEIS ", "SIETE ", "OCHO ", "NUEVE ", "DIEZ ",
        "ONCE ", "DOCE ", "TRECE ", "CATORCE ", "QUINCE ", "DIECISEIS",
        "DIECISIETE", "DIECIOCHO", "DIECINUEVE", "VEINTE"};

    private static final String[] DECENAS = {"VEINTI", "TREINTA ", "CUARENTA ",
        "CINCUENTA ", "SESENTA ", "SETENTA ", "OCHENTA ", "NOVENTA ",
        "CIEN "};

    private static final String[] CENTENAS = {"CIENTO ", "DOSCIENTOS ",
        "TRESCIENTOS ", "CUATROCIENTOS ", "QUINIENTOS ", "SEISCIENTOS ",
        "SETECIENTOS ", "OCHOCIENTOS ", "NOVECIENTOS "};

    private static boolean b;

    /**
     * Convierte un numero en representacion numerica a uno en representacion de
     * texto. El numero es valido si esta entre 0 y 999'999.999
     *
     * @param doubleNumber numero a convertir
     * @return Numero convertido a texto
     * @throws NumberFormatException Si el numero esta fuera del rango
     */
    public static String convertirNumerosLetra(int doubleNumber)
            throws NumberFormatException {
        b = false;
        StringBuilder converted = new StringBuilder();
        try {
            // Validamos que sea un numero legal
            if (doubleNumber < 0) {
                throw new NumberFormatException("El numero debe ser positivo");
            }

            String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#')
                    .split("#");

            // Descompone el ultimo trio de unidades
            int cientos = Integer.parseInt(String.valueOf(getDigitAt(
                    splitNumber[0], 2))
                    + String.valueOf(getDigitAt(splitNumber[0], 1))
                    + String.valueOf(getDigitAt(splitNumber[0], 0)));
            if (cientos >= 0) {
                converted.append(convertNumberF(String.valueOf(cientos)));
            }
            return converted.toString().trim();
        } catch (NumberFormatException ex) {
            ex.getMessage();
            return null;
        }
    }

    public static String convertirNumerosLetras(double doubleNumber)
            throws NumberFormatException {
        b = false;
        StringBuilder converted = new StringBuilder();
        try {

            // Validamos que sea un numero legal
            if (doubleNumber < 0) {
                throw new NumberFormatException("El numero debe ser positivo");
            }

            String splitNumber[] = String.valueOf(doubleNumber).replace('.', '#')
                    .split("#");

            // Descompone el ultimo trio de unidades
            int cientos = Integer.parseInt(String.valueOf(getDigitAt(
                    splitNumber[0], 2))
                    + String.valueOf(getDigitAt(splitNumber[0], 1))
                    + String.valueOf(getDigitAt(splitNumber[0], 0)));
            if (cientos >= 1) {
                converted.append(convertNumber(String.valueOf(cientos)));
            }

            // Descompone los centavos
            String valor = splitNumber[1];
            if (valor.length() == 2) {
                if (valor.substring(0, 1).equals("0")) {
                    converted.append("PUNTO CERO ").append(convertNumber(String.valueOf(valor)));
                } else {
                    converted.append("PUNTO ").append(convertNumber(String.valueOf(valor)));
                }
            } else if (valor.length() == 1 && valor.charAt(0) == '0') {
                converted.append("PUNTO ").append(convertNumber(String.valueOf(valor)));
            } else if (valor.length() == 1 && valor.charAt(0) != '0') {
                b = true;
                converted.append("PUNTO ").append(convertNumber(String.valueOf(valor)));
            }

            return converted.toString().trim();
        } catch (NumberFormatException ex) {
            ex.getMessage();
            return null;
        }
    }

    /**
     * Convierte los trios de numeros que componen las unidades, las decenas y
     * las centenas del numero.
     *
     * @param number Numero a convetir en digitos
     * @return Numero convertido en letras
     */
    private static String convertNumberF(String number) {

        if (number.length() > 3) {
            throw new NumberFormatException(
                    "La longitud maxima debe ser 2 digitos");

        }

        StringBuilder output = new StringBuilder();
        if (getDigitAt(number, 2) != 0) {
            output.append(CENTENAS[getDigitAt(number, 2) - 1]);
        }

        int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
                + String.valueOf(getDigitAt(number, 0)));

        if (b) {
            String decimal = k + "0";
            k = Integer.parseInt(decimal);
            number = decimal;
            UNIDADES[0] = "";
        }
        if (k == 0) {
            output.append("CERO");
        } else if (k <= 20) {
            output.append(UNIDADESF[k]);
        } else if (k== 21) {
            output.append("VEINTIÚN");
        } else if (k > 30 && getDigitAt(number, 0) != 0) {
            output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                    + UNIDADESF[getDigitAt(number, 0)]);
        } else {
            output.append(DECENAS[getDigitAt(number, 1) - 2]
                    + UNIDADESF[getDigitAt(number, 0)] + " ");
        }

        return output.toString();
    }

    private static String convertNumber(String number) {

        if (number.length() > 3) {
            throw new NumberFormatException(
                    "La longitud maxima debe ser 2 digitos");

        }

        StringBuilder output = new StringBuilder();
        if (getDigitAt(number, 2) != 0) {
            output.append(CENTENAS[getDigitAt(number, 2) - 1]);
        }

        int k = Integer.parseInt(String.valueOf(getDigitAt(number, 1))
                + String.valueOf(getDigitAt(number, 0)));

        if (b) {
            String decimal = k + "0";
            k = Integer.parseInt(decimal);
            number = decimal;
            UNIDADES[0] = "";
        }
        if (k == 0) {
            output.append("CERO");
        } else if (k <= 20) {
            output.append(UNIDADES[k]);
        } else if (k > 30 && getDigitAt(number, 0) != 0) {
            output.append(DECENAS[getDigitAt(number, 1) - 2] + "Y "
                    + UNIDADES[getDigitAt(number, 0)]);
        } else {
            output.append(DECENAS[getDigitAt(number, 1) - 2]
                    + UNIDADES[getDigitAt(number, 0)] + " ");
        }

        return output.toString();
    }

    /**
     * Retorna el digito numerico en la posicion indicada de derecha a izquierda
     *
     * @param origin Cadena en la cual se busca el digito
     * @param position Posicion de derecha a izquierda a retornar
     * @return Digito ubicado en la posicion indicada
     */
    private static int getDigitAt(String origin, int position) {
        if (origin.length() > position && position >= 0) {
            return origin.charAt(origin.length() - position - 1) - 48;
        }
        return 0;
    }
}
