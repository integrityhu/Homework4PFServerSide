package hu.infokristaly.archiwar.front.manager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJBException;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.metadata.ConstraintDescriptor;

import org.primefaces.component.datatable.DataTable;

import hu.infokristaly.archiwar.back.domain.Clerk;
import hu.infokristaly.archiwar.middle.services.ClerkService;
import hu.infokristaly.archiwar.util.Utils4EntityEditorUI;
import hu.infokristaly.archiwar.util.Utils4EntityTableUI;

/*
 * http://www.tutorialspoint.com/jsf/jsf_composite_components.htm
 * http://www.tutorialspoint.com/jsf/jsf_customconvertor_tag.htm
 * http://www.mkyong.com/jsf2/jsf-2-datatable-example/
 * RenderKit rk = fc.getRenderKit();
 * 
 */
@Named
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 2193274342943234563L;

	@Inject
	private ClerkService service;

	private Clerk value = new Clerk();

	public Clerk getValue() {
		return value;
	}

	public void setValue(Clerk user) {
		this.value = user;
	}

	public void buildTree() {
		createForm();
		createTable();
	}

	public void initValue() {
		if (value.getId() != null) {
			value = new Clerk();
		}
	}

	public List<Clerk> getUserList() {
		return service.findAll(true);
	}

	public void save() {
		try {
			if (value.getId() == null) {
				service.persist(value);
			} else {
				service.merge(value);
			}
			value = new Clerk();
		} catch (EJBException e) {
			Throwable ex = e.getCause();
			if (ex instanceof ConstraintViolationException) {
				Set<ConstraintViolation<?>> msg = ((ConstraintViolationException) ex).getConstraintViolations();
				msg.forEach(c -> {
					StringBuffer validationExcMsg = new StringBuffer();
					ConstraintDescriptor<?> desc = c.getConstraintDescriptor();
					System.out.println(desc.getAttributes().get("message"));
					String temp = c.getMessageTemplate();
					System.out.println(temp);
					validationExcMsg.append(c.getPropertyPath()).append(":").append(c.getMessage());
					FacesMessage message = new FacesMessage(validationExcMsg.toString());
					FacesContext.getCurrentInstance().addMessage(null, message);
				});
			} else {
				FacesMessage message = new FacesMessage("Failed: " + e.getMessage());
				FacesContext.getCurrentInstance().addMessage(null, message);
			}
		}
	}

	private void createTable() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot root = fc.getViewRoot();
		DataTable table = (DataTable) root.findComponent(":userTable");
		System.out.println("Colums build required.");
		Map<Integer, UIComponent> tableFields = Utils4EntityTableUI.getTableFieldsFromEntityInfo(fc, "u", Clerk.class, true);
		Utils4EntityTableUI.addFieldsToTable(table, tableFields);
		System.out.println("Colums build ready.");
	}

	public void clearForm() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot root = fc.getViewRoot();
		UIComponent form = root.findComponent("user");
		UIComponent grid = root.findComponent(":user:grid");
		grid.getChildren().clear();
	}

	public void createForm() {
		FacesContext fc = FacesContext.getCurrentInstance();
		UIViewRoot root = fc.getViewRoot();
		UIComponent form = root.findComponent("user");
		UIComponent grid = root.findComponent(":user:grid");
		if (grid != null) {
			int count = grid.getChildCount();
			if ((form != null) && (count == 0)) {
				Utils4EntityEditorUI.addFieldsToEditor(grid, Utils4EntityEditorUI.getEditorFieldsFromEntityInfo(fc, Clerk.class));
			}
		}

	}
}
