package no.hig.MartinNGlen.GUIRenderer;

public class JLabelComponent extends ComponentDecorator {
	private static final long serialVersionUID = 1L;
	
	public JLabelComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	/**
	 * Provides a string that declares a JLabel object
	 * @return a string declaring the object
	 */
	public String stringDeclare() {
		return "\tJLabel " +baseComponent.getName()+ " = new JLabel(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
