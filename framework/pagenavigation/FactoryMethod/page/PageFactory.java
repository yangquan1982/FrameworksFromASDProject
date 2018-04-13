/**
 * 
 */
package framework.pagenavigation.FactoryMethod.page;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import shopping.ui.BillPage;
import shopping.ui.LoginPage;
import shopping.ui.MainPanel;
import shopping.ui.ProductListPage;
import shopping.ui.Purchase;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class PageFactory implements IPageFactory {
	private String customName;
	private static IPageFactory factory = new PageFactory();
	private PageFactory() {}
	public static IPageFactory getFactory() {
		return factory;
	}
	@Override
	public APage createPage(EPageName name, APageNavigator navigator) {
		APage page = null;
		switch (name) {
		case REGPAGE:
			page = Registration.getInstance(name, navigator);
			break;
		case LOGINPAGE:
			page = LoginPage.getInstance(name, navigator);
			break;
		case MAINPANEL:
			page = MainPanel.getInstance(name, customName, navigator);
			break;
		case PLISTPAGE:
			page = ProductListPage.getInstance(name, navigator);
			break;
		case PURCHASE:
			page = Purchase.getInstance(name, navigator);
			break;
		case BILLPAGE:
			page = BillPage.getInstance(name, navigator);
			break;
		default:
			break;
		}
		return page;
	}

}
