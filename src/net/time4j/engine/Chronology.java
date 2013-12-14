/*
 * -----------------------------------------------------------------------
 * Copyright © 2013 Meno Hochschild, <http://www.menodata.de/>
 * -----------------------------------------------------------------------
 * This file (Chronology.java) is part of project Time4J.
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

package net.time4j.engine;

import de.menodata.annotations4j.Nullable;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import net.time4j.base.TimeSource;


/**
 * <p>Repr&auml;sentiert ein System von chronologischen Elementen. </p>
 *
 * @param   <T> generischer Typ kompatibel zu {@link ChronoEntity}
 * @author  Meno Hochschild
 */
public class Chronology<T>
    implements ChronoMerger<T> {

    //~ Statische Felder/Initialisierungen --------------------------------

    private static final List<ChronoReference> CHRONOS =
        new CopyOnWriteArrayList<ChronoReference>();
    private static final ReferenceQueue<Chronology<?>> QUEUE =
        new ReferenceQueue<Chronology<?>>();

    //~ Instanzvariablen --------------------------------------------------

    private final Class<T> chronoType;
    private final ChronoMerger<T> merger;
    private final Map<ChronoElement<?>, ElementRule<T, ?>> ruleMap;
    private final List<ChronoExtension> extensions;

    //~ Konstruktoren -----------------------------------------------------

    /**
     * <p>Standard-Konstruktor. </p>
     *
     * <p>Implementierungshinweis: Subklassen sollten grunds&auml;tzlich
     * dem Singleton-Muster folgen und deshalb keinen &ouml;ffentlichen
     * Konstruktor bieten. </p>
     *
     * @param   chronoType      chronologischer Typ
     * @param   chronoMerger    erzeugt eine neue Instanz von T basierend
     *                          auf Informationen aus einer anderen Quelle
     * @param   ruleMap         registrierte Elemente und Regeln
     * @param   extensions      optionale Erweiterungen
     */
    Chronology(
        Class<T> chronoType,
        ChronoMerger<T> chronoMerger,
        Map<ChronoElement<?>, ElementRule<T, ?>> ruleMap,
        List<ChronoExtension> extensions
    ) {
        super();

        if (chronoType == null) {
            throw new NullPointerException("Missing chronological type.");
        } else if (chronoMerger == null) {
            throw new NullPointerException("Missing chronological merger.");
        }

        this.chronoType = chronoType;
        this.merger = chronoMerger;
        this.ruleMap = Collections.unmodifiableMap(ruleMap);
        this.extensions = Collections.unmodifiableList(extensions);

    }

    //~ Methoden ----------------------------------------------------------

    /**
     * <p>Liefert den chronologischen Typ. </p>
     *
     * @return  Typ des Zeitwertkontexts
     */
    public Class<T> getChronoType() {

        return this.chronoType;

    }

    /**
     * <p>Liefert den zu dieser Chronologie zugeh&ouml;rigen Satz
     * von registrierten chronologischen Elementen. </p>
     *
     * @return  unver&auml;nderliche Menge von Elementen ohne Duplikate
     */
    public Set<ChronoElement<?>> getRegisteredElements() {

        return this.ruleMap.keySet();

    }

    /**
     * <p>Ist das angegebene chronologische Element inklusive Regel
     * registriert? </p>
     *
     * @param   element     zu pr&uuml;fendes chronologisches Element
     * @return  {@code true} wenn registriert, sonst {@code false}
     */
    public boolean isRegistered(@Nullable ChronoElement<?> element) {

        if (element == null) {
            return false;
        }

        return this.ruleMap.containsKey(element);

    }

    /**
     * <p>Wird das angegebene chronologische Element unterst&uuml;tzt? </p>
     *
     * <p>Unterst&uuml;tzung ist gegeben, wenn das Element entweder registriert
     * ist oder eine zu dieser Chronologie passende Regel definiert. </p>
     *
     * @param   element     zu pr&uuml;fendes chronologisches Element
     * @return  {@code true} wenn unterst&uuml;tzt, sonst {@code false}
     */
    public boolean isSupported(@Nullable ChronoElement<?> element) {

        if (element == null) {
            return false;
        } else {
            return (
                this.isRegistered(element)
                || (this.getDerivedRule(element) != null)
                || (this.getEpochRule(element) != null)
            );
        }

    }

    @Nullable
    @Override
    public T createFrom(
        TimeSource<?> clock,
        AttributeQuery attributes
    ) {

        return this.merger.createFrom(clock, attributes);

    }

    @Nullable
    @Override
    public T createFrom(
        ChronoEntity<?> parsedValues,
        AttributeQuery attributes
    ) {

        return this.merger.createFrom(parsedValues, attributes);

    }

    /**
     * <p>Liefert die registrierten chronologischen Erweiterungen. </p>
     *
     * <p>Diese Methode wird vom Format-API aufgerufen, um zus&auml;tzlich
     * zu den registrierten Elementen auch alle Erweiterungselemente zu
     * sammeln, die f&uuml;r die Formatierung von Bedeutung sind. </p>
     *
     * @return  unver&auml;nderliche Liste von Erweiterungen
     */
    public List<ChronoExtension> getExtensions() {

        return this.extensions;

    }

    /**
     * <p>Zeitlich aufsteigend sortierte Auflistung der f&uuml;r eine
     * kalendarische Chronologie g&uuml;ltigen &Auml;ren. </p>
     *
     * <p>Alle ISO-Systeme liefern nur eine leere Liste. Ein gregorianischer
     * Kalender hingegen definiert die &Auml;ren {@code BC} und {@code AD}
     * bezogen auf Jesu Geburt. </p>
     *
     * @return  unver&auml;nderliche Liste von &Auml;ren (kann leer sein)
     */
    public List<CalendarEra> getEras() {

        return Collections.emptyList();

    }

    /**
     * <p>Liefert ein getyptes Singleton pro {@code ChronoEntity}-Klasse. </p>
     *
     * @param   <T> Typ der Zeitklasse (Chronologie-Typ)
     * @param   chronoType  chronologischer Typ
     * @return  Chronologie oder {@code null}, wenn nicht gefunden
     */
    @Nullable
    public static <T> Chronology<T> lookup(Class<T> chronoType) {

        try {
            // Initialisierung der Klasse anstoßen, wenn noch nicht erfolgt
            Class.forName(
                chronoType.getName(),
                true,
                chronoType.getClassLoader());
        } catch (ClassNotFoundException cnfe) {
            throw new IllegalStateException(cnfe);
        }

        Chronology<?> ret = null;
        boolean purged = false;

        for (ChronoReference cref : CHRONOS) {
            Chronology<?> chronology = cref.get();

            if (chronology == null) {
                purged = true;
            } else if (chronology.getChronoType() == chronoType) {
                ret = chronology;
                break;
            }
        }

        if (purged) {
            purgeQueue();
        }

        return cast(ret); // type-safe

    }

    /**
     * <p>Registriert die angegebene Chronologie. </p>
     *
     * <p>Die Registrierung ist zur Unterst&uuml;tzung der Methode
     * {@link #lookup(Class)} gedacht und wird einmalig nach Konstruktion
     * einer Chronologie aufgerufen. </p>
     *
     * @param   chronology  neue Chronologie-Instanz, die zu registrieren ist
     * @throws  IllegalStateException wenn bereits registriert
     */
    static void register(Chronology<?> chronology) {

        synchronized (CHRONOS) {
            Class<?> chronoType = chronology.getChronoType();

            for (ChronoReference cref : CHRONOS) {
                Chronology<?> test = cref.get();
                if (
                    (test != null)
                    && (test.getChronoType() == chronoType)
                ) {
                    throw new IllegalStateException(
                        chronoType.getName() + " is already installed.");
                }
            }

            CHRONOS.add(new ChronoReference(chronology, QUEUE));
        }

    }

    /**
     * <p>Bestimmt eine chronologische Regel zum angegebenen Element. </p>
     *
     * @param   <V> Elementwerttyp
     * @param   element     chronologisches Element
     * @return  Regelobjekt
     * @throws  RuleNotFoundException wenn das angegebene Element nicht
     *          in dieser Chronologie registriert ist und auch sonst
     *          keine Ersatzregel ermittelt werden kann
     */
    <V> ElementRule<T, V> getRule(ChronoElement<V> element) {

        return this.getRule(element, true);

    }

    /**
     * <p>Liefert das assoziierte Kalendersystem, wenn verf&uuml;gbar. </p>
     *
     * @return  Kalendersystem
     * @throws  ChronoException wenn kein Kalendersystem verf&uuml;gbar ist
     */
    CalendarSystem<T> getCalendarSystem() {

        throw new ChronoException("Calendar system is not available.");

    }

    /**
     * <p>Engt den Typ der Chronologie via TypeCast ein. </p>
     *
     * @param   <T> chronologischer Typ
     * @param   chronology  fragliche Chronologie
     * @return  getypte Chronologie
     */
    static <T extends ChronoEntity<T>> Chronology<T> narrow(Chronology<?> c) {

        return cast(c);

    }

    private <V> ElementRule<T, V> getRule(
        ChronoElement<V> element,
        boolean withEpochMechanism
    ) {

        if (element == null) {
            throw new NullPointerException("Missing chronological element.");
        }

        ElementRule<?, ?> rule = this.ruleMap.get(element);

        if (rule == null) {
            rule = this.getDerivedRule(element);

            if ((rule == null) && withEpochMechanism) {
                rule = this.getEpochRule(element);

                if (rule == null) {
                    throw new RuleNotFoundException(this, element);
                }
            }
        }

        return cast(rule); // type-safe

    }

    @Nullable
    private <V> ElementRule<?, V> getDerivedRule(ChronoElement<V> element) {

        if (element instanceof BasicElement) {
            BasicElement<V> e = (BasicElement<V>) element;
            return e.derive(narrow(this));
        } else {
            return null;
        }

    }

    @Nullable
    private <V> ElementRule<?, V> getEpochRule(ChronoElement<V> element) {

        ElementRule<?, V> ret = null;

        if (Calendrical.class.isAssignableFrom(this.chronoType)) {
            Chronology<?> foreign = null;
            boolean purged = false;

            for (ChronoReference cref : CHRONOS) {
                Chronology<?> c = cref.get();

                if (c == null) {
                    purged = true;
                } else if (
                    (c != this)
                    && c.isRegistered(element)
                    && Calendrical.class.isAssignableFrom(c.getChronoType())
                ) {
                    foreign = c;
                    break;
                }
            }

            if (purged) {
                purgeQueue();
            }

            if (foreign != null) {
                ret =
                    createRuleByEpoch(
                        element,
                        foreign.getChronoType(),
                        this.chronoType
                    );
            }
        }

        return ret;

    }

    // vom GC behandelte Referenzen wegräumen
    private static void purgeQueue() {

        ChronoReference cref;

        while ((cref = (ChronoReference) QUEUE.poll()) != null) {
            for (ChronoReference test : CHRONOS) {
                if (test.name.equals(cref.name)) {
                    CHRONOS.remove(test);
                    break;
                }
            }
        }

    }

    private static
    <S extends Calendrical<?, S>, T extends Calendrical<?, T>, V>
    ElementRule<?, V> createRuleByEpoch(
        ChronoElement<V> element,
        Class<?> fc, // foreign chronology
        Class<?> tc // this chronology
    ) {


        Class<S> fcc = cast(fc);
        Class<T> tcc = cast(tc);
        Chronology<S> engine = Chronology.lookup(fcc);
        ElementRule<S, V> rule = engine.getRule(element, false);
        return new TransformingRule<S, T, V>(rule, fcc, tcc);

    }

    @SuppressWarnings("unchecked")
    private static <T> T cast(Object obj) {

        return (T) obj;

    }

    //~ Innere Klassen ----------------------------------------------------

    /**
     * <p>Erzeugt eine neue Chronologie ohne Zeitachse und wird meist
     * beim Laden einer {@code ChronoEntity}-Klasse T in einem
     * <i>static initializer</i> benutzt. </p>
     *
     * @param   <T> Typ des Zeitwertkontexts
     * @author  Meno Hochschild
     */
    public static class Builder<T> {

        //~ Instanzvariablen ----------------------------------------------

        final Class<T> chronoType;
        final ChronoMerger<T> merger;
        final Map<ChronoElement<?>, ElementRule<T, ?>> ruleMap;
        final List<ChronoExtension> extensions;

        //~ Konstruktoren -------------------------------------------------

        /**
         * <p>Konstruiert eine neue Instanz. </p>
         *
         * @param   chronoType      chronologischer Typ
         * @param   chronoMerger    erzeugt eine neue Instanz von T aus einer
         *                          anderen Quelle
         */
        Builder(
            Class<T> chronoType,
            ChronoMerger<T> merger
        ) {
            super();

            if (chronoType == null) {
                throw new NullPointerException("Missing chronological type.");
            } else if (merger == null) {
                throw new NullPointerException("Missing chronological merger.");
            }

            this.chronoType = chronoType;
            this.merger = merger;
            this.ruleMap = new HashMap<ChronoElement<?>, ElementRule<T, ?>>();
            this.extensions = new ArrayList<ChronoExtension>();

        }

        //~ Methoden ------------------------------------------------------

        /**
         * <p>Erzeugt ein Hilfsobjekt zum Bauen eines chronologischen
         * Systems. </p>
         *
         * @param   <T> Typ des Zeitwertkontexts
         * @param   chronoType      chronologischer Typ
         * @param   merger          erzeugt eine neue Instanz von T aus einer
         *                          anderen Quelle
         * @return  neues {@code Builder}-Objekt
         * @throws  UnsupportedOperationException wenn T eine Subklasse
         *          von {@code TimePoint} darstellt
         */
        public static <T extends ChronoEntity<T>> Builder<T> setUp(
            Class<T> chronoType,
            ChronoMerger<T> merger
        ) {

            if (TimePoint.class.isAssignableFrom(chronoType)) {
                throw new UnsupportedOperationException(
                    "This builder cannot construct a chronology "
                    + "with a time axis, use TimeAxis.Builder instead.");
            }

            return new Builder<T>(chronoType, merger);

        }

        /**
         * <p>Registriert ein neues Element mitsamt der assoziierten Regel. </p>
         *
         * @param   <V> Elementwerttyp
         * @param   element     zu registrierendes chronologisches Element
         * @param   rule        mit dem Element zu installierende Regel
         * @return  diese Instanz f&uuml;r Methodenverkettungen
         * @throws  IllegalArgumentException wenn das Element schon
         *          registriert wurde (Duplikat)
         */
        public <V> Builder<T> appendElement(
            ChronoElement<V> element,
            ElementRule<T, V> rule
        ) {

            this.checkElementDuplicates(element);
            this.ruleMap.put(element, rule);
            return this;

        }

        /**
         * <p>Registriert eine Zustandserweiterung, die Modelle mit einem
         * eigenen Zustand separat vom Zeitwertkontext erzeugen kann. </p>
         *
         * @param   extension   chronologische Erweiterung
         * @return  diese Instanz f&uuml;r Methodenverkettungen
         */
        public Builder<T> appendExtension(ChronoExtension extension) {

            if (extension == null) {
                throw new NullPointerException(
                    "Missing chronological extension.");
            } else if (!this.extensions.contains(extension)) {
                this.extensions.add(extension);
            }

            return this;

        }

        /**
         * <p>Schlie&szlig;t den Build-Vorgang ab. </p>
         *
         * <p>Intern wird die neue Chronologie f&uuml;r {@code lookup()}
         * schwach registriert. Es wird daher empfohlen, da&szlig; eine
         * Anwendung zus&auml;tzlich die erzeugte Chronologie in einer
         * eigenen statischen Konstanten referenziert. </p>
         *
         * @return  neue Chronologie-Instanz
         * @see     Chronology#lookup(Class)
         */
        public Chronology<T> build() {

            final Chronology<T> chronology =
                new Chronology<T>(
                    this.chronoType,
                    this.merger,
                    this.ruleMap,
                    this.extensions
                );

            Chronology.register(chronology);
            return chronology;

        }

        private void checkElementDuplicates(ChronoElement<?> element) {

            if (element == null) {
                throw new NullPointerException(
                    "Static initialization problem: "
                    + "Check if given element statically refer "
                    + "to any chronology causing premature class loading.");
            }

            String elementName = element.name();

            for (ChronoElement<?> key : this.ruleMap.keySet()) {
                if (
                    key.equals(element)
                    || key.name().equals(elementName)
                ) {
                    throw new IllegalArgumentException(
                        "Element duplicate found: " + elementName);
                }
            }

        }

    }

    // Transformiert eine Elementregel zwischen S und T via Calendrical
    private static class TransformingRule
        <S extends Calendrical<?, S>, T extends Calendrical<?, T>, V>
        implements ElementRule<T, V> {

        //~ Instanzvariablen ----------------------------------------------

        private final ElementRule<S, V> rule;
        private final Class<S> s;
        private final Class<T> t;

        //~ Konstruktoren -------------------------------------------------

        TransformingRule(
            ElementRule<S, V> rule,
            Class<S> s,
            Class<T> t
        ) {
            super();

            this.rule = rule;
            this.s = s;
            this.t = t;

        }

        //~ Methoden ------------------------------------------------------

        @Override
        public V getValue(T context) {

            S src = context.transform(this.s);
            return this.rule.getValue(src);

        }

        @Override
        public T withValue(
            T context,
            V value,
            boolean lenient
        ) {

            S src = context.transform(this.s);
            return this.rule.withValue(src, value, lenient).transform(this.t);

        }

        @Override
        public boolean isValid(
            T context,
            V value
        ) {

            S src = context.transform(this.s);
            return this.rule.isValid(src, value);

        }

        @Override
        public V getMinimum(T context) {

            S src = context.transform(this.s);
            return this.rule.getMinimum(src);

        }

        @Override
        public V getMaximum(T context) {

            S src = context.transform(this.s);
            return this.rule.getMaximum(src);

        }

        @Override
        public ChronoElement<?> getChildAtFloor(T context) {

            S src = context.transform(this.s);
            return this.rule.getChildAtFloor(src);

        }

        @Override
        public ChronoElement<?> getChildAtCeiling(T context) {

            S src = context.transform(this.s);
            return this.rule.getChildAtCeiling(src);

        }

    }

    // Schwache Referenz auf ein chronologisches System
    private static class ChronoReference
        extends WeakReference<Chronology<?>> {

        //~ Instanzvariablen ----------------------------------------------

        private final String name;

        //~ Konstruktoren -------------------------------------------------

        ChronoReference(
            Chronology<?> chronology,
            ReferenceQueue<Chronology<?>> queue
        ) {
            super(chronology, queue);
            this.name = chronology.chronoType.getName();

        }

    }

}
