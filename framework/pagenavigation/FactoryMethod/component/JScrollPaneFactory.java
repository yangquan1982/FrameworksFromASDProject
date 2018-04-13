/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JScrollPane;

/**
 * @author Quan Yang
 *
 */
public class JScrollPaneFactory implements IComponentFactory {
	private static IComponentFactory factory = new JScrollPaneFactory();
	private JScrollPaneFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JScrollPane();
	}
	@Override
	public Component createComponent(String name) {
		return new JScrollPane();
	}

}
