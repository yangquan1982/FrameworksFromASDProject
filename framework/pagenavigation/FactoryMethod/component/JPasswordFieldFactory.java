/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JPasswordField;

/**
 * @author Quan Yang
 *
 */
public class JPasswordFieldFactory implements IComponentFactory {
	private static IComponentFactory factory = new JPasswordFieldFactory();
	private JPasswordFieldFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JPasswordField();
	}
	@Override
	public Component createComponent(String name) {
		return new JPasswordField(name);
	}
}
