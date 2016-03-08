package hu.infokristaly.archiwar.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.AbstractMap.SimpleEntry;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.component.html.HtmlInputText;
import javax.faces.component.html.HtmlOutputLabel;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewDeclarationLanguage;

import org.hibernate.validator.constraints.NotBlank;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.selectbooleancheckbox.SelectBooleanCheckbox;

import hu.infokristaly.archiwar.front.annotations.EntityInfo;

public class Utils4EntityEditorUI {

	public static UIComponent createLabel(Field field) {
		EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
		String info = entityInfo.info();
		String referenced = field.getName() + "_editor";
		HtmlOutputLabel output = new HtmlOutputLabel();
		output.setFor(referenced);
		output.setId(field.getName() + "_label");
		output.setValue(info);
		return output;
	}

	public static UIComponent createInput(ELContext el, ExpressionFactory elFactory, Field field) {
		UIComponent input;
		if (field.getType() == Boolean.class) {
			input = new HtmlSelectBooleanCheckbox();
		} else {
			input = new HtmlInputText();
		}
		input.setId(field.getName() + "_editor");
		ValueExpression valueExp = elFactory.createValueExpression(el, "#" + "{sessionBean.value." + field.getName() + "}", String.class);
		input.setValueExpression("value", valueExp);
		return input;
	}

	public static UIComponent createPFInput(ELContext el, ExpressionFactory elFactory, Field field) {
		UIComponent input;
		if (field.getType() == Boolean.class) {
			input = new SelectBooleanCheckbox();
		} else {
			input = new InputText();
		}
		input.setId(field.getName() + "_editor");
		ValueExpression valueExp = elFactory.createValueExpression(el, "#" + "{sessionBean.value." + field.getName() + "}", String.class);
		input.setValueExpression("value", valueExp);
		return input;
	}

	public static UIComponent getInputTemp(FacesContext context, Field field) throws IOException {
		EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
		String editor = entityInfo.editor();
		String viewId;
		if ((editor != null) && !editor.isEmpty()) {
			viewId = "/" + editor + ".xhtml";
		} else {
			viewId = "/input.xhtml";
		}
		ViewDeclarationLanguage vdl = context.getApplication().getViewHandler().getViewDeclarationLanguage(context, viewId);
		UIViewRoot view = vdl.createView(context, viewId);
		vdl.buildView(context, view);
		view.setId(field.getName() + "_field");
		UIComponent input = view.findComponent("input");
		input.setId(field.getName() + "_editor");
		Application app = context.getApplication();
		Type type = field.getGenericType();
		try {
			Class<?> fieldClass = Class.forName(type.getTypeName());
			System.out.println("type name: " + type.getTypeName());
			ValueExpression valueExp = app.getExpressionFactory().createValueExpression(context.getELContext(), "#" + "{sessionBean.value." + field.getName() + "}", fieldClass);
			input.setValueExpression("value", valueExp);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		UIComponent required = view.findComponent("required");
		if (required != null) {
			NotBlank requiredInfo = field.getAnnotation(NotBlank.class);
			required.setRendered(requiredInfo != null);
			required.setId(field.getName() + "_required");
		}
		int cnt = view.getChildCount();
		System.out.println("child cnt:" + cnt);
		return view;
	}

	public static void addToEditor(UIComponent parent, UIComponent label, UIComponent input) {
		label.setParent(parent);
		parent.getChildren().add(label);
		if (input instanceof UIViewRoot) {
			((UIViewRoot)input).getChildren().forEach(c -> {				
				c.setId(input.getId()+"_panel");
				c.setParent(parent);
				parent.getChildren().add(c);
			});
		} else {
			input.setParent(parent);
			parent.getChildren().add(input);
		}
	}

	public static Map<Integer, SimpleEntry<UIComponent, UIComponent>> getEditorFieldsFromEntityInfo(FacesContext fc, Class<?> clazz) {
		ELContext el = fc.getELContext();
		Application app = fc.getApplication();
		ExpressionFactory elFactory = app.getExpressionFactory();
		Map<Integer, SimpleEntry<UIComponent, UIComponent>> weightMap = new TreeMap<Integer, SimpleEntry<UIComponent, UIComponent>>();
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			if (field.isAnnotationPresent(EntityInfo.class)) {
				UIComponent label = createLabel(field);
				UIComponent input = null;
				 input = createPFInput(el, elFactory, field);
				// input = createInput(el, elFactory, field);
				/*
				try {
					input = getInputTemp(fc, field);
				} catch (IOException e) {
					input = createInput(el, elFactory, field);
				}
				// */
				EntityInfo entityInfo = field.getAnnotation(EntityInfo.class);
				weightMap.put(new Integer(entityInfo.weight()), new SimpleEntry<UIComponent, UIComponent>(label, input));
			}
		}
		return weightMap;
	}

	public static void addFieldsToEditor(UIComponent parent, Map<Integer, SimpleEntry<UIComponent, UIComponent>> weightHashMap) {
		if (weightHashMap.size() > 0) {
			Iterator<Entry<Integer, SimpleEntry<UIComponent, UIComponent>>> iter = weightHashMap.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<Integer, SimpleEntry<UIComponent, UIComponent>> item = iter.next();
				addToEditor(parent, item.getValue().getKey(), item.getValue().getValue());
			}
		}
	}

}
