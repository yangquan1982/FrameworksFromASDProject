/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JComboBox;

/**
 * @author Quan Yang
 *
 */
public class JComboBoxFactory implements IComponentFactory {
	private static IComponentFactory factory = new JComboBoxFactory();
	private JComboBoxFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JComboBox();
	}
	@Override
	public Component createComponent(String name) {
		return new JComboBox();
	}
}
