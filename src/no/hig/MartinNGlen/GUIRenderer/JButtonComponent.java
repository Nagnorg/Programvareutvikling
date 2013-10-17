package no.hig.MartinNGlen.GUIRenderer;

public class JButtonComponent extends ComponentDecorator{
	
	public JButtonComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	public String stringDeclare() {
		return "\tJButton " +baseComponent.getName()+ " = new JButton(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
