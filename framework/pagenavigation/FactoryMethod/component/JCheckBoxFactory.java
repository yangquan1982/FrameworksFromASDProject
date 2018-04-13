/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JCheckBox;

/**
 * @author Quan Yang
 *
 */
public class JCheckBoxFactory implements IComponentFactory {
	private static IComponentFactory factory = new JCheckBoxFactory();
	private JCheckBoxFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JCheckBox();
	}
	@Override
	public Component createComponent(String name) {
		return new JCheckBox(name);
	}

}
