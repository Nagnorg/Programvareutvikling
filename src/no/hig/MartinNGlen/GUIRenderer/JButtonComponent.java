package no.hig.MartinNGlen.GUIRenderer;

public class JButtonComponent extends ComponentDecorator{
	
	public JButtonComponent(BaseComponent newComponent) {
		super(newComponent);
		// TODO Auto-generated constructor stub
	}
	
	public String stringDeclare() {
		return "\tJButtonComponent " +baseComponent.getName()+ " = new JButtonComponent(\"" +baseComponent.getContent()+ "\");";
	}

}
