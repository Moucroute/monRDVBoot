package monRDV.model;

import java.util.Comparator;

public class PraticienComparator implements Comparator<Praticien> {
	
	public PraticienComparator() {
		// TODO Auto-generated constructor stub
	}
	
    public int compareByPrice(Praticien p1, Praticien p2) {
        return p1.getModalites()..compareTo(o2.getStartDate());
    }

}


