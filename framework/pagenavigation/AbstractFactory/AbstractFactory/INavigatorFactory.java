/**
 * 
 */
package framework.pagenavigation.AbstractFactory.AbstractFactory;

import framework.pagenavigation.FactoryMethod.page.EPageName;
import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public interface INavigatorFactory {
	public APage createPageA(EPageName name, APageNavigator navigator);
	public APage createPageB(EPageName name, APageNavigator navigator);
	public APageNavigator createNavigator();
}
