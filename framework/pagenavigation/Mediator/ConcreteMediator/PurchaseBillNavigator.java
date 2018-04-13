/**
 * 
 */
package framework.pagenavigation.Mediator.ConcreteMediator;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.ConcreteState.BillToPurState;
import framework.pagenavigation.State.ConcreteState.PurToBillState;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class PurchaseBillNavigator extends APageNavigator {
	
	private static PurchaseBillNavigator INSTANCE;
    public static PurchaseBillNavigator getInstance() {
        if (INSTANCE == null) {
            synchronized (Registration.class) {
                if (INSTANCE == null) {
                    INSTANCE = new PurchaseBillNavigator();
                }
            }
        }
        return INSTANCE;
    }
	private PurchaseBillNavigator() {
		super();
		this.fromAToBState = new PurToBillState(this);
		this.fromBToAState = new BillToPurState(this);
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
