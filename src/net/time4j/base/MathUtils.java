/*
 * -----------------------------------------------------------------------
 * Copyright © 2012 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (MathUtils.java) is part of project Time4J.
 *
 * Time4J is free software: You can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Time4J is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Time4J. If not, see <http://www.gnu.org/licenses/>.
 * -----------------------------------------------------------------------
 */

package net.time4j.base;


/**
 * <p>Definiert diverse mathematische Routinen, die in kalendarischen
 * Berechnungen gebraucht werden. </p>
 *
 * @author  Meno Hochschild
 */
public final class MathUtils {

    //~ Konstruktoren -----------------------------------------------------

    private MathUtils() {
        // keine Instanzierung
    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Macht einen sicheren TypeCast auf ein int-Primitive. </p>
     *
     * @param   num     long-Primitive
     * @return  int
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static int safeCast(long num) {

        if (num < Integer.MIN_VALUE || num > Integer.MAX_VALUE) {
            throw new ArithmeticException("Out of range: " + num);
        } else {
            return (int) num;
        }

    }

    /**
     * <p>Addiert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Summe
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static int safeAdd(
        int op1,
        int op2
    ) {

        if (op2 == 0) {
            return op1;
        }

        long result = ((long) op1) + ((long) op2);

        if ((result < Integer.MIN_VALUE) || (result > Integer.MAX_VALUE)) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Integer overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        } else {
            return (int) result;
        }

    }

    /**
     * <p>Addiert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Summe
     * @throws  ArithmeticException bei &Uuml;berlauf des long-Bereichs
     */
    public static long safeAdd(
        long op1,
        long op2
    ) {

        if (op2 == 0L) {
            return op1;
        }

        if (
            (op2 > 0)
            ? (op1 > Long.MAX_VALUE - op2)
            : (op1 < Long.MIN_VALUE - op2)
        ) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Long overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        }

        return op1 + op2;

    }

    /**
     * <p>Subtrahiert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Differenz
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static int safeSubtract(
        int op1,
        int op2
    ) {

        if (op2 == 0) {
            return op1;
        }

        long result = ((long) op1) - ((long) op2);

        if ((result < Integer.MIN_VALUE) || (result > Integer.MAX_VALUE)) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Integer overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        } else {
            return (int) result;
        }

    }

    /**
     * <p>Subtrahiert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Differenz
     * @throws  ArithmeticException bei &Uuml;berlauf des long-Bereichs
     */
    public static long safeSubtract(
        long op1,
        long op2
    ) {

        if (op2 == 0L) {
            return op1;
        }

        if (
            (op2 > 0)
            ? (op1 < Long.MIN_VALUE + op2)
            : (op1 > Long.MAX_VALUE + op2)
        ) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Long overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        }

        return op1 - op2;

    }

    /**
     * <p>Multipliziert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Produkt
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static int safeMultiply(
        int op1,
        int op2
    ) {

        if (op2 == 1) {
            return op1;
        }

        long result = ((long) op1) * ((long) op2);

        if ((result < Integer.MIN_VALUE) || (result > Integer.MAX_VALUE)) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Integer overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        } else {
            return (int) result;
        }

    }

    /**
     * <p>Multipliziert die Zahlen mit &Uuml;berlaufkontrolle. </p>
     *
     * @param   op1     erster Operand
     * @param   op2     zweiter Operand
     * @return  Produkt
     * @throws  ArithmeticException bei &Uuml;berlauf des long-Bereichs
     */
    public static long safeMultiply(
        long op1,
        long op2
    ) {

        if (op2 == 1L) {
            return op1;
        }

        if (
            (op2 > 0)
            ? (op1 > Long.MAX_VALUE / op2) || (op1 < Long.MIN_VALUE / op2)
            : ((op2 < -1)
                ? (op1 > Long.MIN_VALUE / op2) || (op1 < Long.MAX_VALUE / op2)
                : (op2 == -1) && (op1 == Long.MIN_VALUE))
        ) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Long overflow: (");
            sb.append(op1);
            sb.append(',');
            sb.append(op2);
            sb.append(')');
            throw new ArithmeticException(sb.toString());
        }

        return op1 * op2;

    }

    /**
     * <p>Pr&uuml;ft auch Extremf&auml;lle beim Negieren. </p>
     *
     * @param   value   Wert, der zu negieren ist
     * @return  der Ausdruck {@code -value}
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static int safeNegate(int value) {

        if (value == Integer.MIN_VALUE) {
            throw new ArithmeticException("Not negatable: " + value);
        } else {
            return -value;
        }

    }

    /**
     * <p>Pr&uuml;ft auch Extremf&auml;lle beim Negieren. </p>
     *
     * @param   value   Wert, der zu negieren ist
     * @return  der Ausdruck {@code -value}
     * @throws  ArithmeticException bei &Uuml;berlauf des int-Bereichs
     */
    public static long safeNegate(long value) {

        if (value == Long.MIN_VALUE) {
            throw new ArithmeticException("Not negatable: " + value);
        } else {
            return -value;
        }

    }

    /**
     * <p>Liefert die gr&ouml;&szlig;te untere Schranke des Quotienten. </p>
     *
     * <p>Beispiele: </p>
     *
     * <ul>
     *  <li>{@code floorDivide(2, 2) == 1}</li>
     *  <li>{@code floorDivide(1, 2) == 0}</li>
     *  <li>{@code floorDivide(0, 2) == 0}</li>
     *  <li>{@code floorDivide(-1, 2) == -1}</li>
     *  <li>{@code floorDivide(-2, 2) == -1}</li>
     *  <li>{@code floorDivide(-3, 2) == -2}</li>
     * </ul>
     *
     * @param   value       Dividend
     * @param   divisor     Teiler
     * @return  Quotient als Divisionsergebnis
     */
    public static int floorDivide(
        int value,
        int divisor
    ) {

        if (value >= 0) {
            return (value / divisor);
        } else {
            return ((value + 1) / divisor) - 1;
        }

    }

    /**
     * <p>Siehe {@link #floorDivide(int, int)}. </p>
     *
     * @param   value       Dividend
     * @param   divisor     Teiler
     * @return  Quotient als Divisionsergebnis
     */
    public static long floorDivide(
        long value,
        int divisor
    ) {

        if (value >= 0) {
            return (value / divisor);
        } else {
            return ((value + 1) / divisor) - 1;
        }

    }

    /**
     * <p>Modulo-Operator, der den Divisionsrest auf Basis von
     * {@link #floorDivide(int, int)} berechnet. </p>
     *
     * <p>Beispiele: </p>
     *
     * <ul>
     *  <li>{@code floorModulo(2, 2) == 0}</li>
     *  <li>{@code floorModulo(1, 2) == 1}</li>
     *  <li>{@code floorModulo(0, 2) == 0}</li>
     *  <li>{@code floorModulo(-1, 2) == 1}</li>
     *  <li>{@code floorModulo(-2, 2) == 0}</li>
     *  <li>{@code floorModulo(-3, 2) == 1}</li>
     * </ul>
     *
     * @param   value       Dividend
     * @param   divisor     Teiler
     * @return  Divisionsrest (nie negativ bei positivem Teiler)
     */
    public static int floorModulo(
        int value,
        int divisor
    ) {

        return (value - divisor * (floorDivide(value, divisor)));

    }

    /**
     * <p>Siehe {@link #floorModulo(int, int)}. </p>
     *
     * @param   value       Dividend
     * @param   divisor     Teiler
     * @return  Divisionsrest (nie negativ bei positivem Teiler)
     */
    public static int floorModulo(
        long value,
        int divisor
    ) {

        long ret = (value - divisor * (floorDivide(value, divisor)));
        return (int) ret; // Type-Cast hier wegen modulo-Semantik sicher

    }

}
