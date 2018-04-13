/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JTextField;

/**
 * @author Quan Yang
 *
 */
public class JTextFieldFactory implements IComponentFactory {
	private static IComponentFactory factory = new JTextFieldFactory();
	private JTextFieldFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JTextField();
	}
	@Override
	public Component createComponent(String name) {
		return new JTextField(name);
	}
}
