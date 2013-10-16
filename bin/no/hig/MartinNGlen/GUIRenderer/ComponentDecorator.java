package no.hig.MartinNGlen.GUIRenderer;

abstract class ComponentDecorator implements Component{

	private BaseComponent baseComponent;
	
	public ComponentDecorator(BaseComponent newComponent){
		baseComponent = newComponent;
	}
	
	public BaseComponent getBaseComponent() {
		  return baseComponent;
		}
	
	public String toString(){
		return baseComponent.toString();
	}
}
