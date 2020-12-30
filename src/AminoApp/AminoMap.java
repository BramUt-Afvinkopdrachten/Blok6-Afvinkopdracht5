package AminoApp;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AminoMap {
    private final ArrayList<String[]> lookupList = new ArrayList<>();
    private final LinkedHashMap<String, Integer> nameMap, threeMap, oneMap;

    /**
     * Constructor voor AminoMap, maakt een lijst met aminozuren en drie hashmaps om
     * op naam, 3 letter code of 1 letter code de rest te kunnen vinden.
     */
    public AminoMap() {
        // Voegt alle aminozuren (naam en 1 & 3 letter code) toe aan loopuplist.
        lookupList.add(new String[] {"Alanine", "Ala", "A"});
        lookupList.add(new String[] {"Arginine", "Arg", "R"});
        lookupList.add(new String[] {"Asparagine", "Asn", "N"});
        lookupList.add(new String[] {"Aspartic acid", "Asp", "D"});
        lookupList.add(new String[] {"Cysteine", "Cys", "C"});
        lookupList.add(new String[] {"Glutamine", "Gln", "Q"});
        lookupList.add(new String[] {"Glutamic acid", "Glu", "E"});
        lookupList.add(new String[] {"Glycine", "Gly", "G"});
        lookupList.add(new String[] {"Histidine", "His", "H"});
        lookupList.add(new String[] {"Isoleucine", "Ile", "I"});
        lookupList.add(new String[] {"Leucine", "Leu", "L"});
        lookupList.add(new String[] {"Lysine", "Lys", "K"});
        lookupList.add(new String[] {"Methionine", "Met", "M"});
        lookupList.add(new String[] {"Phenylalanine", "Phe", "F"});
        lookupList.add(new String[] {"Proline", "Pro", "P"});
        lookupList.add(new String[] {"Serine", "Ser", "S"});
        lookupList.add(new String[] {"Threonine", "Thr", "T"});
        lookupList.add(new String[] {"Tryptophan", "Trp", "W"});
        lookupList.add(new String[] {"Tyrosine", "Tyr", "Y"});
        lookupList.add(new String[] {"Valine", "Val", "V"});

        nameMap = new LinkedHashMap<>();
        threeMap = new LinkedHashMap<>();
        oneMap = new LinkedHashMap<>();
        for (int i = 0; i < lookupList.size(); i++) {
            // Voegt naam met index in loopuplist toe aan nameMap.
            nameMap.put(lookupList.get(i)[0], i);
            // Voegt 3 letter code met index in lookuplist toe aan threeMap.
            threeMap.put(lookupList.get(i)[1], i);
            // Voegt 1 letter code met index in loopuplist toe aan oneMap.
            oneMap.put(lookupList.get(i)[2], i);
        }
    }

    /**
     * Zoekt op basis van de lengte van de input in de goede map,
     * haalt hier de index van het aminozuur in lookuplist uit en
     * returnt de waardes op die index.
     *
     * @param input Zoekterm van de gebruiker.
     * @return Array met de volledige naam, 3 letter code en 1 letter code van het aminozuur.
     * @throws NullPointerException Exception als het aminozuur niet gevonden kan worden.
     */
    public String[] getNames(String input) throws NullPointerException {
        switch (input.length()) {
            case 1 -> {return lookupList.get(oneMap.get(input));}
            case 3 -> {return lookupList.get(threeMap.get(input));}
            default -> {return lookupList.get(nameMap.get(input));}
        }
    }
}
