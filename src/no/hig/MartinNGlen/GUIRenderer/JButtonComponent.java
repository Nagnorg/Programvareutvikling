package no.hig.MartinNGlen.GUIRenderer;

public class JButtonComponent extends ComponentDecorator{
	
	public JButtonComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	public String stringDeclare() {
		return "\tJButtonComponent " +baseComponent.getName()+ " = new JButtonComponent(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
