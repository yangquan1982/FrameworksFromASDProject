/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JDesktopPane;

/**
 * @author Quan Yang
 *
 */
public class JDesktopPaneFactory implements IComponentFactory {
	private static IComponentFactory factory = new JDesktopPaneFactory();
	private JDesktopPaneFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JDesktopPane();
	}
	@Override
	public Component createComponent(String name) {
		return new JDesktopPane();
	}
}
