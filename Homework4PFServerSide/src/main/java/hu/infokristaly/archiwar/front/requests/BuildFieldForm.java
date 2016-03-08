package hu.infokristaly.archiwar.front.requests;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import hu.infokristaly.archiwar.front.manager.SessionBean;

@Named
@RequestScoped
public class BuildFieldForm extends SessionBasedController implements Serializable {

	private static final long serialVersionUID = -4430254810936517477L;

	private boolean initialized = false;
	
	@Inject
	private SessionBean sessionBean;
	
	@PostConstruct
	public void init() {
        initialized = true;
		System.out.println("BuildFieldForm initialized");
	}
	
	public void buildTree() {
        boolean isAjax = isAjax();        
        if (!isAjax) {
        	sessionBean.buildTree();
        	setInitializedController(getClass().getSimpleName());
        }
		
	}
}
