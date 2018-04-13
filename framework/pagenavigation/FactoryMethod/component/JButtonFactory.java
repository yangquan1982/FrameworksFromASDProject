/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JButton;

/**
 * @author Quan Yang
 *
 */
public class JButtonFactory implements IComponentFactory {
	private static IComponentFactory factory = new JButtonFactory();
	private JButtonFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JButton();
	}
	@Override
	public Component createComponent(String name) {
		return new JButton(name);
	}
}
