package AminoApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class AminoMap {
    private final ArrayList<String[]> lookupList = new ArrayList<>();
    private final LinkedHashMap<String, Integer> nameMap, threeMap, oneMap;
    public AminoMap() {
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
            nameMap.put(lookupList.get(i)[0], i);
            threeMap.put(lookupList.get(i)[1], i);
            oneMap.put(lookupList.get(i)[2], i);
        }
    }

    public String[] getNames(String input) throws NullPointerException {
        switch (input.length()) {
            case 1 -> {return lookupList.get(oneMap.get(input));}
            case 3 -> {return lookupList.get(threeMap.get(input));}
            default -> {return lookupList.get(nameMap.get(input));}
        }
    }
}
