package no.hig.MartinNGlen.GUIRenderer;

public class JLabelComponent extends ComponentDecorator {
	
	public JLabelComponent(BaseComponent newComponent) {
		super(newComponent);
		// TODO Auto-generated constructor stub
	}
	
	public String toString() {
		return " ";
	}
	
	public String stringDeclare() {
		return "\tJLabelComponent " +baseComponent.getName()+ " = new JLabelComponent(\"" +baseComponent.getContent()+ "\");";
	}

}
