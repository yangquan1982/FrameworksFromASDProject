/**
 * 
 */
package framework.pagenavigation.FactoryMethod.page;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class RegPageFactory implements IPageFactory {
	private APage regPage = null;
	private static IPageFactory factory = new RegPageFactory();
	private RegPageFactory() {}
	public static IPageFactory getFactory() {
		return factory;
	}
	@Override
	public APage createPage(APageNavigator navigator) {
		return Registration.getInstance(navigator);
	}

}
