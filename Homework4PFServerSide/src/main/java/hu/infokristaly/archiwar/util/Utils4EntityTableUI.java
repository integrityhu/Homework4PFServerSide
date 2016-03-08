package hu.infokristaly.archiwar.util;

import java.lang.reflect.Field;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.MethodExpression;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.component.UIData;
import javax.faces.component.UIOutput;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Null;
import javax.ws.rs.client.Client;

import org.primefaces.component.button.Button;
import org.primefaces.component.celleditor.CellEditor;
import org.primefaces.component.column.Column;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.datatable.DataTable;

import hu.infokristaly.archiwar.back.domain.Clerk;
import hu.infokristaly.archiwar.front.annotations.EntityInfo;

public class Utils4EntityTableUI {

	public static Map<Integer, UIComponent> getTableFieldsFromEntityInfo(FacesContext fc, String var, Class<?> clazz, boolean addEditor) {
		ELContext el = fc.getELContext();
		Application app = fc.getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		Map<Integer, UIComponent> weightMap = new TreeMap<Integer, UIComponent>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(EntityInfo.class)) {
				// UIComponent column = createTableColumn(el, elFactory, var,
				// field);
				UIComponent column = createPrimeTableColumn(el, elFactory, var, field);
				EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
				weightMap.put(new Integer(entityInfo.weight()), column);
			}
		}
		addButtonToEnd(el, elFactory, var, weightMap);
		return weightMap;
	}

	private static UIComponent createHtmlTableColumn(ELContext el, ExpressionFactory elFactory, String var, Field field) {
		HtmlColumn column = new HtmlColumn();
		column.setId(field.getName() + "_column");
		ValueExpression valueExp = elFactory.createValueExpression(el, "#" + "{" + var + "." + field.getName() + "}", String.class);
		UIOutput header = new HtmlOutputText();
		EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
		String info = entityInfo.info();
		header.setValue(info);
		header.setParent(column);
		column.getFacets().put("header", header);
		HtmlOutputText content = new HtmlOutputText();
		content.setParent(column);
		content.setValueExpression("value", valueExp);
		column.getChildren().add(content);
		return column;
	}

	private static UIComponent createPrimeTableColumn(ELContext el, ExpressionFactory elFactory, String var, Field field) {
		FacesContext fc = FacesContext.getCurrentInstance();
		Column column = (Column) fc.getApplication().createComponent(Column.COMPONENT_TYPE);
		column.setId(field.getName() + "_column");
		ValueExpression valueExp = elFactory.createValueExpression(el, "#" + "{" + var + "." + field.getName() + "}", String.class);
		EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
		String info = entityInfo.info();
		column.setHeaderText(info);
		UIOutput content = new UIOutput();
		content.setId(field.getName() + "_content");
		content.setValueExpression("value", valueExp);
		column.getChildren().add(content);
		return column;
	}

	public static void addFieldsToTable(UIComponent parent, Map<Integer, UIComponent> weightHashMap) {
		if (weightHashMap.size() > 0) {
			Iterator<Entry<Integer, UIComponent>> iter = weightHashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<Integer, UIComponent> item = iter.next();
				addToTable(parent, item.getValue());
			}
		}
	}

	private static void addToTable(UIComponent parent, UIComponent column) {
		column.setParent(parent);
		parent.getChildren().add(column);
	}

	public static void cleanUpTableColumns(DataTable table) {
		table.getChildren().forEach(c -> {
			if (c instanceof Column) {
				if (c.getChildCount() > 1) {
					UIComponent out = c.getChildren().get(0);
					if (out instanceof UIOutput) {
						c.getChildren().clear();
						c.getChildren().add(out);
					}
				}
			}
		});
	}

	public static void addButtonToEnd(ELContext el, ExpressionFactory elFactory, String var, Map<Integer, UIComponent> tableFields) {
		Integer max = 0;
		max = tableFields.keySet().stream().max(Integer::compare).get();
		Column col = new Column();
		col.setId("_action_column");
		
		CommandButton button = new CommandButton();
		button.setAjax(true);
		Class<?>[] params = {Client.class};
		MethodExpression methodExp = elFactory.createMethodExpression(el, "#" + "{sessionBean.setValue(" + var + ")}", null, params);
		button.setActionExpression(methodExp);
		ValueExpression valueExp = elFactory.createValueExpression(el, "PF('dlgUserWidget').show();", String.class);
		button.setValueExpression("onsuccess", valueExp);
		button.setUpdate(":user:grid");
		button.setValue("edit");
		button.setParent(col);
		col.getChildren().add(button);
		tableFields.put(max + 1, col);
	}
}
