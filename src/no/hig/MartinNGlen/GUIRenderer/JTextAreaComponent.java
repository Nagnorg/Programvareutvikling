package no.hig.MartinNGlen.GUIRenderer;

public class JTextAreaComponent extends ComponentDecorator{
	
	public JTextAreaComponent(BaseComponent newComponent) {
		super(newComponent);
		// TODO Auto-generated constructor stub
	}

	public String stringDeclare() {
		return "\tJTextAreaComponent " +baseComponent.getName()+ " = new JTextAreaComponent(\"" +baseComponent.getContent()+ "\");";
	}
}
