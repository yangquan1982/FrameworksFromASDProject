/**
 * 
 */
package framework.pagenavigation.Mediator.ConcreteMediator;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.ConcreteState.LogToRegState;
import framework.pagenavigation.State.ConcreteState.RegToLogState;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class RegLogNavigator extends APageNavigator {
	
	private static RegLogNavigator INSTANCE;
    public static RegLogNavigator getInstance() {
        if (INSTANCE == null) {
            synchronized (Registration.class) {
                if (INSTANCE == null) {
                    INSTANCE = new RegLogNavigator();
                }
            }
        }
        return INSTANCE;
    }
	private RegLogNavigator() {
		super();
		this.fromAToBState = new RegToLogState(this);
		this.fromBToAState = new LogToRegState(this);
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
