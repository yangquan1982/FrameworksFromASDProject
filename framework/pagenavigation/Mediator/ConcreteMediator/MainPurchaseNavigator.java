/**
 * 
 */
package framework.pagenavigation.Mediator.ConcreteMediator;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.ConcreteState.MainToPurState;
import framework.pagenavigation.State.ConcreteState.PurToMainState;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class MainPurchaseNavigator extends APageNavigator {
	
	private static MainPurchaseNavigator INSTANCE;
    public static MainPurchaseNavigator getInstance() {
        if (INSTANCE == null) {
            synchronized (Registration.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainPurchaseNavigator();
                }
            }
        }
        return INSTANCE;
    }
	private MainPurchaseNavigator() {
		super();
		this.fromAToBState = new MainToPurState(this);
		this.fromBToAState = new PurToMainState(this);
	}
	
	@Override
	public void navigate(APage senderPage) {
		for (APage aPage : pages) {
			if (aPage != senderPage) {
				aPage.openItself();
			}
		}
		currentState.navigate();
	}
}
