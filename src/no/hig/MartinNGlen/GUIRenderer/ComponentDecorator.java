package no.hig.MartinNGlen.GUIRenderer;

abstract class ComponentDecorator implements Component{

	private Component baseComponent;
	
	public ComponentDecorator(Component newComponent){
		baseComponent = newComponent;
	}
	
	public Component getBaseComponent() {
		  return baseComponent;
		}
	
	public String toString(){
		return baseComponent.toString();
	}
}
