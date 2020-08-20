package fit5042.tutex.calculator;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.ejb.Stateful;

import fit5042.tutex.repository.entities.Property;



@Stateful
public class ComparePropertySessionBean implements CompareProperty{
	private Set<Property> list = new LinkedHashSet<Property>();	
		
	@Override
	public void addProperty(Property p) {
		// TODO Auto-generated method stub
		if(p!=null) {
			
			for(Property property : list) {
				if(property.getPropertyId() == p.getPropertyId()) {
					return;
				}
			}
			list.add(p);
		}

	}

	@Override
	public void removeProperty(Property p) {
		// TODO Auto-generated method stub
		Iterator<Property> iter = list.iterator();
		if(p != null) {
			while(iter.hasNext()) {
				Property property = iter.next();
				if(property.getPropertyId() == p.getPropertyId()) {
					iter.remove();
				}
			}
		}
	}

	@Override
	public int getBestPerRoom() {
		// TODO Auto-generated method stub
		
		Integer bestID = 0;
		double pricePerRoom = 0;
		for (Property p : list) {
			pricePerRoom = p.getPrice()/p.getNumberOfBedrooms();
			bestID = p.getPropertyId();
			break;
		}
		for (Property p : list) {
			double other = p.getPrice()/p.getNumberOfBedrooms();
			if (other < pricePerRoom) {
				pricePerRoom = other;
				bestID = p.getPropertyId();
			}
		}		
		return bestID;
	}
	
	public Set<Property> getList(){

		return list;
	}
	

}
