/**
 * 
 */
package framework.pagenavigation.Mediator.ConcreteMediator;

import framework.pagenavigation.Mediator.AbstractMediator.APageNavigator;
import framework.pagenavigation.State.ConcreteState.MainToPListState;
import framework.pagenavigation.State.ConcreteState.PListToMainState;
import shopping.ui.Registration;
import shopping.ui.abstractproduct.APage;

/**
 * @author Quan Yang
 *
 */
public class MainPListNavigator extends APageNavigator {
	
	private static MainPListNavigator INSTANCE;
    public static MainPListNavigator getInstance() {
        if (INSTANCE == null) {
            synchronized (Registration.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MainPListNavigator();
                }
            }
        }
        return INSTANCE;
    }
	private MainPListNavigator() {
		super();
		this.fromAToBState = new MainToPListState(this);
		this.fromBToAState = new PListToMainState(this);
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
