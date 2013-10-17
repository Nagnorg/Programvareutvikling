package no.hig.MartinNGlen.GUIRenderer;

public class JTextFieldComponent extends ComponentDecorator{

	public JTextFieldComponent(BaseComponent newComponent) {
		super(newComponent);
		// TODO Auto-generated constructor stub
	}
	
	public String stringDeclare() {
		return "\tJTextFieldComponent " +baseComponent.getName()+ " = new JTextFieldComponent(\"" +baseComponent.getContent()+ "\");";
	}

}
