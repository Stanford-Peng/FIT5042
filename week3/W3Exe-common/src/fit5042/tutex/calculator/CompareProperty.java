package fit5042.tutex.calculator;

import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import fit5042.tutex.repository.entities.Property;


@Remote
public interface CompareProperty {
	
	void addProperty(Property p);
	void removeProperty(Property p);
	int getBestPerRoom();
	Set<Property> getList();
	
}
