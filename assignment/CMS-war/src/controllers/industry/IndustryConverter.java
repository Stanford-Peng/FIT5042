package controllers.industry;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import entity.Industry;


@FacesConverter(forClass = entity.Industry.class, value = "industryConverter")
public class IndustryConverter implements Converter<Industry> {
	
	@Inject
	AllIndustryController allIndustryController;
	
	public IndustryConverter() {
		
	}
	
	@Override
    public Industry getAsObject(FacesContext facesContext, UIComponent component, String submittedValue) {
        if (submittedValue.trim().equals("")) {
            return null;
        } else {
            try {
                int number = Integer.parseInt(submittedValue);

                for (Industry industry : allIndustryController.getIndustries()) {
                    if (industry.getIndustryID() == number) {
                        return industry;
                    }
                }

            } catch (NumberFormatException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid contact person"));
            }
        }

        return null;
    }


	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Industry industry) {
		// TODO Auto-generated method stub
        if (industry == null) {
            return "";
        } else {
            return String.valueOf(industry.getIndustryID());
        }
	}

}
