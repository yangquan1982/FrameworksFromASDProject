/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JPanel;

/**
 * @author Quan Yang
 *
 */
public class JPanelFactory implements IComponentFactory {
	private static IComponentFactory factory = new JPanelFactory();
	private JPanelFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JPanel();
	}
	@Override
	public Component createComponent(String name) {
		return new JPanel();
	}
}
