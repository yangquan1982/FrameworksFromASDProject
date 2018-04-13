/**
 * 
 */
package framework.pagenavigation.FactoryMethod.component;

import java.awt.Component;

import javax.swing.JFrame;

/**
 * @author Quan Yang
 *
 */
public class JFrameFactory implements IComponentFactory {
	private static IComponentFactory factory = new JFrameFactory();
	private JFrameFactory() {}
	public static IComponentFactory getFactory() {
		return factory;
	}
	@Override
	public Component createComponent() {
		return new JFrame();
	}
	@Override
	public Component createComponent(String name) {
		return new JFrame(name);
	}
}
