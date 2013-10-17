package no.hig.MartinNGlen.GUIRenderer;

public class JLabelComponent extends ComponentDecorator {
	
	public JLabelComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	public String toString() {
		return " ";
	}
	
	public String stringDeclare() {
		return "\tJLabel " +baseComponent.getName()+ " = new JLabel(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
