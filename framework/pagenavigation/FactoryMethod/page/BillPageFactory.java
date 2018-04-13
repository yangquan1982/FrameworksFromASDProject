/**
 * 
 */
package framework.pagenavigation.FactoryMethod.page;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import shopping.ui.BillPage;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class BillPageFactory implements IPageFactory {
	private static IPageFactory factory = new BillPageFactory();
	private BillPageFactory() {}
	public static IPageFactory getFactory() {
		return factory;
	}
	@Override
	public APage createPage(APageNavigator navigator) {
		return BillPage.getInstance(navigator);
	}

}
