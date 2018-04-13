/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JLabel;

/**
 * @author Quan Yang
 *
 */
public class JLabelFactory implements IComponentFactory {
	private static IComponentFactory factory = new JLabelFactory();
	private JLabelFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JLabel();
	}
	@Override
	public Component createComponent(String name) {
		return new JLabel(name);
	}
}
