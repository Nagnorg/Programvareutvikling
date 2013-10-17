package no.hig.MartinNGlen.GUIRenderer;

public class JLabelComponent extends ComponentDecorator {
	
	public JLabelComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	public String toString() {
		return " ";
	}
	
	public String stringDeclare() {
		return "\tJLabelComponent " +baseComponent.getName()+ " = new JLabelComponent(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
