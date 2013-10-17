package no.hig.MartinNGlen.GUIRenderer;

public class JButtonComponent extends ComponentDecorator{
	private static final long serialVersionUID = 1L;
	
	public JButtonComponent(BaseComponent newComponent) {
		super(newComponent);
	}
	
	/**
	 * Provides a string that declares a JButton object
	 * @return a string declaring the object
	 */
	public String stringDeclare() {
		return "\tJButton " +baseComponent.getName()+ " = new JButton(\"" +baseComponent.getContent()+ "\");";
	}
	
	@Override
	public void contextWindow() {}

}
