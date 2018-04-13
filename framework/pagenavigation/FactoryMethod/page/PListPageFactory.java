/**
 * 
 */
package framework.pagenavigation.FactoryMethod.page;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import shopping.ui.ProductListPage;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class PListPageFactory implements IPageFactory {
	private static IPageFactory factory = new PListPageFactory();
	private PListPageFactory() {}
	public static IPageFactory getFactory() {
		return factory;
	}
	@Override
	public APage createPage(APageNavigator navigator) {
		return ProductListPage.getInstance(navigator);
	}

}
